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
        PrintWriter out = response.getWriter();
        out.println("<h1>요청을 잘 응답 받았습니다</h1>");

        // URL을 파싱해서 어떤 요청인지 파악
        out.println(request.getRequestURI());

        String requestURI = request.getRequestURI();    // /board/list
        String contextPath = request.getContextPath();  // /
        String command = requestURI.substring(contextPath.length());    // /board/list
        String view = "/view/board/";

        if(command.equals("/board/list")){
            ArrayList<Board> boards = boardService.getBoards(); // 게시판 리스트
            request.setAttribute("boards", boards);

            view += "list.jsp";

        } else if (command.equals("/board/createForm")) {
            view += "createForm.jsp";

        } else if (command.contains("/board/create")) {
            String title = request.getParameter("title");
            String writer = request.getParameter("writer");
            String content = request.getParameter("content");

            boardService.addBoard(new Board(null, title, content, writer, LocalDateTime.now(), 0, 0));
            view = "/board/list";

        } else if (command.equals("/board/updateForm")) {
            Long id = Long.parseLong(request.getParameter("id"));
            Board board = boardService.getBoard(id);
            request.setAttribute("board", board);

            view += "updateForm.jsp";

        } else if (command.contains("/board/update")) {
            Long id = Long.parseLong(request.getParameter("id"));
            String title = request.getParameter("title");
            String writer = request.getParameter("writer");
            String content = request.getParameter("content");

            boardService.updateBoard(new Board(id, title, content, writer, LocalDateTime.now(), 0, 0));
            view = "/board/list";

        } else if (command.equals("/board/delete")) {
            Long id = Long.parseLong(request.getParameter("id"));
            Board board = boardService.getBoard(id);
            boardService.deleteBoard(board);

            view = "/board/list";

        }else if (command.contains("/board/detail")){
            Long id = Long.parseLong(request.getParameter("id"));
            Board board = boardService.getBoard(id);
            request.setAttribute("board", board);

            view += "detail.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
}
