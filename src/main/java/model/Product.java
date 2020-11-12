package model;

public class Product {
    private Integer productId;
    private String productName;
    private int price;
    private int quantity;
    private String color;
    private String desc;
    private Category category;

    public Product() {
    }

    public Product(String productName, int price, int quantity, String color, String desc, Category category) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.color = color;
        this.desc = desc;
        this.category = category;
    }

    public Product(Integer productId, String productName, int price, int quantity, String color, String desc, Category category) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.color = color;
        this.desc = desc;
        this.category = category;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", color='" + color + '\'' +
                ", desc='" + desc + '\'' +
                ", category=" + category +
                '}';
    }
}
