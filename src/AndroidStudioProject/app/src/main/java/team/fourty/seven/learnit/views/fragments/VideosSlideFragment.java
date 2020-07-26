package team.fourty.seven.learnit.views.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import team.fourty.seven.learnit.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class VideosSlideFragment extends Fragment
{

    ImageView videoOne;
    ImageView videoTwo;
    ImageView videoThree;
    ImageView videoFour;
    ImageView videoFive;
    ImageView videoSix;



    public VideosSlideFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_slides_video, container, false);










        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
