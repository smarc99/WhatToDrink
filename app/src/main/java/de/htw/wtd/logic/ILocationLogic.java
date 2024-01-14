package de.htw.wtd.logic;

import de.htw.wtd.data.ILocation;

import java.util.ArrayList;

public interface ILocationLogic {
    /**
     * Generiert eine neue Location anhand der Sensordaten
     */
    ILocation getNewLocation();

    /**
     * Liefert alle im Speicher vorhandenen Locations zurück.
     * @return Liste von Locations
     */
    ArrayList<ILocation> getAllLocations();
}

