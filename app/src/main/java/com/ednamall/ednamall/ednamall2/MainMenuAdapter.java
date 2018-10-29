package com.ednamall.ednamall.ednamall2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.MyViewHolder> {

    private Context mContext;
    private List<MainMenu> menuList;

    public Animation animBounce,animZoomin,animZoomout;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);

        }

    }

    public MainMenuAdapter(Context mContext, List<MainMenu> menuList) {
        this.mContext = mContext;
        this.menuList = menuList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }
    private int lastPosition = -1;

    /**
     * Here is the key method to apply the animation
     */
    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.bounce);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    private void setZoomAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.zoom_in);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        MainMenu menulist = menuList.get(position);
        holder.title.setText(menulist.getName());



        Picasso.with(mContext).load(menulist.getThumbnail()).fit().centerCrop()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(holder.thumbnail);


holder.overflow.setVisibility(View.INVISIBLE);
        setAnimation(holder.itemView, position);

holder.thumbnail.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
 switch (holder.getLayoutPosition()){
     case 0:
         Intent intecomming =new Intent(mContext,CommingActivity.class);
         mContext.startActivity(intecomming);
         return;
     case 1:
         Intent intenetnow =new Intent(mContext,NowshowingActivity.class);
         mContext.startActivity(intenetnow);
         return;
     case 2:
         Intent intent7d =new Intent(mContext,SevenDActivity.class);
         mContext.startActivity(intent7d);
         return;
     case 3:
         Intent intentgames =new Intent(mContext,BongosActivity.class);
         mContext.startActivity(intentgames);
         return;

     case 4:
         Intent intentcontact =new Intent(mContext,ContactActivity.class);
         mContext.startActivity(intentcontact);
 default:
return;
 }

    }
});


/*
        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });
         */
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        Toast.makeText(mContext,"Cliecked ",Toast.LENGTH_LONG).show();


        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:


                        return true;
                case R.id.action_play_next:


                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }
}
