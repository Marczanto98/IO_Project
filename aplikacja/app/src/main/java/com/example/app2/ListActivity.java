package com.example.app2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity<C> extends AppCompatActivity {

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

        editText = findViewById(R.id.item_edit_text);
        btn = findViewById(R.id.add_btn);
        itemList = findViewById(R.id.items_list);
        back = findViewById(R.id.back);

        items = FileHelper.readData(this);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        itemList.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.id.add_btn:
                        String itemEntered = editText.getText().toString();
                        if (itemEntered.equals("")) {
                            Toast.makeText(ListActivity.this, "Wprowadź nazwę produktu", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        adapter.add(itemEntered);
                        editText.setText("");
                        FileHelper.writeData(items, ListActivity.this);
                        Toast.makeText(ListActivity.this, "Dodano produkt", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                items.remove(position);
                adapter.notifyDataSetChanged();
                FileHelper.writeData(items, getApplicationContext());
                Toast.makeText(ListActivity.this, "Usunięto", Toast.LENGTH_SHORT).show();
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
