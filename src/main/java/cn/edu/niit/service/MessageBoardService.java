package cn.edu.niit.service;

import cn.edu.niit.dao.MessageBoardDao;
import cn.edu.niit.javabean.MessageBoard;

import java.util.List;

/**
 * @program: xtxLibrary
 * @ClassName: MessageBoardService
 * @description: Test
 * @author: XTX
 * @date: 2021/4/27 22:45
 **/
public class MessageBoardService {
    private MessageBoardDao messageBoardDao=new MessageBoardDao();

    public List<MessageBoard> searchAllMessage(int pageNum, int pageSize) {
        List<MessageBoard> messageBoards =messageBoardDao.searchAllMessage(pageNum,pageSize);
        return messageBoards;
    }

    public String insertMessage(String username, String messageBoard) {

        int result = messageBoardDao.insertMessage( username, messageBoard);

        if (result == 1) {

            return "留言成功";
        } else {
            return "留言失败";
        }
    }

    public int messageCount() {
        return messageBoardDao.selectMessageCount();

    }
}
