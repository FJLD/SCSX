// 导入必需的 java 库
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

// 扩展 HttpServlet 类
public class MyServlet extends HttpServlet {

  public void init() throws ServletException  {
  }

  @Override
  protected void doPost(HttpServletRequest request, 
		HttpServletResponse response) 
				throws ServletException, IOException 
  {
	// 设置响应内容类型
      response.setContentType("text/html");

      // 实际的逻辑是在这里
      String username = request.getParameter("username");
      String password = request.getParameter("password");
      String userType = request.getParameter("user");
      if (username.equals("us") && password.equals("us") && userType.equals("ordinary")) {
          response.setStatus(response.SC_MOVED_TEMPORARILY);
          response.setHeader("Location", "ordinary_user/index.jsp");  
      } else if (username.equals("admin") && password.equals("admin") && userType.equals("admin")){
    	  response.setStatus(response.SC_MOVED_TEMPORARILY);
          response.setHeader("Location", "admin/index.jsp");  
      } else {
    	  response.getWriter().write("notFound");
      }
  }

public void destroy()  {
      // 什么也不做 
  }
}