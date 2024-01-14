package de.htw.wtd.gui;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import de.htw.wtd.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ButtonFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_button, container, false);
        if (rootView != null) {
            rootView.findViewById(R.id.yes_button).setOnClickListener(v -> changeButtonVisibility(true));
            rootView.findViewById(R.id.no_button).setOnClickListener(v -> changeButtonVisibility(true));
            rootView.findViewById(R.id.request_button).setOnClickListener(v -> changeButtonVisibility(false));
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