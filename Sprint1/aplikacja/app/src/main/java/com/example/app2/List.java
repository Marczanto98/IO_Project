package com.example.app2;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class List extends AppCompatActivity {

    private ImageButton imageButton;
    private EditText editText;
    private ListView itemList;
    private ImageButton btn;
    private ImageButton back;

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
//        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        imageButton = (ImageButton)findViewById(R.id.accountButton);
        editText = findViewById(R.id.item_edit_text);
        btn = (ImageButton) findViewById(R.id.add_btn);
        itemList = findViewById(R.id.items_list);
        back = findViewById(R.id.back);

        items = FileHelper.readData(this);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        itemList.setAdapter(adapter);

//        EditText usernameEditText = (EditText) findViewById(R.id.item_edit_text);
//        String sUsername = editText.getText().toString();
//        if (sUsername.matches("")) {
//            Toast.makeText(this, "Wprowadź dane", Toast.LENGTH_SHORT).show();
//            return;
//        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.id.add_btn:
                        String itemEntered = editText.getText().toString();
                        adapter.add(itemEntered);
                        editText.setText("");
                        FileHelper.writeData(items, List.this);
                        Toast.makeText(List.this, "Dodano produkt", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                items.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(List.this, "Usunięto", Toast.LENGTH_SHORT).show();
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(List.this, MainActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(List.this, MainActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });
    }


}
