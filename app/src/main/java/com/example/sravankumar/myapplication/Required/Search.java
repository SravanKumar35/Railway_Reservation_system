package com.example.sravankumar.myapplication.Required;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sravankumar.myapplication.R;

import java.util.Calendar;

public class Search extends AppCompatActivity implements View.OnClickListener {

    EditText from, to, dept_date;
    Button search, search_all;
    private int mYear, mMonth, mDay ;
    String str_from, str_to, str_date, answer;
    boolean net = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        from = findViewById(R.id.from);
        to = findViewById(R.id.to);

        dept_date = findViewById(R.id.dept_date);
        search = findViewById(R.id.search);
        search_all = findViewById(R.id.all);

        dept_date.setOnClickListener(this);
        search.setOnClickListener(this);
        search_all.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public void onClick(View v) {

        if(v == dept_date)
        {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            dept_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            datePickerDialog.show();
        }

        if(v == search)
        {
            ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (null != activeNetwork) {
                if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                {
                    answer="You are connected to a WiFi Network";
                    net  = true;
                }

                if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                {
                    net  = true;
                    answer="You are connected to a Mobile Network";
                }

            }
            else
                answer = "No internet Connectivity";

            if(net)
            {
                str_from = from.getText().toString();
                str_to = to.getText().toString();
                String type = "search";

                if(!str_from.isEmpty() && !str_to.isEmpty())
                {
                    Intent intent = new Intent(this, Results.class);
                    intent.putExtra("from", str_from);
                    intent.putExtra("to", str_to);
                    intent.putExtra("date", str_date);
                    startActivity(intent);
                }
                else if(str_to.isEmpty())
                    Toast.makeText(Search.this, "Enter Destination", Toast.LENGTH_SHORT).show();
                else if(str_from.isEmpty())
                    Toast.makeText(Search.this, "Enter Source", Toast.LENGTH_SHORT).show();

            }
            else
                Toast.makeText(Search.this, answer, Toast.LENGTH_SHORT).show();


        }

        if(v == search_all)
        {

            ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (null != activeNetwork) {
                if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                {
                    answer="You are connected to a WiFi Network";
                    net  = true;
                }

                if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                {
                    net  = true;
                    answer="You are connected to a Mobile Network";
                }

            }
            else
                answer = "No internet Connectivity";

            if(net)
            {
                Toast.makeText(Search.this, "Hello", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, AllTrains.class);
                startActivity(intent);
            }
            else
                Toast.makeText(Search.this, answer, Toast.LENGTH_SHORT).show();


        }
    }
}
