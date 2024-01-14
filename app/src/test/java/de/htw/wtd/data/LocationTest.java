package de.htw.wtd.data;

import org.junit.Test;

public class LocationTest {

    @Test
    public void testGood1(){
        LocationImpl location = new LocationImpl(1.0f, 2.0f, "Test");
        assert(location.getLongitude() == 1.0f);
        assert(location.getLatitude() == 2.0f);
        assert(location.getDescription().equals("Test"));
    }

    @Test
    public void testGood2(){
        LocationImpl location = new LocationImpl(-1.0f, -2.0f, "Test");
        location.setTime(1234567890);
        assert(location.getLongitude() == -1.0f);
        assert(location.getLatitude() == -2.0f);
        assert(location.getTime() == 1234567890);
    }

    @Test
    public void testBad1(){
        try{
            new LocationImpl(1.0f, 2.0f, null);
        }catch(IllegalArgumentException e){
            assert(e.getMessage().equals("Description must not be null"));
        }
    }
}
