package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.data.Comment;
import java.util.ArrayList;

public interface CommentDao  {
    public ArrayList<Comment> getAllByComment(Long id);
    public Comment getById(Long memId);
    public void save(Comment comment);
    public void update(Comment comment);
    public void delete(Long id);
}
