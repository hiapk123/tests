package org.uestc.daoImp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;
import org.uestc.dao.UserDao;

import com.uestc.bean.Users;

public class UserDaoImp implements UserDao {

	@Override
	public boolean ajaxValidateLoginname(String loginname) throws SQLException {
		String sql = "select count(1) from users where u_name=?";
		Number number = qr.query(sql, new ScalarHandler(), loginname);
		return number.intValue() == 0;
	}

	@Override
	public boolean ajaxValidateEmail(String email) throws SQLException {
		String sql = "select count(1) from users where u_email=?";
		Number number = qr.query(sql, new ScalarHandler(), email);
		return number.intValue() == 0;
	}

	@Override
	public void add(Users user) throws SQLException {
		String sql = "insert into users(u_type,u_name,u_phone,u_sex,u_age,u_email,u_info,s_del,u_password) values(?,?,?,?,?,?,?,?,?)";
		Object[] params = { user.getUType(), // u_type
				user.getUName(), // u_name
				user.getUPhone(), // u_phone
				user.getUSex(), // u_sex
				user.getUAge(), // u_age
				user.getUEmail(), // u_email
				user.getUInfo(), // u_info
				user.getSDel(), // s_del
				user.getUPassword()// u_password
		};
		qr.update(sql, params);
	}

	@Override
	public Users findByLoginnameAndLoginpass(String loginname, String loginpass) throws SQLException {
		String sql = "select u_name,u_password,u_id,u_type from users where u_name=? and u_password=?";
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), loginname, loginpass);
//		System.out.println("list为空：" + list == null);
//		System.out.println("list.size() = " + list.size());
		if (list.size() > 0) {
			Users user = new Users();
			Object[] obj = list.get(0);
			user.setUName(obj[0].toString());
			user.setUPassword(obj[1].toString());
			user.setUId(Long.valueOf(obj[2].toString()));
			user.setUType(Integer.valueOf(obj[3].toString()));
			return user;
		}
		return null;
	}

	@Test
	public void testLogin() throws Exception {
		Users user = findByLoginnameAndLoginpass("test", "test");
		if (user == null) {
			System.out.println("该用户不存在");
		} else {
			System.out.println("用户id为："+user.getUId());
		}
	}

}

