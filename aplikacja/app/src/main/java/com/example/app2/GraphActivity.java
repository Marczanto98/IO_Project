package com.example.app2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageButton;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GraphActivity extends AppCompatActivity {

    private LineGraphSeries<DataPoint> series1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        ImageButton backButton = findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        drawGraph();
    }

    void drawGraph() {
        final int BLUE = Color.rgb(56, 105, 250);
        final int RED = Color.rgb(234, 0, 0);
        final int GREEN = Color.rgb(29, 209, 63);
        final int YELLOW = Color.rgb(238, 230, 0);

        GraphView graph = findViewById(R.id.graph);

        @SuppressWarnings("unchecked")
        HashMap<String, String> data = (HashMap<String, String>) getIntent().getSerializableExtra("data");

        // creating 4 data series (could be 1, but in order to create a graph legend, we need 4)
        ArrayList<BarGraphSeries<DataPoint>> dataSeries = new ArrayList<BarGraphSeries<DataPoint>>();
        dataSeries.add(new BarGraphSeries<>(new DataPoint[]{ new DataPoint(1, Double.parseDouble(data.get("confirmed"))) }));
        dataSeries.add(new BarGraphSeries<>(new DataPoint[]{ new DataPoint(2, Double.parseDouble(data.get("deaths"))) }));
        dataSeries.add(new BarGraphSeries<>(new DataPoint[]{ new DataPoint(3, Double.parseDouble(data.get("active"))) }));
        dataSeries.add(new BarGraphSeries<>(new DataPoint[]{ new DataPoint(4, Double.parseDouble(data.get("recovered"))) }));

        dataSeries.get(0).setTitle("confirmed");
        dataSeries.get(0).setColor(BLUE);

        dataSeries.get(1).setTitle("deaths");
        dataSeries.get(1).setColor(RED);

        dataSeries.get(2).setTitle("active");
        dataSeries.get(2).setColor(GREEN);

        dataSeries.get(3).setTitle("recovered");
        dataSeries.get(3).setColor(YELLOW);

        for (BarGraphSeries<DataPoint> series : dataSeries) {
            graph.addSeries(series);
            series.setDrawValuesOnTop(true);
            series.setValuesOnTopColor(Color.BLACK);
        }

        // TODO
        // Zwiększyć szerokość słupków na grafie

        // enabling legend
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graph.getLegendRenderer().setBackgroundColor(Color.rgb(20, 20, 20));
        graph.getLegendRenderer().setTextColor(Color.WHITE);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(5);

        graph.getGridLabelRenderer().setHorizontalLabelsVisible(false); // hide horizontal labels
    }
}
