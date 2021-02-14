package com.example.findme;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private boolean permission_sms=false;
    //ListView lv;
    RecyclerView rv;
    boolean permission_gps= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //traitement des permissions
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)
        {
            permission_sms = true;
        }
        else{
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.RECEIVE_SMS,
                            Manifest.permission.READ_SMS,
                            Manifest.permission.SEND_SMS,
                            Manifest.permission.CALL_PHONE}
                    ,
                    1);
        }

        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            permission_gps = true;
        }
        else{
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION}
                    ,
                    2);
        }

        rv=findViewById(R.id.rv);
        /*PositionManager pm=new PositionManager(this,
                PositionHelper.nom_fichier,
                PositionHelper.version_base
                );
        ArrayList<Position> data=pm.selectionnertout();//new ArrayList<Position>();
        ArrayAdapter ad=new ArrayAdapter(this,android.R.layout.simple_list_item_1,
                data);
        lv.setAdapter(ad);*/
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //on peut supp snackbar et ajouter intent
                Snackbar.make(view, "Lancer l'interface ajout", Snackbar.LENGTH_LONG)
                        .setAction("Nouveau", new View.OnClickListener() {//bouton nouveau va lancer ajout
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(MainActivity.this,Ajout.class));
                            }
                        }).show();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();


        /*String num = "25069923";
        String lon = "12";
        String lat = "11";*/
        PositionManager pm=new PositionManager(this,
                PositionHelper.nom_fichier,
                PositionHelper.version_base);
        pm.inserer("92141615","11","12");

        ArrayList<Position> data=pm.selectionnertout();//new ArrayList<Position>();
        //ArrayAdapter ad=new ArrayAdapter(this,android.R.layout.simple_list_item_1,data);//MyPositionAdapter ad=
        MyRecyclerPositionAdapter ad=new MyRecyclerPositionAdapter(this,data);
        GridLayoutManager manager=new GridLayoutManager(this,1);
        rv.setLayoutManager(manager);
        rv.setAdapter(ad);//affiche les donnée forme simple
        //lv.setOnItemClickListener();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1)
        {
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                permission_sms=true;
            }
            else{
                this.finish();
            }
        }
    }


}