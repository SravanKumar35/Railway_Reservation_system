package com.example.sravankumar.myapplication.Required;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sravankumar.myapplication.R;

import java.lang.reflect.Field;

public class Ticket1 extends AppCompatActivity implements View.OnClickListener {

    Spinner spinner, spinner_berth;
    TextView train_no, avail, price, type;
    String str_avail, str_price, str_train_name, str_type;
    Context mContext;
    Button book;
    EditText name, age, mob_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket1);
        mContext = getApplicationContext();
        train_no = findViewById(R.id.train_no);
        avail = findViewById(R.id.availibility);
        price = findViewById(R.id.pr);
        type = findViewById(R.id.ticket_type);
        name = findViewById(R.id.full_name);
        age = findViewById(R.id.age);
        mob_num = findViewById(R.id.mob_no);
        book = findViewById(R.id.cont);
        book.setOnClickListener(this);
        Bundle extras = getIntent().getExtras();
        assert extras != null;

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        str_avail= sharedPreferences.getString("Avail", null);
        str_price = sharedPreferences.getString("price", null);
        str_train_name= sharedPreferences.getString("train", null);
        str_type = sharedPreferences.getString("type", null);

        train_no.setText(str_train_name);
        avail.setText(str_avail);
        price.setText(str_price);
        type.setText(str_type);

        spinner = findViewById(R.id.spinner);

//        Limiting hieght of Spinner
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(spinner);

            // Set popupWindow height to 500px
            popupWindow.setHeight(300);
        }
        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            // silently fail...
        }

//        Changing value of Button
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                int temp = Integer.valueOf(spinner.getSelectedItem().toString());
                temp = temp*Integer.valueOf(str_price);
                book.setText("Proceed to Book( " + String.valueOf(temp) + "/- )");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        spinner_berth = findViewById(R.id.spinner_berth);
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(spinner_berth);

            // Set popupWindow height to 500px
            popupWindow.setHeight(300);
        }
        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            // silently fail...
        }



    }

    @Override
    public void onClick(View v) {

        if(v == book)
        {

            Intent intent = new Intent(this, Confirm.class);
            String count = spinner.getSelectedItem().toString();
            String str_name = name.getText().toString();
            String str_age = age.getText().toString();
            String str_mob = mob_num.getText().toString();
            String str_berth = spinner_berth.getSelectedItem().toString();

            if((str_name.isEmpty()) && (str_age.isEmpty()) && (str_mob.isEmpty()))
            {
                Toast.makeText(this, "Please fill in the value", Toast.LENGTH_SHORT).show();
            }
            else
            {
                intent.putExtra("No. of tickets", count);
                intent.putExtra("Name", str_name);
                intent.putExtra("Age", str_age);
                intent.putExtra("Mobile Number", str_mob);
                intent.putExtra("Berth Type", str_berth);
                startActivity(intent);
            }

        }

    }


}
