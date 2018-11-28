package project.aut.carato;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CarsAdapter extends ArrayAdapter<Car> implements Filterable {

    private Context mcontext;
    private List<Car> carList = new ArrayList<>();
    private DBHelper mydb;

    public CarsAdapter(Context context, ArrayList<Car> cars){
        super(context,0,cars);
        mcontext = context;
        carList = cars;
        mydb = new DBHelper(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null){
            listItem = LayoutInflater.from(mcontext).inflate(R.layout.car_items,parent,false);
        }

            final Car currentCar = carList.get(position);

            TextView name = (TextView)listItem.findViewById(R.id.name_txt);
            name.setText(currentCar.getName());

            TextView model = (TextView)listItem.findViewById(R.id.model_txt);
            model.setText(currentCar.getModel());

            TextView type = (TextView)listItem.findViewById(R.id.type_txt);
            type.setText(currentCar.getType());

            ImageView image = (ImageView)listItem.findViewById(R.id.imageView2);
            byte[] bytes = currentCar.getImage();
            Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            image.setImageBitmap(bmp);


            return listItem;


    }
}
