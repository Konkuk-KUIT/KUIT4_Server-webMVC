package core.jdbc;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {        //db 연결 관리
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/jwp-basic";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PW = "";

    private static BasicDataSource ds;
    public static DataSource getDataSource() {          //db 연결 풀을 제공하는 BasicDataSource객체 반환
        if (ds == null) {
            ds = new BasicDataSource();
            ds.setDriverClassName(DB_DRIVER);
            ds.setUrl(DB_URL);
            ds.setUsername(DB_USERNAME);
            ds.setPassword(DB_PW);
        }
        return ds;
    }

    public static Connection getConnection() {          //DataSource에서 Connection 객체를 받아서 반환
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
