package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.user.dao.UserDAO;
import jdbc.user.vo.UserVO;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("*.do")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 객체 생성위해 변수 선언
	private UserDAO userDao;
	
	// 포워딩 위하여 변수 선언
	private RequestDispatcher rd;
	
// 서블릿 라이프사이클
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		// 최초 한번 실행
		
		System.out.println("UserServlet init() method called!");
		
		// 객체 생성은 한번만 하면 되니까 init 에서 한번 실행
		userDao = new UserDAO();
	}
       
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("UserServlet destroy() method called!");
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("UserServlet doGet() method called!");
//		response.getWriter().append("User Served at: ").append(request.getContextPath());
		
		// 분기 처리
		String cmd = request.getParameter("cmd"); // jsp에 지정한 키 값 이랑 같게 지정
		System.out.println(">>> cmd : " + cmd);
		if(cmd.equals("userList")) {
			userList(request, response);
		} else if(cmd.equals("userDetail")) {
			userDetail(request, response);
		}
	}

	private void userList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. DAO의 메소드 호출하여 데이터 객체 받기
		List<UserVO> users = userDao.getUsers();
		System.out.println(users);
		
		// 2. 받아온 List 데이터 객체를 JSP에서 사용할 수 있도록 request 객체에 저장
		request.setAttribute("userList", users); // users 객체를 userList 라는 이름으로 request에 저장
		
		// 3. 결과를 출력해줄 JSP(userList.jsp)를 포워딩(해당 페이지로 전달)
		rd = request.getRequestDispatcher("userList.jsp"); // 보여줄 페이지 명 지정
		rd.forward(request, response);
	}
	
	private void userDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 파라미터 받아오기 위해
		String userid = request.getParameter("userid"); // jsp에 지정한 key 값과 동일
		System.out.println(">>> userid : " + userid);
//		// 1. DAO의 메소드 호출하여 데이터 객체 받기
//		List<UserVO> users = userDao.getUsers();
//		System.out.println(users);
//		
//		// 2. 받아온 List 데이터 객체를 JSP에서 사용할 수 있도록 request 객체에 저장
//		request.setAttribute("userList", users); // users 객체를 userList 라는 이름으로 request에 저장
//		
//		// 3. 결과를 출력해줄 JSP(userList.jsp)를 포워딩(해당 페이지로 전달)
//		rd = request.getRequestDispatcher("userList.jsp"); // 보여줄 페이지 명 지정
//		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("UserServlet doPost() method called!");
		doGet(request, response);
	}

}
