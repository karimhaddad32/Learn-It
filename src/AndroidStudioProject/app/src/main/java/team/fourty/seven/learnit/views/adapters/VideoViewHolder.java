package team.fourty.seven.learnit.views.adapters;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import team.fourty.seven.learnit.R;
import team.fourty.seven.learnit.models.YoutubeVideo;
import team.fourty.seven.learnit.views.helpers.OnClickVideoViewHolder;

public class VideoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private List<YoutubeVideo> videos;
    private VideoAdapter videoAdaper;
    private YoutubeVideo youtubeVideo;

    private ImageView imgThumbnail;
    private TextView txtVideoTitle;
    private TextView txtVideoDescription;



    public VideoViewHolder(View itemView,  VideoAdapter adapter, List<YoutubeVideo> videoList) {
        super(itemView);

        videos = videoList;
        videoAdaper = adapter;
        imgThumbnail = itemView.findViewById(R.id.videoThumbnail);
        txtVideoTitle = itemView.findViewById(R.id.videoTitle);
        txtVideoDescription = itemView.findViewById(R.id.videoDescription);
        itemView.setOnClickListener(this);


    }

    public void setVideo(YoutubeVideo video){
        this.youtubeVideo = video;
        imgThumbnail.setImageBitmap(youtubeVideo.getThumbnail());
        txtVideoTitle.setText(youtubeVideo.getTitle());
        txtVideoDescription.setText(youtubeVideo.getDescription());
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
//        Intent intent = new Intent(itemView.getContext(),VideoPlayerActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString("videoId",youtubeVideo.getVideoId());

        OnClickVideoViewHolder activity = (OnClickVideoViewHolder) itemView.getContext();
        activity.sendVideoId(bundle);


    }
}
