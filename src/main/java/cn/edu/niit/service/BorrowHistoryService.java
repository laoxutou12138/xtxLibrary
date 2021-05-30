package cn.edu.niit.service;

import cn.edu.niit.dao.BorrowHistoryDao;
import cn.edu.niit.javabean.BorrowHistory;

import java.util.List;

/**
 * @program: xtxLibrary
 * @ClassName: BorrowHistoryService
 * @description: Test
 * @author: XTX
 * @date: 2021/4/27 17:57
 **/
public class BorrowHistoryService {
    private BorrowHistoryDao borrowHistoryDao=new BorrowHistoryDao();

    public int countBorrowHistoryNum(String id) {
        return BorrowHistoryDao.selectAllHistoryCount(id);
    }

    public List<BorrowHistory> searchAllBorrowHistory(String username, int pageNum, int pageSize) {
        List<BorrowHistory> borrowHistories=borrowHistoryDao.searchAllhistory(username,pageNum,pageSize);
        return borrowHistories;
    }
}
