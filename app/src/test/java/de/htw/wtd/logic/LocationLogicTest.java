package de.htw.wtd.logic;

import de.htw.wtd.data.ILocation;
import de.htw.wtd.data.ILocationStorage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class LocationLogicTest {

    private ArrayList<IPlace> places;
    private ILocationStorage storage;

    @Before
    public void setUp() {
        this.places = new ArrayList<>();
        places.add(new PlaceImpl("BAR", "Testadresse", 1L, 1L, Category.BAR));
        places.add(new PlaceImpl("COFFEESHOP", "Testadresse", 2L, 2L, Category.COFFEESHOP));
        places.add(new PlaceImpl("CREAMICESHOP", "Testadresse", 3L, 3L, Category.CREAMICESHOP));
        places.add(new PlaceImpl("RESTAURANT", "Testadresse", 4L, 4L, Category.RESTAURANT));
        this.storage = Mockito.mock(ILocationStorage.class);
    }

    @Test
    public void testGetNewLocation() {
        ISensorLogic sensor = Mockito.mock(ISensorLogic.class);
        Mockito.when(sensor.getCategory()).thenReturn(Category.BAR);
        LocationLogicImpl logic = new LocationLogicImpl(storage, sensor, places);
        ILocation loc = logic.getNewLocation();
        assert(loc != null);
        assert(loc.getDescription().equals("BAR -> BAR"));
        assert(loc.getLongitude() == 1L);
        assert(loc.getLatitude() == 1L);
    }

    @Test
    public void testGetNewLocation2() {
        ISensorLogic sensor = Mockito.mock(ISensorLogic.class);
        Mockito.when(sensor.getCategory()).thenReturn(Category.COFFEESHOP);
        LocationLogicImpl logic = new LocationLogicImpl(storage, sensor, places);
        ILocation loc = logic.getNewLocation();
        assert(loc != null);
        assert(loc.getDescription().equals("COFFEESHOP -> COFFEESHOP"));
        assert(loc.getLongitude() == 2L);
        assert(loc.getLatitude() == 2L);
    }

    @Test
    public void testGetNewLocation3() {
        ISensorLogic sensor = Mockito.mock(ISensorLogic.class);
        Mockito.when(sensor.getCategory()).thenReturn(Category.CREAMICESHOP);
        LocationLogicImpl logic = new LocationLogicImpl(storage, sensor, places);
        ILocation loc = logic.getNewLocation();
        assert(loc != null);
        assert(loc.getDescription().equals("CREAMICESHOP -> CREAMICESHOP"));
        assert(loc.getLongitude() == 3L);
        assert(loc.getLatitude() == 3L);
    }

    @Test
    public void testGetNewLocation4() {
        ISensorLogic sensor = Mockito.mock(ISensorLogic.class);
        Mockito.when(sensor.getCategory()).thenReturn(Category.RESTAURANT);
        LocationLogicImpl logic = new LocationLogicImpl(storage, sensor, places);
        ILocation loc = logic.getNewLocation();
        assert(loc != null);
        assert(loc.getDescription().equals("RESTAURANT -> RESTAURANT"));
        assert(loc.getLongitude() == 4L);
        assert(loc.getLatitude() == 4L);
    }

    @Test
    public void testAcceptLocation1() {
        ArrayList<ILocation> locs = new ArrayList<>();
        Mockito.when(storage.getLocations()).thenReturn(locs);
        Mockito.doAnswer(i -> {
            locs.add((ILocation) i.getArguments()[0]);
            return null;
        }).when(storage).addLocation(Mockito.any(ILocation.class));
        ISensorLogic sensor = Mockito.mock(ISensorLogic.class);
        Mockito.when(sensor.getCategory()).thenReturn(Category.BAR);
        LocationLogicImpl logic = new LocationLogicImpl(storage, sensor, places);
        ILocation loc = logic.getNewLocation();
        logic.acceptLocation(loc);
        Mockito.verify(storage, Mockito.times(1)).addLocation(loc);
        assert(logic.getAllLocations().size() == 1);
    }

    @Test
    public void testAcceptLocation2() {
        ArrayList<ILocation> locs = new ArrayList<>();
        ISensorLogic sensor = Mockito.mock(ISensorLogic.class);
        Mockito.when(storage.getLocations()).thenReturn(locs);
        Mockito.doAnswer(i -> {
            locs.add((ILocation) i.getArguments()[0]);
            return null;
        }).when(storage).addLocation(Mockito.any(ILocation.class));
        Mockito.when(sensor.getCategory()).thenReturn(Category.BAR);
        LocationLogicImpl logic = new LocationLogicImpl(storage, sensor, places);
        ILocation loc = logic.getNewLocation();
        logic.acceptLocation(loc);
        ILocation loc1 = logic.getNewLocation();
        logic.acceptLocation(loc1);
        Mockito.verify(storage, Mockito.times(2)).addLocation(loc);
        assert(storage.getLocations().size() == 2);
    }
}
