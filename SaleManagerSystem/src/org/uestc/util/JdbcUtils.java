package org.uestc.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;


public final class JdbcUtils {
    
    //������ݿ�Ĳ���
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
     
    //�����ļ�
    private static Properties prop = new Properties();
    private static PoolProperties p = new PoolProperties();
    //ע����
    static {
        try {
            //�������������ȡ�����ļ�
            InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("dbInfo.properties");
            prop.load(is);
            url = prop.getProperty("url");
            user = prop.getProperty("user");
            driver = prop.getProperty("driver");
            password = prop.getProperty("password");
            p.setUrl(url);
            p.setDriverClassName(driver);
            p.setUsername(user);
            p.setPassword(password);
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
          
            //Class.forName(driver);
             
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
   
    public Connection getConnection() throws SQLException {
    	 DataSource datasource=null;
    	 datasource = new DataSource();
         datasource.setPoolProperties(p);
        return datasource.getConnection();
    }
 

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
     public static void main(String[] args) {
		
	}
}