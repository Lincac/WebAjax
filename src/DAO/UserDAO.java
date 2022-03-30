package DAO;

import java.sql.SQLException;

public interface UserDAO {
    boolean LoginUser(String username,String password) throws Exception;
}
