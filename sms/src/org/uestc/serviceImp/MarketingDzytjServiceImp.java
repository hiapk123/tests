package org.uestc.serviceImp;

import org.uestc.service.MarketingDzytjService;

public class MarketingDzytjServiceImp implements MarketingDzytjService {

	@Override
	public String getPageInit() {
		// TODO Auto-generated method stub
		StringBuffer sb=  new StringBuffer();

		sb.append("<table class=\"col-sm-12 col-lg-12 table\">");
		sb.append("<thead >");
		sb.append("<tr class=\"trBack\">");
		sb.append("<th>活动名称</th>");
		sb.append("<th>开始时间</th>");
		sb.append("<th>结束时间</th>");
		sb.append("<th>适用于</th>");
		sb.append("<th>捆绑优惠券</th>");
		sb.append("<th>操作</th>");
		sb.append("</tr>");
		sb.append("</thead>");
		sb.append("<tbody class=\"tbd\"> ");
		sb.append("<tr>");
		sb.append("<td>开业大放送</td>");
		sb.append("<td>2015.07.11</td>");
		sb.append("<td>2015.07.21</td>");
		sb.append("<td>实体店 网店</td>");
		sb.append("<td><a href=\"javascript:void(0)\">立即捆绑</a></td>");
		sb.append("<td><a href=\"javascript:void(0)\">详细</a> | <a href=\"javascript:void(0)\">修改</a> | <a href=\"javascript:void(0)\">删除</a></td>");
		sb.append("</tr>");
		
		
		sb.append("</tbody>");
		sb.append("</table>");
		

		return sb.toString();
	}

	@Override
	public String getAddPage() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("<table class=\"col-sm-12 col-lg-12 table\">");
		sb.append("<thead >");
		sb.append("<tr class=\"trBack\">");
		sb.append("<th>商品名</th>");
		sb.append("<th>分类</th>");
		sb.append("<th>原价</th>");
		sb.append("<th>折扣</th>");
		sb.append("<th>删除</th>");
				
		sb.append("</tr>");
		sb.append("</thead>");
		sb.append("<tbody class=\"tbd\"> ");
		sb.append("<tr>");
		sb.append("<td>伊犁牛奶</td>");
		sb.append("<td>定价</td>");
		sb.append("<td>5.5</td>");
		sb.append("<td><input type=\"text\" value=100 /></td>");
		sb.append("<td><a href=\"javascript:void(0)\">删除</a></td>");
		sb.append("</tr> ");
		sb.append("<tr ><td style=\"text-align: left;\"><a onclick=\"addgoods();\" href=\"javascript:void(0)\">点此打开商品搜索</a></td></tr>");
		
			
		sb.append("</tbody>");
		sb.append("</table>");
		return sb.toString();
	}

	@Override
	public String getAddHead() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
				sb.append("<div class=\"panel-heading\">");
					
				sb.append(" 活动名称：<input id=\"mbt_dzytj_active_name\" type=\"text\" />开始时间:<input type=\"text\" value=\"开始时间\"/>");
				sb.append("结束时间：<input type=\"text\" value=\"结束时间\"  /> 可用于： ");
				sb.append(" <label class=\"checkbox-inline\">");
				sb.append("<input type=\"radio\" name=\"optionsRadiosinline\" id=\"optionsRadios1\" ");
				sb.append("value=\"shitidian\" checked> 实体店");
				sb.append("</label>");
				sb.append(" <label class=\"checkbox-inline\">");
				sb.append("<input type=\"radio\" name=\"optionsRadiosinline\" id=\"optionsRadios2\" ");
				sb.append("value=\"wangdian\"> 网店");
				sb.append("</label>");
				sb.append("<label class=\"checkbox-inline\">");
				sb.append("<input type=\"radio\" name=\"optionsRadiosinline\" id=\"optionsRadios3\" ");
				sb.append("value=\"huiyuanzhuanxiang\"> 会员专享");
				sb.append("</label>");
				   
											sb.append("</div> ");
		return sb.toString();
	}

}
