<%@ page import="java.util.ArrayList" %>
<%@ page import="com.kitri.myservletboard.service.BoardService" %>
<%@ page import="com.kitri.myservletboard.data.Board" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="com.kitri.myservletboard.data.Pagination" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
  ArrayList<Board> boards = (ArrayList<Board>)request.getAttribute("boards");
%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>게시판목록</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<link href="/css/style.css" rel="stylesheet">

<body>
  <jsp:include page="/view/common/header.jsp">
    <jsp:param name="title" value="게시글 목록"/>
  </jsp:include>

  <div>
    <h2 style="text-align: center; margin-top: 100px;"><b>게시판 목록</b></h2>
  </div>
  <div class="container class=d-flex justify-content-center">
    <div class="p-2 border-primary mb-3">
      <table class="table align-middle table-hover">
        <thead class="table-dark">
          <tr>
            <th scope="col">번호</th>
            <th scope="col">제목</th>
            <th scope="col">작성자</th>
            <th scope="col">날짜</th>
            <th scope="col">조회수</th>
            <th scope="col">댓글수</th>
          </tr>
        </thead>
        <tbody class="table-group-divider">
          <% for(Board board : boards){%>
          <tr>
            <th scope="row"><%=board.getId()%></th>
            <td><a href="/board/detail?id=<%=board.getId()%>"><%=board.getTitle()%></a></td>
            <td><%=board.getWriter()%></td>
            <td><%=board.getCreatedAt().format(DateTimeFormatter.ofPattern("YYYY-MM-DD"))%></td>
            <td><%=board.getViewCount()%></td>
            <td><%=board.getComCount()%></td>
          </tr>
          <%}%>
        </tbody>
      </table>
      <div>
        <a href="/board/createForm" role="button" class="btn btn-outline-dark">글쓰기</a>
      </div>
      <div class="d-flex justify-content-center">
      <nav aria-label="Page navigation example">
        <ul class="pagination pagination-sm">
          <%
            Pagination pagination = (Pagination) request.getAttribute("pagination");
            if(pagination.isHasPrev()){
          %>
            <li class="page-item">
              <a class="page-link" href="/board/list?page=<%=pagination.getStartPage()-1%>" tabindex="-1" aria-disabled="true">Previous</a>
            </li>
          <%}else {%>
            <li class="page-item disabled">
              <a class="page-link" href="/board/list?page=<%=pagination.getStartPage()-1%>" tabindex="-1" aria-disabled="true">Previous</a>
            </li>
          <%}%>

          <% for (int i = pagination.getStartPage(); i <= pagination.getEndPage(); i++){
            if (pagination.getPage() == i){%>
              <li class="page-item"><a class="page-link active" href="/board/list?page=<%=i%>"><%=i%></a></li>

            <%}else { %>
              <li class="page-item"><a class="page-link" href="/board/list?page=<%=i%>"><%=i%></a></li>
          <% }
          }%>

          <%
            if(pagination.isHasNext()){
          %>
              <li class="page-item">
                <a class="page-link" href="/board/list?page=<%=pagination.getEndPage()+1%>" tabindex="-1" aria-disabled="true">Next</a>
              </li>
          <%}else {%>
            <li class="page-item  disabled">
              <a class="page-link" href="/board/list?page=<%=pagination.getEndPage()+1%>" tabindex="-1" aria-disabled="true">Next</a>
            </li>
          <%}%>

        </ul>
      </nav>
    </div>
    </div>
  </div>
  </div>
  <div class="p-2">
    <div class="container d-flex justify-content-center">
      <footer>
        <span class="text-muted">&copy; Company's Bootstrap-board</span>
      </footer>
    </div>
  </div>


</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
  integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

</html>