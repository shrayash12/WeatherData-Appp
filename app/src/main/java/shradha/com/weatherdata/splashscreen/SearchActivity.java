package shradha.com.weatherdata.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.List;

import shradha.com.weatherdata.R;

public class SearchActivity extends AppCompatActivity {
    SearchView searchView;
    ListView listView;
    List<String> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = findViewById(R.id.searchView);
        listView = findViewById(R.id.listView);

    }
}