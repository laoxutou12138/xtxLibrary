package cn.edu.niit.dao;

import cn.edu.niit.db.JDBCUtil;
import cn.edu.niit.javabean.MessageBoard;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: xtxLibrary
 * @ClassName: MessageBoardDao
 * @description: Test
 * @author: XTX
 * @date: 2021/4/27 22:50
 **/
public class MessageBoardDao {


    public List<MessageBoard> searchAllMessage(int pageNum, int pageSize) {
        String sql = "select m.*,b.reader,b.portrait from message m join borrow_card b on m.card_id = b.username ORDER BY public_date DESC LIMIT ?,?";
        List<MessageBoard> messageBoards = new ArrayList<MessageBoard>();
        try (ResultSet rs = JDBCUtil.getInstance().executeQueryRS(sql,
                new Object[]{pageNum,pageSize})) {
            while (rs.next()) {
                MessageBoard messageBoard = new MessageBoard(
                        rs.getString("card_id"),
                        rs.getString("reader"),
                        rs.getString("portrait"),
                        rs.getString("detail"),
                        rs.getString("public_date")

                );
                messageBoards.add(messageBoard);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.getInstance().closeAll();
        }
        return messageBoards;
    }

    public int insertMessage(String username, String messageBoard) {

        int result = 0;
        String sql = "insert into message(card_id,detail,public_date) values(?,?,?)";

        try {
                result = JDBCUtil.getInstance().executeUpdate(sql,
                        new Object[]{username,messageBoard,new Date(System.currentTimeMillis())});

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int selectMessageCount() {
        int count = 0;
        String sql = "select count(*) as num from message ";
        try (ResultSet rs = JDBCUtil.getInstance().executeQueryRS(sql, new Object[]{})) {
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.getInstance().closeAll();
        }
        return count;
    }
}
