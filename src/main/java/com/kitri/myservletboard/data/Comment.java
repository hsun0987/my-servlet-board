package com.kitri.myservletboard.data;

import java.time.LocalDateTime;

public class Comment {
    private Long id;
    private Long boardId;
    private Long memberId;
    private String content = "";
    private LocalDateTime createdAt;
    private String name = "";

    public Comment(Long id, Long boardId){
        this.id = id;
        this.boardId = boardId;
    }
    public Comment(Long boardId, Long memberId, String content){
        this.boardId = boardId;
        this.memberId = memberId;
        if(content != null){
            this.content = content;
        }
    }

    public Comment(Long id, Long boardId, Long memberId, String content, LocalDateTime createdAt, String name) {
        this.id = id;
        this.boardId = boardId;
        this.memberId = memberId;
        this.content = content;
        this.createdAt = createdAt;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
