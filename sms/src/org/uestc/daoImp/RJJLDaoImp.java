package org.uestc.daoImp;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.uestc.dao.RJJLDao;
import org.uestc.util.PageBean;
import org.uestc.util.PageConstants;
import org.uestc.util.SqlHelper;

import com.uestc.bean.Employee;
import com.uestc.bean.Sale;

public class RJJLDaoImp implements RJJLDao {

	@Override
	public PageBean<Sale> findAllSalesByUid(Long uId, int pc) throws SQLException {
		int ps = PageConstants.SALE_PAGE_SIZE;

		String sql = "SELECT COUNT(DISTINCT(sa_saler_id)) FROM sale WHERE store_id IN (SELECT s_id FROM store WHERE u_id=?)";
		Number number = qr.query(sql, new ScalarHandler(), uId);
		int tr = number.intValue();

//		sql = "SELECT tt.sa_date,tt.sa_saler_id,tt.sum_price,t1.sum_price,t2.sum_price,t3.sum_price"
//+" FROM (SELECT t.sa_date,t.sa_saler_id,SUM(t.sa_real_price) sum_price FROM sale t WHERE store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY sa_saler_id  LIMIT ?,?) tt"
//+" LEFT JOIN (SELECT t.sa_saler_id, SUM(t.sa_real_price) sum_price FROM sale t WHERE t.sa_type = 1 GROUP BY sa_saler_id) t1 ON t1.sa_saler_id = tt.sa_saler_id"
//+" LEFT JOIN (SELECT t.sa_saler_id, SUM(t.sa_real_price) sum_price FROM sale t WHERE t.sa_type = 2 GROUP BY sa_saler_id) t2 ON t2.sa_saler_id = tt.sa_saler_id"
//+" LEFT JOIN (SELECT t.sa_saler_id, SUM(t.sa_real_price) sum_price FROM sale t WHERE t.sa_type = 3 GROUP BY sa_saler_id) t3 ON t3.sa_saler_id = tt.sa_saler_id";
		
		// 一次性查询到开始时间、结束时间、收银员和收银总额，（现金，银联卡，在线后续单独查询之后拼接起来）
		sql = "SELECT MIN(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')),MAX(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')), sa_saler_id, SUM(sa_real_price) FROM sale WHERE store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY sa_saler_id LIMIT ?,?";
		
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), uId, (pc - 1) * ps, ps);
		List<Sale> saleList = new ArrayList<Sale>();
		for (Object[] obj : list) {
			Sale sale = new Sale();
			Employee employee = new Employee();
			sale.setSaDate(obj[0].toString());
			sale.setSaSerialNum(obj[1].toString()); //将其看作“结束时间”
			employee.setEmpName(findEmpNameByEmpId(Long.valueOf(obj[2].toString())));
			sale.setEmployee(employee); // 收银员
			sale.setSaRealPrice(obj[3].toString()); // 将其看作“收银总额”
			
			sale.setSaGoodsPrice(Double.toString(getCashMoney(Long.valueOf(obj[2].toString()), uId))); // 将其看作“现金”
			sale.setSaProfit(Double.toString(getCupCardMoney(Long.valueOf(obj[2].toString()), uId))); // 将其看作“银联卡”
			sale.setSaGoodsNum(Double.toString(getOnlineMoney(Long.valueOf(obj[2].toString()), uId))); // 在线  将其看作“在线”
			
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
	
//	public double getMoney(int saler_id, String longin, String exit, String sa_type) {
//        String sql = "select sum(t.sa_real_price) from sale t where t.sa_saler_id=? and t.sa_date between ? and ? and t.sa_type=? and t.s_del=1 and t.sa_flag=0";
//        ArrayList<Object[]> list = SqlHelper.executeQuery(sql, saler_id, longin, exit, sa_type);
//        if (list.size() == 1) {
//            if (list.get(0)[0] != null) {
//                double sum = (double) (list.get(0)[0]);
//                return Double.valueOf(Utils.formatNumber(sum));
//            }
//        }
//        return 0;
//    }
	private double getMoney(Long saler_id, String sa_type, Long uId) throws SQLException {
		String sql = "select sum(t.sa_real_price) from sale t where t.sa_saler_id=? and t.sa_type=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?) ";
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), saler_id, sa_type, uId);
		if (list.size() == 1) {
			if (list.get(0)[0] != null) {
				double sum = (double) list.get(0)[0];
				return Double.valueOf(sum);
			}
		}
		return 0;
	}
	
	// 现金
	private double getCashMoney(Long saler_id, Long uId) throws SQLException {
		return getMoney(saler_id, "1", uId);
	}
	// 银联卡
	private double getCupCardMoney(Long saler_id, Long uId) throws SQLException {
		return getMoney(saler_id, "2", uId);
	}
	// 在线
	private double getOnlineMoney(Long saler_id, Long uId) throws SQLException {
		return getMoney(saler_id, "3", uId);
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
