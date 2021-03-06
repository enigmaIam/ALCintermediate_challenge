package com.example.itis.alcintermediate;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.itis.alcintermediate.controller.Detail_Activity;
import com.example.itis.alcintermediate.model.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by enigma on 8/21/17.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<Item> items;
    private Context context;

    public ItemAdapter(Context applicationContext, List<Item> itemArraylist) {
        this.context = applicationContext;
        this.items = itemArraylist;
    }

    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_user, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder viewHolder, int i) {
        viewHolder.title.setText(items.get(i).getLogin());
        viewHolder.githublink.setText(items.get(i).getHtmlUrl());

        Picasso.with(context)
                .load(items.get(i).getAvatarUrl())
                .transform(new CircleTransform())
                .placeholder(R.drawable.load)
                .into(viewHolder.imageView);


    }

    @Override
    public int getItemCount() {
        return items.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, githublink;
        private ImageView imageView;


        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            githublink = (TextView) view.findViewById(R.id.gitlink);
            imageView = (ImageView) view.findViewById(R.id.cover);


            //on itemclick

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Item clickedDataItem = items.get(pos);
                        Intent intent = new Intent(context, Detail_Activity.class);
                        intent.putExtra("login", items.get(pos).getLogin());
                        intent.putExtra("html_url", items.get(pos).getHtmlUrl());
                        intent.putExtra("avatar_url", items.get(pos).getAvatarUrl());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);


                    }
                }


            });


        }
    }

}

