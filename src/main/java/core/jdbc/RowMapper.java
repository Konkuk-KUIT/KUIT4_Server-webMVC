package core.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface RowMapper<T> {     //현재 행을 객체로 매핑
    T mapRow(ResultSet rs) throws SQLException;
}
