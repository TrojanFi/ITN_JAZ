package pl.edu.pjwstk.jaz.Requests;

public class AuctionRequest {

    private String category;
    private String description;
    private String title;
    private int price;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public AuctionRequest(String description, String title, int price, String category) {
        this.description = description;
        this.title = title;
        this.price = price;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
