package com.training.music.solfegio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Rio on 6/12/2017.
 */

public class OptionAdapter extends ArrayAdapter<Option> {

    Context context;
    List<Option> myList;

    public OptionAdapter(Context context, int resource, List<Option> objects) {
        super(context, resource, objects);

        this.context = context;
        this.myList = objects;
    }

    @Override
    public int getCount() {
        if(myList != null)
            return myList.size();
        return 0;
    }

    @Override
    public Option getItem(int position) {
        if(myList != null)
            return myList.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        if(myList != null)
            return myList.get(position).hashCode();
        return 0;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder;

        //If the listview does not have an xml layout ready set the layout
        if (convertView == null){

            //we need a new holder to hold the structure of the cell
            holder = new Holder();

            //get the XML inflation service
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //Inflate our xml cell to the convertView
            convertView = inflater.inflate(R.layout.option_list, null);

            //Get xml components into our holder class
            holder.optionText = (TextView)convertView.findViewById(R.id.number_image);

            //Attach our holder class to this particular cell
            convertView.setTag(holder);

        }else{

            //The listview cell is not empty and contains already components loaded, get the tagged holder
            holder = (Holder)convertView.getTag();

        }

        //Fill our cell with data

        //get our person object from the list we passed to the adapter
        Option option = getItem(position);

        //Fill our view components with data
        holder.optionText.setText(option.getNumber());
        holder.optionText.setCompoundDrawablesWithIntrinsicBounds( 0, 0, option.getImage(), 0);

//        Picasso.with(context).load(person.getImageUrl()).fit().into(holder.imageView);

        return convertView;
    }

    /**
     * This holder must replicate the components in the person_cell.xml
     * We have a textview for the name and the surname and an imageview for the picture
     */
    private class Holder{

        TextView optionText;

    }

}
