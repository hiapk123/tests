package org.uestc.service;

import org.uestc.dao.UserDao;
import org.uestc.daoImp.UserDaoImp;

import com.uestc.bean.Users;

public interface UserService {

	UserDao userDao = new UserDaoImp();

	public boolean ajaxValidateLoginname(String loginname);

	public boolean ajaxValidateEmail(String email);
	
	public void regist(Users user);
	
	public Users login(Users user);
}
