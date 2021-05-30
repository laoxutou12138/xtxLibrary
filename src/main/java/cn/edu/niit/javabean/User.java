package cn.edu.niit.javabean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: xtxLibrary
 * @ClassName: User
 * @description: Test
 * @author: XTX
 * @create: 2021-03-22 15:11
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;
    private String password;
    private String reader;
    private String portrait;
    private String mydescribe;
    private String cellphone;
    private String email;
    private boolean sex;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public User(String username, String password, String reader) {
        this.username = username;
        this.password = password;
        this.reader = reader;
    }



}
