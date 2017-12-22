package com.artisticshowroom.esprit.tn.artisticshowroommobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InscriActivity extends AppCompatActivity {

    Button btnSubmit;
    EditText etlogin,etpassword,etlnom,etprenom,etemail,etphone,etage;
    RadioGroup radioGroup ;
    RadioButton radioButton ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscri);
        etlogin = (EditText) findViewById(R.id.etLog);
        etpassword =(EditText) findViewById(R.id.etPwd);
        etlnom = (EditText) findViewById(R.id.etNom);
        etprenom = (EditText) findViewById(R.id.etPre);
        etemail = (EditText) findViewById(R.id.etEmail);
        etage = (EditText) findViewById(R.id.etAge);
        btnSubmit = (Button) findViewById(R.id.SubmitUser);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postAgent();
            }
        });

        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);

    }




    public void postAgent()
    {
        String nom = etlnom.getText().toString();
        String prenom = etprenom.getText().toString();
        String login = etlogin.getText().toString();
        String pass = etpassword.getText().toString();
        String email = etemail.getText().toString();
        String age = etage.getText().toString();


        postUserVollay(nom,prenom,login,pass,email,age);

        Intent i = new Intent(this,loginActivity.class);
        startActivity(i);
    }
    private void postUserVollay(final String nom, final String prenom, final String login,final String pwd, final String mail, final String age) {
         String url = "" ;
        String nul ="aa";
        int a=1;
        int selectedID = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedID);

        if(radioButton.getText().equals("client")){
            url = "http://192.168.43.36:18080/ArtisticShowroom-web/rest/users/client";
        }else if(radioButton.getText().equals("owner")){
            url = "http://192.168.43.36:18080/ArtisticShowroom-web/rest/users/owner/";

        }else if(radioButton.getText().equals("artist"))
            {
            url = "http://192.168.43.36:18080/ArtisticShowroom-web/rest/users/artist/";

        }
        JSONObject js = new JSONObject();
        try {

            js.put("firstName",nom);
            js.put("lastName",prenom);
            js.put("login",login);
            js.put("password",pwd);
            js.put("mail",mail);
            js.put("age",age);
            System.out.println(js+"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST, url, js,
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
        MySingleton.getInstance(this).addToRequestQueue(jsonObjReq);
    }

}
