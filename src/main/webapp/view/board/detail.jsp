<%@ page import="com.kitri.myservletboard.data.Board" %>
<%@ page import="com.kitri.myservletboard.data.Member" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.kitri.myservletboard.data.Comment" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<jsp:include page="/view/common/head.jsp">
    <jsp:param name="title" value="상세조회"/>
</jsp:include>
<body class="sb-nav-fixed">
<jsp:include page="/view/common/header.jsp"/>

<main class="mt-5 pt-5">
    <div class="container-fluid px-4 ">
        <div class="card mb-4 w-50 mx-auto">
            <div>
                <h2 class="mt-3" style="text-align: center;"><b>게시판 상세</b></h2>
                <hr class="mb-0">
            </div>
            <div class="d-flex flex-column" style="height: 500px;">
                <div class="p-2 border-bottom border-dark-subtle d-flex flex-row-reverse">
                    <div class="pd opacity-75 bg-info-subtle border border-dark rounded-pill"><small>조회수 : ${board.getViewCount()}</small></div>
                    &nbsp
                    <div class="pd opacity-75 bg-success-subtle border border-dark rounded-pill"><small>댓글수 : ${board.getComCount()}</small></div>
                    <div class="d-flex flex-row flex-fill">
                        <div class="pd opacity-75 border border-dark rounded-pill">#${board.getId()}</div>
                    </div>
                </div>
                <div class="p-2 border-bottom">
                    <b>${board.getTitle()}</b>
                </div>
                <div class="p-2 border-bottom">
                    <b>저자 :</b> ${board.getWriter()}
                </div>
                <div class="p-2 border-bottom">
                    <b>등록일시 :</b> ${board.getCreatedAt()}
                </div>
                <div class="m-3 h-75">
                    <textarea class="h-100 form-control bg-white" id="content" name="content"
                              disabled>${board.getContent()}</textarea>
                </div>

                <div class="d-flex flex-row-reverse mb-3 mr-3">
                    <%  Board board = (Board) request.getAttribute("board");
                        Member member = (Member) session.getAttribute("member");
                    %>
                    &nbsp
                    &nbsp
                    <%if (session.getAttribute("member") != null && board.getMemberId().equals(member.getId())) {%>
                        <a href="/board/delete?id=${board.getId()}" class="btn btn-secondary btn-sm" onclick="return confirm('삭제하시겠습니까?')"><small>삭제하기</small></a>
                        &nbsp
                        <a href="/board/updateForm?id=${board.getId()}" class="btn btn-secondary btn-sm"><small>수정하기</small></a>
                    <%}%>
                    &nbsp
                    <a href="/board/list" class="btn btn-secondary btn-sm"><small>목록으로</small></a>
                    &nbsp
                </div>
            </div>
        </div>
        <form action="/comment/create" method="post" class="validation-form" novalidate>
            <div class="card mb-4 w-50 mx-auto">
                <div class="d-flex flex-column" style="height: auto;">
                    <b class="mt-3" style="text-align: center;"><b>댓글</b></b>
                    <div class="p-2 border-primary mb-3 text-center">
                        <table class="table align-middle table-hover">
                            <%if (session.getAttribute("member") != null){%>
                                <tbody class="table-group-divider">
                                 <% ArrayList<Comment> comments = board.getComments();
                                    for (int i = 0; i < comments.size(); i++) { %>
                                 <tr>
                                     <td><%=comments.get(i).getContent()%></td>
                                     <td><%=comments.get(i).getName()%></td>
                                     <td><%=comments.get(i).getCreatedAt().format(DateTimeFormatter.ofPattern("YYYY/MM/dd - HH:mm"))%></td>
                                     <th scope="row">
                                     <td><%if (session.getAttribute("member") != null && comments.get(i).getMemberId().equals(member.getId())) {%>
                                         <a href="/comment/delete?commentId=<%= comments.get(i).getId() %>&id=${board.getId()}" class="btn btn-secondary btn-sm" onclick="return confirm('삭제하시겠습니까?')"><small>삭제하기</small></a>
                                     <%}%>
                                     </td>
                                 </tr>
                                 <%}%>
                                </tbody>
                            <%} else {%>
                                <a href="/member/loginForm" >로그인</a>하고 댓글보기
                            <%}%>
                        </table>
                    </div>
                </div>
            </div>


            <div class="card mb-4 w-50 mx-auto">
                <input type="text" name="id" value="${board.getId()}" hidden>
                <input type="text" name="memberId" value="${member.getId()}}" hidden>
                <div class="d-flex flex-column" style="height: auto;">
                    <div class="m-3 h-75">
                        <textarea class="h-100 form-control bg-white" id="comment" name="comment" placeholder="댓글을 남겨보세요."></textarea>
                    </div>
                    <div class="d-flex flex-row-reverse mb-3 mr-3">
                        &nbsp
                        <input type="submit" class="btn btn-secondary btn-sm" value="등록">
                    </div>
                </div>
            </div>
        </form>
    </div>
</main>
</body>
<style>
    .pd {
        padding-left: 5px;
        padding-right: 5px;
    }
</style>
</html>