package ex.login;

import com.kitri.myservletboard.data.Member;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/ex/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/member/login.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 로그인 처리
        String id = request.getParameter("loginId");
        String pw = request.getParameter("userPassword");


        boolean isLoginFailed = false;
        if (id.isEmpty() || pw.isEmpty()) {
            isLoginFailed = true;
        }
        if (id == null) {
            isLoginFailed = true;
        } else {
            if (!pw.equals(pw)) {
                isLoginFailed = true;
            }
        }

        if (isLoginFailed) {
            request.setAttribute("loginFailed", isLoginFailed);
        } else {
            HttpSession session = request.getSession();
            // session.setAttribute("member", member);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/member/login.jsp");
        dispatcher.forward(request, response);
    }
}