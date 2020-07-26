package team.fourty.seven.learnit.views.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import team.fourty.seven.learnit.R;

public class QuizAdapter_Dismissed extends RecyclerView.Adapter<QuizViewHolder_Dismissed>
{

    private ArrayList<String> quizNames;
    private ArrayList<Color> quizDifficulties;
    private ArrayList<Integer> quizScores;


    public QuizAdapter_Dismissed(ArrayList<String> qNames, ArrayList<Color> qDifficulties, ArrayList<Integer> qScores)
    {

        this.quizNames = qNames;
        this.quizDifficulties = qDifficulties;
        this.quizScores = qScores;

    }

    @Override
    public QuizViewHolder_Dismissed onCreateViewHolder(ViewGroup parent, int viewType)
    {

        Context context = parent.getContext();

        View v = LayoutInflater.from(context).inflate(R.layout.quiz_list_item, parent, false);

        QuizViewHolder_Dismissed quizViewHolderDismissed = new QuizViewHolder_Dismissed(v, this, quizNames, quizDifficulties, quizScores);

        return quizViewHolderDismissed;

    }

    @Override
    public void onBindViewHolder(final QuizViewHolder_Dismissed holder, int position)
    {

        String name = quizNames.get(position);
        Color difficulty = quizDifficulties.get(position);
        int score = quizScores.get(position);

        holder.setQuizName(name);
        holder.setQuizDifficulty(difficulty);
        holder.setQuizScore(score);

    }

    @Override
    public int getItemCount()
    {

        return quizNames.size();

    }

}