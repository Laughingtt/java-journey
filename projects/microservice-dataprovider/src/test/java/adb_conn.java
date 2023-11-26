import java.sql.*;
import java.util.Properties;

public class adb_conn {

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //adb_url是AnalyticDB MySQL集群的连接地址URL，可以在控制台的集群信息页面获取连接URL，3306是端口号。
            //db_name是AnalyticDB MySQL集群中的数据库名称。
            String url = "jdbc:mysql://127.0.0.1:3306/rmjr?useUnicode=true&characterEncoding=UTF-8";
            Properties connectionProps = new Properties();
            //account_name是AnalyticDB MySQL集群中的用户账号：高权限账号或者普通账号。
            connectionProps.put("user", "test");
            //account_password是AnalyticDB MySQL集群中用户账号对应的密码。
            connectionProps.put("password", "test");
            connection = DriverManager.getConnection(url, connectionProps);
            statement = connection.createStatement();
            String query = "select * from Transactions";
            rs = statement.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getObject(1));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
