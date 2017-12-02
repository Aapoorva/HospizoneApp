package com.example.apoorva.hospizone;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SpecDocList extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DatabaseReference databaseReference;
    String field,hospital;
    RecyclerView spec_Doc_List;

    static String DocName,Experience,Degree,Fee,Schedule;
    static Boolean clicked;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spec_doc_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        hospital = i.getStringExtra("hospital");
        field = i.getStringExtra("field");

        clicked = false;

        databaseReference = FirebaseDatabase.getInstance().getReference().child("indian hospital").child(field);
        databaseReference.keepSynced(true);

        spec_Doc_List = (RecyclerView)findViewById(R.id.r_docList);
        spec_Doc_List.setHasFixedSize(true);
        spec_Doc_List.setLayoutManager(new LinearLayoutManager(this));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }



    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<DocListCard,DocListViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<DocListCard,
                DocListViewHolder>(
                DocListCard.class,
                R.layout.spec_doc_list_card,
                DocListViewHolder.class,
                databaseReference
        ){
            @Override
            protected void populateViewHolder(DocListViewHolder viewHolder, DocListCard model, int position) {

                try {
                    viewHolder.setDocName(model.getDocName());
                    viewHolder.setDegree(model.getDegree());
                    viewHolder.setExperience(model.getExperience());
                }
                catch (Exception e){

                    Toast.makeText(SpecDocList.this,e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        };

        spec_Doc_List.setAdapter(firebaseRecyclerAdapter);
    }


    public static class DocListViewHolder extends RecyclerView.ViewHolder {

        View mview;
        private CardView cardView;

        TextView textView_Name, textView_Exp, textView_deg;

        public DocListViewHolder(View itemView) {
            super(itemView);
            mview = itemView;

            textView_Name = (TextView) mview.findViewById(R.id.text_name);
            textView_Exp = (TextView) mview.findViewById(R.id.text_exp);
            textView_deg = (TextView) mview.findViewById(R.id.text_degree);

            //listener set on ENTIRE ROW, you may set on individual components within a row.
            mview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mClickListener.onItemLongClick(v, getAdapterPosition());
                    return true;
                }
            });
        }

        DocListViewHolder.ClickListener mClickListener;

        //Interface to send callbacks...
        public interface ClickListener{
            public void onItemClick(View view, int position);
            public void onItemLongClick(View view, int position);
        }

        public void setOnClickListener(DocListViewHolder.ClickListener clickListener){
            mClickListener = clickListener;
        }

        public void setDocName(String Name){
            TextView textViewname = (TextView)mview.findViewById(R.id.text_name);
            textViewname.setText(Name);
        }

        public void setExperience(String Experience){
            TextView textView = (TextView)mview.findViewById(R.id.text_exp);
            textView.setText(Experience);
        }

        public void setDegree(String degree){
            TextView textView = (TextView)mview.findViewById(R.id.text_degree);
            textView.setText(degree);
        }
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
        getMenuInflater().inflate(R.menu.spec_doc_list, menu);
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
