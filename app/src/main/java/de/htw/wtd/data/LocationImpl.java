package de.htw.wtd.data;

public class LocationImpl implements ILocation{
    private long time;

    private float longitude;
    private float latitude;

    private String description;

    public LocationImpl(float longitude, float latitude, String description){
        this.longitude = longitude;
        this.latitude = latitude;
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
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
    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public long getTime() {
        return time;
    }


}
