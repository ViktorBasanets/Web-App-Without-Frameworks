package developer.dao;

import developer.model.Category;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    static {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(
                    "",
                    "sa",
                    ""
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection connection;

    @Override
    public List<Category> getAll() {

        List<Category> result = new ArrayList<>();
        String query

        return null;
    }
}
