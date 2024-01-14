package de.htw.wtd.logic;

public class PlaceImpl implements IPlace{

    private String name;
    private String address;
    private float longitude;
    private float latitude;
    private Category category;

    public PlaceImpl(String name, String address, float longitude, float latitude, Category category){
        this.name = name;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.category = category;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public float getLongitude() {
        return longitude;
    }

    @Override
    public float getLatitude() {
        return latitude;
    }

    @Override
    public Category getCategory() {
        return category;
    }
}
