<%@ page import="com.kitri.myservletboard.data.SearchKeyword" %>
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
            <li><a href="/view/member/join.jsp">회원가입</a></li>
            <li><a href="/view/member/registration.jsp">회원정보수정</a></li>
            <li><a href="/view/member/login.jsp">로그인</a></li>
        </ul>
    </nav>
    <form id="searchForm" class="form-inline my-2 my-lg-0 ml-auto pr-5" action="/board/list">
        <% SearchKeyword searchKeyword = (SearchKeyword) request.getAttribute("searchKeyword");%>
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
        <button class="btn btn-outline-success" type="submit">Search</button>
    </form>
</header>
</body>
</html>
