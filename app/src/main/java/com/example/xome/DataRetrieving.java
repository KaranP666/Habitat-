package com.example.xome;

public class DataRetrieving {
    String addressofprop,bhks,cityofprop,pincodeofprop,priceofprop,propname,stateofprop,type;

    public DataRetrieving(String addressofprop, String bhks, String cityofprop, String pincodeofprop, String priceofprop, String propname, String stateofprop, String type) {
        this.addressofprop = addressofprop;
        this.bhks = bhks;
        this.cityofprop = cityofprop;
        this.pincodeofprop = pincodeofprop;
        this.priceofprop = priceofprop;
        this.propname = propname;
        this.stateofprop = stateofprop;
        this.type = type;
    }

    public DataRetrieving() {

    }

    public String getAddressofprop() {
        return addressofprop;
    }

    public void setAddressofprop(String addressofprop) {
        this.addressofprop = addressofprop;
    }

    public String getBhks() {
        return bhks;
    }

    public void setBhks(String bhks) {
        this.bhks = bhks;
    }

    public String getCityofprop() {
        return cityofprop;
    }

    public void setCityofprop(String cityofprop) {
        this.cityofprop = cityofprop;
    }

    public String getPincodeofprop() {
        return pincodeofprop;
    }

    public void setPincodeofprop(String pincodeofprop) {
        this.pincodeofprop = pincodeofprop;
    }

    public String getPriceofprop() {
        return priceofprop;
    }

    public void setPriceofprop(String priceofprop) {
        this.priceofprop = priceofprop;
    }

    public String getPropname() {
        return propname;
    }

    public void setPropname(String propname) {
        this.propname = propname;
    }

    public String getStateofprop() {
        return stateofprop;
    }

    public void setStateofprop(String stateofprop) {
        this.stateofprop = stateofprop;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
