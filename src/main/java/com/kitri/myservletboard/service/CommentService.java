package com.kitri.myservletboard.service;

import com.kitri.myservletboard.dao.CommentDao;
import com.kitri.myservletboard.dao.CommentJdbcDao;
import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.data.Comment;

public class CommentService {
    CommentDao commentDao = CommentJdbcDao.getInstance();

    private static final CommentService instance = new CommentService();
    private CommentService(){};
    public static CommentService getInstance(){
        return instance;
    }

    public Comment getComment(Long id){
        return commentDao.getById(id);

    }
    public void addComment(Comment comment){ commentDao.save(comment); }

    public void deleteComment(Long commentId){ commentDao.delete(commentId);}

}
