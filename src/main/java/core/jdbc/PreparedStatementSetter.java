package core.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface PreparedStatementSetter {      //PreparedStatement객체에 파라미터를 설정
    void setParameters(PreparedStatement ps) throws SQLException;
}
