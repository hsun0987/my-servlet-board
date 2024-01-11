<%@ page import="com.kitri.myservletboard.data.SearchKeyword" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<header class = "d-flex">
    <a class="logo" href="/board/list">
        <span class="material-symbols-outlined">
            clear_day
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
        <% SearchKeyword searchKeyword = (SearchKeyword) request.getAttribute("searchKeyword"); %>
        <select name="period">
            <option value="day" >1일</option>
            <option value="week" >1주</option>
            <option value="year" >1년</option>
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
