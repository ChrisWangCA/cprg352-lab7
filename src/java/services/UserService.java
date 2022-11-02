package services;

import dataaccess.UserDB;
import java.util.List;
import models.User;


/**
 * @author Chris Wang
 * @version 1.0
 * @date 2022-10-23
 */
public class UserService {

    public List<User> getAll() throws Exception{
        UserDB userDB = new UserDB();
        return userDB.getAll();
    }

    public User get(String email) throws Exception{
        UserDB userDB = new UserDB();
        return userDB.get(email);
    }

    public void insert(String email,boolean active,String firstname,String lastname,String password,int role) throws Exception{
        UserDB userDB = new UserDB();
        User user = new User(email,active,firstname,lastname,password,role);
        userDB.insert(user);
    }

    public void update(String email,boolean active,String firstname,String lastname,String password,int role) throws Exception{
        UserDB userDB = new UserDB();
        User user = new User(email,active,firstname,lastname,password,role);
        userDB.update(user);
    }

    public void delete(String email) throws Exception{
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        userDB.delete(user);
    }

}
