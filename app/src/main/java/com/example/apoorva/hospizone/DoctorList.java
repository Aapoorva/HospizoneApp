package com.example.apoorva.hospizone;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.BufferUnderflowException;

public class DoctorList extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    EditText e_search;
    Button b_search,b_genPhy,b_neu,b_cardio,b_gynec,b_surgeon,b_dentist;

    String field,hospital;
    Intent i,i2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        i2 = getIntent();
        hospital = i2.getStringExtra("hospital");

        e_search = (EditText)findViewById(R.id.editText_search_Doctors);
        b_search = (Button)findViewById(R.id.b_search_Doctors);
        b_genPhy = (Button)findViewById(R.id.b_genPhy);
        b_neu = (Button)findViewById(R.id.b_neuro);
        b_cardio = (Button)findViewById(R.id.b_Cardio);
        b_gynec = (Button)findViewById(R.id.b_gynec);
        b_surgeon = (Button)findViewById(R.id.b_surgeon);
        b_dentist = (Button)findViewById(R.id.b_dentist);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        i = new Intent(DoctorList.this,SpecDocList.class);

        b_search.setOnClickListener(this);
        b_dentist.setOnClickListener(this);
        b_gynec.setOnClickListener(this);
        b_genPhy.setOnClickListener(this);
        b_neu.setOnClickListener(this);
        b_cardio.setOnClickListener(this);
        b_surgeon.setOnClickListener(this);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.b_search_Doctors){
            String search = e_search.getText().toString();
            if(!search.equals(""))
                field = search.toLowerCase();
            else {
                Toast.makeText(DoctorList.this,"Search field is empty",Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if(v.getId() == R.id.b_gynec){
            field = "gynecologist";
        }
        if(v.getId() == R.id.b_genPhy){
            field = "generalPhysician";
        }
        if(v.getId() == R.id.b_neuro){
            field = "neurologist";
        }
        if(v.getId() == R.id.b_Cardio){
            field = "cardiologist";
        }
        if(v.getId() == R.id.b_surgeon){
            field = "surgeon";
        }
        if(v.getId() == R.id.b_dentist){
            field = "dentist";
        }
        i.putExtra(field,"field");
        i.putExtra(hospital,"hospital");
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.doctor_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
