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

import com.example.lab1dam.DAO.SachDAO;
import com.example.lab1dam.MODEL.Sach;
import com.example.lab1dam.R;

import java.util.ArrayList;
import java.util.List;

public class SachAdapter extends BaseAdapter implements Filterable {
   List<Sach> arrSach;
    List<Sach> arrSOrtSach;
    private  Filter sachFilter;
    private Activity context;
    public LayoutInflater inflater;
    SachDAO sachDAO;

 public SachAdapter(Activity context, List<Sach> arraySach){
     super();
     this.context = context;
     this.arrSach = arraySach;
     this.arrSOrtSach = arraySach;
     this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
     sachDAO = new SachDAO(context);
 }
    @Override
    public int getCount() {
        return arrSach.size();
    }

    @Override
    public Object getItem(int i) {
        return arrSach.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    public  static  class ViewHolder {
        ImageView ivIcon;
      TextView tvBookPrice;
      TextView tvBookName;
      TextView tvSoluong;
      ImageView ivDelete;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if(view==null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_book, null);
            holder.ivIcon = (ImageView) view.findViewById(R.id.ivIcon);
            holder.tvBookPrice = (TextView) view.findViewById(R.id.tvBookPrice);
            holder.tvBookName = (TextView) view.findViewById(R.id.tvBookName);
            holder.tvSoluong = (TextView) view.findViewById(R.id.tvSoluong);
            holder.ivDelete = (ImageView) view.findViewById(R.id.ivDelete);
            holder.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sachDAO.deleteSachByID(arrSach.get(position).getMaSach());
                    arrSach.remove(position);
                    notifyDataSetChanged();
                }
            });
            view.setTag(holder);
        }
        else
            holder =(ViewHolder) view.getTag();

        Sach _entry = (Sach) arrSach.get(position);
        holder.ivIcon.setImageResource(R.drawable.bookicon);
        holder.tvBookName.setText("Mã Sách: "+_entry.getMaSach());
        holder.tvSoluong.setText("Số Lượng: "+_entry.getSoLuong());
        holder.tvBookPrice.setText("Giá: "+_entry.getGiaBia()+"");

        return  view;
    }

    public void notifyDataSetChanged(){
     super.notifyDataSetChanged();
    }

    public  void changeDataset(List<Sach> items){
     this.arrSach = items;
     notifyDataSetChanged();
    }

    public  void  resetData(){
     arrSach =arrSOrtSach;
    }

    @Override
    public Filter getFilter() {
     if(sachFilter == null)
         sachFilter = new CustomFilter();
        return sachFilter;
    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constrains) {
            FilterResults results = new FilterResults();
            if (constrains == null || constrains.length() == 0){
                results.values = arrSOrtSach;
                results.count = arrSOrtSach.size();
            }
            else {
                List<Sach> lsSach = new ArrayList<Sach>();
                for(Sach p : arrSach){
                    if(p.getMaSach().toUpperCase().startsWith(constrains.toString().toUpperCase()))
                        lsSach.add(p);
                }
                results.values = lsSach;
                results.count= lsSach.size();
            }
            return  results;
        }

        @Override
        protected void publishResults(CharSequence constrains, FilterResults results) {
           if(results.count == 0)
               notifyDataSetChanged();
           else {
               arrSach = (List<Sach>) results.values;
               notifyDataSetChanged();
           }
        }
    }
}
