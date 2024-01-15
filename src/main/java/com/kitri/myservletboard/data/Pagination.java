package com.kitri.myservletboard.data;

import java.lang.invoke.SwitchPoint;

public class Pagination {
    private int page;   // 현재 페이지
    private int maxRecordsPerPage = 10; // 한 페이지의 최대 게시글
    private int pagesOnScreen = 5;  // 화면에 표시되는 최대 페이지 번호
    private int startIndex = 0;     // 페이지의 시작 인덱스
    private int totalRecords = 0;   // 게시글 총 수
    private int startPage = 1;      // 시작 페이지번호
    private int endPage = this.pagesOnScreen;   // 끝 페이지번호

    private boolean hasNext = false;
    private boolean hasPrev = false;



    public Pagination(int page, String sort) {
        this.page = page;

        if(sort != null){
            switch (sort){
                case "5":
                    this.maxRecordsPerPage = 5;
                    break;
                case "10" :
                    this.maxRecordsPerPage = 10;
                    break;
                case "15":
                    this.maxRecordsPerPage = 15;
                    break;
                case "20":
                    this.maxRecordsPerPage = 20;
                    break;
                case "30" :
                    this.maxRecordsPerPage = 30;
                    break;
                case "40" :
                    this.maxRecordsPerPage = 40;
                    break;
                case "50":
                    this.maxRecordsPerPage = 50;
                    break;
            }
        }
    }

    public void calcPagination(){   // 페이지네이션 번호 계산
        // 1 2 3 4 5 -> start: 1 end: ?
        // 6 7 8 9 10 -> start: 6 end: ?

        int totalPages = ((int)Math.ceil((double) this.totalRecords / this.maxRecordsPerPage));
        this.startPage = ((int)Math.ceil((double) this.page / this.pagesOnScreen) - 1) * this.pagesOnScreen + 1;

        this.endPage = this.startPage + this.pagesOnScreen - 1;
        if(this.endPage > totalPages){
            this.endPage = totalPages;
        }
        if(this.endPage < totalPages){
            this.hasNext = true;
        }
        if(this.startPage > this.pagesOnScreen){
            this.hasPrev = true;
        }

    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getMaxRecordsPerPage() {
        return maxRecordsPerPage;
    }

    public void setMaxRecordsPerPage(int maxRecordsPerPage) {
        this.maxRecordsPerPage = maxRecordsPerPage;
    }

    public int getPagesOnScreen() {
        return pagesOnScreen;
    }

    public void setPagesOnScreen(int pagesOnScreen) {
        this.pagesOnScreen = pagesOnScreen;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPrev() {
        return hasPrev;
    }

    public void setHasPrev(boolean hasPrev) {
        this.hasPrev = hasPrev;
    }
}
