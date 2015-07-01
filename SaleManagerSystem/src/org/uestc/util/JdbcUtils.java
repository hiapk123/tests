package org.uestc.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
/*import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;*/


public final class JdbcUtils {
    
    //连接数据库的参数
    private static String url = null;
    private static String user = null;
    private static String driver = null;
    private static String password = null;
     
    private JdbcUtils () {
    	
    }
 
    private static JdbcUtils instance = null;
 
    public static JdbcUtils getInstance() {
        if (instance == null) {
            synchronized (JdbcUtils.class) {
                if (instance == null) {
                    instance = new JdbcUtils();
                }
 
            }
        }
 
        return instance;
    }
     
    //配置文件
    private static Properties prop = new Properties();
     
    //注册驱动
    static {
        try {
            //利用类加载器读取配置文件
            InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("dbInfo.properties");
            prop.load(is);
            url = prop.getProperty("url");
            user = prop.getProperty("user");
            driver = prop.getProperty("driver");
            password = prop.getProperty("password");
        /*    PoolProperties p = new PoolProperties();
            p.setUrl("jdbc:mysql://localhost:3306/mysql");
            p.setDriverClassName("com.mysql.jdbc.Driver");
            p.setUsername("root");
            p.setPassword("password");
            p.setJmxEnabled(true);
            p.setTestWhileIdle(false);
            p.setTestOnBorrow(true);
            p.setValidationQuery("SELECT 1");
            p.setTestOnReturn(false);
            p.setValidationInterval(30000);
            p.setTimeBetweenEvictionRunsMillis(30000);
            p.setMaxActive(100);
            p.setInitialSize(10);
            p.setMaxWait(10000);
            p.setRemoveAbandonedTimeout(60);
            p.setMinEvictableIdleTimeMillis(30000);
            p.setMinIdle(10);
            p.setLogAbandoned(true);
            p.setRemoveAbandoned(true);
            p.setJdbcInterceptors(
              "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
              "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
            DataSource datasource = new DataSource();
            datasource.setPoolProperties(p);

            Connection con = null;*/
            Class.forName(driver);
             
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
    //该方法获得连接
    public Connection getConnection() throws SQLException {
    	
        return DriverManager.getConnection(url, user, password);
    }
     
    //释放资源
    public void free(Connection conn, Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                  
                e.printStackTrace();
            } finally {
                if (st != null) {
                    try {
                        st.close();
                    } catch (SQLException e) {
                          
                        e.printStackTrace();
                    } finally {
                        if (conn != null) {
                            try {
                                conn.close();
                            } catch (SQLException e) {
                                  
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }
     
}