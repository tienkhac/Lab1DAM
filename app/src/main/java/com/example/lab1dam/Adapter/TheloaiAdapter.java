package com.example.lab1dam.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab1dam.DAO.NguoiDungDAO;
import com.example.lab1dam.DAO.TheLoaiDAO;
import com.example.lab1dam.List_theloai_Activity;
import com.example.lab1dam.MODEL.HoaDon;
import com.example.lab1dam.MODEL.NguoiDung;
import com.example.lab1dam.MODEL.TheLoai;
import com.example.lab1dam.R;

import java.util.ArrayList;
import java.util.List;

public class TheloaiAdapter extends BaseAdapter {
    List<TheLoai> arrTheLoai = new ArrayList<>() ;
    public Activity context;
    public LayoutInflater inflater;
    TheLoaiDAO theLoaiDAO;

    public TheloaiAdapter(Activity context, List<TheLoai> arrayTheLoai) {
        super();
        this.context = context;
        this.arrTheLoai = arrTheLoai;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        theLoaiDAO = new TheLoaiDAO(context);
    }

    @Override
    public int getCount() {
        return arrTheLoai.size();
    }

    @Override
    public Object getItem(int i) {
        return arrTheLoai.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    public  static  class ViewHolder {
        ImageView ivIcon;
        TextView tvMaTheLoai;
        TextView tvTenTheLoai;
        ImageView ivDelete;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if(view==null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_theloai, null);
            holder.ivIcon = (ImageView) view.findViewById(R.id.ivIcon);
            holder.tvMaTheLoai = (TextView) view.findViewById(R.id.tvMaTheLoai);
            holder.tvTenTheLoai = (TextView) view.findViewById(R.id.tvTenTheLoai);
            holder.ivDelete = (ImageView) view.findViewById(R.id.ivDelete);
            holder.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    theLoaiDAO.deleteTheLoaiByID(arrTheLoai.get(i).getMaTheLoai());
                    arrTheLoai.remove(i);
                    notifyDataSetChanged();
                }
            });
            view.setTag(holder);
    }
        else
            holder =(ViewHolder) view.getTag();

        TheLoai _entry = (TheLoai) arrTheLoai.get(i);
        holder.ivIcon.setImageResource(R.drawable.cateicon);
        holder.tvMaTheLoai.setText(_entry.getMaTheLoai());
        holder.tvTenTheLoai.setText(_entry.getTenTheLoai());

        return  view;
}
    public void notifyDataSetChanged(){
        super.notifyDataSetChanged();
    }

    public  void changeDataset(List<TheLoai> items){
        this.arrTheLoai = items;
        notifyDataSetChanged();
    }
}
