package de.htw.wtd.data;

public interface ILocation {
    /**
     * Gibt die Longitude der Location wieder
     * @return Longitude
     */
    float getLongitude();
    /**
     * Gibt die Latitude der Location wieder
     * @return Latitude als Float
     */
    float getLatitude();
    /**
     * Setzt die Longitude der Location
     * @param longitude als Float
     */
    void setLongitude(float longitude);
    /**
     * Setzt die Latitude der Location
     * @param latitude als Float
     */
    void setLatitude(float latitude);

    /**
     * Gibt die Zeit wieder, zu der die Location erstellt wurde.
     * @return Zeit als Long
     */
    void setTime(long time);
    /**
     * Gibt den Besitzer des Standortes wieder.
     * @return Owner, als String
     */
    String getOwner();
}

