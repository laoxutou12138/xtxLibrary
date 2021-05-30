package cn.edu.niit.dao;

import cn.edu.niit.db.JDBCUtil;
import cn.edu.niit.javabean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @program: xtxLibrary
 * @ClassName: UpdateUserDao
 * @description: Test
 * @author: XTX
 * @create: 2021-03-24 15:23
 **/
public class UpdateUserDao extends JDBCUtil{
    private Connection conn = null;
    private PreparedStatement ps=null;
    private int result=0;
    private ResultSet rs=null;
    public int updateUser(User user) throws SQLException {
        String updateUser="update borrow_card set reader=?,portrait=?,mydescribe=?,cellphone=?,email=?,sex=? where username=?";
        try {
            conn= this.getConnection();
            ps=conn.prepareStatement(updateUser);
            ps.setString(1,user.getReader());
            ps.setString(2,user.getPortrait());
            ps.setString(3,user.getMydescribe());
            ps.setString(4,user.getCellphone());
            ps.setString(5,user.getEmail());
            ps.setBoolean(6,user.isSex());
            ps.setString(7,user.getUsername());
            result=ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            conn.close();
            ps.close();


        }
        return result;
    }

    public int updateUserPassword(User user) throws SQLException {
        String updateUserPw="update borrow_card set password=? where username=?";

        try {
            conn= this.getConnection();
            ps=conn.prepareStatement(updateUserPw);
            ps.setString(1,user.getPassword());
            ps.setString(2,user.getUsername());
            result=ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            conn.close();
            ps.close();
        }
        return result;
    }

    public int updateUserOther(User user) throws SQLException {
        String updateUser="update borrow_card set reader=?,mydescribe=?,cellphone=?,email=?,sex=? where username=?";
        try {
            conn= this.getConnection();
            ps=conn.prepareStatement(updateUser);
            ps.setString(1,user.getReader());
            ps.setString(2,user.getMydescribe());
            ps.setString(3,user.getCellphone());
            ps.setString(4,user.getEmail());
            ps.setBoolean(5,user.isSex());
            ps.setString(6,user.getUsername());
            result=ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            conn.close();
            ps.close();


        }
        return result;


    }
}
