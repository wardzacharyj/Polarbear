package origamiduck.com.polarbear.Model;


public class CurrentCard {
    private String ID;
    private String title;
    private String subTitle;
    private String caption;
    private String imageURL;

    public CurrentCard(String ID,String title, String subTitle, String caption, String imageURL) {
        this.ID = ID;
        this.title = title;
        this.subTitle = subTitle;
        this.caption = caption;
        this.imageURL = imageURL;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
