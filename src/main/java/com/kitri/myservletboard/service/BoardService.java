package com.kitri.myservletboard.service;

import com.kitri.myservletboard.dao.BoardDao;
import com.kitri.myservletboard.dao.BoardMemoryDao;
import data.Board;

import java.util.ArrayList;

public class BoardService {
    BoardDao boardDao = BoardMemoryDao.getInstance();

    // 싱글톤
    private BoardService() {};
    private static final BoardService instance = new BoardService();

    // 게시판 리스트 가져오는 로직
    public static BoardService getInstance(){

        return instance;
    }
    public ArrayList<Board> getBoards(){

        return boardDao.getAll();
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
