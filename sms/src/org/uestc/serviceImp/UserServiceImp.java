package org.uestc.serviceImp;

import java.sql.SQLException;

import org.uestc.service.UserService;

import com.uestc.bean.Users;

public class UserServiceImp implements UserService {

	@Override
	public boolean ajaxValidateLoginname(String loginname) {
		try {
			return userDao.ajaxValidateLoginname(loginname);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean ajaxValidateEmail(String email) {
		try {
			return userDao.ajaxValidateEmail(email);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void regist(Users user) {
		/*
		 * 补齐数据
		 */
		user.setUType(1);
		user.setUPhone("xxxxxx");
		user.setUSex("男");
		user.setUAge("23");
		user.setUInfo("xxx");
		user.setSDel((short) 1);

		try {
			userDao.add(user);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Users login(Users user) {
		try {
			return userDao.findByLoginnameAndLoginpass(user.getUName(), user.getUPassword());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
