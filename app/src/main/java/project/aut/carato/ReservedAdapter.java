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

import project.aut.carato.DBHelper;
import project.aut.carato.ReservedCar;
import project.aut.carato.activities.Reservation;

public class ReservedAdapter extends ArrayAdapter<ReservedCar> implements Filterable {

    private Context mcontext;
    private List<ReservedCar> ReservedCarList = new ArrayList<>();
    List<ReservedCar> mStringFilterList;
    ValueFilter valueFilter;
    private DBHelper mydb;

    public ReservedAdapter(Context context, ArrayList<ReservedCar> ReservedCars){
        super(context,0,ReservedCars);
        mcontext = context;
        ReservedCarList = ReservedCars;
        mStringFilterList = ReservedCars;
        mydb = new DBHelper(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null){
            listItem = LayoutInflater.from(mcontext).inflate(R.layout.reserved_items,parent,false);
        }

        final ReservedCar currentReservedCar = ReservedCarList.get(position);

        TextView name = listItem.findViewById(R.id.reserved_name);
        name.setText(String.format("%s %s", currentReservedCar.getCar().getType(), currentReservedCar.getCar().getName()));

        TextView model = listItem.findViewById(R.id.reserved_from);
        model.setText(currentReservedCar.getReservation().getFrom());

        TextView type = listItem.findViewById(R.id.reserved_to);
        type.setText(currentReservedCar.getReservation().getTo());

        ImageView image = listItem.findViewById(R.id.imageView5);
        byte[] bytes = currentReservedCar.getCar().getImage();
        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        image.setImageBitmap(bmp);


        return listItem;


    }

    @Override
    public int getCount() {
        return  ReservedCarList.size();
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
                ArrayList<ReservedCar> ReservedCarArrayList = new ArrayList<>();

                for (int i = 0; i<mStringFilterList.size(); i++){
                    if ( (mStringFilterList.get(i).getCar().getName().toUpperCase()
                            .contains(constraint.toString().toUpperCase())) ||
                            (mStringFilterList.get(i).getCar().getType().toUpperCase()
                                    .contains(constraint.toString().toUpperCase()))){
                        ReservedCar filtered = new ReservedCar(
                                new Car(mStringFilterList.get(i).getCar().getId(),
                                mStringFilterList.get(i).getCar().getName(),
                                mStringFilterList.get(i).getCar().getModel(),
                                mStringFilterList.get(i).getCar().getType(),
                                mStringFilterList.get(i).getCar().getSeats(),
                                mStringFilterList.get(i).getCar().getDoors(),
                                mStringFilterList.get(i).getCar().getTransmission(),
                                mStringFilterList.get(i).getCar().getRent(),
                                mStringFilterList.get(i).getCar().getImage(),
                                mStringFilterList.get(i).getCar().getColor(),
                                mStringFilterList.get(i).getCar().getcClass()),

                                new Reservation(mStringFilterList.get(i).getReservation().getReservation_id(),
                                        mStringFilterList.get(i).getReservation().getC_id(),
                                        mStringFilterList.get(i).getReservation().getU_id(),
                                        mStringFilterList.get(i).getReservation().getFrom(),
                                        mStringFilterList.get(i).getReservation().getTo()));

                        ReservedCarArrayList.add(filtered);
                    }
                }
                results.count = ReservedCarArrayList.size();
                results.values = ReservedCarArrayList;
            } else {

                results.count = mStringFilterList.size();
                results.values = mStringFilterList;

            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ReservedCarList = (ArrayList<ReservedCar>) results.values;
            notifyDataSetChanged();

        }
    }
}
