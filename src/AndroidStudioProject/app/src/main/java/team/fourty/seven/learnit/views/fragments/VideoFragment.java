package team.fourty.seven.learnit.views.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import team.fourty.seven.learnit.R;
import team.fourty.seven.learnit.views.activities.SkillActivity;
import team.fourty.seven.learnit.models.YoutubeVideo;


/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment implements YouTubePlayer.PlaybackEventListener, YouTubePlayer.PlayerStateChangeListener {

    private String YouTubeKey= "";
    private YouTubePlayerSupportFragment youTubePlayerSupportFragment;
    private String videoId;
    private String title;
    YoutubeVideo youtubeVideo;

    public YoutubeVideo getYoutubeVideo() {
        return youtubeVideo;
    }

    public void setYoutubeVideo(YoutubeVideo youtubeVideo) {
        this.youtubeVideo = youtubeVideo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public YouTubePlayerSupportFragment getYouTubePlayerSupportFragment() {
        return youTubePlayerSupportFragment;
    }

    public void setYouTubePlayerSupportFragment(YouTubePlayerSupportFragment youTubePlayerSupportFragment) {
        this.youTubePlayerSupportFragment = youTubePlayerSupportFragment;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
    //jG4cxSFgQUo

    public VideoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_video, container, false);

        if((getActivity().getClass()).equals(SkillActivity.class)){
            SkillActivity activity = (SkillActivity) getActivity();
            videoId = activity.videoId;

            youTubePlayerSupportFragment = YouTubePlayerSupportFragment.newInstance();
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.add(R.id.videoView, youTubePlayerSupportFragment).commit();

            YouTubeKey = getYoutubeKey();


            youTubePlayerSupportFragment.initialize(YouTubeKey, new YouTubePlayer.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean isRetrieved) {
                    if(!isRetrieved && videoId != null) {

                        youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                        youTubePlayer.cueVideo(videoId);
                        youTubePlayer.play();

                    }
                    else{
                        Toast.makeText(getActivity(), "Null Value",Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                    String errorMessage = youTubeInitializationResult.toString();

                    Toast.makeText(getActivity(), errorMessage,Toast.LENGTH_LONG).show();
                    Log.d("errorMessage",errorMessage);
                }
            });
        }




        return view;
    }

    private String getYoutubeKey() {
        String json ="";
        String youtubeKey = "";
        try {
            InputStream is = this.getActivity().getAssets().open("APIs/api.json");

            BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
            StringBuilder stringBuilder = new StringBuilder();
            String line = buffer.readLine();

            while (line != null){
                stringBuilder.append(line);
                line = buffer.readLine();
            }

            buffer.close();
            json = stringBuilder.toString();

            JSONArray jArray = new JSONArray(json);
            JSONObject obj = jArray.getJSONObject(0);

            youtubeKey = obj.getString("YoutubeKey");



        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return youtubeKey;
    }

    @Override
    public void onPlaying() {

    }

    @Override
    public void onPaused() {

    }

    @Override
    public void onStopped() {

    }

    @Override
    public void onBuffering(boolean b) {

    }

    @Override
    public void onSeekTo(int i) {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onLoaded(String s) {

    }

    @Override
    public void onAdStarted() {

    }

    @Override
    public void onVideoStarted() {

    }

    @Override
    public void onVideoEnded() {

    }

    @Override
    public void onError(YouTubePlayer.ErrorReason errorReason) {

    }
}
