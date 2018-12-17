package com.uvaysss.store.data.uimodel;

public class ProductUiModel {

    private long id;

    private final String title;

    private final double price;

    private String priceFormatted;

    private final long count;

    private String countFormatted;

    public ProductUiModel(
            long id,
            String title,
            double price,
            String priceFormatted,
            long count,
            String countFormatted
    ) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.priceFormatted = priceFormatted;
        this.count = count;
        this.countFormatted = countFormatted;
    }

    public ProductUiModel(long id, String title, double price, long count) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.count = count;
    }

    public ProductUiModel(String title, double price, long count) {
        this.title = title;
        this.price = price;
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String getPriceFormatted() {
        return priceFormatted;
    }

    public long getCount() {
        return count;
    }

    public String getCountFormatted() {
        return countFormatted;
    }
}
