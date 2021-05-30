package cn.edu.niit.javabean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: xtxLibrary
 * @ClassName: MessageBoard
 * @description: Test
 * @author: XTX
 * @date: 2021/4/27 22:34
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageBoard {
    private String card_id;
    private String username;
    private String userportrait;
    private String detail;
    private String public_date;

}
