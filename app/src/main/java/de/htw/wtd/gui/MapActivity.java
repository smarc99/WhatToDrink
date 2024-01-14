package de.htw.wtd.gui;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import de.htw.wtd.R;
import de.htw.wtd.data.ILocation;
import de.htw.wtd.data.ILocationStorage;
import de.htw.wtd.data.LocationStorageImpl;
import de.htw.wtd.logic.*;
import org.jetbrains.annotations.NotNull;
import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapView;
import org.osmdroid.util.GeoPoint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import org.osmdroid.views.overlay.Marker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapActivity extends AppCompatActivity {
    private final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    private MapView mapView;
    private Marker locationMarker;

    private ISensorLogic sensorLogic;

    private ILocationLogic locationLogic;

    private ILocationStorage locationStorage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Logik initialisieren
        this.sensorLogic = new SensorLogicImpl();
        this.locationStorage = new LocationStorageImpl(this.getFilesDir().getAbsolutePath());


        String json = null;
        List<IPlace> places;
        try {
            InputStream is = getAssets().open("locations.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
            json = sb.toString();
            reader.close();
        } catch (IOException ex) {
            System.err.println("Error while reading locations.json: " + ex.getMessage());
        }

        if (json != null) {
            Gson gson = new GsonBuilder().registerTypeAdapter(Category.class, new CategoryDeserializer()).create();
            Type listType = new TypeToken<List<PlaceImpl>>() {}.getType();
            places = gson.fromJson(json, listType);
        }else{
            places = new ArrayList<>();
        }

        this.locationLogic = new LocationLogicImpl(locationStorage, sensorLogic, places);

        // GUI initialisieren


        // Wichtig: Konfigurieren Sie osmdroid
        Configuration.getInstance().load(getApplicationContext(), getPreferences(MODE_PRIVATE));

        // Layout für die Activity setzen
        setContentView(R.layout.activity_map);

        ButtonFragment fragment = new ButtonFragment(this, locationLogic);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmet_container, fragment);
        fragmentTransaction.commit();

        // Karte initialisieren
        mapView = findViewById(R.id.map);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setMultiTouchControls(true);
        mapView.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.NEVER);
        IMapController mapController = mapView.getController();
        mapController.setZoom(9.0);

        // LocationManager und LocationListener initialisieren
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = location -> {
            GeoPoint currentLocation = new GeoPoint(location.getLatitude(), location.getLongitude());

            if (this.locationMarker == null) {
                mapController.animateTo(currentLocation, 15.5, 5000L);
                this.locationMarker = new Marker(mapView);
                this.locationMarker.setPosition(currentLocation);
                this.locationMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                this.locationMarker.setTitle("Dis bischt du!");
                mapView.getOverlays().add(this.locationMarker);
            }else{
                this.locationMarker.setPosition(currentLocation);
            }
        };

        requestPermissionsIfNecessary(new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
        });

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ArrayList<String> permissionsToRequest = new ArrayList<>(Arrays.asList(permissions).subList(0, grantResults.length));
        if (!permissionsToRequest.isEmpty()) {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    Marker addMarker(ILocation loc){
        GeoPoint currentLocation = new GeoPoint(loc.getLatitude(), loc.getLongitude());

        Marker marker = new Marker(mapView);
        marker.setPosition(currentLocation);
        marker.setIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon_marker));
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setTitle(loc.getDescription());
        marker.setAlpha(0.45f);
        mapView.getOverlays().add(marker);
        updateMap();
        return marker;
    }

    void removeMarker(Marker marker){
        mapView.getOverlays().remove(marker);
        updateMap();
    }

    void updateMap(){
        mapView.invalidate();
    }

    private void requestPermissionsIfNecessary(String[] permissions) {
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                permissionsToRequest.add(permission);
            }
        }
        if (!permissionsToRequest.isEmpty()) {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        mapView.onResume(); //wird benötigt für osmdroid
    }

    @Override
    public void onPause(){
        super.onPause();
        mapView.onPause(); //wird benötigt für osmdroid
    }

    /**
     * Deserializer für die Kategorie
     */
    private class CategoryDeserializer implements JsonDeserializer<Category> {
        @Override
        public Category deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            try {
                return Category.valueOf(json.getAsString().toUpperCase());
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.locationStorage.saveLocations();
    }
}
