package org.uestc.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.uestc.util.JdbcUtils;

import com.uestc.bean.Category;
import com.uestc.bean.Goods;
import com.uestc.bean.Store;
import com.uestc.bean.Supplier;

public interface InventoryWarningDao {

	public QueryRunner qr = new QueryRunner(JdbcUtils.getInstance().getDataSource());

	public List<Store> findAllStore() throws SQLException;

	public List<Supplier> findAllSuppliers() throws SQLException;

	public List<Category> findAllCategory() throws SQLException;

	public List<Goods> findByCriteria(String sName, String cName, String suName, String inventoryStatus) throws SQLException;

	public List<Store> findAllStoresByUid(Long uid) throws SQLException;

//	public List<Goods> findAllGoods() throws SQLException;

	List<Goods> findAllGoods(Long uid) throws SQLException;

}
