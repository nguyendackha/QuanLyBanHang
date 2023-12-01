package Helper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class XJdbc {

    public static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static String dburl = "jdbc:sqlserver://LAPTOP-3JH297RQ\\NGUYENDACKHA:1433;databaseName=QuanLyBanHang;encrypt=false";
    public static String username = "sa";
    public static String password = "123456";

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dburl, username, password);
    }

    public static PreparedStatement preparedStatement(String sql, Object... args) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        if (sql.trim().startsWith("{")) {
            pstmt = conn.prepareCall(sql); //proc
        } else {
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); //SQL
        }
        for (int i = 0; i < args.length; i++) {
            pstmt.setObject(i + 1, args[i]);
        }
        return pstmt;
    }

    public static ResultSet executeQuery(String sql, Object... args) {
        try {
            PreparedStatement pstmt = preparedStatement(sql, args);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int executeUpdateAndGetRowsAffected(String sql, Object... args) {
        try (PreparedStatement pstmt = preparedStatement(sql, args)) {
            int rowsAffected = pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // Bạn có thể thực hiện các xử lý khác tùy vào yêu cầu của bạn
                }
            }
            return rowsAffected;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
     public static <T> List<T> singleColumnQuery(String sql, Class<T> clazz, Object... args) {
        List<T> result = new ArrayList<>();
        try (PreparedStatement pstmt = preparedStatement(sql, args);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                T value = (T) rs.getObject(1, clazz);
                result.add(value);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static void executeUpdate(String sql, Object... args) {
        try (PreparedStatement pstmt = preparedStatement(sql, args)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
