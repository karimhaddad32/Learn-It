package team.fourty.seven.learnit.views.adapters;

import android.nfc.Tag;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import team.fourty.seven.learnit.R;
import team.fourty.seven.learnit.controllers.SkillController;
import team.fourty.seven.learnit.models.QuizQuestion;

public class QuestionViewHolder extends RecyclerView.ViewHolder {

    public final String TAG = getClass().getSimpleName();

    private List<QuizQuestion> questions;
    private QuestionAdapter questionAdapter;
    private QuizQuestion question;
    public String correctAnswer = "";
    public boolean correct = false;

    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private TextView txtQuestion;

    SkillController controller;


    public QuestionViewHolder(View itemView, final QuestionAdapter adapter, List<QuizQuestion> list){
        super(itemView);

        Log.i(TAG,"Hello");

        questions = list;
        questionAdapter = adapter;

        controller = new SkillController(itemView.getContext());

        radioGroup = (RadioGroup) itemView.findViewById(R.id.radioGroup);


        radioButton1 = itemView.findViewById(R.id.radioFirstOption);
        radioButton2 = itemView.findViewById(R.id.radioSecondOption);
        radioButton3 = itemView.findViewById(R.id.radioThirdOption);
        radioButton4 = itemView.findViewById(R.id.radioFourthOption);
        txtQuestion = itemView.findViewById(R.id.txtQuestion);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
                String answer = radioButton.getText().toString();
                if(answer.equals(correctAnswer)){
                    adapter.correctAnswers[getAdapterPosition()] = 1;
                }else{
                    adapter.correctAnswers[getAdapterPosition()] = 0;
                }
            }
        });

    }

    public void setQuizQuestion(QuizQuestion question) {

        this.question = question;
        this.txtQuestion.setText(question.getResult().getQuestion());
        setShuffledOptionsToRadioButtons();

    }

    private void setShuffledOptionsToRadioButtons() {

        List<RadioButton> radioButtons = new ArrayList<>();
        List<String> answers = new ArrayList<>();

//        Html.fromHtml(question.getResult().getIncorrect_answers()[0],0).toString();
        answers.add(Html.fromHtml(question.getResult().getIncorrect_answers()[0],0).toString());
        answers.add(Html.fromHtml(question.getResult().getIncorrect_answers()[1],0).toString());
        answers.add(Html.fromHtml(question.getResult().getIncorrect_answers()[2],0).toString());

        radioButtons.add(radioButton1);
        radioButtons.add(radioButton2);
        radioButtons.add(radioButton3);
        radioButtons.add(radioButton4);

        Random random = new Random();
        int number = random.nextInt(4);
        radioButtons.get(number).setText(Html.fromHtml(question.getResult().getCorrect_answer(),0).toString());
        correctAnswer = question.getResult().getCorrect_answer();
        radioButtons.remove(radioButtons.get(number));

        for(int i = 0 ; i < radioButtons.size();i++){

            radioButtons.get(i).setText(answers.get(i));

        }



    }

    public View getView(){
       return this.itemView;
    }




}
