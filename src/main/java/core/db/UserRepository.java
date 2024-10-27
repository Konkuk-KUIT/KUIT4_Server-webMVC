package core.db;

import jwp.model.User;

import java.sql.SQLException;
import java.util.Collection;

public interface UserRepository {
    void addUser(User user) throws SQLException;
    User findUserById(String id) throws SQLException;
    Collection<User> findAll() throws SQLException;
}
