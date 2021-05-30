package cn.edu.niit.dao;

import cn.edu.niit.db.JDBCUtil;
import cn.edu.niit.javabean.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BookDao {

    public List<Book> selectAll(String username, int pageNum, int pageSize) {
        String sql = "SELECT bf.*,s.name as sort,if(bf.id in (SELECT bb.book_id from books b " +
                "join borrow_books bb on b.id = bb.book_id " +
                "where bb.card_id = ? and bb.return_date is null)," +
                "(SELECT borrow_books.borrow_date FROM borrow_books WHERE book_id = bf.id AND card_id = ? " +
                "AND return_date is null),null) " +
                "as borrow_date from books bf join book_sort s on bf.sort_id=s.id limit ?,?";
        List<Book> books = new ArrayList<>();
        try (ResultSet rs = JDBCUtil.getInstance().executeQueryRS(sql,
                new Object[]{username, username, (pageNum - 1) * pageSize, pageSize})) {
            while (rs.next()) {
                Book book = new Book(
                        rs.getString("borrow_date"),
                        rs.getInt("id") + "",
                        rs.getString(
                                "name"),

                        rs.getString("author"),
                        rs.getString("sort"),
                        rs.getString("description"));
                books.add(book);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.getInstance().closeAll();
        }
        return books;
    }


    public int selectAllCount() {
        int count = 0;
        String sql = "select count(*) as num from books";
        try (ResultSet rs = JDBCUtil.getInstance().executeQueryRS(sql, new Object[]{})) {
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
        String sql1 = "select EXISTS( SELECT 1 from borrow_books " +
                "where return_date IS NULL and book_id=? and card_id=?) as store";
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


    public int updataillegal(String illegal, String username, String bookId, String borrow_date) {

        int result = 0;
        String sql =
                "UPDATE borrow_books set illegal=?  WHERE  book_id=? and card_id=?  and borrow_date=?";
        String time = "已超期天" + illegal + "天";
        try {
            result = JDBCUtil.getInstance().executeUpdate(sql,
                    new Object[]{time, bookId, username, borrow_date});

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
