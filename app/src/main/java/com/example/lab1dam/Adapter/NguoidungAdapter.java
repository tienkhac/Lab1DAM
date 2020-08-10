package com.example.lab1dam.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab1dam.DAO.HoaDonChiTietDAO;
import com.example.lab1dam.DAO.NguoiDungDAO;
import com.example.lab1dam.MODEL.HoaDonChiTiet;
import com.example.lab1dam.MODEL.NguoiDung;
import com.example.lab1dam.R;

import java.util.List;

public class NguoidungAdapter extends BaseAdapter {
    List<NguoiDung> arrNguoiDung;
    private Activity context;
    public LayoutInflater inflater;
    NguoiDungDAO nguoiDungDAO;

    public NguoidungAdapter(Activity context, List<NguoiDung> arrayNguoiDung) {
        super();
        this.context = context;
        this.arrNguoiDung = arrayNguoiDung;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        nguoiDungDAO = new NguoiDungDAO(context);
    }

    @Override
    public int getCount() {
        return arrNguoiDung.size();
    }

    @Override
    public Object getItem(int i) {
        return arrNguoiDung.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    public  static  class ViewHolder {
        ImageView ivIcon;
        TextView tvName;
        TextView tvPhone;
        ImageView ivDelete;
    }
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if(view==null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_nguoidung, null);
            holder.ivIcon = (ImageView) view.findViewById(R.id.ivIcon);
            holder.tvName = (TextView) view.findViewById(R.id.tvName);
            holder.tvPhone = (TextView) view.findViewById(R.id.tvPhone);
            holder.ivDelete = (ImageView) view.findViewById(R.id.ivDelete);
           holder.ivDelete.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   nguoiDungDAO.deleteNguoiDungByID(arrNguoiDung.get(i).getUserName());
                   arrNguoiDung.remove(i);
                   notifyDataSetChanged();
               }
           });
            view.setTag(holder);
    }
        else
            holder=(ViewHolder)view.getTag();

        NguoiDung _entry = (NguoiDung) arrNguoiDung.get(i);
        if(i % 3 == 0){
            holder.ivIcon.setImageResource(R.drawable.emone);
        }else if (i % 3 == 1 ){
            holder.ivIcon.setImageResource(R.drawable.emtwo);
        }else {
            holder.ivIcon.setImageResource(R.drawable.emthree);
        }
        holder.tvName.setText(_entry.getHoTen());
        holder.tvPhone.setText(_entry.getPhone());
        return  view;
}
    public void notifyDataSetChanged(){
        super.notifyDataSetChanged();
    }

    public  void changeDataset(List<NguoiDung> items){
        this.arrNguoiDung = items;
        notifyDataSetChanged();
    }
}
