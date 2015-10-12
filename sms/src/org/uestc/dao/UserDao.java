package org.uestc.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.uestc.util.JdbcUtils;

import com.uestc.bean.Users;

public interface UserDao {

	QueryRunner qr = new QueryRunner(JdbcUtils.getInstance().getDataSource());

	public boolean ajaxValidateLoginname(String loginname) throws SQLException;

	public boolean ajaxValidateEmail(String email) throws SQLException;

	public void add(Users user) throws SQLException;

	public Users findByLoginnameAndLoginpass(String loginname, String loginpass) throws SQLException;

}
