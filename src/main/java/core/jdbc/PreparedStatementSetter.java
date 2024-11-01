package core.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

// 함수형 인터페이스 -> 추상 method 1개만 가짐
@FunctionalInterface
public interface PreparedStatementSetter {

    // PreparedStatement의 parameter를 직접 설정해주는 역할
    void setParameters(PreparedStatement ps) throws SQLException;
}



