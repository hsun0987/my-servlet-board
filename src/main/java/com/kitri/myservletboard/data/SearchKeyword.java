package com.kitri.myservletboard.data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class SearchKeyword {
    private String keyword = "";
    private String type = "title";    // title, writer
    private String period = "all";
    private int term = 10000;
    private String sort = "created_at";

    public SearchKeyword(String keyword, String type, String period, String sort) {
        if(keyword != null)
            this.keyword = keyword;
        if(type != null)
            this.type = type;
        if (period != null) {
            this.period = period;
            setTerm(period);
        }
        if (sort != null){
            this.sort = sort;
            setSort(sort);
        }
     }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        switch (sort){
            case "created_at":
                this.sort = "created_at";
                break;
            case "view_count":
                this.sort = "view_count";
                break;
            case "title":
                this.sort = "title";
                break;
        }
    }

    public void setTerm(String period) {
        switch (period){
            case "all":
                this.term = 10000;
                break;
            case "day":
                this.term = 1;
                break;
            case "week":
                this.term = 7;
                break;
            case "month":
                this.term = 31;
                break;
            case "six-month":
                this.term = 182;
                break;
            case "year":
                this.term = 365;
                break;
        }
    }

    public int getTerm() {
        return term;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
