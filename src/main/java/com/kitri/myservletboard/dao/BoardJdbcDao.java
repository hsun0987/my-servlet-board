package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.data.Pagination;
import com.kitri.myservletboard.data.SearchKeyword;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class BoardJdbcDao implements BoardDao{
    // 싱글톤
    private static final BoardJdbcDao instance = new BoardJdbcDao();
    private BoardJdbcDao(){};
    public static BoardJdbcDao getInstance(){
        return instance;
    }
    public Connection connectionDB(){
        Connection conn = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/my_servlet_board";
            String user = "root";
            String pwd = "1234";
            conn = DriverManager.getConnection(url, user, pwd);

        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    public ArrayList<Board> getAll(Pagination pagination, SearchKeyword searchKeyword) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Board> boards = new ArrayList<>();

        try {
            connection = connectionDB();
            String sql = "SELECT * FROM board WHERE " + searchKeyword.getType() + "  LIKE ? AND created_at BETWEEN SUBDATE(NOW(), ?) AND NOW() ORDER BY id LIMIT ?, ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + searchKeyword.getKeyword() + "%");
            ps.setInt(2, searchKeyword.getTerm());
            ps.setInt(3, (pagination.getPage()-1) * pagination.getMaxRecordsPerPage());
            ps.setInt(4, pagination.getMaxRecordsPerPage());
            rs = ps.executeQuery();

            while (rs.next()){
                Long id =  rs.getLong("id");
                String title = rs.getString("Title");
                String content = rs.getString("content");
                String writer = rs.getString("writer");
                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
                int viewCount = rs.getInt("view_count");
                int commentCount = rs.getInt("comment_count");

                boards.add(new Board(id, title, content, writer, createdAt, viewCount, commentCount));
            }
        }catch (Exception e){

        }finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return boards;
    }

    public int count(SearchKeyword searchKeyword){    // totalRecords(총 게시글) 계산하는 메소드
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;

        try {
            connection = connectionDB();
            String sql = "SELECT COUNT(*) FROM board WHERE " + searchKeyword.getType() + " LIKE ? AND created_at BETWEEN SUBDATE(NOW(), ?) AND NOW()";
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + searchKeyword.getKeyword() + "%");
            ps.setInt(2, searchKeyword.getTerm());
            rs = ps.executeQuery();
            rs.next();
            count = rs.getInt("count(*)");

        }catch(Exception e){

        }finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return count;
    }

    public Board getById(Long id){  // DB ->
        // connection
        //ps -> executeQuery()
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Board board1 = null;

        try {
            connection = connectionDB();
            String sql = "SELECT * FROM board WHERE id = ?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            rs.next();
            Long id1 =  rs.getLong("id");
            String title = rs.getString("Title");
            String content = rs.getString("content");
            String writer = rs.getString("writer");
            LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
            int viewCount = rs.getInt("view_count");
            int commentCount = rs.getInt("comment_count");

            board1 = new Board(id1, title, content, writer, createdAt, viewCount, commentCount);

        }catch(Exception e){

        }finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return board1;
    }
    public void save(Board board){  // DB <-
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionDB();
            String sql = "INSERT INTO board(title, content, writer) VALUES (?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, board.getTitle());
            ps.setString(2, board.getContent());
            ps.setString(3, board.getWriter());
            ps.executeUpdate();

        }catch (Exception e){

        }finally {
            try {
                ps.close();
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void update(Board board){    // DB <-
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionDB();
            String sql = "UPDATE board SET title = ?, content = ? WHERE id = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, board.getTitle());
            ps.setString(2, board.getContent());
            ps.setLong(3, board.getId());
            ps.executeUpdate();

        }catch (Exception e){

        }finally {
            try {
                ps.close();
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void delete(Board board){
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionDB();
            String sql = "DELETE FROM board WHERE id = ?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, board.getId());
            ps.executeUpdate();

        }catch (Exception e){

        }finally {
            try {
                ps.close();
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
