package com.example.app2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class SymptomsFragment extends Fragment {

    private ListView listView;
    private ImageButton contactButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.symptoms, container, false);

        listView = view.findViewById(R.id.symptomsList);

        final List<SymptomsModel> symptoms = new ArrayList<>();
        symptoms.add(new SymptomsModel(false, "Gorączka"));
        symptoms.add(new SymptomsModel(false, "Suchy kaszel"));
        symptoms.add(new SymptomsModel(false, "Zmęczenie"));
        symptoms.add(new SymptomsModel(false, "Odkrztuszanie plwociny"));
        symptoms.add(new SymptomsModel(false, "Duszności"));
        symptoms.add(new SymptomsModel(false, "Bóle mięśni i stawów"));
        symptoms.add(new SymptomsModel(false, "Ból gardła"));
        symptoms.add(new SymptomsModel(false, "Ból głowy"));
        symptoms.add(new SymptomsModel(false, "Dreszcze"));
        symptoms.add(new SymptomsModel(false, "Nudności"));
        symptoms.add(new SymptomsModel(false, "Zapalenie błony śluzowej nosa"));
        symptoms.add(new SymptomsModel(false, "Biegunka"));
        symptoms.add(new SymptomsModel(false, "Krwioplucie"));
        symptoms.add(new SymptomsModel(false, "Zapalenie spojówek"));

        final CustomAdapter adapter = new CustomAdapter(getActivity(), symptoms);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SymptomsModel model = symptoms.get(position);

                if(model.isSelected())
                    model.setSelected(false);
                else
                    model.setSelected(true);

                symptoms.set(position, model);

                adapter.updateRecords(symptoms);
            }
        });

        contactButton = view.findViewById(R.id.contactBtn);

        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(getActivity(), Contact.class);
                startActivity(intentLoadNewActivity);

            }
        });

        return view;
    }
}
