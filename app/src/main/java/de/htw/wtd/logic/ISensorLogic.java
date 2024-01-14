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
}
