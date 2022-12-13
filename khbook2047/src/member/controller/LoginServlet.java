package member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.json.simple.JSONObject;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.lo")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자의 정보를 JSON 형식으로 전달하기 위해 ContentType 변경
		response.setContentType("application/json;charset=UTF-8");
		MemberService mservice = new MemberService();
		
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		JSONObject job = new JSONObject();
		
		// 입력받은 사용자의 ID와 비밀번호를 인자로 하여 Service의 loginMember() 호출
		Member m = mservice.loginMember(id, passwd);
		if (m != null) {
			HttpSession session = request.getSession();
			session.setAttribute("member", m);
			job.put("result", "ok");
			job.put("name", m.getName());
		} else {
			job.put("result", "fail");
		}
		
		PrintWriter out = response.getWriter();
		out.println(job.toJSONString());
		
		out.flush();
		out.close();
	}

}
