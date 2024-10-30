package core.jdbc;


import jwp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate<T> {
    public void update(String sql, PreparedStatementSetter pstmtSetter) throws SQLException {

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmtSetter.setParameters(pstmt);
            pstmt.executeUpdate();
        }
    }

    public <T> List<T> queryAll(String sql, RowMapper<T> rowMapper) throws SQLException {
        List<T> objects = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {     // 왜 얜 여기서 했지
            while (rs.next()) {
                T object = rowMapper.mapRow(rs);
                objects.add(object);
            }
        }
        return objects;
    }

    public T queryForObject(String sql, PreparedStatementSetter pstmtSetter, RowMapper<T> rowMapper) throws SQLException {
        ResultSet rs = null;
        T object = null;        // 얜 왜 뺐지.. return 하려고!

        try ( Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql) ) {
            pstmtSetter.setParameters(pstmt);       // 얘 때문에 rs 따로 빼준 듯
            rs = pstmt.executeQuery();
            if (rs.next()) {
                object = rowMapper.mapRow(rs);
            }
        } finally {
            if (rs != null) rs.close();
        }
        return object;
    }

    public <T> List<T> queryByAtt(String sql, PreparedStatementSetter pstmtSetter, RowMapper<T> rowMapper) throws SQLException {
        List<T> objects = new ArrayList<>();
        ResultSet rs = null;

        try ( Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql) ) {
            pstmtSetter.setParameters(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                T object = rowMapper.mapRow(rs);
                objects.add(object);
            }
        } finally {
            if (rs != null) rs.close();
        }
        return objects;
    }

    public int queryId (String sql, PreparedStatementSetter pstmtSetter, RowMapper<Integer> rowMapper) throws SQLException {
        ResultSet rs = null;
        int id = 0;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmtSetter.setParameters(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                id = rowMapper.mapRow(rs);
            }
        } finally {
            if (rs != null) rs.close();
        }
        return id;
    }
}
