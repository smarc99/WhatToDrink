package de.htw.wtd.data;

public interface ILocation {

    /**
     * Gibt die Beschreibung der Location wieder
     * @return Beschreibung
     */
    String getDescription();

    /**
     * Gibt die Longitude der Location wieder
     *
     * @return Longitude
     */
    float getLongitude();

    /**
     * Gibt die Latitude der Location wieder
     *
     * @return Latitude als Float
     */
    float getLatitude();

    /**
     * Setzt die Zeit
     *
     * @param time Zeit als Long
     */
    void setTime(long time);

    /**
     * Gibt die Zeit wieder, zu der die Location erstellt wurde.
     *
     * @return Zeit als Long
     */
    long getTime();
}


