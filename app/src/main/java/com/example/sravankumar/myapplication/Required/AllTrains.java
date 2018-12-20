package com.example.sravankumar.myapplication.Required;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.sravankumar.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AllTrains extends AppCompatActivity {


    List<train> trainList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager recyclerViewlayoutManager;
    RecyclerView.Adapter recyclerViewadapter;
    ProgressBar progressBar;
    String from, to;

    String HTTP_JSON_URL = "http://evangelistic-infect.000webhostapp.com/APP/search1.php";
    String GET_JSON_FROM_SERVER_NAME_1 = "train_no";
    String GET_JSON_FROM_SERVER_NAME_2 = "source";
    String GET_JSON_FROM_SERVER_NAME_3 = "dest";
    String GET_JSON_FROM_SERVER_NAME_4 = "arrival_time";
    String GET_JSON_FROM_SERVER_NAME_5 = "departure_time";
    String GET_JSON_FROM_SERVER_NAME_6 = "train_name";
    String GET_JSON_FROM_SERVER_NAME_7 = "avail_seats_sl";
    String GET_JSON_FROM_SERVER_NAME_8 = "price_sl";
    JsonArrayRequest jsonArrayRequest ;
    RequestQueue requestQueue ;
    View ChildView ;
    int GetItemPosition ;
    ArrayList<String> TrainNames, sourceNames, destNames, arrTimes, deptTimes, TrainNos, availNos, priceNos;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);
        Bundle extras = getIntent().getExtras();
        assert extras != null;
//        from = extras.getString("from");
//        to = extras.getString("to");
//    String date = getIntent().getStringExtra("Date");

        trainList = new ArrayList<>();
        recyclerView =  findViewById(R.id.recyclerView1);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        recyclerView.setHasFixedSize(true);
        recyclerViewlayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerViewlayoutManager);
        progressBar.setVisibility(View.VISIBLE);

        TrainNames = new ArrayList<>();
        sourceNames = new ArrayList<>();
        destNames = new ArrayList<>();
        arrTimes = new ArrayList<>();
        deptTimes = new ArrayList<>();
        TrainNos = new ArrayList<>();
        availNos = new ArrayList<>();
        priceNos = new ArrayList<>();

        JSON_DATA_WEB_CALL();

        /*recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(AllTrains.this, new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent motionEvent) {

                    return true;
                }


            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

                ChildView = Recyclerview.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                if(ChildView != null && gestureDetector.onTouchEvent(motionEvent)) {

                    GetItemPosition = Recyclerview.getChildAdapterPosition(ChildView);

                    Toast.makeText(AllTrains.this, TrainNames.get(GetItemPosition), Toast.LENGTH_LONG).show();
                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });*/

    }

    private void JSON_DATA_WEB_CALL() {

        jsonArrayRequest = new JsonArrayRequest("http://evangelistic-infect.000webhostapp.com/APP/search1.php",

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        progressBar.setVisibility(View.GONE);

                        JSON_PARSE_DATA_AFTER_WEBCALL(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(jsonArrayRequest);

    }

    public void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array){

        for(int i = 0; i<array.length(); i++) {

            train GetDataAdapter2 = new train();


            JSONObject json = null;
            try {
                json = array.getJSONObject(i);

                GetDataAdapter2.setTrain_no(json.getString(GET_JSON_FROM_SERVER_NAME_1));
                GetDataAdapter2.setSource(json.getString(GET_JSON_FROM_SERVER_NAME_2));
                GetDataAdapter2.setDest(json.getString(GET_JSON_FROM_SERVER_NAME_3));
                GetDataAdapter2.setArr_time(json.getString(GET_JSON_FROM_SERVER_NAME_4));
                GetDataAdapter2.setDept_time(json.getString(GET_JSON_FROM_SERVER_NAME_5));
                GetDataAdapter2.setTrain_name(json.getString(GET_JSON_FROM_SERVER_NAME_6));
                GetDataAdapter2.setavail_seats(json.getString(GET_JSON_FROM_SERVER_NAME_7));
                GetDataAdapter2.setPrice(json.getString(GET_JSON_FROM_SERVER_NAME_8));
                TrainNames.add(json.getString(GET_JSON_FROM_SERVER_NAME_1));
                sourceNames.add(json.getString(GET_JSON_FROM_SERVER_NAME_2));
                destNames.add(json.getString(GET_JSON_FROM_SERVER_NAME_3));
                arrTimes.add(json.getString(GET_JSON_FROM_SERVER_NAME_4));
                deptTimes.add(json.getString(GET_JSON_FROM_SERVER_NAME_5));
                TrainNos.add(json.getString(GET_JSON_FROM_SERVER_NAME_6));
                availNos.add(json.getString(GET_JSON_FROM_SERVER_NAME_5));
                priceNos.add(json.getString(GET_JSON_FROM_SERVER_NAME_6));


            } catch (JSONException e) {

                e.printStackTrace();
            }
            trainList.add(GetDataAdapter2);
        }

        recyclerViewadapter = new RecyclerViewCardViewAdapter(trainList, this);

        recyclerView.setAdapter(recyclerViewadapter);

    }


}
