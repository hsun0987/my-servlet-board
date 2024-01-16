package com.kitri.myservletboard.data;

public class Member {
    private String id;
    private String pw;
    private String name;
    private String email;

    public Member(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

    public Member(String name, String id, String pw, String email) {
        this.name = name;
        this.id = id;
        this.pw = pw;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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