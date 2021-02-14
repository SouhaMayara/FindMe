package com.example.findme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class MyPositionAdapter extends BaseAdapter {
    Context con;
    ArrayList<Position> data;

    public MyPositionAdapter(Context con, ArrayList<Position> data) {
        this.con = con;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //creation d'un view: Oncreate
        LayoutInflater inf=LayoutInflater.from(con);
        CardView element=(CardView)inf.inflate(R.layout.view_position,null);

        //recup des composants : Holder
        TextView tv_id=element.findViewById(R.id.tv_id_view);
        TextView tv_num=element.findViewById(R.id.tv_num_view);
        TextView tv_lon=element.findViewById(R.id.tv_lon_view);
        TextView tv_lat=element.findViewById(R.id.tv_lat_view);
        Button btn_call=element.findViewById(R.id.btn_call_view);
        Button btn_map=element.findViewById(R.id.btn_map_view);
        Button btn_sms=element.findViewById(R.id.btn_sms_view);

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(con, "appel de :"+data.get(i).numero,Toast.LENGTH_SHORT).show();
            }
        });
        //affectation de valeurs
        Position p=data.get(i);
        tv_id.setText(""+p.id);
        tv_num.setText(""+p.numero);
        tv_lon.setText(""+p.longitude);
        tv_lat.setText(""+p.latitude);


        return element;
    }
}
