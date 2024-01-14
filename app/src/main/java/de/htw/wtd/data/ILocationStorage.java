package de.htw.wtd.data;

import java.util.ArrayList;
public interface ILocationStorage {
    /**
     * Gibt alle gespeicherten Locations wieder.
     * @return Liste von Locations
     */
    ArrayList<ILocation> getLocations();

    /**
     * Fügt Location hinzu.
     * @param loc, welche hinzugefügt werden soll.
     */
    void addLocation(ILocation loc);
}
