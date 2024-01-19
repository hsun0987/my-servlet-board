package com.kitri.myservletboard.data;
import com.kitri.myservletboard.dao.BoardDao;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Board {
    private Long id;
    private String title;
    private String writer;
    private String content;
    private LocalDateTime createdAt;
    private int viewCount;
    private int comCount;
    private Long memberId;
    private ArrayList <Comment> comments = new ArrayList<>();

    public Board(){

    }
    // 게시글 등록할 때 생성자
    public Board(String title, String writer, String content, Long memberId){
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.memberId = memberId;
    }

    // 게시글 수정할 때 생성자
    public Board(Long id, String title, String writer, String content){
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.content = content;
    }

    public Board(Long id, String title, String content, String writer, LocalDateTime createdAt, int viewCount, int comCount,Long memberId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.viewCount = viewCount;
        this.comCount = comCount;
        this.createdAt = createdAt;
        this.memberId = memberId;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() { return writer; }

    public void setWriter(String writer) { this.writer = writer; }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(String writer) {
        this.memberId = memberId;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getComCount() {
        return comCount;
    }

    public void setComCount(int comCount) {
        this.comCount = comCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
