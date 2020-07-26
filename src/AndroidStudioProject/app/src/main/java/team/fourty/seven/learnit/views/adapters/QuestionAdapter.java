package team.fourty.seven.learnit.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import team.fourty.seven.learnit.R;
import team.fourty.seven.learnit.models.QuizQuestion;
import team.fourty.seven.learnit.models.QuizQuestionsList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionViewHolder> {

    private List<QuizQuestion> questionsList;
    public int[] correctAnswers;

    QuestionViewHolder holder;

    public QuestionAdapter(List<QuizQuestion> questionsList) {

        this.questionsList = questionsList;
        correctAnswers = new int[questionsList.size()];
    }

    @Override
    public QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.quiz_item, parent, false);

         holder = new QuestionViewHolder(view, this, questionsList);

        return holder;
    }

    @Override
    public void onBindViewHolder(QuestionViewHolder questionViewHolder, int position) {

        QuizQuestion question = questionsList.get(position);
        questionViewHolder.setQuizQuestion(question);


    }

    @Override
    public int getItemCount() {
        return questionsList.size();
    }

    public int[] getCorrectAnswers() {
        return this.correctAnswers;
    }




}