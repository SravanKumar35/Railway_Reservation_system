package com.example.sravankumar.myapplication.Required;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sravankumar.myapplication.R;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity implements View.OnClickListener {
    EditText first_name, middle_name, last_name, username, password, dob, card_id, h_no, street, city, state, mob_no;
    String str_first, str_middle, str_last, str_user_id, str_pswd, str_dob, str_gender,
            str_card, str_hno, str_street, str_city, str_state, str_mob, answer;
    Button register;
    RadioGroup gender;
    RadioButton btn_selected;
    TextView old_user;
    boolean net = false;

    private int mYear, mMonth, mDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        first_name = findViewById(R.id.first_name);
        middle_name = findViewById(R.id.middle_name);
        last_name = findViewById(R.id.last_name);
        username = findViewById(R.id.user_id);
        password = findViewById(R.id.pswd);
        dob = findViewById(R.id.dob);
        card_id = findViewById(R.id.card_id);
        h_no = findViewById(R.id.h_no);
        street = findViewById(R.id.street);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        mob_no = findViewById(R.id.mob_no);



        gender = findViewById(R.id.gender);
        old_user = findViewById(R.id.old_user);


        register = findViewById(R.id.register);
        register.setOnClickListener(this);
        dob.setClickable(true);
        dob.setOnClickListener(this);
        old_user.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v == register) {

            ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (null != activeNetwork) {
                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                    answer = "You are connected to a WiFi Network";
                    net = true;
                }

                if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                    net = true;
                    answer = "You are connected to a Mobile Network";
                }

            } else
                answer = "No internet Connectivity";
            if (net) {
                str_first = first_name.getText().toString();
                str_middle = middle_name.getText().toString();
                str_last = last_name.getText().toString();
                str_user_id = username.getText().toString();
                str_pswd = password.getText().toString();
                str_card = card_id.getText().toString();
                str_hno = h_no.getText().toString();
                str_street = street.getText().toString();
                str_city = city.getText().toString();
                str_state = state.getText().toString();
                str_mob = mob_no.getText().toString();
                str_dob = dob.getText().toString();

                int selected_id = gender.getCheckedRadioButtonId();
                if (selected_id == -1) {
                    Toast.makeText(Register.this, "Please select any gender", Toast.LENGTH_SHORT).show();
                } else {
                    btn_selected = findViewById(selected_id);
                    str_gender = btn_selected.getText().toString();
                }

                if (((str_first.isEmpty()) || (str_middle.isEmpty()) || (str_last.isEmpty())) && (str_user_id.isEmpty())
                        && (str_pswd.isEmpty()) && (str_dob.isEmpty()) && (str_card.isEmpty())
                        && (str_hno.isEmpty()) && (str_street.isEmpty()) && (str_city.isEmpty()) && (str_state.isEmpty())
                        && (str_mob.isEmpty())) {
                    Toast.makeText(Register.this, "Please fill all entries", Toast.LENGTH_SHORT).show();
                } else {

                    if(isValidEmail(str_user_id) && isValidPassword(str_pswd))
                    {
                        Toast.makeText(Register.this, str_dob, Toast.LENGTH_SHORT).show();
                        String type = "register";
                        Backgroundworker backgroundworker = new Backgroundworker(this);
                        backgroundworker.execute(type, str_first, str_middle, str_last, str_user_id, str_pswd, str_dob,
                                str_gender, str_card, str_hno, str_street, str_city, str_state, str_mob);
                    }

                    if(!isValidPassword(str_user_id.trim())) {
                        Toast.makeText(Register.this, "Invalid password", Toast.LENGTH_SHORT).show();
                    }
                    if(!isValidEmail(str_pswd.trim()))
                        Toast.makeText(Register.this, "Invalid user_id", Toast.LENGTH_SHORT).show();



                }
            }
        }

        if (v == dob) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            dob.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

    }

    public boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    public boolean isValidEmail(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";


        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

}
