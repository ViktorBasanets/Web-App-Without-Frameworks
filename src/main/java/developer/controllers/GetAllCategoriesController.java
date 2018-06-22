package developer.controllers;

import developer.services.CategoriService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllCategoriesController implements Controller {
    private CategoriService categoriService = new CategoriServiceImpl();
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("categories", categoriService.getAll());
        try {
            request.getRequestDispatcher("/WEB-INF/views/categories.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
