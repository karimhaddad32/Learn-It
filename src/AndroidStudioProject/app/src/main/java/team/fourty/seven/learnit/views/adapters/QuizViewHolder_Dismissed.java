package team.fourty.seven.learnit.views.adapters;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class QuizViewHolder_Dismissed extends RecyclerView.ViewHolder
//        implements View.OnClickListener
{

    QuizAdapter_Dismissed quizAdapterDismissed;

    private TextView quizName;
    private String name;
    private ArrayList<String> quizNames;

    private RatingBar quizDifficulty;
    private Color difficulty;
    private ArrayList<Color> quizDifficulties;

    private ProgressBar quizScore;
    private int score;
    private ArrayList<Integer> quizScores;



    public QuizViewHolder_Dismissed(View v, QuizAdapter_Dismissed quizAdapterDismissed, ArrayList<String> stringArrayList, ArrayList<Color> colorArrayList, ArrayList<Integer> intArrayList)
    {

        super(v);

        this.quizAdapterDismissed = quizAdapterDismissed;

        this.quizNames = stringArrayList;
        this.quizDifficulties = colorArrayList;
        this.quizScores = intArrayList;

//        this.quizName = (TextView) v.findViewById(R.id.quizNameTextView);
//        this.quizDifficulty = (RatingBar) v.findViewById(R.id.quizDifficultyRatingBar);
//        this.quizScore = (ProgressBar) v.findViewById(R.id.quizScoreProgressBar);

//        LinearLayout view = (LinearLayout) itemView.findViewById(R.id.quizListLinearLayout);
//        view.setOnClickListener(this);

    }



    public void setQuizName(String text)
    {

        this.name = text;
        this.quizName.setText(text);

    }
    public void setQuizDifficulty(Color color)
    {

        this.difficulty = color;
        this.quizDifficulty.getProgressDrawable().setColorFilter(
                Color.parseColor("#0064A8"), PorterDuff.Mode.SRC_ATOP);

    }
    public void setQuizScore(int number)
    {

        this.score = number;
        this.quizScore.setProgress(number);

    }



    public String getName()
    {
        return name;
    }
    public Color getDifficulty()
    {
        return difficulty;
    }
    public int getScore()
    {
        return score;
    }


//    @Override
//    public void onClick(View v)
//    {
//
//        QuizFragment quizFragment = new QuizFragment();
//        fragment.show(getSupportFragmentManager(), "Quiz");
//
//    }

}
