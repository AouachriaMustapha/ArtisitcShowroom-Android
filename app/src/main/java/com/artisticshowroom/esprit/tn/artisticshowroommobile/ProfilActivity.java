package com.artisticshowroom.esprit.tn.artisticshowroommobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.artisticshowroom.esprit.tn.artisticshowroommobile.Entity.User;

public class ProfilActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView et_fullname ;
    private EditText et_email ;
    private EditText et_password ;
    private TextView btnSubmit ;
    private ImageView img ;
    private EditText user_name ;
    private ImageButton btnEdit ;
    private LinearLayout layout_pass ;
    Button btnlist;
    Button btnlogout;

    User user ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        et_fullname = (TextView) findViewById(R.id.et_fullname) ;
        img= (ImageView) findViewById(R.id.imgProfile) ;
        user_name = (EditText) findViewById(R.id.user_name);
        et_email = (EditText) findViewById(R.id.et_email) ;
        et_password = (EditText) findViewById(R.id.et_password) ;
        user = (User) getIntent().getSerializableExtra("User");
        btnlogout=(Button)findViewById(R.id.Nuser);
        et_fullname.setText(user.getFirstName());
        et_email.setText(user.getMail());
        et_password.setText(user.getPassword());
        user_name.setText(user.getLogin());
        btnlogout.setOnClickListener(this);
         btnlist =(Button)findViewById(R.id.listuser);
          btnlist.setOnClickListener(new View.OnClickListener(){
                                       @Override
                                       public void onClick(View v) {
                                           Intent   intent= new Intent(ProfilActivity.this,ListUser.class);
                                           startActivity(intent);
                                       }

                                   }
        );
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        if (v==btnlist){

            intent= new Intent(ProfilActivity.this,ListUser.class);
            startActivity(intent);
        }
        else if(v==btnlogout){
            intent= new Intent(ProfilActivity.this,loginActivity.class);
            startActivity(intent);
            finish();

        }
    }
}
