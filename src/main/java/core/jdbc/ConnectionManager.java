package core.jdbc;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

// DB와 JDBC의 connection
// DB에 연결하기 위한
public class ConnectionManager {
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/jwp-basic";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PW = "";

    // datasource : 미리 만든 연결 재활용 or 필요할 때 새로운 연결 생성 (Java에서 DB와 연결할 때 사용)
    private static BasicDataSource ds;

    // db에 연결 할 ds객체 반환
    public static DataSource getDataSource() {
        // db의 정보설정
        if (ds == null) { // 싱글톤 패턴 -> 객체 1번만 생성
            ds = new BasicDataSource();
            ds.setDriverClassName(DB_DRIVER);
            ds.setUrl(DB_URL);
            ds.setUsername(DB_USERNAME);
            ds.setPassword(DB_PW);
        }

        // ds는 connection 객체 제공
        return ds;
    }

    // Connection : DB에 연결된 상태에서 SQL문을 실행가능하게
    // 실제 DB와의 연겲을 나타내는 Connection객체 생성 및 반환
    public static Connection getConnection() {
        // ds의 connection을 반환
        try {
            // ds.getConnection()
            return getDataSource().getConnection();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
