package project.aut.carato.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.time.LocalDate;

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void BookNow(View view) {
        LocalDate start = LocalDate.of(2016,1,1);
        LocalDate end = LocalDate.of(2016,1,15);
        LocalDate wanted = LocalDate.of(2016,1,16);

        Boolean containsWanted = ( ! wanted.isBefore( start ) ) && ( wanted.isBefore( end ) ) ;

        if (containsWanted)
            Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show();
    }
}
