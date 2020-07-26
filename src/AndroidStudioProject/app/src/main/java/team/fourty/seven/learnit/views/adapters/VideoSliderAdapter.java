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

public class VideoSliderAdapter extends RecyclerView.Adapter<VideoSliderViewHolder> {

    List<YoutubeVideo> videos;

    public VideoSliderAdapter(List<YoutubeVideo> videoList){this.videos = videoList;}


    @NonNull
    @Override
    public VideoSliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.item_image_list,parent,false);

        VideoSliderViewHolder videoSliderViewHolder = new VideoSliderViewHolder(view,this,videos);

        return videoSliderViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoSliderViewHolder videoSliderViewHolder, int position) {
        YoutubeVideo youtubeVideo = videos.get(position);
        videoSliderViewHolder.setVideo(youtubeVideo);

    }

    @Override
    public int getItemCount() {
        return videos.size();
    }
}
