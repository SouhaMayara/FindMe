package com.example.findme;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class Ajout extends AppCompatActivity {

    private Button btnval,btnqte;
    private EditText ednbr,edlong,edlat;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);

        Bundle b= this.getIntent().getExtras();
        if(b!=null){
            String body = b.getString("BODY");
        }

        btnval = findViewById(R.id.btnval_ajout);
        btnqte = findViewById(R.id.btnqte_ajout);

        ednbr = findViewById(R.id.ednum_ajout);
        edlong = findViewById(R.id.edlong_ajout);
        edlat = findViewById(R.id.edlat_ajout);

        btnqte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ajout.this.finish();
            }
        });

        btnval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nbr = ednbr.getText().toString();
                String lon = edlong.getText().toString();
                String lat = edlat.getText().toString();

                PositionManager manager = new PositionManager(Ajout.this,PositionHelper.nom_fichier,1);
                manager.inserer(nbr,lon,lat);
            }
        });

        //Bundle
        b =this.getIntent().getExtras();
        if(b!=null){
            String body = b.getString("BODY");
            String num = b.getString("NUMERO");
            ednbr.setText(num);
            String [] t = body.split("#");
            edlong.setText(t[1]);
            edlat.setText(t[2]);
        }else{
            ednbr.setHint("ecrire votre numero ...");//recuoeration numero de sim
            FusedLocationProviderClient mClient=
                    LocationServices.getFusedLocationProviderClient(this.getApplicationContext()
                    );
            mClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location!=null){
                        edlong.setText(location.getLongitude()+"");
                        edlat.setText(location.getLatitude()+"");
                    }
                }
            });
            LocationRequest request = LocationRequest.create().setInterval(10).setSmallestDisplacement(10);
            LocationCallback mcall = new LocationCallback(){
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    Location location = locationResult.getLastLocation();
                    if(location!=null){
                        edlong.setText(location.getLongitude()+"");
                        edlat.setText(location.getLatitude()+"");
                    }
                }
            };
            mClient.requestLocationUpdates(request,mcall,null);
        }
    }
}