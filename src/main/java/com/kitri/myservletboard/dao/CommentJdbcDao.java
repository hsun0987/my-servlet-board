package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.data.Comment;
import com.kitri.myservletboard.data.Member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class CommentJdbcDao implements CommentDao{
    private static final CommentJdbcDao instance = new CommentJdbcDao();
    private CommentJdbcDao(){};
    public static CommentJdbcDao getInstance(){ return instance; }

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

    public ArrayList<Comment> getAllByComment(Long bId){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Comment> comments = new ArrayList<>();

        try {
            connection = connectionDB();
            // memberId, boardId 작성자, 댓글 내용, 댓글 작성 시간
            String sql = "SELECT comment.*, member.name " +
                         "FROM board, member, comment " +
                         "WHERE board.id = comment.board_id AND member.id = comment.member_id " +
                         "AND comment.board_id = ?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, bId);
            rs = ps.executeQuery();

            while (rs.next()){
                // comments를 arraylist에 저장 :  댓글 id, 댓글 내용, 댓글 작성 시간, 작성자
                Long id = rs.getLong("id");
                Long boardId = rs.getLong("board_id");
                Long memberId = rs.getLong("member_id");
                String content = rs.getString("content");
                LocalDateTime createAt = rs.getTimestamp("created_at").toLocalDateTime();
                String name = rs.getString("name");

                comments.add(new Comment(id, boardId, memberId, content, createAt, name));
            }

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
        return comments;
    }
    public Comment getById(Long memId){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Comment comment = null;

        try {
            connection = connectionDB();
            String sql = "SELECT comment.*, member.name FROM member, comment WHERE member.id = comment.member_id AND member_id = ?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, memId);
            rs = ps.executeQuery();

            rs.next();

            Long id = rs.getLong("id");
            Long boardId = rs.getLong("board_id");
            Long memberId = rs.getLong("member_id");
            String content = rs.getString("content");
            LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
            String name = rs.getString("name");

            comment = new Comment(id, boardId, memberId, content, createdAt, name);

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

        return comment;
    }
    public void save(Comment comment){
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionDB();
            String sql = "INSERT INTO comment(board_id, member_id, content) VALUES (?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, comment.getBoardId());
            ps.setLong(2, comment.getMemberId());
            ps.setString(3, comment.getContent());
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
    public void update(Comment comment){

    }
    public void delete(Long commentId){
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionDB();
            String sql = "DELETE FROM comment WHERE id = ?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, commentId);
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
