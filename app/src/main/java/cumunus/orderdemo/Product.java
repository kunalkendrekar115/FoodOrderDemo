package cumunus.orderdemo;

/**
 * Created by kunalkendrekar on 1/12/16.
 */
public class Product {

    private int id,imageid,price,selected_qty;
    private String productname,description;

    public int getId() {
        return id;
    }

    public int getSelected_qty() {
        return selected_qty;
    }

    public void setSelected_qty(int selected_qty) {
        this.selected_qty = selected_qty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        return id == product.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return productname;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
