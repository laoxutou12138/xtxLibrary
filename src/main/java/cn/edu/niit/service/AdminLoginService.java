package cn.edu.niit.service;

import cn.edu.niit.dao.LoginDao;
import cn.edu.niit.javabean.Admin;

import javax.servlet.http.HttpSession;

/**
 * @program: xtxLibrary
 * @ClassName: LoginService
 * @description: Test
 * @author: XTX
 * @create: 2021-03-22 15:12
 **/
public class AdminLoginService {


    private LoginDao loginDao =new LoginDao();
    public String adminLogin(Admin adminParam, HttpSession session){
        Admin admin = loginDao.adminLogin(adminParam.getUsername());
        if (admin == null){
            return "用户不存在";
        }else {
            if (adminParam.getPassword().equals(admin.getPassword())){
                session.setAttribute("admin",admin);
                session.setAttribute("isLogin",true);
                return "1";
            }else{
                return "密码错误";
            }
        }

    }
}
