package io.inovace.movie_catalog_service.models;

public class CatalogItem {
    public CatalogItem(String rating, String name, String desc) {
        this.rating = rating;
        this.name = name;
        this.desc = desc;
    }

    private String name;
    private String desc;
    private  String rating;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
