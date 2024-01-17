package com.kitri.myservletboard.data;

public class Member {
    private Long id;
    private String loginId;
    private String pw;
    private String name;
    private String email;

    // 로그인 생성자
    public Member(String loginId, String pw) {
        this.loginId = loginId;
        this.pw = pw;
    }

    // 회원가입 생성자
    public Member(Long id, String loginId, String pw, String name, String email) {
        this.id = id;
        this.name = name;
        this.loginId = loginId;
        this.pw = pw;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String id) {
        this.loginId = loginId;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}