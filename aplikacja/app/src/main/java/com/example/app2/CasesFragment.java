package com.example.app2;

import java.util.*;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CasesFragment extends Fragment implements View.OnClickListener{

    private TextView confirmed;
    private TextView deaths;
    private TextView active;
    private TextView recovered;
    private HashMap<String, String> data;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cases, container, false);

        confirmed = view.findViewById(R.id.allConfirmed);
        deaths = view.findViewById(R.id.allDeaths);
        active = view.findViewById(R.id.allActive);
        recovered = view.findViewById(R.id.allRecovered);
        data = null;

        ImageButton showCharts = view.findViewById(R.id.imageButton19);
        showCharts.setOnClickListener(this);

        getDataAndSetValues();

        return view;
    }

    private void getDataAndSetValues() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        String url = "https://disease.sh/v2/countries/poland?allowNull=true";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try
                {
                    JSONObject jsonObject = new JSONObject(response.toString());

                    data = new HashMap<>();

                    data.put("confirmed", jsonObject.getString("cases"));
                    data.put("deaths", jsonObject.getString("deaths"));
                    data.put("active", jsonObject.getString("active"));
                    data.put("recovered", jsonObject.getString("recovered"));

                    setStatValues();
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.d("Error Response", error.toString());
            }
        });

        queue.add(stringRequest);
    }

    private void setStatValues() {
        confirmed.setText(data.get("confirmed"));
        deaths.setText(data.get("deaths"));
        active.setText(data.get("active"));
        recovered.setText(data.get("recovered"));
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.imageButton19:
                if (data != null) {
                    Intent intentLoadNewActivity = new Intent(getActivity(), GraphActivity.class);
                    startActivity(intentLoadNewActivity);
                }
                break;
        }
    }
}
