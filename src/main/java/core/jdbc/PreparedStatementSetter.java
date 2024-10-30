package core.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

//추상메서드 하나만 가지는 인터페이스 구현
@FunctionalInterface
public interface PreparedStatementSetter{
    void setParameters(PreparedStatement ps) throws SQLException;
}
