package com.itrex.dollarprice;

public class PricesModel {
    String buy;
    String name;
    String sell;

    public PricesModel() {
    }

    public PricesModel (String buy, String name, String sell) {
        this.buy = buy;
        this.name = name;
        this.sell = sell;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }
}
