package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.data.Member;

public interface MemberDao {
    public Member getById(String loginId);
    public void save(Member member);
}
