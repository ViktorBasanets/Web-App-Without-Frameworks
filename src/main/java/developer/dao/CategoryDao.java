package developer.dao;

import developer.model.Category;

import java.util.List;

public interface CategoryDao {

    List<Category> getAll();

    Category getById(Long id);
}
