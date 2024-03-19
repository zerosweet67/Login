package Util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

public class ConnectionTest {

    public static void main(String[] args) {
    	
    	Connection con;

        try {
            // 获取初始上下文
            Context ctx = new InitialContext();
            Context envContext = (Context)ctx.lookup("java:comp/env");
            // 查找数据源
            DataSource ds = (DataSource) ctx.lookup("jdbc/orcl");

            // 获取数据库连接
            con = ds.getConnection();

            // 打印连接成功消息
            System.out.println("数据库连接成功");

        } catch (Exception e) {
            // 打印连接失败消息和异常信息
            System.out.println("数据库连接失败");
            e.printStackTrace();
        
        }
    }
}
