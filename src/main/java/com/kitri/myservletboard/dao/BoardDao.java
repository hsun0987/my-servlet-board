package com.kitri.myservletboard.dao;
import data.Board;
import java.util.ArrayList;

public interface BoardDao {
    // DAO : 데이터의 CRUD 작업을 시행하는 클래스
    public ArrayList<Board> getAll();
    public Board getById(Long id);   // 조회
    public void save(Board board);   // 등록
    public void update(Board board); // 수정
    public void delete(Board board); // 삭제
}
