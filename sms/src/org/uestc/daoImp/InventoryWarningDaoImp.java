package org.uestc.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.uestc.dao.InventoryWarningDao;
import org.uestc.util.Expression;
import org.uestc.util.PageBean;
import org.uestc.util.PageConstants;

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
	public PageBean<Goods> findByCombination(String sName, String cName, String suName, String inventoryStatus, Long uid, int pc) throws SQLException {
		/*
		 * 1.得到ps
		 * 2.得到tr
		 * 3.得到beanList
		 * 4.创建PageBean，返回
		 */
		
		// 1.得到ps
		int ps = PageConstants.GOODS_PAGE_SIZE;
		
		// 2.得到tr
		String sql = "";
		Number number = null;
		int tr = 0;
		
		// -----------------------------------------------------------------------------------
		// -----------------------------------------------------------------------------------
		// -----------------------------------------------------------------------------------

		if (sName.equals("全部门店")) {
			if (cName.equals("全部分类")) {
				if (suName.equals("全部供货商")) {
					// 1 1 1
					if (inventoryStatus.equals("库存不足")) {  //g_stock_num库存量 g_stock_min库存下限 g_stock_max库存上限
						sql = "select count(*) from goods where CAST(g_stock_num AS DECIMAL)<=CAST(g_stock_min AS DECIMAL) and s_name in(select s_name from store where u_id=?)";
					} else if (inventoryStatus.equals("库存过剩")) {
						sql = "select count(*) from goods where CAST(g_stock_num AS DECIMAL)>=CAST(g_stock_max AS DECIMAL) and s_name in(select s_name from store where u_id=?)";
					} else {
						sql = "select count(*) from goods where s_name in(select s_name from store where u_id=?)";
					}
					number = (Number) qr.query(sql, new ScalarHandler(), uid);
					tr = number.intValue();
//					System.out.println("库存不足的总记录条数："+tr);
					
//					sql = "select count(*) from goods where s_name in(select s_name from store where u_id=?)";
//					number = (Number) qr.query(sql, new ScalarHandler(), uid);
//					tr = number.intValue();
				} else {
					// 1 1 0
					if (inventoryStatus.equals("库存不足")) {  //g_stock_num库存量 g_stock_min库存下限 g_stock_max库存上限
						sql = "select count(*) from goods where CAST(g_stock_num AS DECIMAL)<=CAST(g_stock_min AS DECIMAL) and su_name=? and s_name in(select s_name from store where u_id=?)";
					} else if (inventoryStatus.equals("库存过剩")) {
						sql = "select count(*) from goods where CAST(g_stock_num AS DECIMAL)>=CAST(g_stock_max AS DECIMAL) and su_name=? and s_name in(select s_name from store where u_id=?)";
						
					} else {
						sql = "select count(*) from goods where su_name=? and s_name in(select s_name from store where u_id=?)";
					}
					number = (Number) qr.query(sql, new ScalarHandler(), suName, uid);
					tr = number.intValue();
				}
			} else {
				if (suName.equals("全部供货商")) {
					// 1 0 1
					if (inventoryStatus.equals("库存不足")) {
						sql = "select count(*) from goods where CAST(g_stock_num AS DECIMAL)<=CAST(g_stock_min AS DECIMAL) and c_name=? and s_name in(select s_name from store where u_id=?)";
					} else if (inventoryStatus.equals("库存过剩")) {
						sql = "select count(*) from goods where CAST(g_stock_num AS DECIMAL)>=CAST(g_stock_max AS DECIMAL) and c_name=? and s_name in(select s_name from store where u_id=?)";
					} else {
						sql = "select count(*) from goods where c_name=? and s_name in(select s_name from store where u_id=?)";
					}
					
					
					number = (Number) qr.query(sql, new ScalarHandler(), cName, uid);
					tr = number.intValue();
				} else {
					// 1 0 0
					if (inventoryStatus.equals("库存不足")) {
						sql = "select count(*) from goods where CAST(g_stock_num AS DECIMAL)<=CAST(g_stock_min AS DECIMAL) and c_name=? and su_name=? and s_name in(select s_name from store where u_id=?)";
					} else if (inventoryStatus.equals("库存过剩")) {
						sql = "select count(*) from goods where CAST(g_stock_num AS DECIMAL)>=CAST(g_stock_max AS DECIMAL) and c_name=? and su_name=? and s_name in(select s_name from store where u_id=?)";
					} else {
						sql = "select count(*) from goods where c_name=? and su_name=? and s_name in(select s_name from store where u_id=?)";
					}
					number = (Number) qr.query(sql, new ScalarHandler(), cName, suName, uid);
					tr = number.intValue();
				}
			}
		} else {
			if (cName.equals("全部分类")) {
				if (suName.equals("全部供货商")) {
					// 0 1 1
					if (inventoryStatus.equals("库存不足")) {
						sql = "select count(*) from goods where CAST(g_stock_num AS DECIMAL)<=CAST(g_stock_min AS DECIMAL) and s_name=? and s_name in(select s_name from store where u_id=?)";
					} else if (inventoryStatus.equals("库存过剩")) {
						sql = "select count(*) from goods where CAST(g_stock_num AS DECIMAL)>=CAST(g_stock_max AS DECIMAL) and s_name=? and s_name in(select s_name from store where u_id=?)";
					} else {
						sql = "select count(*) from goods where s_name=? and s_name in(select s_name from store where u_id=?)";
					}
					number = (Number) qr.query(sql, new ScalarHandler(), sName, uid);
					tr = number.intValue();
				} else {
					// 0 1 0
					if (inventoryStatus.equals("库存不足")) {
						sql = "select count(*) from goods where CAST(g_stock_num AS DECIMAL)<=CAST(g_stock_min AS DECIMAL) and s_name=? and su_name=? and s_name in(select s_name from store where u_id=?)";
					} else if (inventoryStatus.equals("库存过剩")) {
						sql = "select count(*) from goods where CAST(g_stock_num AS DECIMAL)>=CAST(g_stock_max AS DECIMAL) and s_name=? and su_name=? and s_name in(select s_name from store where u_id=?)";
					} else {
						sql = "select count(*) from goods where s_name=? and su_name=? and s_name in(select s_name from store where u_id=?)";
					}
					number = (Number) qr.query(sql, new ScalarHandler(), sName, suName, uid);
					tr = number.intValue();
				}
			} else {
				if (suName.equals("全部供货商")) {
					// 0 0 1
					if (inventoryStatus.equals("库存不足")) {
						sql = "select count(*) from goods where CAST(g_stock_num AS DECIMAL)<=CAST(g_stock_min AS DECIMAL) and s_name=? and c_name=? and s_name in(select s_name from store where u_id=?)";
					} else if (inventoryStatus.equals("库存过剩")) {
						sql = "select count(*) from goods where CAST(g_stock_num AS DECIMAL)>=CAST(g_stock_max AS DECIMAL) and s_name=? and c_name=? and s_name in(select s_name from store where u_id=?)";
					} else {
						sql = "select count(*) from goods where s_name=? and c_name=? and s_name in(select s_name from store where u_id=?)";
					}
					number = (Number) qr.query(sql, new ScalarHandler(), sName, cName, uid);
					tr = number.intValue();
				} else {
					// 0 0 0
					if (inventoryStatus.equals("库存不足")) {
						sql = "select count(*) from goods where CAST(g_stock_num AS DECIMAL)<=CAST(g_stock_min AS DECIMAL) s_name=? and c_name=? and su_name=? and s_name in(select s_name from store where u_id=?)";
					} else if (inventoryStatus.equals("库存过剩")) {
						sql = "select count(*) from goods where CAST(g_stock_num AS DECIMAL)>=CAST(g_stock_max AS DECIMAL) and s_name=? and c_name=? and su_name=? and s_name in(select s_name from store where u_id=?)";
					} else {
						sql = "select count(*) from goods where s_name=? and c_name=? and su_name=? and s_name in(select s_name from store where u_id=?)";
					}
					number = (Number) qr.query(sql, new ScalarHandler(), sName, cName, suName, uid);
					tr = number.intValue();
				}
			}
		}

		// -----------------------------------------------------------------------------------
		// -----------------------------------------------------------------------------------
		// -----------------------------------------------------------------------------------

		// 3.得到beanList,即当前页记录
		List<Object[]> list = null;
		
		if (sName.equals("全部门店")) {
			if (cName.equals("全部分类")) {
				if (suName.equals("全部供货商")) {
					// 1 1 1
					if (inventoryStatus.equals("库存不足")) {
						sql = "select g_name,s_name,c_name,su_name,g_barcode,g_stock_num,g_stock_max,g_stock_min,g_prod_date,g_giq from goods where CAST(g_stock_num AS DECIMAL)<=CAST(g_stock_min AS DECIMAL) and s_name in (select s_name from store where u_id=?) limit ?,?";
					} else if (inventoryStatus.equals("库存过剩")) {
						sql = "select g_name,s_name,c_name,su_name,g_barcode,g_stock_num,g_stock_max,g_stock_min,g_prod_date,g_giq from goods where CAST(g_stock_num AS DECIMAL)>=CAST(g_stock_max AS DECIMAL) and s_name in (select s_name from store where u_id=?) limit ?,?";
					} else {
						sql = "select g_name,s_name,c_name,su_name,g_barcode,g_stock_num,g_stock_max,g_stock_min,g_prod_date,g_giq from goods where s_name in (select s_name from store where u_id=?) limit ?,?";
					}
					list = qr.query(sql, new ArrayListHandler(), uid, (pc-1)*ps, ps);
				} else {
					// 1 1 0
					//-----------------------------------------------------------------------------
					//-----------------------------------------------------------------------------
					//-----------------------------------------------------------------------------
					if (inventoryStatus.equals("库存不足")) {
						sql = "select g_name,s_name,c_name,su_name,g_barcode,g_stock_num,g_stock_max,g_stock_min,g_prod_date,g_giq from goods where CAST(g_stock_num AS DECIMAL)<=CAST(g_stock_min AS DECIMAL) and su_name=? and s_name in (select s_name from store where u_id=?) limit ?,?";
					} else if (inventoryStatus.equals("库存过剩")) {
						sql = "select g_name,s_name,c_name,su_name,g_barcode,g_stock_num,g_stock_max,g_stock_min,g_prod_date,g_giq from goods where CAST(g_stock_num AS DECIMAL)>=CAST(g_stock_max AS DECIMAL) and su_name=? and s_name in (select s_name from store where u_id=?) limit ?,?";
					} else {
						sql = "select g_name,s_name,c_name,su_name,g_barcode,g_stock_num,g_stock_max,g_stock_min,g_prod_date,g_giq from goods where su_name=? and s_name in (select s_name from store where u_id=?) limit ?,?";
					}
					list = qr.query(sql, new ArrayListHandler(), suName, uid, (pc-1)*ps, ps);
				}
			} else {
				if (suName.equals("全部供货商")) {
					// 1 0 1
					if (inventoryStatus.equals("库存不足")) {
						sql = "select g_name,s_name,c_name,su_name,g_barcode,g_stock_num,g_stock_max,g_stock_min,g_prod_date,g_giq from goods where CAST(g_stock_num AS DECIMAL)<=CAST(g_stock_min AS DECIMAL) and c_name=? and s_name in (select s_name from store where u_id=?) limit ?,?";
					} else if (inventoryStatus.equals("库存过剩")) {
						sql = "select g_name,s_name,c_name,su_name,g_barcode,g_stock_num,g_stock_max,g_stock_min,g_prod_date,g_giq from goods where CAST(g_stock_num AS DECIMAL)>=CAST(g_stock_max AS DECIMAL) and c_name=? and s_name in (select s_name from store where u_id=?) limit ?,?";
					} else {
						sql = "select g_name,s_name,c_name,su_name,g_barcode,g_stock_num,g_stock_max,g_stock_min,g_prod_date,g_giq from goods where c_name=? and s_name in (select s_name from store where u_id=?) limit ?,?";
					}
					list = qr.query(sql, new ArrayListHandler(), cName, uid, (pc-1)*ps, ps);
				} else {
					// 1 0 0
					if (inventoryStatus.equals("库存不足")) {
						sql = "select g_name,s_name,c_name,su_name,g_barcode,g_stock_num,g_stock_max,g_stock_min,g_prod_date,g_giq from goods where CAST(g_stock_num AS DECIMAL)<=CAST(g_stock_min AS DECIMAL) and c_name=? and su_name=? and s_name in (select s_name from store where u_id=?) limit ?,?";
					} else if (inventoryStatus.equals("库存过剩")) {
						sql = "select g_name,s_name,c_name,su_name,g_barcode,g_stock_num,g_stock_max,g_stock_min,g_prod_date,g_giq from goods where CAST(g_stock_num AS DECIMAL)>=CAST(g_stock_max AS DECIMAL) and c_name=? and su_name=? and s_name in (select s_name from store where u_id=?) limit ?,?";
					} else {
						sql = "select g_name,s_name,c_name,su_name,g_barcode,g_stock_num,g_stock_max,g_stock_min,g_prod_date,g_giq from goods where c_name=? and su_name=? and s_name in (select s_name from store where u_id=?) limit ?,?";
					}
					list = qr.query(sql, new ArrayListHandler(), cName, suName, uid, (pc-1)*ps, ps);
				}
			}
		} else {
			if (cName.equals("全部分类")) {
				if (suName.equals("全部供货商")) {
					// 0 1 1
					if (inventoryStatus.equals("库存不足")) {
						sql = "select g_name,s_name,c_name,su_name,g_barcode,g_stock_num,g_stock_max,g_stock_min,g_prod_date,g_giq from goods where CAST(g_stock_num AS DECIMAL)<=CAST(g_stock_min AS DECIMAL) and s_name=? and s_name in (select s_name from store where u_id=?) limit ?,?";
					} else if (inventoryStatus.equals("库存过剩")) {
						sql = "select g_name,s_name,c_name,su_name,g_barcode,g_stock_num,g_stock_max,g_stock_min,g_prod_date,g_giq from goods where CAST(g_stock_num AS DECIMAL)>=CAST(g_stock_max AS DECIMAL) and s_name=? and s_name in (select s_name from store where u_id=?) limit ?,?";
					} else {
						sql = "select g_name,s_name,c_name,su_name,g_barcode,g_stock_num,g_stock_max,g_stock_min,g_prod_date,g_giq from goods where s_name=? and s_name in (select s_name from store where u_id=?) limit ?,?";
					}
					list = qr.query(sql, new ArrayListHandler(), sName, uid, (pc-1)*ps, ps);
				} else {
					// 0 1 0
					if (inventoryStatus.equals("库存不足")) {
						sql = "select g_name,s_name,c_name,su_name,g_barcode,g_stock_num,g_stock_max,g_stock_min,g_prod_date,g_giq from goods where CAST(g_stock_num AS DECIMAL)<=CAST(g_stock_min AS DECIMAL) and s_name=? and su_name=? and s_name in (select s_name from store where u_id=?) limit ?,?";
					} else if (inventoryStatus.equals("库存过剩")) {
						sql = "select g_name,s_name,c_name,su_name,g_barcode,g_stock_num,g_stock_max,g_stock_min,g_prod_date,g_giq from goods where CAST(g_stock_num AS DECIMAL)>=CAST(g_stock_max AS DECIMAL) and s_name=? and su_name=? and s_name in (select s_name from store where u_id=?) limit ?,?";
					} else {
						sql = "select g_name,s_name,c_name,su_name,g_barcode,g_stock_num,g_stock_max,g_stock_min,g_prod_date,g_giq from goods where s_name=? and su_name=? and s_name in (select s_name from store where u_id=?) limit ?,?";
					}
					list = qr.query(sql, new ArrayListHandler(), sName, suName, uid, (pc-1)*ps, ps);
				}
			} else {
				if (suName.equals("全部供货商")) {
					// 0 0 1
					if (inventoryStatus.equals("库存不足")) {
						sql = "select g_name,s_name,c_name,su_name,g_barcode,g_stock_num,g_stock_max,g_stock_min,g_prod_date,g_giq from goods where CAST(g_stock_num AS DECIMAL)<=CAST(g_stock_min AS DECIMAL) and s_name=? and c_name=? and s_name in (select s_name from store where u_id=?) limit ?,?";
					} else if (inventoryStatus.equals("库存过剩")) {
						sql = "select g_name,s_name,c_name,su_name,g_barcode,g_stock_num,g_stock_max,g_stock_min,g_prod_date,g_giq from goods where CAST(g_stock_num AS DECIMAL)>=CAST(g_stock_max AS DECIMAL) and s_name=? and c_name=? and s_name in (select s_name from store where u_id=?) limit ?,?";
					} else {
						sql = "select g_name,s_name,c_name,su_name,g_barcode,g_stock_num,g_stock_max,g_stock_min,g_prod_date,g_giq from goods where s_name=? and c_name=? and s_name in (select s_name from store where u_id=?) limit ?,?";
					}
					list = qr.query(sql, new ArrayListHandler(), sName, cName, uid, (pc-1)*ps, ps);
				} else {
					// 0 0 0
					if (inventoryStatus.equals("库存不足")) {
						sql = "select g_name,s_name,c_name,su_name,g_barcode,g_stock_num,g_stock_max,g_stock_min,g_prod_date,g_giq from goods where CAST(g_stock_num AS DECIMAL)<=CAST(g_stock_min AS DECIMAL) and s_name=? and c_name=? and su_name=? and s_name in (select s_name from store where u_id=?) limit ?,?";
					} else if (inventoryStatus.equals("库存过剩")) {
						sql = "select g_name,s_name,c_name,su_name,g_barcode,g_stock_num,g_stock_max,g_stock_min,g_prod_date,g_giq from goods where CAST(g_stock_num AS DECIMAL)>=CAST(g_stock_max AS DECIMAL) and s_name=? and c_name=? and su_name=? and s_name in (select s_name from store where u_id=?) limit ?,?";
					} else {
						sql = "select g_name,s_name,c_name,su_name,g_barcode,g_stock_num,g_stock_max,g_stock_min,g_prod_date,g_giq from goods where s_name=? and c_name=? and su_name=? and s_name in (select s_name from store where u_id=?) limit ?,?";
					}
					list = qr.query(sql, new ArrayListHandler(), sName, cName, suName, uid, (pc-1)*ps, ps);
				}
			}
		}
				
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
		
		// 4.创建PageBean，返回
		PageBean<Goods> pb = new PageBean<Goods>();
		pb.setBeanList(goodsList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);
		
		return pb;
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
	public List<Goods> findAllGoods(Long uid) throws SQLException {
		String sql = "select g_name,s_name,c_name,su_name,g_barcode,g_stock_num,g_stock_max,g_stock_min,g_prod_date,g_giq from goods where s_name in(select s_name from store where u_id=?)";
//		String sql = "select g_name,s_name,c_name,su_name,g_barcode,g_stock_num,g_stock_max,g_stock_min,g_prod_date,g_giq from goods where s_name="+"\"门店1\"";
//		String sql = "select g_name,s_name,c_name,su_name,g_barcode,g_stock_num,g_stock_max,g_stock_min,g_prod_date,g_giq from goods";
		// String sql = "select * from goods where s_name=? and c_name=? and su_name=?";
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), uid);
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

	/**
	 * 通用的查询方法（重载），以分页的方式
	 */
	@Override
	public PageBean<Goods> findByCriteria(List<Expression> exprList, int pc) throws SQLException {
		/*
		 * 1.得到ps
		 * 2.得到tr
		 * 3.得到beanList
		 * 4.创建PageBean，返回
		 */
		int ps = PageConstants.GOODS_PAGE_SIZE;
		
		// 2.通过exprList来生成where子句
		StringBuilder whereSql = new StringBuilder(" where 1=1");
		List<Object> params = new ArrayList<Object>();
		for (Expression expr : exprList) {
			whereSql.append(" and ").append(expr.getName()).append(" ").append(expr.getOperator()).append(" ");
			if (!expr.getOperator().equals("is null")) {
				whereSql.append("?");
				params.add(expr.getValue());
			}
		}
		System.out.println(whereSql);
		// 3.得到tr
		String sql = "select count(*) from goods"+whereSql;
		Number number = (Number) qr.query(sql, new ArrayListHandler(), params.toArray());
		int tr = number.intValue();
		// 4.得到beanList,即当前页记录
		sql = "select g_name,s_name,c_name,su_name,g_barcode,g_stock_num,g_stock_max,g_stock_min,g_prod_date,g_giq from goods"+whereSql+" limit ?,?";
//		sql = "select g_name,s_name,c_name,su_name,g_barcode,g_stock_num,g_stock_max,g_stock_min,g_prod_date,g_giq from goods where s_name in(select s_name from store where u_id=?) limit ?,?";
		params.add((pc-1)*ps);
		params.add(ps);
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), params.toArray());
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
		
		// 5.创建PageBean，返回
		PageBean<Goods> pb = new PageBean<Goods>();
		pb.setBeanList(goodsList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);
		
		return pb;
	}

	@Override
	public PageBean<Goods> findByUid(Long uid, int pc) throws SQLException {
		/*
		 * 1.得到ps
		 * 2.得到tr
		 * 3.得到beanList
		 * 4.创建PageBean，返回
		 */
		
		// 1.得到ps
		int ps = PageConstants.GOODS_PAGE_SIZE;
		// 2.得到tr
//		String sql = "select count(*) from goods";
		String sql = "select count(*) from goods where s_name in(select s_name from store where u_id=?)";
//		Number number = (Number) qr.query(sql, new ScalarHandler());
		Number number = (Number) qr.query(sql, new ScalarHandler(), uid);
		int tr = number.intValue();
		// 3.得到beanList,即当前页记录
//		sql = "select g_name,s_name,c_name,su_name,g_barcode,g_stock_num,g_stock_max,g_stock_min,g_prod_date,g_giq from goods limit ?,?";
		sql = "select g_name,s_name,c_name,su_name,g_barcode,g_stock_num,g_stock_max,g_stock_min,g_prod_date,g_giq from goods where s_name in (select s_name from store where u_id=?) limit ?,?";
//		sql = "select g_name,s_name,c_name,su_name,g_barcode,g_stock_num,g_stock_max,g_stock_min,g_prod_date,g_giq from goods where s_name in(select s_name from store where u_id=?) limit ?,?";
//		List<Object[]> list = qr.query(sql, new ArrayListHandler(), (pc-1)*ps, ps);
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), uid, (pc-1)*ps, ps);
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
		
		// 4.创建PageBean，返回
		PageBean<Goods> pb = new PageBean<Goods>();
		pb.setBeanList(goodsList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);
		
		return pb;
		
	}
	
	
}
