package cn.edu.niit.service;

import cn.edu.niit.dao.BorrowInfoDao;
import cn.edu.niit.javabean.BorrowInfo;

import java.util.List;

/**
 * @program: xtxLibrary
 * @ClassName: BorrowInfoService
 * @description: Test
 * @author: XTX
 * @date: 2021/4/23 21:42
 **/
public class BorrowInfoService {
    BorrowInfoDao borrowInfoDao=new BorrowInfoDao();

    public boolean isStore(String username, String bookId) {
        return borrowInfoDao.selectStore(username, bookId);
    }

    public List<BorrowInfo> searchAllBorrowInfo(String username, int pageNum, int pageSize) {
        List<BorrowInfo> borrowInfos=borrowInfoDao.selectBorrowInfoAll(pageNum,pageSize,username);
        for (BorrowInfo borrowInfo : borrowInfos) {
            borrowInfo.setStore(isStore(username, borrowInfo.getBook_id()));
        }
        return borrowInfos;
    }

    public int countBorrowNum(String username){
        return borrowInfoDao.selectAllBorrowCount(username);
    }

    public String storeBook(Boolean store, String username, String bookId, String borrow_date) {
        int result = borrowInfoDao.insertStoreBook(store, username, bookId,borrow_date);

        if (result == 1) {

            return "借阅成功";
        } else {
            return "归还成功";
        }
    }
}
