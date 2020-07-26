package team.fourty.seven.learnit.views.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import team.fourty.seven.learnit.R;
import team.fourty.seven.learnit.controllers.SkillController;
import team.fourty.seven.learnit.models.QuizQuestion;
import team.fourty.seven.learnit.models.QuizQuestionsList;
import team.fourty.seven.learnit.models.QuizResult;
import team.fourty.seven.learnit.views.adapters.QuestionAdapter;
import team.fourty.seven.learnit.views.adapters.QuestionViewHolder;
import team.fourty.seven.learnit.views.adapters.QuizAdapter;
import team.fourty.seven.learnit.views.adapters.QuizViewHolder;

public class QuestionsActivity extends AppCompatActivity implements View.OnClickListener {

    public final String TAG = getClass().getSimpleName();

    private Button btnSubmitAnswers;
    private QuizQuestionsList list;
    private TextView txtTitle;

    private RecyclerView recyclerView;
    private QuestionAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    String testName = "";

    LinearLayout layout;
    SkillController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        Intent intent = getIntent();

        list = intent.getParcelableExtra("quiz");
        list.setQuestionList(intent.<QuizQuestion>getParcelableArrayListExtra("quiz_questions"));
        for (int i = 0; i < list.getQuestionList().size(); i++) {
            list.getQuestionList().get(i).setResult((QuizResult) intent.getParcelableArrayListExtra("quiz_result").get(i));
        }

        testName = intent.getStringExtra("name");
        Log.i(TAG, list.toString());
        Log.i(TAG, list.getQuestionList().toString());

        controller = new SkillController(this);


        findViews();
        setViews();

        this.layoutManager = new LinearLayoutManager(this);
         this.adapter = new QuestionAdapter(list.getQuestionList());


        this.recyclerView.setAdapter(adapter);
        this.recyclerView.setLayoutManager(layoutManager);

        btnSubmitAnswers.setOnClickListener(this);

    }

    private void findViews() {

        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerViewQuestions);
        this.btnSubmitAnswers = (Button) findViewById(R.id.btnSubmitAnswers);
        this.txtTitle = (TextView) findViewById(R.id.txtQuizTitle2);
        this.layout = (LinearLayout) findViewById(R.id.rootLayoutQuestions);
    }

    private void setViews() {

        txtTitle.setText(list.getTestName());

    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

        double correctAnsers = 0;
        String result = "0%";
        Log.i(TAG,adapter.getItemCount() + " asdasd");
        for (int i = 0; i < adapter.correctAnswers.length ; i++) {
            if(adapter.correctAnswers[i] == 1){
                correctAnsers++;
            }

//            RadioGroup radioGroup = (RadioGroup) recyclerView.findViewHolderForAdapterPosition(i).itemView.getRootView().findViewById(R.id.radioGroup);
//            if (radioGroup.getCheckedRadioButtonId() != -1) {
//
//                RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
//                String answer = radioButton.getText().toString();
//                Log.i(TAG, answer);
//            }
//
//            RadioGroup radioGroup3 = (RadioGroup) recyclerView.findViewHolderForLayoutPosition(i).itemView.getRootView().findViewById(R.id.radioGroup);
//            if (radioGroup3.getCheckedRadioButtonId() != -1) {
//
//                RadioButton radioButton = (RadioButton) radioGroup3.findViewById(radioGroup3.getCheckedRadioButtonId());
//                String answer = radioButton.getText().toString();
//                Log.i(TAG, answer);
//            }
//
//            RadioGroup radioGroup1 = (RadioGroup) recyclerView.getLayoutManager().findViewByPosition(i).findViewById(R.id.radioGroup);
//            if (radioGroup1.getCheckedRadioButtonId() != -1) {
//
//                RadioButton radioButton = (RadioButton) radioGroup1.findViewById(radioGroup1.getCheckedRadioButtonId());
//                String answer = radioButton.getText().toString();
//                Log.i(TAG, answer);
//            }
//
//            RadioGroup radioGroup2 = (RadioGroup) recyclerView.getLayoutManager().getChildAt(i).findViewById(R.id.radioGroup);
//            if (radioGroup2.getCheckedRadioButtonId() != -1) {
//
//                RadioButton radioButton = (RadioButton) radioGroup2.findViewById(radioGroup2.getCheckedRadioButtonId());
//                String answer = radioButton.getText().toString();
//                Log.i(TAG, answer);
//            }

            Log.i(TAG,adapter.correctAnswers[i]  + " <- answer ");
        }



        if(correctAnsers > 0) {
            result = String.format("%.2f", ((correctAnsers / adapter.getItemCount()) * 100)) + "%";
        }
        list.setGrade(result);

        Log.i(TAG,"Correct answers: " + correctAnsers + " Grade = " + result);
        Log.i(TAG,"Array Length " + adapter.correctAnswers.length );
        intent.putExtra("name",testName);
        intent.putExtra("result",result);
        intent.putExtra("category",list.getQuestionList().get(0).getResult().getCategory());
        intent.putExtra("difficulty",list.getQuestionList().get(0).getResult().getDifficulty());

        setResult(1, intent);
        finish();
    }
}
