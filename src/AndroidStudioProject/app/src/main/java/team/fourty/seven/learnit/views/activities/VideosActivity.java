package team.fourty.seven.learnit.views.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import team.fourty.seven.learnit.R;
import team.fourty.seven.learnit.models.YoutubeVideo;
import team.fourty.seven.learnit.views.adapters.VideoAdapter;
import team.fourty.seven.learnit.views.adapters.VideoViewHolder;
import team.fourty.seven.learnit.views.fragments.FullScreenVideoFragment;
import team.fourty.seven.learnit.controllers.SkillController;
import team.fourty.seven.learnit.views.helpers.OnClickVideoViewHolder;

public class VideosActivity extends MenuActivity implements OnClickVideoViewHolder {

    private RecyclerView recyclerView;
    RecyclerView.Adapter<VideoViewHolder> adapter;
    private RecyclerView.LayoutManager layoutManager;

    private String category;
    FullScreenVideoFragment fragment;


    List<YoutubeVideo> youtubeVideos;
    SkillController controller;
    boolean fragmentIsOpen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);

        fragmentIsOpen =false;

        this.recyclerView = (RecyclerView) findViewById(R.id.videoRecycler);

        this.layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        controller = new SkillController(this);

        youtubeVideos = getYoutubeVideos();

        this.adapter = new VideoAdapter(youtubeVideos);

        this.recyclerView.setLayoutManager(layoutManager);

        this.recyclerView.setAdapter(adapter);
    }

    private List<YoutubeVideo> getYoutubeVideos() throws NullPointerException {

        List<YoutubeVideo> youtubeVideos = new ArrayList<>();

        category = getIntent().getStringExtra("category");

        youtubeVideos = controller.getSkillVideos(category);

        return youtubeVideos;

    }


    @Override
    public void sendVideoId(Bundle videoIdBundle) {


        fragment = new FullScreenVideoFragment();


        fragment.setArguments(videoIdBundle);
        fragmentIsOpen = true;
        fragment.show(getSupportFragmentManager(),"video");

    }


}
