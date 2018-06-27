package developer.dao;

import developer.model.Category;
import developer.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    private static Connection connection;

    public CategoryDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Category> getAll() {

        List<Category> categories = new ArrayList<>();
        String query = "SELECT ID, CATEGORY_NAME, DESCRIPTION FROM CATEGORIES";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                categories.add(new Category(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        null)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    @Override
    public Category getById(Long id) {

        List<Product> products = new ArrayList<>();
        Category category = null;
        String query = "SELECT C.ID, C.CATEGORY_NAME, C.DESCRIPTION, P.ID, P.NAME, P.PRICE" +
                "FROM CATEGORIES C " +
                "JOIN PRODUCTS P ON C.ID=P.FK_CATEGORY_ID" +
                "WHERE C.ID=?";

        PreparedStatement statement;
        ResultSet resultSet;

        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            boolean hasNext = resultSet.next();
            if (hasNext) {
                category = new Category(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        products);
            }

            while (hasNext) {
                products.add(new Product(
                        resultSet.getLong(4),
                        resultSet.getString(5),
                        resultSet.getDouble(6),
                        category));

                hasNext = resultSet.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return category;
    }
}
