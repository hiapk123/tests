package org.uestc.serviceImp;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.uestc.service.InventoryWarningService;
import org.uestc.util.PageBean;

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
	public PageBean<Goods> findByCombination(String sName, String cName, String suName, String inventoryStatus, Long uid, int pc) {
		try {
			return inventoryWarningDao.findByCombination(sName, cName, suName, inventoryStatus, uid, pc);
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
	public List<Goods> findAllGoods(Long uid) {
		try {
			return inventoryWarningDao.findAllGoods(uid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public PageBean<Goods> findByUid(Long uid, int pc) {
		try {
			return inventoryWarningDao.findByUid(uid, pc);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public PageBean<Goods> findAll(int pc) {
		try {
			return inventoryWarningDao.findAll(pc);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public PageBean<Goods> findAllByCombination(String sName, String cName, String suName, String inventoryStatus, int pc) {
		try {
			return inventoryWarningDao.findAllByCombination(sName, cName, suName, inventoryStatus, pc);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

