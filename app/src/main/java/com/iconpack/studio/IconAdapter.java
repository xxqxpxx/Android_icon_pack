package com.iconpack.studio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class IconAdapter extends BaseAdapter {
    
    private Context context;
    private List<IconItem> iconList;
    private LayoutInflater inflater;
    
    public IconAdapter(Context context, List<IconItem> iconList) {
        this.context = context;
        this.iconList = iconList;
        this.inflater = LayoutInflater.from(context);
    }
    
    @Override
    public int getCount() {
        return iconList.size();
    }
    
    @Override
    public Object getItem(int position) {
        return iconList.get(position);
    }
    
    @Override
    public long getItemId(int position) {
        return position;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.icon_item, parent, false);
            holder = new ViewHolder();
            holder.iconImage = convertView.findViewById(R.id.icon_image);
            holder.iconName = convertView.findViewById(R.id.icon_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        
        IconItem iconItem = iconList.get(position);
        holder.iconImage.setImageResource(iconItem.getIconResource());
        holder.iconName.setText(iconItem.getIconName());
        
        return convertView;
    }
    
    private static class ViewHolder {
        ImageView iconImage;
        TextView iconName;
    }
}

