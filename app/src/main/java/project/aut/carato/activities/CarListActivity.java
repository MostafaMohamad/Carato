package project.aut.carato.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.widget.SearchView;
import android.widget.Toast;
import android.app.SearchManager;
import android.widget.SearchView.OnQueryTextListener;

import java.util.ArrayList;

import project.aut.carato.Car;
import project.aut.carato.CarsAdapter;
import project.aut.carato.DBHelper;
import project.aut.carato.R;

public class CarListActivity extends AppCompatActivity {
    
    private ListView listView;
    private DBHelper mydb;
    private ArrayList<Car> arrayList;
    private CarsAdapter carsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);
        
        mydb = new DBHelper(this);
        listView = findViewById(R.id.car_Listview);
        listView.setTextFilterEnabled(true);
        
        PopulateListview();
    }

    private void PopulateListview() {
        arrayList = mydb.GetAllCars();

        carsAdapter = new CarsAdapter(this,arrayList);
        listView.setAdapter(carsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CarListActivity.this,CarDetailsActivity.class);
                intent.putExtra("current",arrayList.get(position));
                startActivity(intent);

            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.listsearch).getActionView();

        assert searchManager != null;
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                carsAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }
}
