package org.uestc.daoImp;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.uestc.dao.SalesDao;
import org.uestc.util.JdbcUtils;
/*
 * public class RSHanlderDemo {
    //ScalarHandler:获取结果集中第一行数据指定列的值,常用来进行单值查询
    @Test
    public void tes9() throws SQLException{
        QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
        Long count = (Long)runner.query("select count(*) from account",new ScalarHandler());
        System.out.println(count);
    }
     
    //KeyedHandler(name)：将结果集中的每一行数据都封装到一个Map里(List<Map>)，再把这些map再存到一个map里，其key为指定的列。
    @Test
    public void tes8() throws SQLException{
        QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
         Map<Object, Map<String, Object>> map = runner.query("select * from account where money>?", new KeyedHandler("id"),500);
        System.out.println(map);
    }
    //ColumnListHandler：将结果集中某一列的数据存放到List中。
    @Test
    public void tes7() throws SQLException{
        QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
        List<Object>list = runner.query("select * from account where money>?", new ColumnListHandler(3),500);
        System.out.println(list);
    }
    //MapListHandler：将结果集中的每一行数据都封装到一个Map里，然后再存放到List
    @Test
    public void tes6() throws SQLException{
        QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
        List<Map<String, Object>> list = runner.query("select * from account where money>?", new MapListHandler(),500);
        System.out.println(list);
    }
     
    //MapHandler：将结果集中的第一行数据封装到一个Map里，key是列名，value就是对应的值。
    @Test
    public void tes5() throws SQLException{
        QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
         Map<String, Object> map = runner.query("select * from account where money>?", new MapHandler(),500);
        System.out.println(map);
    }
     
    //BeanListHandler：将结果集中的每一行数据都封装到一个对应的JavaBean实例中，存放到List里。
    @Test
    public void tes4() throws SQLException{
        QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
        List<Account>list = runner.query("select * from account where money>?", new BeanListHandler<Account>(Account.class),500);
        System.out.println(list);
    }
     
    //BeanHandler：将结果集中的第一行数据封装到一个对应的JavaBean实例中。
    @Test
    public void tes3() throws SQLException{
        QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
        Account acc = runner.query("select * from account where money>?", new BeanHandler<Account>(Account.class),500);
        System.out.println(acc);
    }
    //ArrayListHandler：把结果集中的每一行数据都转成一个对象数组，再存放到List中。
    @Test
    public void tes2() throws SQLException{
        QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
        List<Object[]> list = runner.query("select * from account where money>?", new ArrayListHandler(),500);
        System.out.println(list);
    }
     
    //ArrayHandler:把结果集中的第一行数据转成对象数组。
    @Test
    public void test1() throws SQLException{
        QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
        Object[] objs = runner.query("select * from account where money>?", new ArrayHandler(),500);
        System.out.println(objs);
    }
}
 */
/*
 * 
①ArrayHandler:把结果集中的第一行数据转成对象数组。
②ArrayListHandler：把结果集中的每一行数据都转成一个对象数组，再存放到List中。
③BeanHandler：将结果集中的第一行数据封装到一个对应的JavaBean实例中。
④BeanListHandler：将结果集中的每一行数据都封装到一个对应的JavaBean实例中，存放到List里。
⑤MapHandler：将结果集中的第一行数据封装到一个Map里，key是列名，value就是对应的值。
⑥MapListHandler：将结果集中的每一行数据都封装到一个Map里，然后再存放到List
⑦ColumnListHandler：将结果集中某一列的数据存放到List中。
⑧KeyedHandler(name)：将结果集中的每一行数据都封装到一个Map里(List<Map>)，再把这些map再存到一个map里，其key为指定的列。
⑨ScalarHandler：获取结果集中第一行数据指定列的值，常用来进行单值查询。
 */
public class SalesDaoImp implements SalesDao{
	
	@Override
	public void save(String sql) {
		QueryRunner qr=new QueryRunner(JdbcUtils.getInstance().getDataSource());
		
	}


}
