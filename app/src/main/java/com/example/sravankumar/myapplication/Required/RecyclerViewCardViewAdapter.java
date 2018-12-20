package com.example.sravankumar.myapplication.Required;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sravankumar.myapplication.R;

import java.util.List;

public class RecyclerViewCardViewAdapter extends RecyclerView.Adapter<RecyclerViewCardViewAdapter.ViewHolder> {

    Context context;

    List<train> trains;
    public static String train_number = null;

    public RecyclerViewCardViewAdapter(List<train> getDataAdapter, Context context){

        super();

        this.trains = getDataAdapter;

        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        train getDataAdapter1 =  trains.get(position);

        String x = getDataAdapter1.getTrain_name() + " (" + getDataAdapter1.getTrain_no() + ")";
        holder.train_no.setText(x);
        holder.from.setText(getDataAdapter1.getSource());
        holder.to.setText(getDataAdapter1.getDest());
        holder.arr_time.setText(getDataAdapter1.getArr_time());
        holder.dept_time.setText(getDataAdapter1.getDept_time());
        holder.avail_sl.setText(getDataAdapter1.getavail_seats());
        holder.price_sl.setText(getDataAdapter1.getPrice());
        train_number = getDataAdapter1.getTrain_no();


    }

    @Override
    public int getItemCount() {

        return trains.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView train_no, from ,to, arr_time, dept_time, avail_sl, avail_1, avail_2;
        Button price_sl, price_1, price_2;
        Context c;


        public ViewHolder(View itemView) {

            super(itemView);
            c = itemView.getContext();

            train_no = itemView.findViewById(R.id.train_no);
            from = itemView.findViewById(R.id.from);
            to = itemView.findViewById(R.id.to);
            arr_time = itemView.findViewById(R.id.arr_time);
            dept_time = itemView.findViewById(R.id.dep_time);
            avail_sl = itemView.findViewById(R.id.avail_sl);
            price_sl = itemView.findViewById(R.id.price_sl);
            price_sl.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {

            if(v == price_sl)
            {

                String x = avail_sl.getText().toString();
                String y = price_sl.getText().toString();
                String z = train_no.getText().toString();


                SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = sharedpreferences.edit();

                Intent  intent1 = new Intent(c, Ticket1.class );
                editor.putString("Avail", x);
                editor.putString("price", y);
                editor.putString("train", z);
                editor.putString("type", "Sleeper");
                editor.putString("Train_no", train_number);
                editor.apply();
                context.startActivity(intent1);
            }

        }
    }
}
