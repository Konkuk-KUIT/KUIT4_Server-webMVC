package core.jdbc;

import java.sql.*;
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

    public void update(String sql, PreparedStatementSetter pstmtSetter, KeyHolder keyHolder) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmtSetter.setParameters(pstmt);
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    keyHolder.setId((int)rs.getLong(1));
                }
            }
        }
    }

    public <T> List<T>  query(String sql, RowMapper<T> rowMapper) throws SQLException {
        List<T> objects = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                T object = rowMapper.mapRow(rs);
                objects.add(object);
            }
        }
        return objects;
    }

    public T queryForObject(String sql, PreparedStatementSetter pstmtSetter, RowMapper<T> rowMapper) throws SQLException {
        T object = null;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmtSetter.setParameters(pstmt);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                object = rowMapper.mapRow(rs);
            }
        }
        return object;
    }

}
