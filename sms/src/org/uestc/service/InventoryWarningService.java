package org.uestc.service;

import java.util.List;

import org.uestc.dao.InventoryWarningDao;
import org.uestc.daoImp.InventoryWarningDaoImp;

import com.uestc.bean.Category;
import com.uestc.bean.Goods;
import com.uestc.bean.Store;
import com.uestc.bean.Supplier;

public interface InventoryWarningService {

	public InventoryWarningDao inventoryWarningDao = new InventoryWarningDaoImp();

	public List<Store> findAllStore();

	public List<Supplier> findAllSuppliers();

	public List<Category> findAllCategory();

	public List<Goods> findByCriteria(String sName, String cName, String suName, String inventoryStatus);

}
