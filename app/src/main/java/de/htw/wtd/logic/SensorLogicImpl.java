package de.htw.wtd.logic;

public class SensorLogicImpl implements ISensorLogic{

    private double temperatur;
    private double lightLevel;

    @Override
    public void setTemperatur(double temperatur) {
        this.temperatur = temperatur;
    }

    @Override
    public void setLightLevel(double lightLevel) {
        this.lightLevel = lightLevel;
    }

    @Override
    public double getTemperatur() {
        return temperatur;
    }

    @Override
    public double getLightLevel() {
        return lightLevel;
    }

    @Override
    public Category getCategory() {
        double temp = getTemperatur();
        double light = getLightLevel();

        Category searchCategory;
        if (temp < 15) {
            if(light < 1000) {
                searchCategory = Category.RESTAURANT;
            } else {
                searchCategory = Category.COFFEESHOP;
            }
        } else {
            if(light < 1000) {
                searchCategory = Category.BAR;
            } else {
                searchCategory = Category.CREAMICESHOP;
            }
        }
        return searchCategory;
    }
}
