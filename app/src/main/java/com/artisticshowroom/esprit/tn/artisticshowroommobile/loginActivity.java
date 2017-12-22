package com.artisticshowroom.esprit.tn.artisticshowroommobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.artisticshowroom.esprit.tn.artisticshowroommobile.Entity.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class loginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText txtlogin;
    EditText txtpwd;
    Button btnsignin;
    Button btnsignup;

    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtlogin =(EditText)findViewById(R.id.logintxt);
        txtpwd =(EditText)findViewById(R.id.pwdtxt);
        btnsignin= (Button)findViewById(R.id.signin);
        btnsignup = (Button)findViewById(R.id.btnsignup);

        btnsignup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
             Intent   intent= new Intent(loginActivity.this,InscriActivity.class);
                startActivity(intent);

            }

        });
        btnsignin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.e("zix : ","tesss");
                login(txtlogin.getText().toString(),txtpwd.getText().toString());
            }

        });


    }

  public void login (final String log, final String pass){

  String URL="http://192.168.43.36:18080/ArtisticShowroom-web/rest/users/auth/"+log+"/"+pass;

      StringRequest postRequest = new StringRequest(Request.Method.GET, URL,
              new Response.Listener<String>() {
                  @Override
                  public void onResponse(String response) {

                      Log.d("Response", response);


                      try {
                          JSONObject j = new JSONObject(response);
                          User user = new User(j);
                          Intent intent = new Intent(getBaseContext(), ProfilActivity.class);
                          intent.putExtra("User", (Serializable) user);
                          startActivity(intent);

                      } catch (JSONException e) {
                          e.printStackTrace();
                      }


                  }


              },
              new Response.ErrorListener() {
                  @Override
                  public void onErrorResponse(VolleyError error) {
                      // error
                      Log.d("Error.Response", error.toString());
                  }
              }
      ) {

      };
      queue = Volley.newRequestQueue(loginActivity.this);
      queue.add(postRequest);
  }



    @Override
    public void onClick(View v) {
        Intent intent;
        if (v==btnsignin){

            intent= new Intent(loginActivity.this,ProfilActivity.class);
            startActivity(intent);
        }

        else if(v==btnsignup)
        {
            intent= new Intent(loginActivity.this,InscriActivity.class);
            startActivity(intent);
        }


    }


}
