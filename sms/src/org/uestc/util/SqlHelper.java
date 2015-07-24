package org.uestc.util;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.uestc.bean.Users;

public final class SqlHelper {
	// 定义需要的变量
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	JdbcUtils jdbcUtils = JdbcUtils.getInstance();
	public static List<Object[]> find(String sql,Object ...params) {
		List<Object[]> list=null;
		QueryRunner runner=null;
		try {
			runner= new QueryRunner(JdbcUtils.getInstance().getDataSource());
		    list = runner.query(sql, new ArrayListHandler(),params);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			runner=null;
		}
		return list;
	}
	
	// 统一的select语句，为了能够访问结果集，将结果集放入ArrayList，这样可以直接关闭资源
	@SuppressWarnings({ "rawtypes", "unchecked" })
	
	public ArrayList executeQuery(String sql, String[] parameters) {
		ArrayList results = new ArrayList();

		try {
			conn = jdbcUtils.getConnection();
			ps = conn.prepareStatement(sql);

			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setString(i + 1, parameters[i]);
				}
			}

			rs = ps.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			int column = rsmd.getColumnCount();

			while (rs.next()) {
				Object[] objects = new Object[column];

				for (int i = 1; i <= column; i++) {
					objects[i - 1] = rs.getObject(i);
				}

				results.add(objects);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			jdbcUtils.free(conn, ps, rs);
		}
		return results;
	}
	
	// 该方法执行一个update/delete/insert语句
	// sql语句是带问号的格式，如：update table_name set column_name = ? where ...
	// parameters = {"...", "..."...}；
	public void executeUpdate(String sql, String[] parameters) {

		try {
			conn = jdbcUtils.getConnection();
			ps = conn.prepareStatement(sql);
			// 给？赋值
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setString(i + 1, parameters[i]);
				}
			}
			// 执行语句
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			// 关闭资源
			jdbcUtils.free(conn, ps, rs);
		}
	}

	// 可以执行多个update、delete、insert语句（考虑事务）
	public void executeUpdate(String[] sqls, String[][] parameters) {
		try {
			// 得到连接
			conn = jdbcUtils.getConnection();
			// 多个sql语句，考虑事务
			conn.setAutoCommit(false);

			for (int i = 0; i < sqls.length; i++) {
				if (parameters[i] != null) {
					ps = conn.prepareStatement(sqls[i]);

					for (int j = 0; j < parameters[i].length; j++) {
						ps.setString(j + 1, parameters[i][j]);
					}

					ps.executeUpdate();
				}

			}

			conn.commit();
		} catch (SQLException e) {
			// 回滚
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			jdbcUtils.free(conn, ps, rs);
		}
	}
	
	
	
	public static <T> List<T> findBean(Class<T> clazz,String sql,Object ...params) {	
		
		List<T> list=null;
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getInstance().getDataSource());
		    list = runner.query(sql, new BeanListHandler<T>(clazz),params);
		    runner=null;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}
	
	public static void main(String[] args) {
		//test
		
	}
}
