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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CarsAdapter extends ArrayAdapter<Car> implements Filterable {

    private Context mcontext;
    private List<Car> carList = new ArrayList<>();
    List<Car> mStringFilterList;
    ValueFilter valueFilter;
    private DBHelper mydb;

    public CarsAdapter(Context context, ArrayList<Car> cars){
        super(context,0,cars);
        mcontext = context;
        carList = cars;
        mStringFilterList = cars;
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

    @Override
    public int getCount() {
        return  carList.size();
    }


    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0){
                ArrayList<Car> carArrayList = new ArrayList<>();

                for (int i = 0; i<mStringFilterList.size(); i++){
                    if ( (mStringFilterList.get(i).getName().toUpperCase()
                            .contains(constraint.toString().toUpperCase())) ||
                            (mStringFilterList.get(i).getType().toUpperCase()
                            .contains(constraint.toString().toUpperCase()))){
                        Car filtered = new Car(mStringFilterList.get(i).getName(),
                                mStringFilterList.get(i).getModel(),
                                mStringFilterList.get(i).getType(),
                                mStringFilterList.get(i).getSeats(),
                                mStringFilterList.get(i).getDoors(),
                                mStringFilterList.get(i).getTransmission(),
                                mStringFilterList.get(i).getRent(),
                                mStringFilterList.get(i).getImage(),
                                mStringFilterList.get(i).getColor(),
                                mStringFilterList.get(i).getcClass());

                        carArrayList.add(filtered);
                    }
                }
                results.count = carArrayList.size();
                results.values = carArrayList;
            } else {

                results.count = mStringFilterList.size();
                results.values = mStringFilterList;

            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            carList = (ArrayList<Car>) results.values;
            notifyDataSetChanged();

        }
    }
}
