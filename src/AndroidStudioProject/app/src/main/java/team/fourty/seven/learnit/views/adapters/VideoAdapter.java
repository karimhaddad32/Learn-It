package team.fourty.seven.learnit.views.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import team.fourty.seven.learnit.R;
import team.fourty.seven.learnit.models.YoutubeVideo;

public class VideoAdapter extends RecyclerView.Adapter<VideoViewHolder> {


    List<YoutubeVideo> videos;

    public VideoAdapter(List<YoutubeVideo> videoList){this.videos = videoList;}


    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.item_video,parent,false);

        VideoViewHolder videoViewHolder = new VideoViewHolder(view,this,videos);

        return videoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder videoViewHolder, int position) {
        YoutubeVideo youtubeVideo = videos.get(position);
        videoViewHolder.setVideo(youtubeVideo);

    }

    @Override
    public int getItemCount() {
        return videos.size();
    }
}
