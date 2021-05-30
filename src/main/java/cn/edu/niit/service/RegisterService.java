package cn.edu.niit.service;

import cn.edu.niit.dao.RegisterDao;
import cn.edu.niit.javabean.User;

import java.sql.SQLException;

/**
 * @program: xtxLibrary
 * @ClassName: RegisterService
 * @description: Test
 * @author: XTX
 * @create: 2021-03-23 01:32
 **/
public class RegisterService {
    private RegisterDao registerDao=new RegisterDao();
    public String  register(User user) throws SQLException {
            String result=String.valueOf(registerDao.register(user));

         if (result.equals("0")){

            return "注册失败";
         }else {
                return "注册成功";
            }
        }

    }



