package cn.edu.niit.service;

import cn.edu.niit.dao.BookDao;
import cn.edu.niit.dao.FavoritesInfoDao;
import cn.edu.niit.javabean.Book;
import cn.edu.niit.javabean.BorrowHistory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @program: xtxLibrary
 * @ClassName: BookService
 * @description: Test
 * @author: XTX
 * @date: 2021/4/12 16:11
 **/
public class BookService {
    private BookDao bookDao=new BookDao();
    FavoritesInfoDao favoritesInfoDao=new FavoritesInfoDao();


    public List<Book> searchAllBooks(String username,int pageNum, int pageSize) {
        List<Book> books=bookDao.selectAll(username,pageNum,pageSize);
        for (Book book : books) {
            book.setStore(isStore(username, book.getId()));
            book.setCollection_status(iscollection_status(username, book.getId()));
        }
        return books;
    }

    public void BooksStats(List<BorrowHistory> borrowHistories,BookService bookService) throws ParseException {
        Iterator it = borrowHistories.iterator();
        while (it.hasNext()) {
            BorrowHistory bookstat = (BorrowHistory) it.next();
            String bookstatEnd_date = bookstat.getBorrow_date();
            String end_date = bookstat.getEnd_date();
            String book_id = bookstat.getBook_id();
            String card_id = bookstat.getCard_id();
            SimpleDateFormat borrow = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            SimpleDateFormat end = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date borrowtime = borrow.parse(bookstatEnd_date);
            Date endtime = end.parse(end_date);
            if (endtime.before(borrowtime)) {
                bookService.updataillegal(bookService.getDay(end_date).toString(),card_id,book_id,bookstatEnd_date);
            }
        }
    }

    public int updataillegal(String illegal,String username, String bookId,String borrow_date){
        return bookDao.updataillegal(illegal,username,bookId,borrow_date);
    }

    public int countNum() {
        return bookDao.selectAllCount();
    }


    public static Long getDay(String enddate) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long days = null;
        try {
            Date currentTime = dateFormat.parse(dateFormat.format(new Date()));
            Date pastTime = dateFormat.parse(enddate);
            long diff = currentTime.getTime() - pastTime.getTime();
            days = diff / (1000 * 60 * 60 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    public boolean isStore(String username, String bookId) {
        return bookDao.selectStore(username, bookId);
    }


    public boolean iscollection_status(String username, String bookId) {
        return favoritesInfoDao.selectcollection_statusByusername(username, bookId);
    }
}
