package com.artisticshowroom.esprit.tn.artisticshowroommobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.artisticshowroom.esprit.tn.artisticshowroommobile.Entity.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class detailluserActivity extends AppCompatActivity implements View.OnClickListener {
    EditText name, lastname,login, pass;
    Button delete;
    Button update;
    Button upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailluser);
        Bundle b = getIntent().getExtras();

        final User  user = (User) getIntent().getSerializableExtra("User");

        name = (EditText) findViewById(R.id.detailname);
        lastname=(EditText) findViewById(R.id.detaillastname);
        login= (EditText) findViewById(R.id.detaillogin);
        pass=(EditText) findViewById(R.id.pass);
        name.setText(user.getFirstName());
        lastname.setText(user.getLastName());
        login.setText(user.getLogin());
        pass.setText(user.getPassword());
        delete = (Button) findViewById(R.id.btn_delete);
        update = (Button) findViewById(R.id.btn_update);
        upload = (Button) findViewById(R.id.btn_uplodimg);

         upload.setOnClickListener(this);


        delete.setOnClickListener(new View.OnClickListener() {

            int id = user.getId();
            @Override
            public void onClick(View v) {
                String url = "http://192.168.43.36:18080/ArtisticShowroom-web/rest/users/delete/"+user.getId();

                StringRequest postRequest = new StringRequest(Request.Method.DELETE, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }
                ) {

                };
                MySingleton.getInstance(detailluserActivity.this).addToRequestQueue(postRequest);
                Intent i = new Intent(detailluserActivity.this,ListUser.class);
                startActivity(i);


            }
        });

        update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String url = "http://192.168.43.36:18080/ArtisticShowroom-web/rest/users/uppuser/"+user.getId();
                JSONObject js = new JSONObject();
                try {

                    js.put("firstName",name.getText().toString());
                    js.put("lastName",lastname.getText().toString());
                    js.put("login",login.getText().toString());
                    js.put("password",pass.getText().toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                        Request.Method.PUT, url, js,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {

                    /**
                     * Passing some request headers
                     */
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json; charset=utf-8");
                        return headers;
                    }

                };
                MySingleton.getInstance(detailluserActivity.this).addToRequestQueue(jsonObjReq);
                Intent i = new Intent(detailluserActivity.this,ListUser.class);
                startActivity(i);


            }
        });

    }
    @Override
    public void onClick(View v) {
        Intent intent;
        if (v==upload){

            intent= new Intent(detailluserActivity.this,UploadimageActivity.class);
            startActivity(intent);
        }

    }
}
