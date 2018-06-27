package developer.dao;

import developer.model.User;

public interface UserDao {

    void createUser(User user);

    void update(User user);

    User getById(Long id);

    User getByEmail(String email);

    User getByToken(String token);
}
