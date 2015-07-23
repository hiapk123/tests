package org.uestc.util;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
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
		}finally {
			runner=null;
		}
		return list;
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
		for (int j = 0; j < 5000; j++) {
			List<Users> list=findBean(Users.class, "select * from users");
			System.out.println(j);
			for(int i=0;i<list.size();i++){
				System.out.println(list.get(i).getU_age());
			}
		}
		
		
		return null;
	}

		
	// ͳһ��select��䣬Ϊ���ܹ����ʽ�����������������ArrayList����������ֱ�ӹر���Դ
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
}