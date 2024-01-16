package com.kitri.myservletboard.service;

import com.kitri.myservletboard.dao.MemberDao;
import com.kitri.myservletboard.dao.MemberJdbcDao;
import com.kitri.myservletboard.data.Member;

public class MemberService {
    MemberDao memberDao = MemberJdbcDao.getInstance();
    private static final MemberService instance = new MemberService();
    private MemberService(){};
    public static MemberService getInstance(){
        return instance;
    }

    public Member getMember(String id){
        return memberDao.getById(id);
    }
    public void addMember(Member member){ memberDao.save(member);}

    public boolean isLogined(Member member){
        boolean isLoginFailed = false;
        String id = memberDao.getById(member.getId()).getId();
        String pw = memberDao.getById(member.getId()).getPw();

        if (member.getId().isEmpty() || member.getPw().isEmpty()) {
            isLoginFailed = true;
        }
        if (id == null) {
            isLoginFailed = true;
        } else if (!member.getPw().equals(pw)) {
                isLoginFailed = true;
        }

       return isLoginFailed;
    }

}
