package com.example.sravankumar.myapplication.Required;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Backgroundworker extends AsyncTask<String, Void, String> {

    Context context;
    AlertDialog alertDialog;


    Backgroundworker(Context ctx) {

        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {

        String type = params[0];

        String login_url =  "https://evangelistic-infect.000webhostapp.com/APP/login.php";
        String register_url =  "https://evangelistic-infect.000webhostapp.com/APP/entry.php";
        String search_url =  "https://evangelistic-infect.000webhostapp.com/APP/search.php";
        String book_url = "https://evangelistic-infect.000webhostapp.com/APP/book.php";

        if(type.equals("login"))
        {
            try
            {
                String user_name = params[1];
                String pswd = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(pswd, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream , "iso-8859-1"));

                String result = "";
                String line = "";
                while((line = bufferedReader.readLine())!= null)
                {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else if(type.equals("register"))
        {
            try
            {
                String first_name = params[1];
                String middle_name = params[2];
                String last_name = params[3];
                String user_name = params[4];
                String pswd = params[5];
                String dob = params[6];
                String gender = params[7];
                String card_no = params[8];
                String h_no = params[9];
                String street = params[10];
                String city = params[11];
                String state = params[12];
                String mob_no = params[13];
                //int mob_no = Integer.valueOf(temp);

                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("first_name", "UTF-8") + "=" + URLEncoder.encode(first_name, "UTF-8") + "&"
                        + URLEncoder.encode("middle_name", "UTF-8") + "=" + URLEncoder.encode(middle_name, "UTF-8") + "&"
                        + URLEncoder.encode("last_name", "UTF-8") + "=" + URLEncoder.encode(last_name, "UTF-8") + "&"
                        + URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(pswd, "UTF-8") + "&"
                        + URLEncoder.encode("dob", "UTF-8") + "=" + URLEncoder.encode(dob, "UTF-8") + "&"
                        + URLEncoder.encode("gender", "UTF-8") + "=" + URLEncoder.encode(gender, "UTF-8") + "&"
                        + URLEncoder.encode("card_id", "UTF-8") + "=" + URLEncoder.encode(card_no, "UTF-8") + "&"
                        + URLEncoder.encode("h_no", "UTF-8") + "=" + URLEncoder.encode(h_no, "UTF-8") + "&"
                        + URLEncoder.encode("street", "UTF-8") + "=" + URLEncoder.encode(street, "UTF-8") + "&"
                        + URLEncoder.encode("city", "UTF-8") + "=" + URLEncoder.encode(city, "UTF-8") + "&"
                        + URLEncoder.encode("state", "UTF-8") + "=" + URLEncoder.encode(state, "UTF-8") + "&"
                        + URLEncoder.encode("mob_no", "UTF-8") + "=" + URLEncoder.encode(mob_no, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream , "iso-8859-1"));

                String result = "";
                String line = "";
                while((line = bufferedReader.readLine())!= null)
                {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else if(type.equals("confirm"))
        {
            try
            {
                String updated = params[2];
                String train_no = params[1];
                String ur = "https://evangelistic-infect.000webhostapp.com/APP/book.php";
                URL url = new URL(ur);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("updated", "UTF-8") + "=" + URLEncoder.encode(updated, "UTF-8") + "&"
                        + URLEncoder.encode("train_no", "UTF-8") + "=" + URLEncoder.encode(train_no, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream , "iso-8859-1"));

                String result = "";
                String line = "";
                while((line = bufferedReader.readLine())!= null)
                {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPreExecute()
    {
        alertDialog  = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Status");
    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        alertDialog.show();
//        Toast.makeText(context, "Go ahead" + result, Toast.LENGTH_SHORT).show();
        if (result.equals("Login Success")) {

            Intent intent = new Intent(context, Search.class);
            context.startActivity(intent);
        }

        else if(result.equals("Login Unsuccess"))
        {
            Toast.makeText(context, "Invalid User id or password 2", Toast.LENGTH_SHORT).show();
        }

        if (result.contains("User Added")) {
            Intent intent2 = new Intent(context, Login.class);
            context.startActivity(intent2);
        }

        if(result.contains("search"))
        {
            context.startActivity(new Intent(context, Results.class));
        }

        if (result.contains("Booking Successful"))
        {
            Intent intent = new Intent(context, Search.class);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onProgressUpdate(Void... values)
    {
        super.onProgressUpdate(values);
    }
}
