package org.uestc.service;

import java.util.List;

public interface SalesService {
	public void save();
	
	//��ѯ���û����еĵ���
	public List<Object[]> findStoreByUserID(int uid);
	//���յ��̺����ڲ�ѯ���۶������,����֧��,�ֽ�����
	public List<Object[]> getXiaoshouAndLiRun(int sid, String start, String end);
	//���ݵ��̺����ڲ�ѯ�����ܵ���
	public int getXiaoShouCount(int sid, String start, String end);
	//�ֽ���
	public int getCashCount(int sid, String start, String end);
	//��������
	public int getBankCount(int sid, String start, String end);
	//��������
	public int getOnlineCount(int sid, String start, String end);
	//��Ա��ֵ����
	public int getChongZhiCount(int sid, String start, String end);
	//��ֵ���ͽ��
	public List<Object[]> getChongzhiAndGiv(int sid, String start, String end);
	//�ֽ����۵������
	//public List<Object[]> getCash(int sid, String start, String end);
	//���п����۽��
	//public List<Object[]> getBank(int sid, String start, String end);
	//����
	//public List<Object[]> getOnline(int sid, String start, String end);
	//����3������
	public List<Object[]> get(int sid, String start, String end,int type);
	//��Ա��ֵ��ʽ
	public List<Object[]> getVip(int sid, String start, String end,int type);
	

}
