package cn.edu.niit.dao;

import cn.edu.niit.db.JDBCUtil;
import cn.edu.niit.javabean.BorrowInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @program: xtxLibrary
 * @ClassName: BorrowInfoDao
 * @description: Test
 * @author: XTX
 * @date: 2021/4/23 21:37
 **/
public class BorrowInfoDao {

    public List<BorrowInfo> selectBorrowInfoAll(int pageNum, int pageSize, String username) {
        String sql = "select books.*,book_sort.name as sort,borrow_books.* from books,book_sort,borrow_books " +
                "where books.sort_id=book_sort.id AND return_date IS NULL AND books.id=book_id and card_id=? limit ?,?";
        List<BorrowInfo> borrowInfos = new ArrayList<BorrowInfo>();
        try (ResultSet rs = JDBCUtil.getInstance().executeQueryRS(sql,
                new Object[]{username, (pageNum - 1) * pageSize, pageSize})) {
            while (rs.next()) {
                BorrowInfo borrowInfo = new BorrowInfo(
                        rs.getString("card_id"),
                        rs.getString("book_id"),
                        rs.getString("name"),
                        rs.getString("author"),
                        rs.getString("sort"),
                        rs.getString("borrow_date"),
                        rs.getString("end_date"),
                        rs.getString("return_date"),
                        rs.getString("illegal"));
                borrowInfos.add(borrowInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.getInstance().closeAll();
        }
        return borrowInfos;
    }

    public int selectAllBorrowCount(String username) {
        int count = 0;
        String sql = "SELECT count(*) FROM borrow_books WHERE return_date is NULL and card_id=?";
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


    public boolean selectStore(String username, String bookId) {
        String sql1 = "select (select 1 from borrow_books where book_id=? and card_id=? AND return_date IS  NULL) AS store";
        try (ResultSet rs =
                     JDBCUtil.getInstance().executeQueryRS(sql1,
                             new Object[]{
                                     bookId, username
                             });) {

            while (rs.next()) {

                return rs.getBoolean("store");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.getInstance().closeAll();
        }

        return false;
    }

    public int insertStoreBook(Boolean store, String username, String bookId, String borrow_date) {
        int result = 0;
        String sql = "insert into borrow_books(book_id, card_id, " +
                "borrow_date, end_date ,store) values(?,?,?,?,?)";

        Date date = new Date(System.currentTimeMillis());
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, 30);
        try {
            if (selectStore(username, bookId) == true) {
                result = updateStoreBook(username, bookId, borrow_date) + 1;

            } else {
                result = JDBCUtil.getInstance().executeUpdate(sql,
                        new Object[]{bookId, username,
                                date, calendar.getTime(), true});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int updateStoreBook(String username, String bookId, String borrow_date) {
        int result = 0;
        String sql =
                "UPDATE borrow_books set return_date=?  " +
                        "WHERE  book_id=? and card_id=?  and borrow_date=?";
        System.out.println(borrow_date);
        try {
            result = JDBCUtil.getInstance().executeUpdate(sql,
                    new Object[]{new Date(System.currentTimeMillis()), bookId, username, borrow_date});

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
