package cn.edu.niit.javabean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: xtxLibrary
 * @ClassName: FavoritesInfo
 * @description: Test
 * @author: XTX
 * @date: 2021/4/23 21:34
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoritesInfo {
    private String bookName;
    private String bookAuthor;
    private String book_sort;
    private String book_decription;
    private String User_ID;
    private String book_id;
    private boolean collection_status;

    public FavoritesInfo(String User_ID,String book_id,String bookName, String bookAuthor, String book_sort, String book_decription) {
        this.User_ID=User_ID;
        this.book_id=book_id;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.book_sort = book_sort;
        this.book_decription = book_decription;
    }
}
