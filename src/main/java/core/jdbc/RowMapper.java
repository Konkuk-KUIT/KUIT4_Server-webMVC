package core.jdbc;

import jwp.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface RowMapper<T> { // ResultSet의 정보들을 가져와서 객체를 반환해주는 함수형 인터페이스
    T mapRow(ResultSet rs) throws SQLException;
}
