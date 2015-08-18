package org.uestc.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.uestc.dao.InventoryWarningDao;

import com.uestc.bean.Category;
import com.uestc.bean.Goods;
import com.uestc.bean.Store;
import com.uestc.bean.Supplier;

public class InventoryWarningDaoImp implements InventoryWarningDao {

	@Override
	public List<Store> findAllStore() throws SQLException {
		String sql = "select s_name from store";
		List<Object[]> list = qr.query(sql, new ArrayListHandler());
		List<Store> storeList = new ArrayList<Store>();
		for (Object[] obj : list) {
			// System.out.println("obj: " + obj[0]);
			Store store = new Store();
			store.setSName(obj[0].toString());
			// System.out.println("obj.toString(): " + obj.toString());
			storeList.add(store);
		}
		return storeList;
	}

	// public List<Object[]> findAllStore() throws SQLException {
	// String sql = "select s_name from store";
	// List<Object[]> list = qr.query(sql, new ArrayListHandler());
	// return list;
	// }

	@Override
	public List<Supplier> findAllSuppliers() throws SQLException {
		String sql = "select su_name from supplier";
		List<Object[]> list = qr.query(sql, new ArrayListHandler());
		List<Supplier> supplierList = new ArrayList<Supplier>();
		for (Object[] obj : list) {
			Supplier supplier = new Supplier();
			supplier.setSuName(obj[0].toString());
			supplierList.add(supplier);
		}
		return supplierList;
	}

	@Override
	public List<Category> findAllCategory() throws SQLException {
		String sql = "select c_name from category";
		List<Object[]> list = qr.query(sql, new ArrayListHandler());
		List<Category> categoryList = new ArrayList<Category>();
		for (Object[] obj : list) {
			Category category = new Category();
			category.setCName(obj[0].toString());
			categoryList.add(category);
		}
		return categoryList;
	}

	@Override
	public List<Goods> findByCriteria(String sName, String cName, String suName, String inventoryStatus) throws SQLException {
		String sql = "select g_name,s_name,c_name,su_name,g_barcode,g_stock_num,g_stock_max,g_stock_min,g_prod_date,g_giq from goods where s_name=? and c_name=? and su_name=?";
		// String sql = "select * from goods where s_name=? and c_name=? and su_name=?";
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), sName, cName, suName);
		List<Goods> goodsList = new ArrayList<Goods>();
		for (Object[] obj : list) {
			Goods goods = new Goods();
			goods.setGName(obj[0].toString());
			goods.setSName(obj[1].toString());
			goods.setCName(obj[2].toString());
			goods.setSuName(obj[3].toString());
			goods.setGBarcode(obj[4].toString());
			goods.setGStockNum(obj[5].toString());
			goods.setGStockMax(obj[6].toString());
			goods.setGStockMin(obj[7].toString());
			goods.setGProdDate(obj[8].toString());
			goods.setGGiq(obj[9].toString());
			goodsList.add(goods);
		}

		return goodsList;
	}

	@Override
	public List<Store> findAllStoresByUid(Long uid) throws SQLException {
		String sql = "select s_name from store where u_id=?";
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), uid);
		List<Store> storeList = new ArrayList<Store>();
		for (Object[] obj : list) {
			// System.out.println("obj: " + obj[0]);
			Store store = new Store();
			store.setSName(obj[0].toString());
			// System.out.println("obj.toString(): " + obj.toString());
			storeList.add(store);
		}
		return storeList;
	}

	@Override
	public List<Goods> findAllGoods() throws SQLException {
		String sql = "select g_name,s_name,c_name,su_name,g_barcode,g_stock_num,g_stock_max,g_stock_min,g_prod_date,g_giq from goods";
		// String sql = "select * from goods where s_name=? and c_name=? and su_name=?";
		List<Object[]> list = qr.query(sql, new ArrayListHandler());
		List<Goods> goodsList = new ArrayList<Goods>();
		for (Object[] obj : list) {
			Goods goods = new Goods();
			goods.setGName(obj[0].toString());
			goods.setSName(obj[1].toString());
			goods.setCName(obj[2].toString());
			goods.setSuName(obj[3].toString());
			goods.setGBarcode(obj[4].toString());
			goods.setGStockNum(obj[5].toString());
			goods.setGStockMax(obj[6].toString());
			goods.setGStockMin(obj[7].toString());
			goods.setGProdDate(obj[8].toString());
			goods.setGGiq(obj[9].toString());
			goodsList.add(goods);
		}

		return goodsList;
	}
}
