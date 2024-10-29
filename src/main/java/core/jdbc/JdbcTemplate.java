package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate<T> {

    public void update(String sql, PreparedStatementSetter pstmtSetter) throws SQLException {

        try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {  //try with recourse 문
            pstmtSetter.setParameters(pstmt);

            pstmt.executeUpdate();

        }
    }

    public <T> List<T> query(String sql, RowMapper<T> rowMapper) throws SQLException{

        List<T> objects = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            while(rs.next()){
                T objectc = rowMapper.mapRow(rs);
                objects.add(objectc);
            }
        }

        return objects;
    }

    public T queryForObject(String sql, PreparedStatementSetter pstmtSetter, RowMapper<T> rowMapper) throws SQLException{
        ResultSet rs = null;
        T objecct = null;

        try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmtSetter.setParameters(pstmt);

            rs = pstmt.executeQuery();
            if(rs.next()){
                objecct = rowMapper.mapRow(rs);
            }
        }

        return objecct;
    }




}
