package org.uestc.serviceImp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.uestc.service.SalesService;
import org.uestc.util.JdbcUtils;
import org.uestc.util.SqlHelper;

public class SalesServiceImp implements SalesService{

	@Override
	public void save() {
		
		
	}

	@Override
	public List<Object[]> findStoreByUserID(int uid) {
		String sql="select s_id,s_name from store where u_id=?";
		List<Object[]> list=SqlHelper.find(sql, new Object[]{uid});
		return list;
	}

	

	@Override
	public int getXiaoShouCount(int sid, String start, String end) {
		String sql="select count(sa_id)  from sale where store_id=? and s_del=1 and sa_date between ? and ?";
		
		return JdbcUtils.getInstance().getCount(sql, sid,start,end);
	}

	@Override
	public List<Object[]> getXiaoshouAndLiRun(int sid, String start, String end) {
		try {
			String sql="select sum(sa_real_price),sum(sa_profit),sum(sa_shishou),sum(sa_repay) from sale where store_id=? and sa_flag=1 and s_del=1 and sa_date between ? and ?";
			QueryRunner queryRunner=new QueryRunner(JdbcUtils.getInstance().getDataSource());
			List<Object[]> list=queryRunner.query(sql, new ArrayListHandler(), sid,start,end);
			return list;
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getCashCount(int sid, String start, String end) {
		String sql="select count(sa_id) from sale where sa_type=1 and sa_flag=0 and s_del=1 and sa_date between ? and ?";
		return JdbcUtils.getInstance().getCount(sql, sid,start,end);
	}

	@Override
	public int getBankCount(int sid, String start, String end) {
		
		String sql="select count(sa_id) from sale where sa_type=2 and sa_flag=0 and s_del=1 and sa_date between ? and ?";
		return JdbcUtils.getInstance().getCount(sql, sid,start,end);
	}

	@Override
	public int getOnlineCount(int sid, String start, String end) {
		String sql="select count(sa_id) from sale where sa_type=3 and sa_flag=0 and s_del=1 and sa_date between ? and ?";
		return JdbcUtils.getInstance().getCount(sql, sid,start,end);
	}

	@Override
	public int getChongZhiCount(int sid, String start, String end) {
		String sql="select count(v_c_id) from vip_consume_log where v_type=2 and s_del=1 and v_date between ? and ?";
		return JdbcUtils.getInstance().getCount(sql, sid,start,end);
	}

	@Override
	public List<Object[]> getChongzhiAndGiv(int sid, String start, String end) {
		try {
			String  sql="select sum(v_payin_off),sum(v_giving) from vip_consume_log where v_type=2 and s_del=1 and store_id=? and v_date between ? and ?";
			QueryRunner queryRunner=new QueryRunner(JdbcUtils.getInstance().getDataSource());
			List<Object[]> list=queryRunner.query(sql, new ArrayListHandler(), sid,start,end);
			return list;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
		
	}

	/*@Override
	public List<Object[]> getCash(int sid, String start, String end) {
		try {
			String sql="select count(sa_real_price),count(sa_profit),count(sa_shishou),count(sa_repay) from sale where store_id=? and sa_flag=1 and s_del=1 and sa_type=1";
			QueryRunner queryRunner=new QueryRunner(JdbcUtils.getInstance().getDataSource());
			List<Object[]> list=queryRunner.query(sql, new ArrayListHandler(), sid,start,end);
			return list;
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object[]> getBank(int sid, String start, String end) {
		try {
			String sql="select count(sa_real_price),count(sa_profit),count(sa_shishou),count(sa_repay) from sale where store_id=? and sa_flag=1 and s_del=1 and sa_type=2";
			QueryRunner queryRunner=new QueryRunner(JdbcUtils.getInstance().getDataSource());
			List<Object[]> list=queryRunner.query(sql, new ArrayListHandler(), sid,start,end);
			return list;
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object[]> getOnline(int sid, String start, String end) {
		try {
			String sql="select count(sa_real_price),count(sa_profit),count(sa_shishou),count(sa_repay) from sale where store_id=? and sa_flag=1 and s_del=1 and sa_type=3";
			QueryRunner queryRunner=new QueryRunner(JdbcUtils.getInstance().getDataSource());
			List<Object[]> list=queryRunner.query(sql, new ArrayListHandler(), sid,start,end);
			return list;
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return null;
	}
*/
	@Override
	public List<Object[]> get(int sid, String start, String end, int type) {
		try {
			String sql="select sum(sa_real_price) from sale where store_id=? and sa_date between ? and ? and sa_flag=1 and s_del=1 and sa_type=?";
			QueryRunner queryRunner=new QueryRunner(JdbcUtils.getInstance().getDataSource());
			List<Object[]> list=queryRunner.query(sql, new ArrayListHandler(), sid,start,end,type);
			return list;
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object[]> getVip(int sid, String start, String end, int type) {
		try {
			String sql="select sum(v_payin_off) from vip_consume_log where s_del=1 and v_type=1 and store_id=? and v_date between ? and ? and vtype=?";
			QueryRunner queryRunner=new QueryRunner(JdbcUtils.getInstance().getDataSource());
			List<Object[]> list=queryRunner.query(sql, new ArrayListHandler(), sid,start,end,type);
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
