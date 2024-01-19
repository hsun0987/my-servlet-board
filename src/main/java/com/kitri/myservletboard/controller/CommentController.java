package com.kitri.myservletboard.controller;

import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.data.Comment;
import com.kitri.myservletboard.data.Member;
import com.kitri.myservletboard.service.BoardService;
import com.kitri.myservletboard.service.CommentService;
import com.kitri.myservletboard.service.MemberService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/comment/*")
public class CommentController extends HttpServlet {
    CommentService commentService = CommentService.getInstance();
    MemberService memberService = MemberService.getInstance();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();


        // URL을 파싱해서 어떤 요청인지 파악
        out.println(request.getRequestURI());

        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = requestURI.substring(contextPath.length());
        String view = "/view/board/";
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");

        if (command.contains("comment/create")) {
            Long boardId = Long.parseLong(request.getParameter("id"));
            Long memberId = member.getId();
            String comment = request.getParameter("comment");
            Comment comments = new Comment(boardId, memberId, comment);
            commentService.addComment(comments);

            request.setAttribute("comment", comments);

            view = "/board/detail";
        }

        if (command.contains("comment/delete")){
            Long commentId =Long.parseLong(request.getParameter("commentId"));
            commentService.deleteComment(commentId);

            view = "/board/detail";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
}
