package core.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

// 함수형 인터페이스 -> 추상 메서드를 하나만 가지는 인터페이스
@FunctionalInterface
public interface PreparedStatementSetter {
    void setParameters(PreparedStatement ps) throws SQLException;
}
