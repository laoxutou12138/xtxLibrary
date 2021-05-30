package cn.edu.niit.dao;

import cn.edu.niit.javabean.Admin;
import cn.edu.niit.javabean.User;
import cn.edu.niit.db.JDBCUtil;

import java.sql.ResultSet;

/**
 * @program: xtxLibrary
 * @ClassName: LoginDao
 * @description: Test
 * @author: XTX
 * @create: 2021-03-22 15:11
 **/
public class LoginDao {
    public User selectOne(String username){
        User user = null;
        try(ResultSet resultSet = JDBCUtil.getInstance().executeQueryRS("select * from borrow_card where username = ?",new Object[]{username})){

            while (resultSet.next()){
                user=new User(resultSet.getString("username"),resultSet.getString("password"),resultSet.getString("reader"),
                        resultSet.getString("portrait"),resultSet.getString("mydescribe"),resultSet.getString("cellphone"),
                        resultSet.getString("email"),resultSet.getBoolean("sex"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public Admin adminLogin(String username){
        Admin admin = null;
        try(ResultSet resultSet = JDBCUtil.getInstance().executeQueryRS("select * from admin where username = ?",new Object[]{username})){
            while (resultSet.next()){
                admin=new Admin(resultSet.getString("username"),resultSet.getString("password"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return admin;
    }
}
