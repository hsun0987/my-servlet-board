package com.kitri.myservletboard.dao;

import data.Board;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class BoardMemoryDao implements BoardDao{
    // DAO 인터페이스 구현체
    // 싱글톤으로 생성자 구현
    private static final BoardMemoryDao instance = new BoardMemoryDao();
    public static BoardMemoryDao getInstance(){
        return instance;
    }
    ArrayList<Board> memoryBoardDB = new ArrayList<>();
    private BoardMemoryDao(){   // 게시판 목록 초기값 세팅
        memoryBoardDB.add(new Board(1L, "1번째 글입니다", "반갑습니다~", "이름1", LocalDateTime.now(), 10, 1));
        memoryBoardDB.add(new Board(2L, "2번째 글입니다", "반갑습니다~", "이름2", LocalDateTime.now(), 10, 1));
        memoryBoardDB.add(new Board(3L, "3번째 글입니다", "반갑습니다~", "이름3", LocalDateTime.now(),10, 1));
        memoryBoardDB.add(new Board(4L, "4번째 글입니다", "반갑습니다~", "이름4", LocalDateTime.now(), 10, 1));
        memoryBoardDB.add(new Board(5L, "5번째 글입니다", "반갑습니다~", "이름5", LocalDateTime.now(),10, 1));
        memoryBoardDB.add(new Board(6L, "6번째 글입니다", "반갑습니다~", "이름6", LocalDateTime.now(), 10, 1));
        memoryBoardDB.add(new Board(7L, "7번째 글입니다", "반갑습니다~", "이름7", LocalDateTime.now(), 10, 1));
        memoryBoardDB.add(new Board(8L, "8번째 글입니다", "반갑습니다~", "이름8", LocalDateTime.now(), 10, 1));
        memoryBoardDB.add(new Board(9L, "9번째 글입니다", "반갑습니다~", "이름9", LocalDateTime.now(), 10, 1));
        memoryBoardDB.add(new Board(10L, "10번째 글입니다", "반갑습니다~", "이름10", LocalDateTime.now(), 10, 1));
    }
    Long id = 11L;

    // BoardDao 추상메소드 구현
    public ArrayList<Board> getAll(){
        return  memoryBoardDB;
    }
    public Board getById(Long id){   // 조회
        return memoryBoardDB.stream().filter(board -> {
            return board.getId() == id;
        }).findFirst().get();
    }
    public void save(Board board){   // 등록
        // id(게시글 번호) 자동 부여
        if(!memoryBoardDB.contains(id))
            board.setId(id++);
        memoryBoardDB.add(board);
    }
    public void update(Board board){ // 수정
        Board board1 = getById(board.getId());
        board1.setTitle(board.getTitle());
        board1.setContent(board.getContent());
    }
    public void delete(Board board){ // 삭제
        memoryBoardDB.remove(board);
    }
}
