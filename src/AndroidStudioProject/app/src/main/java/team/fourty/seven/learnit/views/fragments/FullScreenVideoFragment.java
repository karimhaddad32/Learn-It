package team.fourty.seven.learnit.views.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import team.fourty.seven.learnit.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FullScreenVideoFragment extends DialogFragment {

    private String YouTubeKey = "";
    private final String YOUTUBE_VIDEO_TIMER = "videoTimer";
    private YouTubePlayer youTubePlayer;
    private String videoId;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    int timer;


    public FullScreenVideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_full_screen_video, container, false);

        preferences = getActivity().getSharedPreferences(YOUTUBE_VIDEO_TIMER,getActivity().MODE_PRIVATE);



        videoId = getArguments().getString("videoId",null);
        timer = preferences.getInt(videoId,0);



        final YouTubePlayerSupportFragment youTubePlayerSupportFragment = YouTubePlayerSupportFragment.newInstance();

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.youtubeFrame, youTubePlayerSupportFragment).commit();


        YouTubeKey = getYoutubeKey();
        youTubePlayerSupportFragment.initialize(YouTubeKey, new YouTubePlayer.OnInitializedListener() {



            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean restored) {

                if(!restored){

                    youTubePlayer = player;
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                    youTubePlayer.loadVideo(videoId,timer);
                    youTubePlayer.play();


                    youTubePlayer.setOnFullscreenListener(new YouTubePlayer.OnFullscreenListener() {
                        @Override
                        public void onFullscreen(boolean fullScreen) {
                            if(fullScreen){
                                editor = preferences.edit();
                                editor.putInt(videoId,youTubePlayer.getCurrentTimeMillis());
                                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                                Log.i("preference","timer: " + youTubePlayer.getCurrentTimeMillis());
                                Log.i("preference",String.valueOf("preference: " + preferences.getInt(videoId,0)));
                            }
                            else{
                                editor = preferences.edit();
                                editor.putInt(videoId,youTubePlayer.getCurrentTimeMillis());
                                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                                youTubePlayer.seekToMillis(timer);

                                Log.i("preference",String.valueOf("timer: " + youTubePlayer.getCurrentTimeMillis()));
                                Log.i("preference",String.valueOf("preference: " + preferences.getInt(videoId,0)));
                            }
                        }
                    });
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult error) {
                String errorMessage = error.toString();
                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
                Log.wtf("errorMessage:", errorMessage);
            }
        });

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
    public void onDetach() {
        super.onDetach();
    }
}
