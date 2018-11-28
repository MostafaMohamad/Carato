package project.aut.carato.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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
}
