package core.db;

import jwp.dao.UserDao;
import jwp.model.User;

import java.sql.SQLException;
import java.util.Collection;

public class DBUserRepository implements UserRepository {

    private UserDao userDao;
    private static DBUserRepository dbUserRepository;

    public DBUserRepository() {
        this.userDao = new UserDao();
    }

    public static DBUserRepository getInstance() {
        if (dbUserRepository == null) {
            dbUserRepository = new DBUserRepository();
            return dbUserRepository;
        }
        return dbUserRepository;
    }
    @Override
    public void addUser(User user) throws SQLException {
        userDao.insert(user);
    }

    @Override
    public User findUserById(String id) throws SQLException {
        return userDao.findByUserId(id);
    }

    @Override
    public Collection<User> findAll() throws SQLException {
        return userDao.findAll();
    }

    public void update(User user) throws SQLException {
        userDao.update(user);
    }
}
