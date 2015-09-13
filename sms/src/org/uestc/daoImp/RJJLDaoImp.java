package org.uestc.daoImp;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.uestc.dao.RJJLDao;
import org.uestc.util.PageBean;
import org.uestc.util.PageConstants;

import com.uestc.bean.Employee;
import com.uestc.bean.Sale;

public class RJJLDaoImp implements RJJLDao {

	@Override
	public PageBean<Sale> findAllSalesByUid(Long uId, int pc) throws SQLException {
		int ps = PageConstants.SALE_PAGE_SIZE;

		String sql = "SELECT COUNT(DISTINCT(sa_saler_id)) FROM sale WHERE store_id IN (SELECT s_id FROM store WHERE u_id=?)";
		Number number = qr.query(sql, new ScalarHandler(), uId);
		int tr = number.intValue();

		sql = "SELECT tt.sa_date,tt.sa_saler_id,tt.sum_price,t1.sum_price,t2.sum_price,t3.sum_price"
+" FROM (SELECT t.sa_date,t.sa_saler_id,SUM(t.sa_real_price) sum_price FROM sale t WHERE store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY sa_saler_id  LIMIT ?,?) tt"
+" LEFT JOIN (SELECT t.sa_saler_id, SUM(t.sa_real_price) sum_price FROM sale t WHERE t.sa_type = 1 GROUP BY sa_saler_id) t1 ON t1.sa_saler_id = tt.sa_saler_id"
+" LEFT JOIN (SELECT t.sa_saler_id, SUM(t.sa_real_price) sum_price FROM sale t WHERE t.sa_type = 2 GROUP BY sa_saler_id) t2 ON t2.sa_saler_id = tt.sa_saler_id"
+" LEFT JOIN (SELECT t.sa_saler_id, SUM(t.sa_real_price) sum_price FROM sale t WHERE t.sa_type = 3 GROUP BY sa_saler_id) t3 ON t3.sa_saler_id = tt.sa_saler_id";
//		sql = "SELECT sa_saler_id, SUM(sa_real_price) FROM sale WHERE store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY sa_saler_id LIMIT ?,?";
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), uId, (pc - 1) * ps, ps);
		List<Sale> saleList = new ArrayList<Sale>();
		for (Object[] obj : list) {
			Sale sale = new Sale();
			Employee employee = new Employee();
			employee.setEmpName(findEmpNameByEmpId(Long.valueOf(obj[0].toString())));
			sale.setEmployee(employee); // 收银员
			sale.setSaRealPrice(obj[1].toString()); // 将其看作“收银总额”
			sale.setSaGoodsPrice(obj[2].toString()); // 将其看作“现金”
			sale.setSaProfit(obj[3].toString()); // 将其看作“银联卡”
			sale.setSaGoodsNum(obj[4].toString()); // 在线  将其看作“在线”
			saleList.add(sale);
		}

		PageBean<Sale> pb = new PageBean<Sale>();
		pb.setBeanList(saleList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);

		return pb;
	}

	@Override
	public PageBean<Sale> findByCombination(String storeName, String beginTime, String endTime, Long uId, int pc)
			throws SQLException, ParseException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	private String findEmpNameByEmpId(Long empId) throws SQLException {
		String sql = "select emp_name from employee where emp_id=?";
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), empId);
		if (list.size() > 0) {
			Object[] obj = list.get(0);
			return obj[0].toString();
		}
		return null;
	}


}
