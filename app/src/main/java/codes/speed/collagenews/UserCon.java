package codes.speed.collagenews;

public class UserCon {
    private String title;
    private String content;
    private String catagary;
    private String collage;
    private String url;
    private String auther;

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCatagary() {
        return catagary;
    }

    public void setCatagary(String catagary) {
        this.catagary = catagary;
    }

    public String getCollage() {
        return collage;
    }

    public void setCollage(String collage) {
        this.collage = collage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UserCon(String title, String content, String catagary, String collage, String url, String auther) {
        this.title = title;
        this.content = content;
        this.catagary = catagary;
        this.collage = collage;
        this.url = url;
        this.auther = auther;
    }

    public UserCon() {
    }
}
