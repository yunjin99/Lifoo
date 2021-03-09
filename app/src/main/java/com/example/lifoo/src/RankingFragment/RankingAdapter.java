package com.example.lifoo.src.RankingFragment;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifoo.R;
import com.example.lifoo.src.AlertFragment.AlertAdapter;
import com.example.lifoo.src.PostDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.RankingViewHolder> {
    Context context;
    ArrayList<RankingItem> rankingItems;

    public RankingAdapter(ArrayList<RankingItem> rankingItems){
        this.rankingItems=rankingItems;
    }

    public interface OnItemClickListener
    {
        void onItemClick(View v, int pos);
    }
    private AlertAdapter.OnItemClickListener mListener = null;
    public void setOnItemClickListener(AlertAdapter.OnItemClickListener listener)
    {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public RankingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_item, null);
        return new RankingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RankingViewHolder holder, int position) {
        RankingItem rankingItem = rankingItems.get(position);

        holder.rankingImage.setImageDrawable(rankingItem.getRankingPost());
        holder.badgeImage.setImageDrawable(rankingItem.getRankingBadge());
        holder.rankingPostTitle.setText(rankingItem.getRankingPostTitle());
        holder.rankingPostTime.setText(rankingItem.getRankingPostTime());
        holder.rankingPostEmoji.setText(rankingItem.getRankingPostEmoji());


    }

    @Override
    public int getItemCount() {
        return this.rankingItems.size();
    }

    public class RankingViewHolder extends RecyclerView.ViewHolder {
        ImageView rankingImage;
        ImageView badgeImage;
        TextView rankingPostTitle;
        TextView rankingPostTime;
        TextView rankingPostEmoji;

        public RankingViewHolder(final View itemView) {
            super(itemView);
            this.rankingImage = itemView.findViewById(R.id.ranking_post_image);
            this.badgeImage = itemView.findViewById(R.id.ranking_post_badge);
            this.rankingPostTitle = itemView.findViewById(R.id.ranking_post_title);
            this.rankingPostTime = itemView.findViewById(R.id.ranking_post_time);
            this.rankingPostEmoji = itemView.findViewById(R.id.ranking_post_emojicounter);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION)
                    {
                        // click event
                        mListener.onItemClick(v,pos);

                        Intent intent = new Intent(context, PostDetailActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    }
                }
            });
        }
    }
}
