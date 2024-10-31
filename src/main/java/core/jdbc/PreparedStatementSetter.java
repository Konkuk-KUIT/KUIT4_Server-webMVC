package core.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface PreparedStatementSetter {
    //함수형 인터페이스
    void setParameters(PreparedStatement ps )throws SQLException;

}
