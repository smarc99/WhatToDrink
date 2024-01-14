package de.htw.wtd.logic;

import org.junit.Before;
import org.junit.Test;

public class SensorLogicTest {

    private SensorLogicImpl sensorLogic;

    @Before
    public void setUp() {
        this.sensorLogic = new SensorLogicImpl();
    }

    @Test
    public void testGetCategory1() {
        sensorLogic.setTemperatur(0);
        sensorLogic.setLightLevel(500);
        assert(sensorLogic.getCategory() == Category.RESTAURANT);
    }

    @Test
    public void testGetCategory2() {
        sensorLogic.setTemperatur(0);
        sensorLogic.setLightLevel(1500);
        assert(sensorLogic.getCategory() == Category.COFFEESHOP);
    }

    @Test
    public void testGetCategory3() {
        sensorLogic.setTemperatur(20);
        sensorLogic.setLightLevel(500);
        assert(sensorLogic.getCategory() == Category.BAR);
    }

    @Test
    public void testGetCategory4() {
        sensorLogic.setTemperatur(20);
        sensorLogic.setLightLevel(1500);
        assert(sensorLogic.getCategory() == Category.CREAMICESHOP);
    }

    @Test
    public void testGetCategory5() {
        sensorLogic.setTemperatur(-10);
        sensorLogic.setLightLevel(1000);
        assert(sensorLogic.getCategory() == Category.COFFEESHOP);
    }

    @Test
    public void testGetCategory6() {
        sensorLogic.setTemperatur(-10);
        sensorLogic.setLightLevel(0);
        assert(sensorLogic.getCategory() == Category.RESTAURANT);
    }

    @Test
    public void testGetTemperature1() {
        sensorLogic.setTemperatur(0);
        assert(sensorLogic.getTemperatur() == 0);
    }

    @Test
    public void testGetTemperature2() {
        sensorLogic.setTemperatur(-10);
        assert(sensorLogic.getTemperatur() == -10);
    }

    @Test
    public void testGetLightLevel1() {
        sensorLogic.setLightLevel(0);
        assert(sensorLogic.getLightLevel() == 0);
    }

    @Test
    public void testGetLightLevel2() {
        sensorLogic.setLightLevel(99999);
        assert(sensorLogic.getLightLevel() == 99999);
    }

}
