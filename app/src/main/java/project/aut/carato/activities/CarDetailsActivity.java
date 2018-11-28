package project.aut.carato.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

import project.aut.carato.Car;
import project.aut.carato.R;

public class CarDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);

        TextView trans = findViewById(R.id.transmission_txt);
        TextView rent = findViewById(R.id.rent_txt);
        TextView doors = findViewById(R.id.doors_txt);
        TextView seats = findViewById(R.id.seats_txt);
        TextView year = findViewById(R.id.year_txt);
        TextView name = findViewById(R.id.car_name_txt);
        TextView color = findViewById(R.id.color_txt);
        TextView cClass = findViewById(R.id.cClass_txt);

        Intent intent = getIntent();
        Car car = (Car)intent.getSerializableExtra("current");

        trans.setText(car.getTransmission());
        rent.setText(car.getRent());
        doors.setText(car.getDoors());
        seats.setText(car.getSeats());
        year.setText(car.getModel());
        name.setText(car.getType()+" "+car.getName());
        color.setText(car.getColor());
        cClass.setText(car.getcClass());



        byte[] bytes = car.getImage();
        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        ImageView image = findViewById(R.id.imageView3);
        image.setImageBitmap(bmp);


    }
}
