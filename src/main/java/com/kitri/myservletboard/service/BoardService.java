package com.kitri.myservletboard.service;

import com.kitri.myservletboard.dao.BoardDao;
import com.kitri.myservletboard.dao.BoardMemoryDao;
import data.Board;

import java.util.ArrayList;

public class BoardService {
    // 싱글톤으로 생성자 구현
    private static final BoardService instance = new BoardService();
    private BoardService(){};
    public static BoardService getInstance(){
        return instance;
    }

    BoardDao boardDao = BoardMemoryDao.getInstance();

    // 게시판 CRUD 메소드 (DAO를 통해 가져오기)
    public ArrayList<Board> getBoards(){    // 게시글 모든 목록조회
        return boardDao.getAll();
    }
    public Board getBoard(Long id){         // 조회
        return boardDao.getById(id);
    }
    public void addBoard(Board board){      // 등록
        boardDao.save(board);
    }
    public void updateBoard(Board board){   // 수정
        boardDao.update(board);
    }
    public void deleteBoard(Board board){   // 삭제
        boardDao.delete(board);
    }
}