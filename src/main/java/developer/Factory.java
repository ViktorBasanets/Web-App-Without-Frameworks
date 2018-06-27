package developer;

import developer.controllers.GetAllCategoriesController;
import developer.controllers.GetCategoryByIdController;
import developer.controllers.LoginController;
import developer.controllers.SignUpController;
import developer.dao.CategoryDao;
import developer.dao.CategoryDaoImpl;
import developer.dao.UserDao;
import developer.dao.UserDaoImpl;
import developer.services.CategoryService;
import developer.services.CategoryServiceImpl;
import developer.services.UserService;
import developer.services.UserServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;

public class Factory {

    private static Connection connection;


    static {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static GetCategoryByIdController getCategoryByIdController() {
        return new GetCategoryByIdController(getCategoryService());
    }

    public static GetAllCategoriesController getAllCategoriesController() {
        return new GetAllCategoriesController(getCategoryService());
    }

    public static UserDao getUserDaoImpl() {
        return new UserDaoImpl(connection);
    }

    public static SignUpController getSignUpController() {
        return new SignUpController(getUserService());
    }

    public static UserService getUserService() {
        return new UserServiceImpl(getUserDaoImpl());
    }

    public static LoginController getLoginController() {
        return new LoginController(getUserService());
    }

    private static CategoryService getCategoryService() {
        return new CategoryServiceImpl(getCategoryDaoImpl());
    }

    private static CategoryDao getCategoryDaoImpl() {
        return new CategoryDaoImpl(connection);
    }
}
