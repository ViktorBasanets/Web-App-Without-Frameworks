package developer;

import developer.controllers.Controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class MainServlet extends HttpServlet {

    private static Map<Request, Controller> controllerMap = new HashMap<>();

    @Override
    public void init() {

        controllerMap.put(new Request("GET", "/servlet/categories"), Factory.getAllCategoriesController());
        controllerMap.put(new Request("GET", "/servlet/category"), Factory.getCategoryByIdController());
        controllerMap.put(new Request("POST", "/servlet/signup"), Factory.getSignUpController());
        controllerMap.put(new Request("GET", "/servlet/signup"), processView().apply("signup"));
        controllerMap.put(new Request("POST", "/servlet/login"), Factory.getLoginController());
        controllerMap.put(new Request("GET", "/servlet/login"), processView().apply("login"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        /*PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h1>Hello from Servlet!</h1>");ё
        writer.println("</body>");
        writer.println("</html>");*/

        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) {

        Request request = new Request(req.getMethod(), req.getRequestURI());
        controllerMap.getOrDefault(request, processView().apply("404"))
                .process(req, resp);

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

    private Function<String, Controller> processView() {

        return x -> (req, resp) -> {
            try {
                req.getRequestDispatcher(String.format("/WEB-INF/views/%s.jsp", x)).forward(req, resp);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        };

        /*return x -> {
            try {
                request.getRequestDispatcher(String.format("/WEB-INF/%s/404.jsp", x));
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        };*/
    }

    /*private void processSignUpVies(HttpServletRequest req, HttpServletResponse res) {
        try {
            req.getRequestDispatcher("/WEB-INF/views/signup/404.jsp");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
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
    }*/
}
