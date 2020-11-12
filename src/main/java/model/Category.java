package model;

public class Category {
    private Integer categoryId;
    private String categoryName;
    private String categoryStatus;

    public Category() {
    }

    public Category(Integer categoryId, String categoryName, String categoryStatus) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryStatus = categoryStatus;
    }

    public Category(String categoryName, String categoryStatus) {
        this.categoryName = categoryName;
        this.categoryStatus = categoryStatus;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(String categoryStatus) {
        this.categoryStatus = categoryStatus;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryStatus='" + categoryStatus + '\'' +
                '}';
    }
}
