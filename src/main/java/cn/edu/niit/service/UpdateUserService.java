package cn.edu.niit.service;

import cn.edu.niit.dao.UpdateUserDao;
import cn.edu.niit.javabean.User;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * @program: xtxLibrary
 * @ClassName: UpdateUserService
 * @description: Test
 * @author: XTX
 * @create: 2021-03-24 15:38
 **/
public class UpdateUserService {
    UpdateUserDao updateUserDao=new UpdateUserDao();

    public String update(User user, HttpSession session) throws SQLException {
        String result=String.valueOf(updateUserDao.updateUser(user));
        if (result.equals("1")) {
            session.setAttribute("user",user);
            session.setAttribute("isLogin",true);
            return "信息修改成功";
        } else {
            return "信息修改失败";
        }
    }

    public String updateOther(User user, HttpSession session) throws SQLException {
        String result=String.valueOf(updateUserDao.updateUserOther(user));
        if (result.equals("1")) {
            session.setAttribute("user",user);
            session.setAttribute("isLogin",true);
            return "信息修改成功";
        } else {
            return "信息修改失败";
        }

    }

    public String updatePW(User user, HttpSession session) throws SQLException {
        String result=String.valueOf(updateUserDao.updateUserPassword(user));
        if (result.equals("1")) {
            session.setAttribute("user",user);
            session.setAttribute("isLogin",true);
            return "密码修改成功";
        } else {
            return "密码修改失败";
        }

    }

}
