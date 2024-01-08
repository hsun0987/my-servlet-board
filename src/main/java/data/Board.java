package data;

import java.time.LocalDateTime;

public class Board {
    // 게시판에 필요한 요소
    private Long id;                 // 게시글 번호
    private String title;            // 제목
    private String content;          // 내용
    private String writer;           // 작성자
    private LocalDateTime createdAt; // 작성일
    private int viewCount;           // 조회수
    private int commentCount;        // 댓글수

    // 생성자
    public  Board(){};

    public Board(Long id, String title, String content, String writer, LocalDateTime createdAt, int viewCount, int commentCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdAt = createdAt;
        this.viewCount = viewCount;
        this.commentCount = commentCount;
    }

    // getter & setter
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

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}
