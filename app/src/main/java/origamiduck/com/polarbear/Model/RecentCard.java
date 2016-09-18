package origamiduck.com.polarbear.Model;

/**
 * Created by Zach on 9/17/16.
 */
public class RecentCard {
    private String title;
    private String subTitle;
    private int pointLabel;     // -1 red 1 green

    public RecentCard(String title, String subTitle, int pointLabel) {
        this.title = title;
        this.subTitle = subTitle;
        this.pointLabel = pointLabel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subTitle;
    }

    public void setSubtitle(String subtitle) {
        this.subTitle = subtitle;
    }

    public int getPointLabel() {
        return pointLabel;
    }

    public void setPointLabel(int pointLabel) {
        this.pointLabel = pointLabel;
    }
}
