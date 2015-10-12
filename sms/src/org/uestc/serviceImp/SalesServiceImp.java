package org.uestc.serviceImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.uestc.service.SalesService;
import org.uestc.util.DateFormatUtils;
import org.uestc.util.JdbcUtils;
import org.uestc.util.SqlHelper;

import com.uestc.bean.ShiftChange;

public class SalesServiceImp implements SalesService {

	private QueryRunner queryRunner;

	public SalesServiceImp() {
		queryRunner = new QueryRunner(JdbcUtils.getInstance().getDataSource());
	}

	@Override
	public List<Object[]> findStoreByUserID(int uid) {
		String sql = "select s_id,s_name from store where u_id=?";
		List<Object[]> list = SqlHelper.find(sql, new Object[] { uid });
		return list;
	}

	@Override
	public int getXiaoShouCount(int sid, String start, String end) {
		String sql = "select count(sa_id)  from sale where store_id=? and s_del=1 and sa_date between ? and ?";

		try {
			return JdbcUtils.getInstance().getCount(sql, sid, start, end);
		} catch (Exception e) {

		}
		return 0;
	}

	@Override
	public List<Object[]> getXiaoshouAndLiRun(int sid, String start, String end) {
		try {
			String sql = "select sum(sa_real_price),sum(sa_profit),sum(sa_shishou),sum(sa_repay) from sale where store_id=? and sa_flag=1 and s_del=1 and sa_date between ? and ?";

			List<Object[]> list = queryRunner.query(sql, new ArrayListHandler(), sid, start, end);
			return list;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getCashCount(int sid, String start, String end) {
		try {
			String sql = "select count(sa_id) from sale where sa_type=1 and sa_flag=0 and s_id=? and s_del=1 and sa_date between ? and ?";
			return JdbcUtils.getInstance().getCount(sql, sid, start, end);
		} catch (Exception e) {

		}
		return 0;
	}

	@Override
	public int getBankCount(int sid, String start, String end) {

		try {
			String sql = "select count(sa_id) from sale where sa_type=2 and sa_flag=0 and s_id=? and s_del=1 and sa_date between ? and ?";
			return JdbcUtils.getInstance().getCount(sql, sid, start, end);
		} catch (Exception e) {

		}
		return 0;
	}

	@Override
	public int getOnlineCount(int sid, String start, String end) {
		try {
			String sql = "select count(sa_id) from sale where sa_type=3 and sa_flag=0 and s_id=? and s_del=1 and sa_date between ? and ?";
			return JdbcUtils.getInstance().getCount(sql, sid, start, end);
		} catch (Exception e) {

		}
		return 0;
	}

	@Override
	public int getChongZhiCount(int sid, String start, String end) {
		try {
			String sql = "select count(v_c_id) from vip_consume_log where v_type=2 and store_id=? and s_del=1 and v_date between ? and ?";
			return JdbcUtils.getInstance().getCount(sql, sid, start, end);
		} catch (Exception e) {

		}
		return 0;
	}

	@Override
	public List<Object[]> getChongzhiAndGiv(int sid, String start, String end) {
		try {
			String sql = "select sum(v_payin_off),sum(v_giving) from vip_consume_log where v_type=2 and s_del=1 and store_id=? and v_date between ? and ?";

			List<Object[]> list = queryRunner.query(sql, new ArrayListHandler(), sid, start, end);
			return list;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<Object[]> get(int sid, String start, String end, int type) {
		try {
			String sql = "select sum(sa_real_price) from sale where store_id=? and sa_date between ? and ? and sa_flag=1 and s_del=1 and sa_type=?";

			List<Object[]> list = queryRunner.query(sql, new ArrayListHandler(), sid, start, end, type);
			return list;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object[]> getVip(int sid, String start, String end, int type) {
		try {
			String sql = "select sum(v_payin_off) from vip_consume_log where s_del=1 and v_type=1 and store_id=? and v_date between ? and ? and vtype=?";

			List<Object[]> list = queryRunner.query(sql, new ArrayListHandler(), sid, start, end, type);
			return list;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object[]> getJiaoBanList(int sid, String start, String end) {
		try {
			String sql = "select sum(v_payin_off) from vip_consume_log where s_del=1 and v_type=1 and store_id=? and v_date between ? and ? and vtype=?";

			List<Object[]> list = queryRunner.query(sql, new ArrayListHandler(), sid, start, end);
			return list;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ShiftChange> findShiftChange(int sid, String start, String end) {
		String sql = "SELECT t1.shift_id,t1.shift_start_date,t1.shift_end_date,"
				+ "t1.shift_money,t2.emp_id,t2.emp_name FROM shift_change t1,employee "
				+ "t2 WHERE t1.emp_id=t2.emp_id and t2.store_id=? and t1.shift_start_date > ? "
				+ "and t1.shift_end_date < ?";
		List<ShiftChange> shList = null;
		try {
			shList = new ArrayList<>();
			// 获得了原始对象数组
			List<Object[]> raw = queryRunner.query(sql, new ArrayListHandler(), sid, start, end);
			for (int i = 0; i < raw.size(); i++) {
				ShiftChange shiftChange = new ShiftChange();
				shiftChange.shiftId = (Integer) raw.get(i)[0];
				shiftChange.shiftStartDate = DateFormatUtils.LongTimeToDate((Long)raw.get(i)[1]);//日期毫秒数转化为日期字符串
				shiftChange.shiftEndDate = DateFormatUtils.LongTimeToDate((Long)raw.get(i)[2]);//日期毫秒数转化为日期字符串
				shiftChange.pettyCash = (String) raw.get(i)[3];
				shiftChange.empName = (String) raw.get(i)[5];
				shiftChange.shiftTotalMoney = this.getTotalCash((Integer) raw.get(i)[4], start, end);
				shiftChange.cash = this.getCash((Integer) raw.get(i)[4], start, end, 1);
				shiftChange.bank = this.getCash((Integer) raw.get(i)[4], start, end, 2);
				shiftChange.online = this.getCash((Integer) raw.get(i)[4], start, end, 3);
				shList.add(shiftChange);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return shList;
	}

	/***
	 * 获取该员工的销售总额
	 * 
	 * @param empid
	 * @param start
	 * @param end
	 * @return
	 */
	private String getTotalCash(int empid, String start, String end) {
		String sql = "SELECT sum(sa_profit) FROM sale WHERE sa_saler_id=? and sa_date between ? and ?";
		String total = "0";
		// 也可用queryRunner
		List<Object[]> list = SqlHelper.find(sql, empid, start, end);
		if (list != null && list.size() == 1) {
			if (list.get(0)[0] != null) {
				total = (String) list.get(0)[0];
			}
		}
		return total;
	}

	/***
	 * 获取各种不同类型支付方式的总额
	 * 
	 * @param empid
	 * @param start
	 * @param end
	 * @param type(1,2,3)
	 *            1-现金，2-银联卡，3-网银
	 * @return
	 */
	private String getCash(int empid, String start, String end, int type) {
		String total = "0";
		String sql = "SELECT sum(sa_profit) FROM sale WHERE sa_saler_id=? and and sa_date between ? sa_type=?";

		List<Object[]> list = SqlHelper.find(sql, empid, start, end, type);
		if (list != null && list.size() == 1) {
			if (list.get(0)[0] != null) {
				total = (String) list.get(0)[0];
			}
		}
		return total;
	}

	@Override
	public List<Object[]> findCash(int sid, String start, String end) {
		String sql="SELECT t1.cash_date,t2.emp_name,t1.cash_howmuch,t1.cash_type,t1.cash_info from cash t1,employee t2 where t1.empid=t2.emp_id and t2.store_id=? and t1.cash_date between ? and ?";
		List<Object[]> list=SqlHelper.find(sql, sid,start,end);
		return list;
	}
}
