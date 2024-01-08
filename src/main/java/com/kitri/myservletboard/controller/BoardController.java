package com.kitri.myservletboard.controller;

import com.kitri.myservletboard.dao.BoardDao;
import com.kitri.myservletboard.dao.BoardMemoryDao;
import com.kitri.myservletboard.service.BoardService;
import data.Board;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
    BoardService boardService = BoardService.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<h1>요청을 잘 응답 받았습니다</h1>");


        // URL을 파싱해서 어떤 요청인지 파악
        out.println(request.getRequestURI());

        String requestURI = request.getRequestURI();    // /board/list
        String contextPath = request.getContextPath();  // /
        String command = requestURI.substring(contextPath.length());    // /board/list
        String view = "/view/board/";

        // #(페이지)를 응답하는 방법
          // 1. 리다이렉트 (내 담당이 아닐 때 넘겨주는 것)
          // 2. 포워드

        if(command.equals("/board/list")){
            // 요청 : 게시글 리스트
            // 응답 : 게시글 리스트 페이지

            ArrayList<Board> boards = boardService.getBoards(); // 게시판 리스트
            request.setAttribute("boards", boards);

            //리다이렉트
            //response.sendRedirect("/view/board/list.jsp");
            // response.addHeader("Refresh", "2; url = "+ "/view/board/list.jsp");

            //포워드
            view += "list.jsp";

        } else if (command.equals("/board/createForm")) {
            // 요청 : 게시글 등록을 위한 등록폼
            // 응답 : 등록폼 페이지
            //response.sendRedirect("/view/board/createForm.jsp");

            // 포워드
            view += "createForm.jsp";

        } else if (command.contains("/board/create")) {

            String title = request.getParameter("title");
            String writer = request.getParameter("writer");
            String content = request.getParameter("content");

            boardService.addBoard(new Board(null, title, content, writer, LocalDateTime.now(), 0, 0));
            view = "/board/list";

        } else if (command.equals("/board/updateForm")) {
            //response.sendRedirect("/view/board/updateForm.jsp");

            //포워드
            view += "updateForm.jsp";

        } else if (command.equals("/board/update")) {

            String title = request.getParameter("title");
            String writer = request.getParameter("writer");
            String content = request.getParameter("content");

            boardService.updateBoard(new Board(null, title, content, writer, LocalDateTime.now(), 0, 0));
            view += "updateForm.jsp";

        } else if (command.equals("/board/delete")) {

        } else if (command.contains("/board/detail")){
            // /board/detail?id=3

            Long id = Long.parseLong(request.getParameter("id"));
            Board board = boardService.getBoard(id);
            request.setAttribute("board", board);

            view += "detail.jsp";
        }


        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);


    }
}
