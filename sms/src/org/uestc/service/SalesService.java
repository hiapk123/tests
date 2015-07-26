package org.uestc.service;

import java.util.List;

import com.uestc.bean.ShiftChange;

public interface SalesService {

	public List<Object[]> findStoreByUserID(int uid);

	public List<Object[]> getXiaoshouAndLiRun(int sid, String start, String end);

	public int getXiaoShouCount(int sid, String start, String end);

	public int getCashCount(int sid, String start, String end);

	public int getBankCount(int sid, String start, String end);

	public int getOnlineCount(int sid, String start, String end);

	public int getChongZhiCount(int sid, String start, String end);

	public List<Object[]> getChongzhiAndGiv(int sid, String start, String end);

	public List<Object[]> get(int sid, String start, String end, int type);

	public List<Object[]> getVip(int sid, String start, String end, int type);

	public List<Object[]> getJiaoBanList(int sid, String start, String end);
	/***
	 * 交接班记录
	 * @param sid
	 * @param start
	 * @param end
	 * @return
	 */
	public List<ShiftChange> findShiftChange(int sid, String start, String end);

}
