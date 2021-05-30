package cn.edu.niit.javabean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: xtxLibrary
 * @ClassName: Book
 * @description: Test
 * @author: XTX
 * @date: 2021/4/12 15:43
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String id;
    private String name;
    private String author;
    private String sort;
    private String description;
    private String borrow_date;
    private boolean store;
    private boolean collection_status;

    public Book(String borrow_date,String id, String name, String author, String sort,
                String description) {
        this.borrow_date=borrow_date;
        this.id = id;
        this.name = name;
        this.author = author;
        this.sort = sort;
        this.description = description;
    }
}
