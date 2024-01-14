package de.htw.wtd.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import de.htw.wtd.logic.PlaceImpl;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LocationStorageImpl implements ILocationStorage{

    private ArrayList<ILocation> locations;
    private static final String FILE_NAME = "saved_locs.json";
    private final String FILE_PATH;

    public LocationStorageImpl(String path){
        this.FILE_PATH = path + "/" + FILE_NAME;
        loadLocations();
    }

    @Override
    public ArrayList<ILocation> getLocations() {
        return locations;
    }

    @Override
    public void addLocation(ILocation loc) {
        locations.add(loc);
    }

    /**
     * Ladet alle Locations aus einer saved_locs.json Datei
     */
    private void loadLocations() {
        File file = new File(FILE_PATH);
        String json = null;
        try{
            if (!file.exists()) {
                locations = new ArrayList<>();
                return;
            }
            //write all locations to file
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append('\n');
                }
                json = sb.toString();
            }
        } catch (IOException ex) {
            System.err.println("Error while reading locations.json: " + ex.getMessage());
        }

        if (json != null) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<LocationImpl>>() {}.getType();
            locations = gson.fromJson(json, listType);
        }else{
            locations = new ArrayList<>();
        }
    }

    @Override
    public void saveLocations() {
        Gson gson = new Gson();
        String json = gson.toJson(locations);

        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write(json);
        } catch (IOException e){
            System.err.println("Error while writing locations.json: " + e.getMessage());
        }
    }

}
