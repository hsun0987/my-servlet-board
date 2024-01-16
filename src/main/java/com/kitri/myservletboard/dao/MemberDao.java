package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.data.Member;

public interface MemberDao {
    public Member getById(String id);
    public void save(Member member);
}
