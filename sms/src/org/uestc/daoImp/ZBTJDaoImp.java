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

public class ZBTJDaoImp implements ZBTJDao {

	@Override
	public PageBean<Sale> findAllSalesByUid(Long uId, int pc) throws SQLException {
		int ps = PageConstants.SALE_PAGE_SIZE;
		String sql = "select count(*) from sale where store_id in(select s_id from store where u_id=?)";
		Number number = qr.query(sql, new ScalarHandler(), uId);
		int tr = number.intValue();

		sql = "SELECT store_id,count(sa_serial_num),sa_real_price,sa_profit FROM sale WHERE store_id in(select s_id from store where u_id=?) limit ?,?";
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), uId, (pc-1)*ps, ps);
		List<Sale> saleList = new ArrayList<Sale>();
		for (Object[] obj : list) {
			Sale sale = new Sale();
			sale.setStore(findStoreByStoreId(Long.valueOf(obj[0].toString())));
			
		}
		return null;
	}
	
	private Store findStoreByStoreId(Long id) {
		
		return null;
	}

}
