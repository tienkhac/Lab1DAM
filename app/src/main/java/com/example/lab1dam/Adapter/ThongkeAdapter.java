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
import com.example.lab1dam.MODEL.HoaDonChiTiet;
import com.example.lab1dam.R;

import java.util.List;

public class ThongkeAdapter extends BaseAdapter {
    List<HoaDonChiTiet> arrHoaDonChiTiet;
    public Activity context;
    public LayoutInflater inflater;
    HoaDonChiTietDAO hoaDonChiTietDAO;

    public ThongkeAdapter(Activity context, List<HoaDonChiTiet> arrHoaDonChiTiet) {
        super();
        this.context = context;
        this.arrHoaDonChiTiet = arrHoaDonChiTiet;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(context);
    }
    @Override
    public int getCount() {
        return arrHoaDonChiTiet.size();
    }
    @Override
    public Object getItem(int i) {
        return arrHoaDonChiTiet.get(i);
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    public  static  class ViewHolder {
        TextView tvMasach;
        TextView tvSoluong;
        TextView tvGiaBia;
        TextView tvThanhTien;
        ImageView ivDelete;
    }

 @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

     if(convertView==null){
         holder = new ViewHolder();
         convertView = inflater.inflate(R.layout.item_hoa_don_chi_tiet, null);
         holder.tvMasach = (TextView) convertView.findViewById(R.id.tvMasach);
         holder.tvSoluong = (TextView) convertView.findViewById(R.id.tvSoluong);
         holder.tvGiaBia = (TextView) convertView.findViewById(R.id.tvGiaBia);
         holder.tvThanhTien = (TextView) convertView.findViewById(R.id.tvThanhTien);
         holder.ivDelete = (ImageView) convertView.findViewById(R.id.ivDelete);
         holder.ivDelete.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 hoaDonChiTietDAO.deleteHoaDonChiTietByID(String.valueOf(arrHoaDonChiTiet.get(position).getMaHDCT()));
                 arrHoaDonChiTiet.remove(position);
                 notifyDataSetChanged();
             }
         });
         convertView.setTag(holder);
     }
     else
         holder = (ViewHolder) convertView.getTag();

     HoaDonChiTiet _entry = (HoaDonChiTiet) arrHoaDonChiTiet.get(position);
     holder.tvMasach.setText("Mã Sách: "+_entry.getSach().getMaSach());
     holder.tvSoluong.setText("Số Lượng: "+_entry.getSoLuongMua());
     holder.tvGiaBia.setText("Giá bìa: "+_entry.getSach().getGiaBia() +" VND");
     holder.tvThanhTien.setText("Thành Tiền: "+_entry.getSoLuongMua()*_entry.getSach().getGiaBia() + "VND");
     return  convertView;
    }

    public void notifyDataSetChanged(){
        super.notifyDataSetChanged();
    }

    public  void changeDataset(List<HoaDonChiTiet> items){
        this.arrHoaDonChiTiet = items;
        notifyDataSetChanged();
    }
}
