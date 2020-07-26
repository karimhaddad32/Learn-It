package team.fourty.seven.learnit.views.adapters;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import team.fourty.seven.learnit.R;
import team.fourty.seven.learnit.controllers.SkillController;
import team.fourty.seven.learnit.models.QuizQuestionsList;
import team.fourty.seven.learnit.models.QuizResult;
import team.fourty.seven.learnit.views.activities.QuestionsActivity;
import team.fourty.seven.learnit.views.activities.QuizzesActivity;

import static java.security.AccessController.getContext;

public class QuizViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public final String TAG = getClass().getSimpleName();

    private List<QuizQuestionsList> listOfLists;
    private QuizQuestionsList quiz;

    private TextView txtQuizTitle;
    private TextView txtQuizGrade;
    private TextView txtQuizDifficulty;
    private TextView txtQuizNumberOfQuestions;

    private QuizAdapter quizAdapter;

    SkillController controller = new SkillController(itemView.getContext());

    public QuizViewHolder(View itemView, QuizAdapter adapter, List<QuizQuestionsList> quizQuestionsLists) {

        super(itemView);

        this.listOfLists = quizQuestionsLists;
        this.quizAdapter = adapter;
        //Views
        this.txtQuizTitle = itemView.findViewById(R.id.txtQuizTitle);
        this.txtQuizGrade = itemView.findViewById(R.id.txtGrade);
        this.txtQuizDifficulty = itemView.findViewById(R.id.txtDifficulty);
        this.txtQuizNumberOfQuestions = itemView.findViewById(R.id.txtNumberOfQuestions);

        itemView.setOnClickListener(this);


    }

    public void setQuestionsList(QuizQuestionsList quiz){

        this.quiz = quiz;

        SharedPreferences preferences = itemView.getContext().getSharedPreferences("Quizzes", itemView.getContext().MODE_PRIVATE);
        String result = preferences.getString(quiz.getQuestionList().get(0).getResult().getCategory()+quiz.getQuestionList().get(0).getResult().getDifficulty()+quiz.getTestName(),"");

        Log.i(TAG,quiz.getQuestionList().get(0).getResult().getCategory()+quiz.getTestName());
        Log.i(TAG,preferences.getString(quiz.getQuestionList().get(0).getResult().getCategory()+quiz.getQuestionList().get(0).getResult().getDifficulty()+quiz.getTestName(),""));


        if(!preferences.getString(quiz.getQuestionList().get(0).getResult().getCategory()+quiz.getQuestionList().get(0).getResult().getDifficulty()+quiz.getTestName(),"").equals("")){
            this.txtQuizGrade.setText(result);
        }else{
            this.txtQuizGrade.setText(String.valueOf(quiz.getGrade()));
        }



        this.txtQuizTitle.setText(quiz.getTestName());
        this.txtQuizDifficulty.setText(quiz.getDifficulty());

        this.txtQuizNumberOfQuestions.setText(String.valueOf(quiz.getNumberOfQuestions()));

        if(quiz.getDifficulty().equals(controller.EASY_DIFFICULTY)){
            itemView.setBackgroundColor(itemView.getContext().getResources().getColor(R.color.easy));
        }
        else if(quiz.getDifficulty().equals(controller.MEDIUM_DIFFICULTY)){
            itemView.setBackgroundColor(itemView.getContext().getResources().getColor(R.color.medium));
        }
        else if(quiz.getDifficulty().equals(controller.HARD_DIFFICULTY))
        {
            itemView.setBackgroundColor(itemView.getContext().getResources().getColor(R.color.hard));
        }



    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        QuizzesActivity activity = (QuizzesActivity) itemView.getContext();
        Intent intent = new Intent(activity, QuestionsActivity.class);
        intent.putExtra("quiz",this.quiz);
        intent.putParcelableArrayListExtra("quiz_questions", (ArrayList<? extends Parcelable>) quiz.getQuestionList());
        List<QuizResult> results = new ArrayList<>();
        for(int i = 0 ; i < quiz.getQuestionList().size();i++){
            results.add(quiz.getQuestionList().get(i).getResult());
        }
        intent.putExtra("name", quiz.getTestName());
        intent.putParcelableArrayListExtra("quiz_result", (ArrayList<? extends Parcelable>) results);


        Log.i(TAG, quiz.toString());
        Log.i(TAG, quiz.getQuestionList().toString());
        Log.i(TAG, results.toString());

        activity.startActivityForResult(intent,1,null);

    }





}
