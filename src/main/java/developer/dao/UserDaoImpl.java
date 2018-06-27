package developer.dao;

import developer.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao {

    private final Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User getById(Long id) {

        String query = "SELECT ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, TOKEN " +
                "FROM USERS WHERE ID = ?; ";

        ResultSet resultSet;
        User user = null;

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if(resultSet.first()) {
                user = new User(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getByEmail(String email) {
        return newUser("EMAIL", email);
    }

    @Override
    public User getByToken(String token) {
        return newUser("TOKEN", token);
    }

    @Override
    public void createUser(User user) {

        String query = "INSERT INTO USERS (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, TOKEN) " +
                "VALUES (?, ?, ?, ?, ?); ";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getToken());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        String query = "UPDATE USERS SET FIRST_NAME = ? LAST_NAME = ? EMAIL = ? PASSWORD = ? TOKEN = ? WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getToken());
            statement.setLong(6, user.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private User newUser(String field, String attribute) {

        String query = "SELECT ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, TOKEN " +
                "FROM USERS WHERE " + field + " = ?";

        ResultSet resultSet;
        User user = null;

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, attribute);
            resultSet = statement.executeQuery();
            if(resultSet.first()) {
                user = new User(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
