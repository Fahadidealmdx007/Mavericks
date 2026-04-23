package com.mavericks.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    private String id;

    private String name;
    private String category;
    private String collectionName;

    @Column(length = 1200)
    private String description;

    @Column(columnDefinition = "DOUBLE DEFAULT 0")
    private Double price;

    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer stock;

    private String colors;
    private String sizes;

    @Column(length = 3000)
    private String images;

    private String quote;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean featured;

    @Column(name = "new_drop", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean newDrop;

    // F1-specific fields
    @Column(columnDefinition = "VARCHAR(255) DEFAULT ''")
    private String team;

    @Column(columnDefinition = "VARCHAR(255) DEFAULT ''")
    private String driver;

    @Column(columnDefinition = "INTEGER DEFAULT 2026")
    private Integer season;

    @Column(columnDefinition = "VARCHAR(255) DEFAULT 'fan'")
    private String edition;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean limited;

    @Column(columnDefinition = "DOUBLE DEFAULT 0")
    private Double discount;

    @Column(length = 500, columnDefinition = "VARCHAR(500) DEFAULT ''")
    private String tags;

    public Product() {}

    public Product(String id, String name, String category, String collectionName,
                   String description, Double price, Integer stock, String colors, String sizes,
                   String images, String quote, Boolean featured, Boolean newDrop,
                   String team, String driver, Integer season, String edition,
                   Boolean limited, Double discount, String tags) {
        this.id = id; this.name = name; this.category = category;
        this.collectionName = collectionName; this.description = description;
        this.price = price; this.stock = stock; this.colors = colors;
        this.sizes = sizes; this.images = images; this.quote = quote;
        this.featured = featured; this.newDrop = newDrop;
        this.team = team; this.driver = driver; this.season = season;
        this.edition = edition; this.limited = limited;
        this.discount = discount; this.tags = tags;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String id, name, category, collectionName, description;
        private String colors, sizes, images, quote;
        private String team, driver, edition, tags;
        private Double price = 0.0, discount = 0.0;
        private Integer stock = 0, season = 2026;
        private Boolean featured = false, newDrop = false, limited = false;

        public Builder id(String v)             { this.id = v; return this; }
        public Builder name(String v)           { this.name = v; return this; }
        public Builder category(String v)       { this.category = v; return this; }
        public Builder collectionName(String v) { this.collectionName = v; return this; }
        public Builder description(String v)    { this.description = v; return this; }
        public Builder price(double v)          { this.price = v; return this; }
        public Builder stock(int v)             { this.stock = v; return this; }
        public Builder colors(String v)         { this.colors = v; return this; }
        public Builder sizes(String v)          { this.sizes = v; return this; }
        public Builder images(String v)         { this.images = v; return this; }
        public Builder quote(String v)          { this.quote = v; return this; }
        public Builder featured(boolean v)      { this.featured = v; return this; }
        public Builder newDrop(boolean v)       { this.newDrop = v; return this; }
        public Builder team(String v)           { this.team = v; return this; }
        public Builder driver(String v)         { this.driver = v; return this; }
        public Builder season(int v)            { this.season = v; return this; }
        public Builder edition(String v)        { this.edition = v; return this; }
        public Builder limited(boolean v)       { this.limited = v; return this; }
        public Builder discount(double v)       { this.discount = v; return this; }
        public Builder tags(String v)           { this.tags = v; return this; }

        public Product build() {
            return new Product(id, name, category, collectionName, description,
                price, stock, colors, sizes, images, quote,
                featured != null ? featured : false,
                newDrop  != null ? newDrop  : false,
                team    != null ? team    : "",
                driver  != null ? driver  : "",
                (season != null && season > 0) ? season : 2026,
                edition != null ? edition : "fan",
                limited  != null ? limited  : false,
                discount != null ? discount : 0.0,
                tags    != null ? tags    : "");
        }
    }

    public String getId()                      { return id; }
    public void setId(String id)               { this.id = id; }
    public String getName()                    { return name; }
    public void setName(String v)              { this.name = v; }
    public String getCategory()                { return category; }
    public void setCategory(String v)          { this.category = v; }
    public String getCollectionName()          { return collectionName; }
    public void setCollectionName(String v)    { this.collectionName = v; }
    public String getDescription()             { return description; }
    public void setDescription(String v)       { this.description = v; }
    public Double getPrice()                   { return price != null ? price : 0.0; }
    public void setPrice(Double v)             { this.price = v; }
    public Integer getStock()                  { return stock != null ? stock : 0; }
    public void setStock(Integer v)            { this.stock = v; }
    public String getColors()                  { return colors; }
    public void setColors(String v)            { this.colors = v; }
    public String getSizes()                   { return sizes; }
    public void setSizes(String v)             { this.sizes = v; }
    public String getImages()                  { return images; }
    public void setImages(String v)            { this.images = v; }
    public String getQuote()                   { return quote; }
    public void setQuote(String v)             { this.quote = v; }
    public Boolean isFeatured()                { return featured != null && featured; }
    public void setFeatured(Boolean v)         { this.featured = v; }
    public Boolean isNewDrop()                 { return newDrop != null && newDrop; }
    public void setNewDrop(Boolean v)          { this.newDrop = v; }
    public String getTeam()                    { return team != null ? team : ""; }
    public void setTeam(String v)              { this.team = v; }
    public String getDriver()                  { return driver != null ? driver : ""; }
    public void setDriver(String v)            { this.driver = v; }
    public Integer getSeason()                 { return season != null ? season : 2026; }
    public void setSeason(Integer v)           { this.season = v; }
    public String getEdition()                 { return edition != null ? edition : "fan"; }
    public void setEdition(String v)           { this.edition = v; }
    public Boolean isLimited()                 { return limited != null && limited; }
    public void setLimited(Boolean v)          { this.limited = v; }
    public Double getDiscount()                { return discount != null ? discount : 0.0; }
    public void setDiscount(Double v)          { this.discount = v; }
    public String getTags()                    { return tags != null ? tags : ""; }
    public void setTags(String v)              { this.tags = v; }
}
