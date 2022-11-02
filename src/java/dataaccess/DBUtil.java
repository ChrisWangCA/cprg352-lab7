package dataaccess;

import java.sql.*;

/**
 * @author Chris Wang
 * @version 1.0
 * @date 2022-10-23
 */
public class DBUtil {

    public static void closePreparedStatement(PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void closeResultSet(ResultSet rs){
        try{
            if (rs != null) {
                rs.close();
            }
        }catch (SQLException e){
            System.out.println(e);
        }
    }

}
