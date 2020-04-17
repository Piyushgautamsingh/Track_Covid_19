package com.example.track_covid_19.ui.india;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.track_covid_19.R;

public class states_details extends AppCompatActivity {

    TextView tvstateName, tvDetailTotalCases, tvDetailTotalDeaths,
            tvDetailTotalRecovered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_country_detail);

        // call view
        tvstateName = findViewById(R.id.tvstateName);
        tvDetailTotalCases = findViewById(R.id.tvDetailTotalCases);
        tvDetailTotalDeaths = findViewById(R.id.tvDetailTotalDeaths);
        tvDetailTotalRecovered = findViewById(R.id.tvDetailTotalRecovered);


        // call Covid Country
        india_state india_state = getIntent().getParcelableExtra("EXTRA_COVID");

        // set text view
        tvstateName.setText(india_state.getmindia_state());
        tvDetailTotalCases.setText(india_state.getmCases());
        tvDetailTotalDeaths.setText(india_state.getmDeaths());
        tvDetailTotalRecovered.setText(india_state.getmRecovered());

    }
}
