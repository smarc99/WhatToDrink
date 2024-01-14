package de.htw.wtd.logic;

public class SensorLogicImpl implements ISensorLogic{

    private double temperatur;
    private double lightLevel;

    /**
     * Setzt die Außentemperatur.
     * @param temperatur
     */
    public void setTemperatur(double temperatur) {
        this.temperatur = temperatur;
    }

    /**
     * Setzt das Lichtlevel.
     * @param lightLevel
     */
    public void setLightLevel(double lightLevel) {
        this.lightLevel = lightLevel;
    }

    @Override
    public double getTemperatur() {

        double temperatur = 0;

        return temperatur;
    }

    @Override
    public double getLightLevel() {
        return 0;
    }
}
