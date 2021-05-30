package cn.edu.niit.service;

import cn.edu.niit.dao.FavoritesInfoDao;
import cn.edu.niit.javabean.FavoritesInfo;

import java.util.List;

/**
 * @program: xtxLibrary
 * @ClassName: FavoritesInfoService
 * @description: Test
 * @author: XTX
 * @date: 2021/4/23 23:48
 **/
public class FavoritesInfoService {
    FavoritesInfoDao favoritesInfoDao=new FavoritesInfoDao();

    public int countfavoritesInfoNum(String username) {
        return favoritesInfoDao.selectAllfavoritesInfoCount(username);
    }

    public List<FavoritesInfo> searfavoritesInfoByUserName(String username, int pageNum, int pageSize) {
        List<FavoritesInfo> favoritesInfos=favoritesInfoDao.selectfavoritesInfoAll(pageNum,pageSize,username);
        for (FavoritesInfo favoritesInfo : favoritesInfos) {
            favoritesInfo.setCollection_status(isStore(username, favoritesInfo.getBook_id()));
        }
        return favoritesInfos;
    }

    private boolean isStore(String username, String book_id) {

        return favoritesInfoDao.selectcollection_statusByusername(username, book_id);
    }

    public String storeBook(String username, String bookId) {
        int result = favoritesInfoDao.insertfavoritesInfoBook( username, bookId);

        if (result == 1) {

            return "收藏成功";
        } else {
            return "已取消收藏";
        }
    }

}
