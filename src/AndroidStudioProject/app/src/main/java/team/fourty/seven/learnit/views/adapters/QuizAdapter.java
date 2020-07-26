package team.fourty.seven.learnit.views.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import team.fourty.seven.learnit.R;
import team.fourty.seven.learnit.models.QuizQuestionsList;

public class QuizAdapter extends RecyclerView.Adapter<QuizViewHolder> {

    private List<QuizQuestionsList> quizzesList;

    public QuizAdapter(List<QuizQuestionsList> listOfLists){

        this.quizzesList = listOfLists;

    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.quiz_list_item,parent,false);

        QuizViewHolder viewHolder = new QuizViewHolder(view,this,quizzesList);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder quizViewHolder, int position) {

        QuizQuestionsList quiz = quizzesList.get(position);
        quizViewHolder.setQuestionsList(quiz);

    }

    @Override
    public int getItemCount() {
        return quizzesList.size();
    }
}
