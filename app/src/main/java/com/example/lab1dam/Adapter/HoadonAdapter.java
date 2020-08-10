package com.example.lab1dam.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab1dam.DAO.HoaDonChiTietDAO;
import com.example.lab1dam.DAO.HoaDonDAO;
import com.example.lab1dam.DAO.SachDAO;
import com.example.lab1dam.MODEL.HoaDon;
import com.example.lab1dam.MODEL.Sach;
import com.example.lab1dam.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoadonAdapter extends BaseAdapter implements Filterable {
    List<HoaDon> arrHoaDon;
    List<HoaDon> arrSOrtHoaDon;
    private  Filter HoaDonFilter;
    private Activity context;
    public LayoutInflater inflater;
    HoaDonDAO hoaDonDAO;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public HoadonAdapter(Activity context, List<HoaDon> arrayHoaDon) {
        super();
        this.context = context;
        this.arrHoaDon = arrayHoaDon;
        this.arrSOrtHoaDon = arrayHoaDon;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        hoaDonDAO = new HoaDonDAO(context);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(context);
    }
    @Override
    public int getCount() {
        return arrHoaDon.size();
    }

    @Override
    public Object getItem(int i) {
        return arrHoaDon.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    public  static  class ViewHolder {
        ImageView ivIcon;
        TextView tvMaHoaDon;
        TextView tvNgayMua;
        ImageView ivDelete;
    }


    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if(view==null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_hoadon, null);
            holder.ivIcon = (ImageView) view.findViewById(R.id.ivIcon);
            holder.tvMaHoaDon = (TextView) view.findViewById(R.id.tvMaHoaDon);
            holder.tvNgayMua = (TextView) view.findViewById(R.id.tvNgayMua);
            holder.ivDelete = (ImageView) view.findViewById(R.id.ivDelete);
            holder.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(hoaDonChiTietDAO.checkHoaDon(arrHoaDon.get(position).getMaHoaDon())){
                        Toast.makeText(context,"Bạn phải xóa hóa đơn chi tiết trước khi xóa hóa đơn này", Toast.LENGTH_LONG).show();
                    }
                    else {
                        hoaDonDAO.deleteHoaDonByID(arrHoaDon.get(position).getMaHoaDon());
                        arrHoaDon.remove(position);
                        notifyDataSetChanged();
                    }
                }
            });
            view.setTag(holder);
        }
        else
            holder =(ViewHolder) view.getTag();

        HoaDon _entry = (HoaDon) arrHoaDon.get(position);
        holder.ivIcon.setImageResource(R.drawable.hdicon);
        holder.tvMaHoaDon.setText(_entry.getMaHoaDon());
        holder.tvNgayMua.setText(sdf.format(_entry.getNgayMua()));

        return  view;
    }

    public void notifyDataSetChanged(){
        super.notifyDataSetChanged();
    }

    public  void changeDataset(List<HoaDon> items){
        this.arrHoaDon = items;
        notifyDataSetChanged();
    }

    public  void  resetData(){
        arrHoaDon =arrSOrtHoaDon;
    }
    @Override
    public Filter getFilter() {
        if(HoaDonFilter == null)
            HoaDonFilter = new CustomFilter();
        return HoaDonFilter;
    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();
            if (charSequence == null || charSequence.length() == 0){
                results.values = arrSOrtHoaDon;
                results.count = arrSOrtHoaDon.size();
            }
            else {
                List<HoaDon> lsHoaDon = new ArrayList<HoaDon>();
                for(HoaDon p : arrHoaDon){
                    if(p.getMaHoaDon().toUpperCase().startsWith(charSequence.toString().toUpperCase()))
                        lsHoaDon.add(p);
                }
                results.values = lsHoaDon;
                results.count= lsHoaDon.size();
            }
            return  results;

        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            if(results.count == 0)
                notifyDataSetChanged();
            else {
                arrHoaDon = (List<HoaDon>) results.values;
                notifyDataSetChanged();
            }
        }
    }
}
