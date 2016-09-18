package origamiduck.com.polarbear.MyDatabase;

import java.util.UUID;

/**
 * Created by Zach on 9/14/16.
 */
public class Food {

    private String ID;
    private String barcode;
    private String name;
    private String type;
    private String brand;
    private String netWeight;
    private String entryDate;
    private String expirationDate;
    private String imageURL;
    private int skipFlag;

    public static final int SKIP_FALSE = 0;
    public static final int SKIP_TRUE = 1;



    @Override
    public String toString() {
        return "Food{" +
                "barcode='" + barcode + '\'' +
                ", ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", netWeight='" + netWeight + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }



    public Food(){

    }

    public Food(String ID, String barcode, String name, String type, String brand,
                String netWeight, String entryDate, String expirationDate, String imageURL, int skipFlag) {

        this.ID = ID;
        this.barcode = barcode;
        this.name = name;
        this.type = type;
        this.brand = brand;
        this.netWeight = netWeight;
        this.entryDate = entryDate;
        this.expirationDate = expirationDate;
        this.imageURL = imageURL;
        this.skipFlag = skipFlag;

    }

    public int getSkipFlag() {
        return skipFlag;
    }

    public void setSkipFlag(int skipFlag) {
        this.skipFlag = skipFlag;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(String netWeight) {
        this.netWeight = netWeight;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

}
