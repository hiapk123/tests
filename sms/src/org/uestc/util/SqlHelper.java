<<<<<<< HEAD
package org.uestc.util;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

public final class SqlHelper {

	public static List<Object[]> find(String sql,Object ...params) {
		List<Object[]> list=null;
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getInstance().getDataSource());
		    list = runner.query(sql, new ArrayListHandler(),params);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}
}
=======
package org.uestc.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public final class SqlHelper {
	// 定义需要的变量
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	private static JdbcUtils jdbcUtils = JdbcUtils.getInstance();

	public static List<Object[]> find(String sql, Object... params) {
		List<Object[]> list = null;
		QueryRunner runner = null;
		try {
			runner = new QueryRunner(JdbcUtils.getInstance().getDataSource());
			list = runner.query(sql, new ArrayListHandler(), params);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			runner = null;
		}
		return list;
	}

	// 返回Map集合，取值容易，耦合降低
	public static ArrayList<Map> findAll(String sql, Object... parameters) {
		ArrayList<Map> results = new ArrayList<>();

		try {
			conn = jdbcUtils.getConnection();
			ps = conn.prepareStatement(sql);

			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setObject(i + 1, parameters[i]);
				}
			}

			rs = ps.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			int column = rsmd.getColumnCount();

			while (rs.next()) {
				Map<String, Object> map = new HashMap<>();
				for (int i = 1; i <= column; i++) {
					map.put(rsmd.getColumnName(i), rs.getObject(i));
				}
				results.add(map);
			}
		} catch (SQLException e) {
		} finally {
			jdbcUtils.free(conn, ps, rs);
		}
		return results;
	}

	// 统一的select语句，为了能够访问结果集，将结果集放入ArrayList，这样可以直接关闭资源
	@SuppressWarnings({ "rawtypes", "unchecked" })

	public static ArrayList executeQuery(String sql, String[] parameters) {
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
	public static void executeUpdate(String sql, String[] parameters) {

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
	public static void executeUpdate(String[] sqls, String[][] parameters) {
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

	public static <T> List<T> findBean(Class<T> clazz, String sql, Object... params) {

		List<T> list = null;
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getInstance().getDataSource());
			list = runner.query(sql, new BeanListHandler<T>(clazz), params);
			runner = null;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return list;
	}

	public static void main(String[] args) {
		// test

	}
}
>>>>>>> refs/remotes/origin/master
