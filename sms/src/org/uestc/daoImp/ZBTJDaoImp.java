package org.uestc.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.uestc.dao.ZBTJDao;
import org.uestc.util.PageBean;
import org.uestc.util.PageConstants;

import com.uestc.bean.Sale;
import com.uestc.bean.Store;
import com.uestc.bean.Users;

public class ZBTJDaoImp implements ZBTJDao {

	@Override
	public PageBean<Sale> findAllSalesByUid(Long uId, int pc) throws SQLException {
		int ps = PageConstants.SALE_PAGE_SIZE;
		
		String sql = "select count(DISTINCT(sa_serial_num)) from sale where store_id in(select s_id from store where u_id=?)";
//		String sql = "select count(*) from sale where store_id in(select s_id from store where u_id=?) GROUP BY sa_serial_num";
		Number number = qr.query(sql, new ScalarHandler(), uId);
		int tr = number.intValue();

		sql = "SELECT store_id,count(sa_serial_num),sum(sa_real_price),sum(sa_profit) FROM sale WHERE store_id in(select s_id from store where u_id=?) GROUP BY sa_serial_num limit ?,?;";
//		sql = "SELECT store_id,count(sa_serial_num),sa_real_price,sa_profit FROM sale WHERE store_id in(select s_id from store where u_id=?) limit ?,?";
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), uId, (pc-1)*ps, ps);
		List<Sale> saleList = new ArrayList<Sale>();
		for (Object[] obj : list) {
			Sale sale = new Sale();
			sale.setStore(findStoreByStoreId(Long.valueOf(obj[0].toString())));
			sale.setSaGoodsNum(obj[1].toString()); // 将其看作“销售单数”
			sale.setSaRealPrice(obj[2].toString());
			sale.setSaProfit(obj[3].toString());
			saleList.add(sale);
		}
		
		PageBean<Sale> pb = new PageBean<Sale>();
		pb.setBeanList(saleList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);
		
		return pb;
	}
	
	private Store findStoreByStoreId(Long id) throws SQLException {
		String sql = "select s_name from store where s_id=?";
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), id);
		if (list.size() > 0) {
			Store store = new Store();
			Object[] obj = list.get(0);
			store.setSName(obj[0].toString());
			return store;
		}
		return null;
	}

}
