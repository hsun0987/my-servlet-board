package com.kitri.myservletboard.controller;

import com.kitri.myservletboard.data.Member;
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

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
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
        String view = "/view/member/";

        String name = request.getParameter("name");
        String id = request.getParameter("userId");
        String pw = request.getParameter("pw");
        String email = request.getParameter("email");
        Member member;

        if(command.contains("member/joinForm")){
            view += "join.jsp";

        }else if(command.contains("member/join")){
            member = new Member(name, id, pw, email);
            memberService.addMember(member);
            view = "/board/list";

        } else if (command.contains("member/loginForm")) {
            view += "login.jsp";

        } else if (command.contains("member/login")) {
            member = new Member(id, pw);
            boolean isLoginFailed = memberService.isLogined(member);

            if (isLoginFailed) {
                request.setAttribute("loginFailed", isLoginFailed);
                view += "login.jsp";

            } else {
                HttpSession session = request.getSession();
                session.setAttribute("member", member);
                view = "/board/list";
            }
        }else if(command.contains("member/logout")){
            request.getSession().invalidate();
            view = "/board/list";

        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }

}