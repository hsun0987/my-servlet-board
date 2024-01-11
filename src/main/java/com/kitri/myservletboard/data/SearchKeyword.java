package com.kitri.myservletboard.data;

public class SearchKeyword {
    private String keyword = "";
    private String type = "title";    // title, writer

    public SearchKeyword(String keyword, String type) {
        if(keyword != null)
            this.keyword = keyword;
        if(type != null)
            this.type = type;
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
    
}
