package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.data.Member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberJdbcDao implements MemberDao{
    private static final MemberJdbcDao instance = new MemberJdbcDao();
    private MemberJdbcDao(){};
    public static MemberJdbcDao getInstance(){
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

    public Member getById(String logId) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Member member = null;

        try {
            connection = connectionDB();
            String sql = "SELECT * FROM member WHERE login_id = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, logId);
            rs = ps.executeQuery();

            rs.next();

            Long id = rs.getLong("id");
            String loginId = rs.getString("login_id");
            String pw = rs.getString("password");
            String name = rs.getString("name");
            String email = rs.getString("email");

            member = new Member(id, loginId, pw, name, email);

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

        return member;
    }
    public void save(Member member){
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionDB();
            String sql = "INSERT INTO member(login_id, password, name, email) VALUES (?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, member.getLoginId());
            ps.setString(2, member.getPw());
            ps.setString(3, member.getName());
            ps.setString(4, member.getEmail());
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
