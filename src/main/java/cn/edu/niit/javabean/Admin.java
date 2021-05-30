package cn.edu.niit.javabean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: xtxLibrary
 * @ClassName: Admin
 * @description: Test
 * @author: XTX
 * @create: 2021-03-29 14:53
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    private String username;
    private String password;
}
