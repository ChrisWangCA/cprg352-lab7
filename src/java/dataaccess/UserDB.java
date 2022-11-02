package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.User;
/**
 * @author Chris Wang
 * @version 1.0
 * @date 2022-10-23
 */
public class UserDB {

    public List<User> getAll() throws Exception{
        List<User> users = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection conn = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM user";

        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                String email = rs.getString(1);
                boolean active = rs.getBoolean(2);
                String first_name = rs.getString(3);
                String last_name = rs.getString(4);
                String password = rs.getString(5);
                int role = rs.getInt(6);
                User user = new User(email, active, first_name, last_name, password, role);
                users.add(user);
            }
        }finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(conn);
        }
        return users;
    }
    public User get(String email) throws Exception{

        User user = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection conn = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM user WHERE email=?";

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if(rs.next()){
                boolean active = rs.getBoolean(2);
                String first_name = rs.getString(3);
                String last_name = rs.getString(4);
                String password = rs.getString(5);
                int role = rs.getInt(6);
                user = new User(email,active,first_name,last_name,password,role);

            }
        }finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(conn);
        }
        

        return user;
    }

    public void insert(User user) throws Exception{
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection conn = cp.getConnection();
        PreparedStatement ps = null;


        String query = "INSERT INTO USER(email,active,first_name,last_name,password,role) VALUES(?,?,?,?,?,?)";

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, user.getEmail());
            ps.setBoolean(2, user.isActive());
            ps.setString(3, user.getFirst_name());
            ps.setString(4, user.getLast_name());
            ps.setString(5, user.getPassword());
            ps.setInt(6, user.getRole());
            ps.executeUpdate();
        }finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(conn);
        }
    }

    public void update(User user) throws Exception{
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection conn = cp.getConnection();
        PreparedStatement ps = null;

        String query = "update user set active = ? , first_name = ? , last_name = ? , password = ? , role = ? where email = ?";

        try {
            ps = conn.prepareStatement(query);
            ps.setBoolean(1, user.isActive());
            ps.setString(2, user.getFirst_name());
            ps.setString(3, user.getLast_name());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getRole());
            ps.setString(6, user.getEmail());
            ps.executeUpdate();
        }finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(conn);
        }
    }

    public void delete(User user) throws Exception{
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection conn = cp.getConnection();
        PreparedStatement ps = null;

        String query = "delete from user where email = ?";

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, user.getEmail());
            ps.executeUpdate();
        }finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(conn);
        }
    }
}
