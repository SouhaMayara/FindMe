package com.example.findme;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecyclerPositionAdapter
extends RecyclerView.Adapter <MyRecyclerPositionAdapter.MyViewHolder> {
   Context con;
   ArrayList<Position> data;
   public MyRecyclerPositionAdapter(Context con,ArrayList<Position> data)
   {
       this.con=con;
       this.data=data;
   }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //creation d'un view: Oncreate
        LayoutInflater inf=LayoutInflater.from(con);
        CardView element=(CardView)inf.inflate(R.layout.view_position,null);
        return new MyViewHolder(element);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //affectation de valeurs
        Position p=data.get(position);
        holder.tv_id.setText(""+p.id);
        holder.tv_num.setText(""+p.numero);
        holder.tv_lon.setText(""+p.longitude);
        holder.tv_lat.setText(""+p.latitude);
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_id,tv_num,tv_lon,tv_lat;
        Button btn_call,btn_map,btn_sms;
       public MyViewHolder(@NonNull View element) {
           super(element);
            //recup des composants : Holder
            tv_id=element.findViewById(R.id.tv_id_view);
            tv_num=element.findViewById(R.id.tv_num_view);
            tv_lon=element.findViewById(R.id.tv_lon_view);
            tv_lat=element.findViewById(R.id.tv_lat_view);
            btn_call=element.findViewById(R.id.btn_call_view);
            btn_map=element.findViewById(R.id.btn_map_view);
            btn_sms=element.findViewById(R.id.btn_sms_view);


           btn_call.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   int indice = getAdapterPosition();
                   Toast.makeText(con, "appel a :"+data.get(indice).numero, Toast.LENGTH_SHORT).show();
                   //faire appel
                   Intent callIntent = new Intent(Intent.ACTION_CALL);
                   callIntent.setData(Uri.parse("tel:"+data.get(indice).numero));
                   if(ActivityCompat.checkSelfPermission(con,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                       return;
                   }
                   con.startActivity(callIntent);
               }
           });
            //btn_call.setOnClickListener(new View.OnClickListener() {
              //  @Override
                //public void onClick(View view) {
                  //  int indice=getAdapterPosition();
                    //a faire: appeler le numero
                    //méthode1:lancer numerotation du tel juste bouton appeler
                    //méthode2:directement il fait numerotation et lance l'appel
                   /* Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel: "+tv_num));*/


                    /* String num=data.get(indice).numero;
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:"+num));
                    Toast.makeText(con, "appel à:"+data.get(indice).numero, Toast.LENGTH_SHORT).show();

                    if(ActivityCompat.checkSelfPermission(con,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                       return;
                    }
                    con.startActivity(intent);
                }
            });*/
            btn_map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Position p=data.get(getAdapterPosition());


                    Intent i = new Intent(con,MapsActivity.class);
                    i.putExtra("NUMERO",p.numero);
                    i.putExtra("LONGITUDE",p.longitude);
                    i.putExtra("LATITUDE",p.latitude);
                    con.startActivity(i);
                }
            });
            btn_sms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Position p=data.get(getAdapterPosition());

                    SmsManager manager=SmsManager.getDefault();//SIM1
                    manager.sendTextMessage(
                            p.numero,
                            null,
                            "Findme#10.78#36.46", // normalemet position automatique 
                            null,
                            null
                    );
                }
            });
        }
    }
}
