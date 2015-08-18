package org.uestc.serviceImp;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.uestc.service.InventoryWarningService;

import com.uestc.bean.Category;
import com.uestc.bean.Goods;
import com.uestc.bean.Store;
import com.uestc.bean.Supplier;

public class InventoryWarningServiceImp implements InventoryWarningService {

	@Override
	public List<Store> findAllStore() {
		try {
			return inventoryWarningDao.findAllStore();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testFindAllStore() throws Exception {
		List<Store> list = findAllStore();
		// System.out.println(list.size());
		// String str = Arrays.toString(list.get(0));
		// String str1 = Arrays.toString(list.get(1));
		// System.out.println(str);
		// System.out.println(str1);
		//
		// String[] strArray = str.split(",");

		// System.out.println("strArray[0]: " + strArray[0]);
		// Store store = new Store();

		for (Store store : list) {
			System.out.println(store.getSName());
		}

	}

	@Override
	public List<Supplier> findAllSuppliers() {
		try {
			return inventoryWarningDao.findAllSuppliers();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Category> findAllCategory() {
		try {
			return inventoryWarningDao.findAllCategory();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Goods> findByCriteria(String sName, String cName, String suName, String inventoryStatus) {
		try {
			return inventoryWarningDao.findByCriteria(sName, cName, suName, inventoryStatus);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Store> findAllStoresByUid(Long uid) {
		try {
			return inventoryWarningDao.findAllStoresByUid(uid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Goods> findAllGoods() {
		try {
			return inventoryWarningDao.findAllGoods();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
