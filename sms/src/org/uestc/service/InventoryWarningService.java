package org.uestc.service;

import java.util.List;

import org.uestc.dao.InventoryWarningDao;
import org.uestc.daoImp.InventoryWarningDaoImp;
import org.uestc.util.PageBean;

import com.uestc.bean.Category;
import com.uestc.bean.Goods;
import com.uestc.bean.Store;
import com.uestc.bean.Supplier;

public interface InventoryWarningService {

	public InventoryWarningDao inventoryWarningDao = new InventoryWarningDaoImp();

	public List<Store> findAllStore();

	public List<Supplier> findAllSuppliers();

	public List<Category> findAllCategory();

	public PageBean<Goods> findByCombination(String sName, String cName, String suName, String inventoryStatus, Long uid, int pc);

	public List<Store> findAllStoresByUid(Long uid);

//	public List<Goods> findAllGoods();

	List<Goods> findAllGoods(Long uid);
	
	public PageBean<Goods> findByUid(Long uid, int pc);

	public PageBean<Goods> findAll(int pc);

	public PageBean<Goods> findAllByCombination(String sName, String cName, String suName, String inventoryStatus, int pc);

}

