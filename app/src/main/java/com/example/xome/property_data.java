package com.example.xome;

public class property_data {
    String Ownerofprop,Propname,Priceofprop,BHKs,Type,Addressofprop,Pincodeofprop,cityofprop,stateofprop;

    public property_data() {
    }

    public property_data(String ownerofprop, String propname, String priceofprop, String BHKs, String type, String addressofprop, String pincodeofprop, String cityofprop, String stateofprop) {
        this.Ownerofprop = ownerofprop;
        this.Propname = propname;
        this.Priceofprop = priceofprop;
        this.BHKs = BHKs;
        this.Type = type;
        this.Addressofprop = addressofprop;
        this.Pincodeofprop = pincodeofprop;
        this.cityofprop = cityofprop;
        this.stateofprop = stateofprop;
    }

    public String getOwnerofprop() {
        return Ownerofprop;
    }

    public void setOwnerofprop(String ownerofprop) {
        Ownerofprop = ownerofprop;
    }

    public String getPropname() {
        return Propname;
    }

    public void setPropname(String propname) {
        Propname = propname;
    }

    public String getPriceofprop() {
        return Priceofprop;
    }

    public void setPriceofprop(String priceofprop) {
        Priceofprop = priceofprop;
    }

    public String getBHKs() {
        return BHKs;
    }

    public void setBHKs(String BHKs) {
        this.BHKs = BHKs;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getAddressofprop() {
        return Addressofprop;
    }

    public void setAddressofprop(String addressofprop) {
        Addressofprop = addressofprop;
    }

    public String getPincodeofprop() {
        return Pincodeofprop;
    }

    public void setPincodeofprop(String pincodeofprop) {
        Pincodeofprop = pincodeofprop;
    }

    public String getCityofprop() {
        return cityofprop;
    }

    public void setCityofprop(String cityofprop) {
        this.cityofprop = cityofprop;
    }

    public String getStateofprop() {
        return stateofprop;
    }

    public void setStateofprop(String stateofprop) {
        this.stateofprop = stateofprop;
    }
}
