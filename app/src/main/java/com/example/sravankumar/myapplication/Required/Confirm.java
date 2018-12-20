package com.example.sravankumar.myapplication.Required;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sravankumar.myapplication.R;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Confirm extends AppCompatActivity implements View.OnClickListener {

    String train_name, class_type, no_of_tickets, name, age, mob_number, final_price, berth_type, price,train_number, str_avail, answer, date;
    TextView train_value, class_value, tickets_value, name_value, age_value,mobile_value, final_value, berth_value;
    Button confirm;
    boolean net = false;
    static int id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        Bundle extras = getIntent().getExtras();
        assert extras != null;

        no_of_tickets = extras.getString("No. of tickets");
        name = extras.getString("Name");
        age = extras.getString("Age");
        mob_number = extras.getString("Mobile Number");
        berth_type = extras.getString("Berth Type");

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        price = sharedPreferences.getString("price", null);
        str_avail= sharedPreferences.getString("Avail", null);
        train_name= sharedPreferences.getString("train", null);
        class_type = sharedPreferences.getString("type", null);
        train_number = sharedPreferences.getString("Train_no", null);
        SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(this);
        date = sharedPreferences1.getString("Date", null);

        train_value = findViewById(R.id.train_value);
        class_value = findViewById(R.id.class_value);
        tickets_value = findViewById(R.id.ticket_count);
        name_value = findViewById(R.id.name_value);
        age_value = findViewById(R.id.age_value);
        mobile_value = findViewById(R.id.mob_value);
        final_value = findViewById(R.id.price_value);
        berth_value = findViewById(R.id.berth_value);

        int temp = Integer.valueOf(price)*Integer.valueOf(no_of_tickets);
        final_price = String.valueOf(temp);

        train_value.setText(train_name);
        class_value.setText(class_type);
        tickets_value.setText(no_of_tickets);
        name_value.setText(name);
        age_value.setText(age);
        mobile_value.setText(mob_number);
        final_value.setText(final_price);
        berth_value.setText(berth_type);

        confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v == confirm)
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

            if(net) {
                String type = "confirm";
                String up = String.valueOf(Integer.valueOf(str_avail) - Integer.valueOf(no_of_tickets));
                Backgroundworker backgroundworker = new Backgroundworker(this);
                backgroundworker.execute(type, train_number, up);

                displayTicket(train_number, train_name, no_of_tickets, class_type, name, age, berth_type, mob_number, date);
            }
            else
                Toast.makeText(this, answer, Toast.LENGTH_SHORT).show();
        }

    }

    private void displayTicket(String train_number, String train_name, String no_of_tickets, String class_type,
                               String name, String age, String berth_type, String mob_number, String date)
    {
        Document doc = new Document();
        String x = "/Ticket_" + String.valueOf(id) + ".pdf";
        id = id+1;
        String outpath = Environment.getExternalStorageDirectory() + x;
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(outpath));
            doc.open();
            doc.addTitle("Ticket Details");
            doc.add(new Paragraph("Train No : " + train_number));
            doc.add(new Paragraph("Train Name : " +train_name));
            doc.add(new Paragraph("No. of Tickets : " + no_of_tickets));
            doc.add(new Paragraph("No. of Tickets : " + class_type));
            doc.add(new Paragraph("Passenger Name : " + name));
            doc.add(new Paragraph("Age : " + age));
            doc.add(new Paragraph("Berth Type : " + berth_type));
            doc.add(new Paragraph("Travel date : " + date));
            doc.add(new Paragraph("Mobile No. : " + mob_number));
            doc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        //openPdf();
    }

    void openPdf()
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();

        File file = new File(path, "mypdf.pdf");

        intent.setDataAndType( Uri.fromFile(file), "application/pdf" );
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Confirm.this, Search.class);
        startActivity(intent);

    }
}
