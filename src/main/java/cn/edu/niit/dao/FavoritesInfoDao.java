package cn.edu.niit.dao;

import cn.edu.niit.db.JDBCUtil;
import cn.edu.niit.javabean.FavoritesInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: xtxLibrary
 * @ClassName: FavoritesInfoDao
 * @description: Test
 * @author: XTX
 * @date: 2021/4/23 23:48
 **/
public class FavoritesInfoDao {


    public int selectAllfavoritesInfoCount(String username) {
        int count = 0;
        String sql = "select count(*) as num from favoritelist_books where card_id=?";
        try (ResultSet rs = JDBCUtil.getInstance().executeQueryRS(sql, new Object[]{username})) {
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

    public List<FavoritesInfo> selectfavoritesInfoAll(int pageNum, int pageSize, String username) {
        String sql = "select books.*,book_sort.name as sort,favoritelist_books.* from books,book_sort,favoritelist_books \n" +
                "where books.sort_id=book_sort.id AND books.id=book_id and card_id=? limit ?,?";
        List<FavoritesInfo> favoritesInfos = new ArrayList<FavoritesInfo>();
        try (ResultSet rs = JDBCUtil.getInstance().executeQueryRS(sql,
                new Object[]{username, (pageNum - 1) * pageSize, pageSize})) {
            while (rs.next()) {
                FavoritesInfo favoritesInfo = new FavoritesInfo(
                        rs.getString("card_id"),
                        rs.getString("book_id"),
                        rs.getString("name"),
                        rs.getString("author"),
                        rs.getString("sort"),
                        rs.getString("description")
                        );
                favoritesInfos.add(favoritesInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.getInstance().closeAll();
        }
        return favoritesInfos;

    }


    public boolean selectcollection_statusByusername(String username, String book_id) {

        String sql1 = "select EXISTS( SELECT 1 FROM favoritelist_books " +
                "WHERE card_id=? and book_id=?) as collection_status";
        try (ResultSet rs =
                     JDBCUtil.getInstance().executeQueryRS(sql1,
                             new Object[]{
                                      username,book_id
                             });) {

            while (rs.next()) {

                return rs.getBoolean("collection_status");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.getInstance().closeAll();
        }

        return false;
    }

    public int insertfavoritesInfoBook( String username, String bookId) {

        int result = 0;
        String sql = "insert into favoritelist_books(card_id,book_id,collection_status) " +
                "values(?,?,?)";

        try {
            if (selectcollection_statusByusername(username, bookId) == false) {
                result = JDBCUtil.getInstance().executeUpdate(sql,
                        new Object[]{username,bookId,true});
                System.out.println("新增");
            } else {
                result = delfavoritesInfoBook(username, bookId) + 1;
                System.out.println("删除");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    private int delfavoritesInfoBook(String username, String bookId) {
        int result = 0;
        String sql = "DELETE FROM favoritelist_books WHERE book_id=? and card_id=?;";
        try {
            result = JDBCUtil.getInstance().executeUpdate(sql,
                    new Object[]{bookId, username});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
