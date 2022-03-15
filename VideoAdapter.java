package com.govind8061.practiceproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.StyledPlayerView;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    List<String> videosUrl;
    OnPageScrolledListener listener;

    public VideoAdapter(List<String> videosUrl) {
        this.videosUrl = videosUrl;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_video_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String videoUrl=videosUrl.get(position);
        ExoPlayer player=new ExoPlayer.Builder(holder.playerView.getContext()).build();
        holder.playerView.setPlayer(player);
        MediaItem mediaItem = MediaItem.fromUri(videoUrl);
        player.setMediaItem(mediaItem);
        player.prepare();
    }

    @Override
    public int getItemCount() {
        return videosUrl.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        StyledPlayerView playerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            playerView=itemView.findViewById(R.id.playerView);
        }
    }

    public interface OnPageScrolledListener{
        void onPageScroll(int item,StyledPlayerView playerView);
    }

    public void setOnPageScrolledListener(OnPageScrolledListener listener){
        this.listener=listener;
    }
}
