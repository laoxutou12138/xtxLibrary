package cn.edu.niit.dao;

import cn.edu.niit.db.JDBCUtil;
import cn.edu.niit.javabean.BorrowHistory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: xtxLibrary
 * @ClassName: BorrowHistoryDao
 * @description: Test
 * @author: XTX
 * @date: 2021/4/27 18:00
 **/
public class BorrowHistoryDao {


    public static int selectAllHistoryCount(String username) {
        int count = 0;
        String sql = "SELECT count(*) FROM borrow_books WHERE card_id=?";
        try (ResultSet rs = JDBCUtil.getInstance().executeQueryRS(sql, new Object[]{username})) {
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.getInstance().closeAll();
        }
        return count;
    }


    public List<BorrowHistory> searchAllhistory(String username, int pageNum, int pageSize) {

        String sql = "select books.*,book_sort.name as sort,borrow_books.* from books,book_sort,borrow_books " +
                "where books.sort_id=book_sort.id and books.id=book_id " +
                "and card_id=? order by IF(ISNULL(return_date),0,1) ," +
                "return_date desc limit ?,? ";
        List<BorrowHistory> borrowHistories = new ArrayList<BorrowHistory>();
        try (ResultSet rs = JDBCUtil.getInstance().executeQueryRS(sql,
                new Object[]{username, (pageNum - 1) * pageSize, pageSize})) {
            while (rs.next()) {
                BorrowHistory borrowHistory = new BorrowHistory(
                        rs.getString("card_id"),
                        rs.getString("book_id"),
                        rs.getString("name"),
                        rs.getString("author"),
                        rs.getString("sort"),
                        rs.getString("borrow_date"),
                        rs.getString("end_date"),
                        rs.getString("return_date"));
                borrowHistories.add(borrowHistory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.getInstance().closeAll();
        }
        return borrowHistories;
    }
}
