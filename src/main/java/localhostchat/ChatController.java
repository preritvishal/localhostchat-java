package localhostchat;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import localhostchat.message.MessageServiceImpl;
import localhostchat.message.MessageStore;

@WebServlet(name = "chat", urlPatterns = { "/chat" })
public class ChatController extends HttpServlet {

	private static final long serialVersionUID = 8746327621138184534L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userMessage = req.getParameter("message");

		if (userMessage != null || userMessage != "") {
			MessageServiceImpl.workWithIt(userMessage);
		}
		
		resp.sendRedirect("./");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println(MessageStore.getObject().getMessagesInString());
	}

}