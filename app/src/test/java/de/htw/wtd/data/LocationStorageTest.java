package de.htw.wtd.data;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class LocationStorageTest {

    private ILocationStorage storage;
    private final String FILE_PATH = "src/test/resources";
    @Before
    public void setup() {
        //Clear file
        File file = new File(FILE_PATH + "/saved_locs.json");
        file.delete();
        storage = new LocationStorageImpl(FILE_PATH);
    }

    @Test
    public void testGetLocationsGood1() {
        assert(storage.getLocations().isEmpty());
    }

    @Test
    public void testGetLocationsGood2() {
        ILocation loc = new LocationImpl(0f, 0f, "Test");
        storage.addLocation(loc);
        assert(storage.getLocations().get(0).equals(loc));
    }

    @Test
    public void testGetLocationsGood3() {
        ILocation loc = new LocationImpl(0f, 0f, "Test");
        ILocation loc2 = new LocationImpl(-4f, 2f, "Test2");
        storage.addLocation(loc);
        storage.addLocation(loc2);
        assert(storage.getLocations().size() == 2);
        assert(storage.getLocations().get(0).equals(loc));
        assert(storage.getLocations().get(1).equals(loc2));
    }

    @Test
    public void testAddLocationGood1() {
        ILocation loc = new LocationImpl(0f, 0f, "Test");
        storage.addLocation(loc);
        assert(storage.getLocations().contains(loc));
    }

    @Test
    public void testAddLocationBad1() {
        try {
            storage.addLocation(null);
            assert(false);
        } catch (IllegalArgumentException ex) {
            assert(true);
        }
    }

    @Test
    public void testAddLocationGood2() {
        ILocation loc = new LocationImpl(0f, 0f, "Test");
        storage.addLocation(loc);
        storage.addLocation(loc);
        assert(storage.getLocations().size() == 2);
    }

    @Test
    public void testSaveLocationsGood1() {
        ILocation loc = new LocationImpl(0f, 0f, "Test");
        ILocation loc2 = new LocationImpl(-4f, 2f, "Test2");
        storage.addLocation(loc);
        storage.addLocation(loc2);
        storage.saveLocations();
        ILocationStorage storage2 = new LocationStorageImpl(FILE_PATH);
        assert(storage2.getLocations().size() == 2);
        assert(storage2.getLocations().stream().anyMatch(l -> l.equals(loc)));
        assert(storage2.getLocations().stream().anyMatch(l -> l.equals(loc2)));
    }
}
