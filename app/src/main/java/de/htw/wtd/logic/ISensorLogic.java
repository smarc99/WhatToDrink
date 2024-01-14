package de.htw.wtd.logic;

public interface ISensorLogic {
    /**
     * Liefert die Außentemperatur zurück.
     * @return Außentemperatur
     */
    double getTemperatur();

    /**
     * Liefert das Lichtlevel zurück.
     * @return Lichtlevel
     */
    double getLightLevel();

    /**
     * Setzt die Außentemperatur.
     * @param temperatur
     */
     void setTemperatur(double temperatur);

    /**
     * Setzt das Lichtlevel.
     * @param lightLevel
     */
     void setLightLevel(double lightLevel);

    /**
     * Ermittelt die Kategorie anhand der Sensordaten.
     * @return Kategorie
     */
    Category getCategory();
}
