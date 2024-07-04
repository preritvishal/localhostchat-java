package localhostchat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "chat", urlPatterns = { "/chat" })
public class ChatController extends HttpServlet {

	private static final long serialVersionUID = 8746327621138184534L;

	private List<String> messageStore = new ArrayList<>();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userMessage = req.getParameter("message");

		if (userMessage == null || userMessage == "") {
			return;
		}

		switch (userMessage) {
		
		case "/clear", "/cls", "/clean" -> {
			messageStore.clear();
		}
		
		case "/last" -> {
			messageStore.remove(messageStore.size() - 1);
		}
		
		case "/testing", "/dummy" -> {
			messageStore.clear();
			for (int i = 0; i < 13; ++i)
				messageStore.add("{\n\t\"dummy data\": \"Person" + (i + 1) + "\"\n}");
		}
		
		default -> {

			messageStore.add(userMessage);

			if (messageStore.size() > 25) {
				messageStore = messageStore.subList(1, messageStore.size());
			}
		}
		}

		resp.sendRedirect("./");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println(toJson(messageStore));
//		resp.getWriter().println(toJson(messageStore));
		resp.getWriter().println("[\"{\\n\\tdummy data: Person1\\n}\"]");
	}

	private String toJson(List<String> messages) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(messages);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "some error occured!";
	}
}
