package developer.servlets;

import developer.Request;
import developer.controllers.Controller;
import developer.controllers.GetAllCategoriesController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class MainServlet extends HttpServlet {
    private static Map<Request, Controller> controllerMap = new HashMap<>();

    @Override
    public void init() throws ServletException {
        controllerMap.put(new Request("GET", "/servlet/categories"), new GetAllCategoriesController());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Request request1 = new Request(request.getMethod(), request.getRequestURI());
        controllerMap.getOrDefault(request1, this::process404)
                .process(request, response);


        /*
        if(request.getMethod().equals("GET")) {
            if (request.getRequestURI().equals("/servlet/home")) {
                request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
            } else if (request.getRequestURI().equals("/servlet/params")) {
                processParams(request, response);
            } else if (request.getRequestURI().equals("/servlet/login")) {
                request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/WEB-INF/views/404.jsp").forward(request, response);
            }
        } else if (request.getMethod().equals("POST")) {
            if (request.getRequestURI().equals("/servlet/login")) {
                processLogin(request, response);
            }
        } else {
            request.getRequestDispatcher("/WEB-INF/views/404.jsp").forward(request, response);
        }
        */
    }

    private void process404(HttpServletRequest request, HttpServletResponse response) {

        try {
            request.getRequestDispatcher("/WEB-INF/views/404.jsp");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {

        }

    }

    private void processLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("username", request.getParameter("username"));
        request.setAttribute("password", request.getParameter("password"));

        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }


    private void processParams(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        final Map<String, String[]> params = request.getParameterMap();
        String resp = params.entrySet().stream()
                .flatMap(e -> Stream.of(e.getValue()))
                .reduce("params", (v1, v2) -> v1 + " " + v2);
        request.setAttribute("parameters", resp);
        request.getRequestDispatcher("/WEB-INF/views/params.jsp").forward(request, response);
    }
}
