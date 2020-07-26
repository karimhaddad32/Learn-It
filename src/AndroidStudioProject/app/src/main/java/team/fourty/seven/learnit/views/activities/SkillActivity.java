package team.fourty.seven.learnit.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import team.fourty.seven.learnit.R;
import team.fourty.seven.learnit.models.EBook;
import team.fourty.seven.learnit.controllers.SkillController;
import team.fourty.seven.learnit.models.YoutubeVideo;
import team.fourty.seven.learnit.views.adapters.EBookSilderViewHolder;
import team.fourty.seven.learnit.views.adapters.EBookSliderAdapter;
import team.fourty.seven.learnit.views.helpers.OnDescriptionFragment;
import team.fourty.seven.learnit.views.helpers.OnSlidesFragment;
import team.fourty.seven.learnit.views.adapters.VideoSliderAdapter;
import team.fourty.seven.learnit.views.adapters.VideoSliderViewHolder;

public class SkillActivity extends MenuActivity implements View.OnClickListener, OnSlidesFragment, OnDescriptionFragment {


    private static final String TAG = "SkillActivity";
    private final String youtubeKey = "AIzaSyD0rsQcbOCn1D_D79gjvVTc1XDgNoMEFxQ";

    private RecyclerView eBookRecycler;
    private RecyclerView youtubeVideoRecycler;

    private RecyclerView.Adapter<EBookSilderViewHolder> eBookAdapter;
    private RecyclerView.Adapter<VideoSliderViewHolder> youtubeAdapter;

    private StaggeredGridLayoutManager videoStaggeredGridLayout;
    private StaggeredGridLayoutManager ebookStaggeredGridLayout;


    public int descriptionId;
    public String videoId;
    public int titleId;
    public String category;



    SkillController skillController;

    private TextView txtDescription;
    private TextView txtTitle;
    private ImageButton btnExpandVideos;
    private ImageButton btnExpandEBooks;
    private Button quizButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        category = getIntent().getStringExtra("category");

        skillController = new SkillController(this);


        this.ebookStaggeredGridLayout = new StaggeredGridLayoutManager(1,LinearLayoutManager.HORIZONTAL);
        this.videoStaggeredGridLayout = new StaggeredGridLayoutManager(1,LinearLayoutManager.HORIZONTAL);

        setVideoId();

        setContentView(R.layout.activity_skill);

       List<EBook> eBookList = new ArrayList<>(skillController.getSkillEBooks(category));
       List<YoutubeVideo> videos = new ArrayList<>(skillController.getSkillVideos(category));

        findViews();

        txtDescription.setText(descriptionId);
        txtTitle.setText(titleId);
        txtTitle.setAllCaps(true);


        btnExpandVideos.setOnClickListener(this);
        btnExpandEBooks.setOnClickListener(this);
        quizButton.setOnClickListener(this);

        this.youtubeAdapter = new VideoSliderAdapter(videos);
        this.eBookAdapter = new EBookSliderAdapter(eBookList);

       this.youtubeVideoRecycler.setLayoutManager(videoStaggeredGridLayout);
       this.eBookRecycler.setLayoutManager(ebookStaggeredGridLayout);

        this.youtubeVideoRecycler.setAdapter(youtubeAdapter);
        this.eBookRecycler.setAdapter(eBookAdapter);

    }

    public void findViews() {

        this.youtubeVideoRecycler = (RecyclerView) findViewById(R.id.imageVideoSlideRecycler);
        this.eBookRecycler = (RecyclerView) findViewById(R.id.imageEBookSlideRecycler);
        this.txtDescription = (TextView) findViewById(R.id.txtDescription);
        this.txtTitle = (TextView) findViewById(R.id.txtSkillTitle);
        btnExpandVideos = (ImageButton) findViewById(R.id.btnExpandVideos);
        btnExpandEBooks = (ImageButton) findViewById(R.id.btnExpandEBooks);
        quizButton = (Button) findViewById(R.id.quizButton);

    }

    public void setVideoId() {


        //CRITICAL THINKING
        if (category.equals(skillController.CRITICAL_THINKING_CAT)) {
            videoId = "Cum3k-Wglfw";
            descriptionId = R.string.txtDescriptionCriticalThinking;
            titleId = R.string.txtCriticalThinking;
        }
        //LAW
        else if (category.equals(skillController.LAW_CAT)) {
            videoId = "FiOiBFHNxRQ";
            descriptionId = R.string.txtDescriptionLaw;
            titleId = R.string.txtLaw;
        }
        //CODING
        //https://www.youtube.com/channel/UC8butISFwT-Wl7EV0hUK0BQ
        else if (category.equals(skillController.CODING_CAT)) {
            videoId = "cKhVupvyhKk";
            descriptionId = R.string.txtDescriptionCoding;
            titleId = R.string.txtCoding;

            //books link: https://coderseye.com/best-coding-books-for-beginners/

        }
        //TECHNOLOGY
        //Youtube Link: https://www.youtube.com/watch?v=jG4cxSFgQUo
        else if (category.equals(skillController.TECHNOLOGY_CAT)) {
            videoId = "jG4cxSFgQUo";
            descriptionId = R.string.txtDescriptionTechnology;
            titleId = R.string.txtTechnology;
        }

        //PAINTING
        if (category.equals(skillController.PAINTING_CAT)) {
            videoId = "oL_U_R_4xTo";
            descriptionId = R.string.txtDescriptionPainting;
            titleId = R.string.txtPainting;
        }
        //SCULPTING
        //https://www.youtube.com/user/scrybe/search?query=Sculpey+101+Class
        else if (category.equals(skillController.SCULPTING_CAT)) {
            videoId = "As3l1I5vumY";
            descriptionId = R.string.txtDescriptionSculpting;
            titleId = R.string.txtSculpting;
        }
        //MUSIC
        else if (category.equals(skillController.MUSIC_CAT)) {
            videoId = "n2z02J4fJwg";
            descriptionId = R.string.txtDescriptionMusic;
            titleId = R.string.txtMusic;
        }
        //SELF DEFENSE
        else if (category.equals(skillController.SELF_DEFENSE_CAT)) {
            videoId = "M4_8PoRQP8w";
            descriptionId = R.string.txtDescriptionSelfDefense;
            titleId = R.string.txtSelfDefense;
        }








    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnExpandVideos) {

            Intent intent = new Intent(this, VideosActivity.class);
            intent.putExtra("category", category);
            startActivity(intent);

        } else if (v.getId() == R.id.btnExpandEBooks) {

            Intent intent = new Intent(this, EBooksActivity.class);
            intent.putExtra("category", category);
            startActivity(intent);

        } else if (v.getId() == R.id.quizButton) {

            Intent intent = new Intent(this, QuizzesActivity.class);
            intent.putExtra("category", category);
            startActivity(intent);

        }
    }
}
