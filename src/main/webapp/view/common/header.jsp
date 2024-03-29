<%@ page import="com.kitri.myservletboard.data.SearchKeyword" %>
<%@ page import="com.kitri.myservletboard.data.Pagination" %>
<%@ page import="com.kitri.myservletboard.service.MemberService" %>
<%@ page import="com.kitri.myservletboard.data.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<header class = "d-flex">
    <a class="logo d-flex align-items-center text-dark text-decoration-none" href="/board/list">
        <span class="material-symbols-outlined">
            home
        </span>
    </a>
    <nav>
        <ul class="nav-items">

            <li><a href="/board/list">게시글목록</a></li>
            <li><a href="/member/joinForm">회원가입</a></li>
            <li><a href="/member/registration">회원정보수정</a></li>
            <%if (session.getAttribute("member") == null){%>
                <li><a href="/member/loginForm">로그인</a></li>
            <%} else {%>
                 <li><a href="/member/logout">로그아웃</a></li>
            <%}%>

        </ul>
    </nav>
    <form id="searchForm" class="form-inline my-2 my-lg-0 ml-auto pr-5" action="/board/list">
        <% SearchKeyword searchKeyword;
            if (request.getAttribute("searchKeyword") == null){
                searchKeyword = new SearchKeyword("", "title", "all", "created_at");
            }else
                searchKeyword = (SearchKeyword) request.getAttribute("searchKeyword");

            Pagination pagination;
            if (request.getAttribute("pagination") == null){
                pagination = new Pagination(1, "10");
            }else pagination = (Pagination) request.getAttribute("pagination");%>
        <select name="period">
            <option value="all" <%if(searchKeyword.getPeriod().equals("all")){%>selected="selected"<%}%> >전체기간</option>
            <option value="day" <%if(searchKeyword.getPeriod().equals("day")){%>selected="selected"<%}%> >1일</option>
            <option value="week" <%if(searchKeyword.getPeriod().equals("week")){%>selected="selected"<%}%> >1주</option>
            <option value="month" <%if(searchKeyword.getPeriod().equals("month")){%>selected="selected"<%}%> >1개월</option>
            <option value="six-month" <%if(searchKeyword.getPeriod().equals("six-month")){%>selected="selected"<%}%> >6개월</option>
            <option value="year" <%if(searchKeyword.getPeriod().equals("year")){%>selected="selected"<%}%> >1년</option>
        </select>
        <select name="type">
            <option value="title" <%if(searchKeyword.getType().equals("title")){%>selected="selected"<%}%>>제목</option>
            <option value="writer" <%if(searchKeyword.getType().equals("writer")){%>selected="selected"<%}%>>작성자</option>
        </select>
        &nbsp;
        <input name="keyword" class="form-control me-2" type="search" placeholder="Search" aria-label="Search" value="${searchKeyword.getKeyword()}">
        <input type="hidden" name="sort" value= "<%=searchKeyword.getSort()%>">
        <input type="hidden" name="pageSort" value= "<%=pagination.getMaxRecordsPerPage()%>">
        <button class="btn btn-outline-success" type="submit">Search</button>
    </form>
</header>
</body>
</html>
