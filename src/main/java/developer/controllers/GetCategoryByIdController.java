package developer.controllers;

import developer.services.CategoryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetCategoryByIdController implements Controller {

    private CategoryService categoryService;

    public GetCategoryByIdController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) {

        String attribute = "category";
        Long id = Long.parseLong(req.getParameter("c_id"));

        req.setAttribute(attribute, categoryService.getById(id));
        try {
            req.getRequestDispatcher("/WEB-INF/views/category.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
