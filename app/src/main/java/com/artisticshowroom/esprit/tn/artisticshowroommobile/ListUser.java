package com.artisticshowroom.esprit.tn.artisticshowroommobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.artisticshowroom.esprit.tn.artisticshowroommobile.Entity.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListUser extends AppCompatActivity {

    ListView list;
    List<String> lusers = new ArrayList<>();
    List<User> users = new ArrayList<>();
    Button btnRecherche;
    Button btnUndo;
    EditText etRecherche;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        btnRecherche = (Button) findViewById(R.id.btn_recherche);
        btnUndo = (Button) findViewById(R.id.btn_undo);
        etRecherche = (EditText) findViewById(R.id.recherche);
        btnRecherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rechercheusers(etRecherche.getText().toString());
            }
        });

        btnUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allUser();
            }
        });

        allUser();
        list =(ListView) findViewById(R.id.list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(position);
                Intent i = new Intent(ListUser.this,detailluserActivity.class);
                i.putExtra("User",users.get(position));
                startActivity(i);
            }
        });
    }


    private void rechercheusers(String s) {
        lusers.clear();
        users.clear();
        JsonArrayRequest request = new JsonArrayRequest("http://192.168.43.36:18080/ArtisticShowroom-web/rest/users/getuser/"+s,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        for(int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                User u = new User(jsonObject);
                                lusers.add(u.getFirstName() + "\n" + u.getLastName());
                                users.add(u);

                            }
                            catch(JSONException e) {
                            }
                        }

                        ArrayAdapter adapter = new ArrayAdapter<String>(ListUser.this, R.layout.activity_listview, lusers);

                        adapter.notifyDataSetChanged();
                        list.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Toast.makeText(MainActivity.this, "Unable to fetch data: " + volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println(volleyError.getMessage());
                    }
                });
        MySingleton.getInstance(this).addToRequestQueue(request);
    }

    private void allUser() {
        lusers.clear();
        users.clear();

        JsonArrayRequest request = new JsonArrayRequest("http://192.168.43.36:18080/ArtisticShowroom-web/rest/users",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        for(int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                 User u = new User(jsonObject);
                                lusers.add(u.getFirstName() + "\n" + u.getLastName());
                                users.add(u);

                            }
                            catch(JSONException e) {
                            }
                        }

                        ArrayAdapter adapter = new ArrayAdapter<String>(ListUser.this, R.layout.activity_listview, lusers);
                        list.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Toast.makeText(MainActivity.this, "Unable to fetch data: " + volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println(volleyError.getMessage());
                    }
                });
        MySingleton.getInstance(this).addToRequestQueue(request);

    }


}
