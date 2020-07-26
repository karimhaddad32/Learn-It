package team.fourty.seven.learnit.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import team.fourty.seven.learnit.R;
import team.fourty.seven.learnit.models.YoutubeVideo;
import team.fourty.seven.learnit.views.adapters.VideoSliderAdapter;

public class VideoSliderViewHolder extends RecyclerView.ViewHolder {


    private VideoSliderAdapter videoSliderAdapter;

    private ImageView imageViewVideo;
    private TextView txtTitle;

    private List<YoutubeVideo> videos;
    private YoutubeVideo youtubeVideo;

    public VideoSliderViewHolder(View itemView, VideoSliderAdapter adapter, List<YoutubeVideo> videoList) {
        super(itemView);

        videos = videoList;
        videoSliderAdapter = adapter;

        imageViewVideo = itemView.findViewById(R.id.imageViewSlideImage);
        txtTitle = itemView.findViewById(R.id.txtTitleSlide);

    }

    public void setVideo(YoutubeVideo video){
        this.youtubeVideo = video;

        imageViewVideo.setImageBitmap(youtubeVideo.getThumbnail());
        txtTitle.setText(youtubeVideo.getTitle());

    }
}
