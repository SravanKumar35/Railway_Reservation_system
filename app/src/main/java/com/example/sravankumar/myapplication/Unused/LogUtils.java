package com.example.sravankumar.myapplication.Unused;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sravankumar.myapplication.R;

import java.util.ArrayList;

public class LogUtils {

    public static String TAG = "Const";
    public static boolean LOGGING_ENABLED = true;

    public static void LOGD(String message) {
        if (LOGGING_ENABLED) {
            Log.d(TAG, message);
        }
    }

    public static void LOGD(String message, Throwable cause) {
        if (LOGGING_ENABLED) {
            Log.d(TAG, message, cause);
        }
    }

    public static void LOGV(String message) {
        if (LOGGING_ENABLED) {
            Log.v(TAG, message);
        }
    }

    public static void LOGV(String message, Throwable cause) {
        if (LOGGING_ENABLED) {
            Log.v(TAG, message, cause);
        }
    }

    public static void LOGI(String message) {
        if (LOGGING_ENABLED) {
            Log.i(TAG, message);
        }
    }

    public static void LOGI(String message, Throwable cause) {
        if (LOGGING_ENABLED) {
            Log.i(TAG, message, cause);
        }
    }

    public static void LOGW(String message) {
        if (LOGGING_ENABLED) {
            Log.w(TAG, message);
        }
    }

    public static void LOGW(String message, Throwable cause) {
        if (LOGGING_ENABLED) {
            Log.w(TAG, message, cause);
        }
    }

    public static void LOGE(String message) {
        if (LOGGING_ENABLED) {
            Log.e(TAG, message);
        }
    }

    public static void LOGE(String message, Throwable cause) {
        if (LOGGING_ENABLED) {
            Log.e(TAG, message, cause);
        }
    }

    public static class Ticket extends AppCompatActivity implements View.OnClickListener {

        Spinner spinner;
        TextView train_no, avail, price, type;
        String str_avail, str_price, str_train_name, str_type;
        PermissionsChecker checker;
        Context mContext;
        private ListView listView;
        private MovieAdapter mAdapter;
        Button cont, book;
        LinearLayout list;
        EditText number;
        ArrayList<Movie> moviesList;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.ticket);

            mContext = getApplicationContext();
            train_no = findViewById(R.id.train_no);
            avail = findViewById(R.id.availibility);
            price = findViewById(R.id.pr);
            type = findViewById(R.id.ticket_type);

            cont = findViewById(R.id.cont);
            book = findViewById(R.id.book);
            cont.setOnClickListener(this);
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

            cont = findViewById(R.id.cont);
            cont.setOnClickListener(this);

            number = findViewById(R.id.number);
            list = findViewById(R.id.list);

            listView = findViewById(R.id.movies_list);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2);
            moviesList = new ArrayList<>();



            /*spinner = (Spinner) findViewById(R.id.spinner);
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
            }*/
        }

        @Override
        public void onClick(View v) {

            if(v == cont)
            {
                int x = Integer.valueOf(number.getText().toString());
                listView.setAdapter(null);
                list.setVisibility(View.VISIBLE);
                for(int i=0;i<x;i++)
                {
                    moviesList.add(new Movie( "Berth" , "Gender"));
                }

                mAdapter = new MovieAdapter(this,moviesList);
                listView.setAdapter(mAdapter);

                int val = x*Integer.valueOf(price.getText().toString());
                book.setText("Proceed " + "( " + String.valueOf(val) + "/- )");
    //                startActivity(new Intent(this, ThankYou.class));

            }

            if(v == book)
            {



            }

        }
    }
}