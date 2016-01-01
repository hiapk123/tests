package org.uestc.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.uestc.util.Expression;
import org.uestc.util.JdbcUtils;
import org.uestc.util.PageBean;

import com.uestc.bean.Category;
import com.uestc.bean.Goods;
import com.uestc.bean.Store;
import com.uestc.bean.Supplier;

public interface InventoryWarningDao {

	public QueryRunner qr = new QueryRunner(JdbcUtils.getInstance().getDataSource());

	public List<Store> findAllStore() throws SQLException;

	public List<Supplier> findAllSuppliers() throws SQLException;

	public List<Category> findAllCategory() throws SQLException;

	public PageBean<Goods> findByCombination(String sName, String cName, String suName, String inventoryStatus, Long uid, int pc) throws SQLException;

	public List<Store> findAllStoresByUid(Long uid) throws SQLException;

//	public List<Goods> findAllGoods() throws SQLException;

	public List<Goods> findAllGoods(Long uid) throws SQLException;
	
	public PageBean<Goods> findByCriteria(List<Expression> exprList, int pc) throws SQLException;
	
	public PageBean<Goods> findByUid(Long uid, int pc) throws SQLException;

	public PageBean<Goods> findAll(int pc) throws SQLException;

	public PageBean<Goods> findAllByCombination(String sName, String cName, String suName, String inventoryStatus,
			int pc) throws SQLException;
}

