package com.kitri.myservletboard.service;

import com.kitri.myservletboard.dao.BoardDao;
import com.kitri.myservletboard.dao.BoardJdbcDao;
import com.kitri.myservletboard.dao.CommentDao;
import com.kitri.myservletboard.dao.CommentJdbcDao;
import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.data.Comment;
import com.kitri.myservletboard.data.Pagination;
import com.kitri.myservletboard.data.SearchKeyword;

import java.util.ArrayList;

public class BoardService {
   // BoardDao boardDao = BoardMemoryDao.getInstance();
    BoardDao boardDao = BoardJdbcDao.getInstance();
    CommentDao commentDao = CommentJdbcDao.getInstance();

    // 싱글톤
    private BoardService() {};
    private static final BoardService instance = new BoardService();

    // 게시판 리스트 가져오는 로직
    public static BoardService getInstance(){
        return instance;
    }

    public Board getBoard(Long id){
        Board board = boardDao.getById(id);
        board.setComments(commentDao.getAllByComment(id));

        return board;
    }
    public ArrayList<Board> getBoards(Pagination pagination, SearchKeyword searchKeyword){
        pagination.setTotalRecords(((BoardJdbcDao)boardDao).count(searchKeyword));   // totalRecords(총 게시글) 계산
        pagination.calcPagination();

        return boardDao.getAll(pagination, searchKeyword);
    }
    public void addBoard(Board board){
        boardDao.save(board);
    }
    public void updateBoard(Board board){
        boardDao.update(board);
    }
    public void deleteBoard(Board board){
        boardDao.delete(board);
    }


}
