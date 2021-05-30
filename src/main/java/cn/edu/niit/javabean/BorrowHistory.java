package cn.edu.niit.javabean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: xtxLibrary
 * @ClassName: BorrowInfo
 * @description: Test
 * @author: XTX
 * @date: 2021/4/23 10:27
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowHistory {

    private String card_id;
    private String book_id;
    private String bookname;
    private String author;
    private String sort;
    private String borrow_date;
    private String end_date;
    private String return_date;
    private boolean store;

    public BorrowHistory(String card_id, String book_id, String bookname, String author, String sort, String borrow_date, String end_date, String return_date) {
        this.card_id = card_id;
        this.book_id = book_id;
        this.bookname = bookname;
        this.author = author;
        this.sort = sort;
        this.borrow_date = borrow_date;
        this.end_date = end_date;
        this.return_date = return_date;
    }
}
