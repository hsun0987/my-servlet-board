package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.service.BoardService;
import data.Board;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class BoardMemoryDao implements BoardDao{

    // 싱글톤
    private static final BoardMemoryDao instance = new BoardMemoryDao();
    public static BoardMemoryDao getInstance(){
        return instance;
    }

    ArrayList<Board> memoryBoardDB = new ArrayList<>();
    Long id = 11L;

    private BoardMemoryDao() {
        memoryBoardDB.add(new Board(1L, "1번째 글입니다", "반갑습니다~", "손흥민", LocalDateTime.now(), 10, 1));
        memoryBoardDB.add(new Board(2L, "2번째 글입니다", "반갑습니다~", "손흥민", LocalDateTime.now(), 10, 1));
        memoryBoardDB.add(new Board(3L, "3번째 글입니다", "반갑습니다~", "손흥민", LocalDateTime.now(),10, 1));
        memoryBoardDB.add(new Board(4L, "4번째 글입니다", "반갑습니다~", "손흥민", LocalDateTime.now(), 10, 1));
        memoryBoardDB.add(new Board(5L, "5번째 글입니다", "반갑습니다~", "손흥민", LocalDateTime.now(),10, 1));
        memoryBoardDB.add(new Board(6L, "6번째 글입니다", "반갑습니다~", "손흥민", LocalDateTime.now(), 10, 1));
        memoryBoardDB.add(new Board(7L, "7번째 글입니다", "반갑습니다~", "손흥민", LocalDateTime.now(), 10, 1));
        memoryBoardDB.add(new Board(8L, "8번째 글입니다", "반갑습니다~", "손흥민", LocalDateTime.now(), 10, 1));
        memoryBoardDB.add(new Board(9L, "9번째 글입니다", "반갑습니다~", "손흥민", LocalDateTime.now(), 10, 1));
        memoryBoardDB.add(new Board(10L, "10번째 글입니다", "반갑습니다~", "손흥민", LocalDateTime.now(), 10, 1));
    }

    public ArrayList<Board> getAll(){
        return memoryBoardDB;
    }
    public Board getById(Long id){
        return memoryBoardDB.stream().filter(board -> {
            return board.getId() == id;
        }).findFirst().get();
    }
    public void save(Board board){
        // id 부여
        if (!memoryBoardDB.contains(id))
            board.setId(id++);

        memoryBoardDB.add(board);
    }
    public void update(Board board){
        Board board1 = getById(board.getId());
        board1.setTitle(board.getTitle());
        board1.setContent(board.getContent());

        // memoryBoardDB.remove(board1);
        // memoryBoardDB.add(board);
    }
    public void delete(Board board){
        memoryBoardDB.remove(board);
    }
}
