package de.htw.wtd.data;

public class LocationImpl implements ILocation{
    private double temperatur;
    private double lightLevel;

    private long time;

    private float longitude;
    private float latitude;

    @Override
    public String getOwner() {
        return owner;
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
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    @Override
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    @Override
    public void setTime(long time) {
        this.time = time;
    }


}
