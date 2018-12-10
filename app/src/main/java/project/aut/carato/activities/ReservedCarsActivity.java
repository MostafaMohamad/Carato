package project.aut.carato.activities;

import android.app.SearchManager;
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;

import java.util.ArrayList;

import project.aut.carato.DBHelper;
import project.aut.carato.R;
import project.aut.carato.ReservedAdapter;
import project.aut.carato.ReservedCar;
import project.aut.carato.SharedPrefs;

public class ReservedCarsActivity extends AppCompatActivity {

    private ListView listView;
    private DBHelper mydb;
    private ArrayList<ReservedCar> arrayList;
    private ReservedAdapter reservedAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserved_cars);

        mydb = new DBHelper(this);
        listView = findViewById(R.id.reserved_list);
        listView.setTextFilterEnabled(true);

        PopulateListview();
    }

    private void PopulateListview() {
        arrayList = mydb.GetReservedCars(Integer.parseInt(SharedPrefs.getInstance(this).getUserId()));

        reservedAdapter = new ReservedAdapter(this,arrayList);
        listView.setAdapter(reservedAdapter);


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
                reservedAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }
}
