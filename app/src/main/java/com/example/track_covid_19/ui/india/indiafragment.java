package com.example.track_covid_19.ui.india;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.track_covid_19.R;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class indiafragment extends Fragment {
    RecyclerView rvindia_state;
    ProgressBar progressBar;
    stateadapter stateadapter;

    private static final String TAG = indiafragment.class.getSimpleName();
    List<india_state> states;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.states, container, false);

        // set has option menu as true because we have menu
        setHasOptionsMenu(true);

        // call view
        rvindia_state = root.findViewById(R.id.rvindia_state);
        progressBar = root.findViewById(R.id.progress_circular_country);
        rvindia_state.setLayoutManager(new LinearLayoutManager(getActivity()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvindia_state.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.line_divider));
        rvindia_state.addItemDecoration(dividerItemDecoration);

        //call list
        states = new ArrayList<>();

        // call Volley method
        getDataFromServer();

        return root;
    }

    private void showRecyclerView() {
       stateadapter = new stateadapter(states, getActivity());
        rvindia_state.setAdapter(stateadapter);

        ItemClickSupport_i.addTo(rvindia_state).setOnItemClickListener(new ItemClickSupport_i.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedindian_states(states.get(position));
            }
        });
    }

    private void showSelectedindian_states(india_state indian_state) {
        Intent states_details = new Intent(getActivity(), states_details.class);
        states_details.putExtra("EXTRA_COVID", indian_state);
        startActivity(states_details);
    }

    private void getDataFromServer() {
        String url = "https://api.covid19india.org/data.json";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                if (response != null) {
                    Log.e(TAG, "onResponse: " + response);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject data = jsonArray.getJSONObject(i);

                            // Extract JSONObject inside JSONObject
                            JSONObject statecode = data.getJSONObject("statecode");


                            states.add(new india_state(
                                    data.getString("state"), data.getString("confirmed"),
                                    data.getString("deaths"), data.getString("recovered")

                            ));
                        }


                        // Action Bar Title
                        getActivity().setTitle(jsonArray.length()+"states");

                        showRecyclerView();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        Log.e(TAG, "onResponse: " + error);
                    }
                });
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = new SearchView(getActivity());
        searchView.setQueryHint("Search...");
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (stateadapter != null) {
                    stateadapter.getFilter().filter(newText);
                }
                return true;
            }
        });

        searchItem.setActionView(searchView);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
