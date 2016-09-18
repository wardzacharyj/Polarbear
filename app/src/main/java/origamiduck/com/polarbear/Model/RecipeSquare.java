package origamiduck.com.polarbear.Model;

public class RecipeSquare {
    private String imageURL;
    private String title;
    private String subtitle;

    public RecipeSquare(String imageURL, String title, String subtitle) {
        this.imageURL = imageURL;
        this.title = title;
        this.subtitle = subtitle;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
