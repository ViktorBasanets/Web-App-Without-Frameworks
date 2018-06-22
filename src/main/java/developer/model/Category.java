package developer.model;

import java.util.List;

public class Category {

    private Long id;
    private String categoryName;
    private String description;
    private List<Product> products;

    public Category() {
    }

    public Category(String categoryName, String description, List<Product> products) {

        this.categoryName = categoryName;
        this.description = description;
        this.products = products;
    }

    public Category(Long id, String categoryName, String description, List<Product> products) {

        this.id = id;
        this.categoryName = categoryName;
        this.description = description;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
