package cn.edu.niit.service;

import cn.edu.niit.dao.LoginDao;
import cn.edu.niit.javabean.Login;
import cn.edu.niit.javabean.User;

import javax.servlet.http.HttpSession;

/**
 * @program: xtxLibrary
 * @ClassName: LoginService
 * @description: Test
 * @author: XTX
 * @create: 2021-03-22 15:12
 **/
public class LoginService {
    private LoginDao loginDao =new LoginDao();
    public String login(Login loginParam, HttpSession session){
        User user = loginDao.selectOne(loginParam.getUsername());
        if (user == null){
            return "用户不存在";
        }else {
            if (loginParam.getPassword().equals(user.getPassword())){
                session.setAttribute("user",user);
                session.setAttribute("isLogin",true);
                session.setAttribute("id", user.getUsername());
                return "1";
            }else {
                return "密码错误";
            }
        }

    }

    public User getUserInfo(String name){
        return loginDao.selectOne(name);
    }
}
