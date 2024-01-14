package de.htw.wtd.logic;

public interface IPlace {

    /**
     * Gibt den Namen wieder
     * @return Name
     */
    String getName();

    /**
     * Gibt die Adresse wieder
     */
    String getAddress();

    /**
     * Gibt die Longitude wieder
     */
    float getLongitude();

    /**
     * Gibt die Latitude wieder
     */
    float getLatitude();

    /**
     * Gibt die Kategorie wieder
     */
    Category getCategory();


}
