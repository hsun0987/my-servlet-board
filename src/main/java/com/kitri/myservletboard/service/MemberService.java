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

    public Member getMember(String loginId){
        return memberDao.getById(loginId);
    }

    public void addMember(Member member){ memberDao.save(member);}

    public boolean isLogined(Member member){
        boolean isLoginFailed = false;
        String loginId = memberDao.getById(member.getLoginId()).getLoginId();
        String pw = memberDao.getById(member.getLoginId()).getPw();

        if (member.getLoginId().isEmpty() || member.getPw().isEmpty()) {
            isLoginFailed = true;
        }
        if (loginId == null) {
            isLoginFailed = true;
        } else if (!member.getPw().equals(pw)) {
                isLoginFailed = true;
        }

       return isLoginFailed;
    }

}
