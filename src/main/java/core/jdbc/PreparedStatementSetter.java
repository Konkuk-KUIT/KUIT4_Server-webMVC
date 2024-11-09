package core.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

// 함수형 인터페이스 선언 (추상메소드를 하나만 가지는 인터페이스)

// sql의 placeholder(?) 부분에 들어 갈 데이터 설정
@FunctionalInterface

public interface PreparedStatementSetter {
    // PreparedStatement 매개변수에 실제 삽입 수행예정
    void setParameters(PreparedStatement ps) throws SQLException;
}
