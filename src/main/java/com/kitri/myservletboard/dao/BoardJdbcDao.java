package com.kitri.myservletboard.dao;

import data.Board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.SplittableRandom;

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

    public ArrayList<Board> getAll() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Board> boards = new ArrayList<>();

        try {
            connection = connectionDB();
            String sql = "SELECT * FROM board";
            ps = connection.prepareStatement(sql);
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
