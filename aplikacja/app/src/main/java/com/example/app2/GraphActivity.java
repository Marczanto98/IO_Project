package com.example.app2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class GraphActivity extends AppCompatActivity {

    private LineGraphSeries<DataPoint> series1;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        backButton = findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        drawGraph();
    }

    void drawGraph() {
        double x, y;

        x = 0;

        GraphView graph = findViewById(R.id.graph);
        series1 = new LineGraphSeries<>();

        int numDataPoints = 500;

        for (int i = 0; i < numDataPoints; ++i) {
            x += 0.1;
            y = Math.sin(x);
            series1.appendData(new DataPoint(x, y), false, 100);
        }

        graph.addSeries(series1);

        graph.getViewport().setScrollable(true); // enables horizontal scrolling
        graph.getViewport().setScrollableY(true); // enables vertical scrolling
        graph.getViewport().setScalable(true); // enables horizontal zooming and scrolling
        graph.getViewport().setScalableY(true); // enables vertical zooming and scrolling
    }
}
