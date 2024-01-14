package de.htw.wtd.gui;

import android.os.Bundle;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import de.htw.wtd.R;
import de.htw.wtd.data.ILocation;
import de.htw.wtd.logic.ILocationLogic;
import org.osmdroid.views.overlay.Marker;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ButtonFragment extends Fragment {

    private ILocationLogic locationLogic;

    private MapActivity mapActivity;

    private ILocation location;

    private Marker locationMarker;

    public ButtonFragment(MapActivity mapActivity, ILocationLogic locationLogic) {
        this.mapActivity = mapActivity;
        this.locationLogic = locationLogic;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_button, container, false);
        if (rootView != null) {
            rootView.findViewById(R.id.yes_button).setOnClickListener(v -> {
                changeButtonVisibility(true);
                if (location != null) {
                    locationLogic.acceptLocation(location);
                    this.mapActivity.updateMap();
                }
            });
            rootView.findViewById(R.id.no_button).setOnClickListener(v -> {
                changeButtonVisibility(true);
                this.location = null;
                if (locationMarker != null) {
                    this.mapActivity.removeMarker(locationMarker);
                    this.locationMarker = null;
                    this.mapActivity.updateMap();
                }
            });
            rootView.findViewById(R.id.request_button).setOnClickListener(v -> {
                this.location = locationLogic.getNewLocation();
                if(location != null) {
                    changeButtonVisibility(false);
                    System.out.println("Location: " + location.getDescription() + " " + location.getLatitude() + " " + location.getLongitude() + " " + location.getTime());
                    if(this.locationMarker != null){
                        this.mapActivity.removeMarker(locationMarker);
                        this.mapActivity.addMarker(location, true);
                    }
                    this.locationMarker = this.mapActivity.addMarker(location);
                    this.mapActivity.updateMap();
                }else {
                    Toast.makeText(mapActivity, "Fehler, beim holen einer Location", Toast.LENGTH_SHORT).show();
                }
            });
        }
        return rootView;
    }

    private void changeButtonVisibility(boolean requestButtonVisible) {
        View rootView = getView();
        if (rootView != null) {
            rootView.findViewById(R.id.yes_button).setVisibility(!requestButtonVisible ? View.VISIBLE : View.GONE);
            rootView.findViewById(R.id.no_button).setVisibility(!requestButtonVisible ? View.VISIBLE : View.GONE);
            rootView.findViewById(R.id.request_button).setVisibility(requestButtonVisible ? View.VISIBLE : View.GONE);
        }
    }
}