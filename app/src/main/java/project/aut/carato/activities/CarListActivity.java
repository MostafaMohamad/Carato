package project.aut.carato.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import project.aut.carato.Car;
import project.aut.carato.CarsAdapter;
import project.aut.carato.DBHelper;
import project.aut.carato.R;
import project.aut.carato.SharedPrefs;
import project.aut.carato.User;

public class CarListActivity extends AppCompatActivity {
    
    private ListView listView;
    private DBHelper mydb;
    private ArrayList<Car> arrayList;
    private CarsAdapter carsAdapter;
    private DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);



        final FloatingActionButton fab = findViewById(R.id.fab);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        
        mydb = new DBHelper(this);



        listView = findViewById(R.id.car_Listview);
        listView.setTextFilterEnabled(true);
        
        PopulateListview();

        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectItemDrawer(menuItem);

                        return true;
                    }
                });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mDrawerLayout.isDrawerOpen(Gravity.START)){
                    mDrawerLayout.closeDrawers();
                    fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu));
                }
                else {
                    mDrawerLayout.openDrawer(Gravity.START);
                    fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_close));
                }


            }
        });

        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {
                fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_close));

            }

            @Override
            public void onDrawerClosed(@NonNull View view) {
                fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu));

            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });

        GetUserId();

        View headerView = navigationView.getHeaderView(0);
        TextView fname = headerView.findViewById(R.id.profile_name_txt);
        User mUser = mydb.GetUserById(Integer.valueOf(SharedPrefs.getInstance(this).getUserId()));
        fname.setText(mUser.getName());


    }

    private void GetUserId() {
        try {
            String username = getIntent().getStringExtra("uname");
            String uid = Integer.toString(mydb.GetUserId(username));
            SharedPrefs.getInstance(this).setUserId(uid);
        }catch (Exception ignored){}

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

    public  void selectItemDrawer(MenuItem menuItem){
        switch (menuItem.getItemId()) {
            case R.id.nav_reservations:
                startActivity(new Intent(CarListActivity.this, ReservedCarsActivity.class));
                break;
            case R.id.nav_profile:
                startActivity(new Intent(CarListActivity.this,ProfileActivity.class));
                break;
        }

        mDrawerLayout.closeDrawers();

    }

    public void Logout(View view) {
        mDrawerLayout.closeDrawers();
        SharedPrefs.getInstance(this).ClearSharedPrefs();
        Intent intent = new Intent(this,SplashActivity.class);
        startActivity(intent);
        finish();
    }
}
