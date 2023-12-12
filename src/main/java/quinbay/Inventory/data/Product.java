package quinbay.Inventory.data;

public class Product {
    int productId;
    String productName;
    String category;
    int availbleCount;
    double price;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAvailbleCount() {
        return availbleCount;
    }

    public void setAvailbleCount(int availbleCount) {
        this.availbleCount = availbleCount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
