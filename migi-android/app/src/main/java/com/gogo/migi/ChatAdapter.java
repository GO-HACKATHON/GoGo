package com.gogo.migi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.gogo.migi.model.Chat;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends BaseAdapter {
    public class ViewHolder {
        TextView chatBox;
    }

    public Context context;
    List<Chat> chats;
    ArrayList<Chat> chatLists;

    public ChatAdapter(List<Chat> apps, Context context) {
        this.chats = apps;
        this.context = context;
        chatLists = new ArrayList<Chat>();
        chatLists.addAll(chats);
    }

    @Override
    public int getCount() {
        return chats.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        ViewHolder viewHolder;

        if (rowView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            rowView = inflater.inflate(R.layout.item_list, null);
            // configure view holder
            viewHolder = new ViewHolder();
            viewHolder.chatBox = (TextView) rowView.findViewById(R.id.chat_box);
            rowView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.chatBox.setText(chats.get(position).getMessage() + "");

        // Listen for ListView Item Click
//        rowView.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // Send single item click data to ViewDetailProduct Class
//                Intent intent = new Intent(context, MainActivity.class);
//                // Pass all product id
//                intent.putExtra(Config.PROD_ID,(product.get(position).getProductId()));
//
//                context.startActivity(intent);
//            }
//        });

        return rowView;
    }
}
