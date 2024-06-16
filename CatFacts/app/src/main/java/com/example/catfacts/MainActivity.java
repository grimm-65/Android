package com.example.catfacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONObject;
import org.json.JSONException;



public class MainActivity extends AppCompatActivity {

    Button Btn_GetFact, Btn_GetcatImg;

    ImageView ImgGlide;

    String Imgurl= "https://cataas.com/cat";

    TextView joke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Btn_GetFact = findViewById(R.id.Btn_GetFact);
        Btn_GetcatImg = findViewById(R.id.Btn_GetCatImg);

        Btn_GetFact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

                String url = "https://catfact.ninja/fact";

                JsonObjectRequest request  = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    //Fuction is expecting a JSONOBJECT
                    public void onResponse(JSONObject response) {

                        joke = findViewById(R.id.joke);
                        try {

                            String fact = response.getString("fact");
                            joke.setText(fact);

                            //Convert the json response  to string
                           // Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_LONG).show();

                        }catch (Exception e){

                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(MainActivity.this,  error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
                queue.add(request);

            }

        });

        Btn_GetcatImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            ImgGlide = findViewById(R.id.ImgGlide);

                Glide.with(MainActivity.this)
                        .load("https://cataas.com/cat")
                        .fitCenter()
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .placeholder(R.drawable.ph)
                        .into(ImgGlide);

                //Toast.makeText(MainActivity.this, "Suuuuup", Toast.LENGTH_SHORT).show();
            }
        });

    }
}