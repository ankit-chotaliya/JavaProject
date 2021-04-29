public class Room {
    public String id,bookby,price,isac,isfood,days;

    public Room(String id, String bookby, String price, String isac, String isfood, String days) {
        this.id = id;
        this.bookby = bookby;
        this.price = price;
        this.isac = isac;
        this.isfood = isfood;
        this.days = days;
    }
    public Room() {

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getBookby() {
        return bookby;
    }

    public String getPrice() {
        return price;
    }

    public String getIsac() {
        return isac;
    }

    public String getIsfood() {
        return isfood;
    }

    public String getDays() {
        return days;
    }

    public void setBookby(String bookby) {
        this.bookby = bookby;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setIsac(String isac) {
        this.isac = isac;
    }

    public void setIsfood(String isfood) {
        this.isfood = isfood;
    }

    public void setDays(String days) {
        this.days = days;
    }
}
