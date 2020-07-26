package team.fourty.seven.learnit.models;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class QuizQuestionsList implements Parcelable {

    private List<QuizQuestion> questionList;
    private int numberOfQuestions;
    private String grade;
    private int total;
    private String testName;
    private String difficulty;
    private String difficultyColor;

    public QuizQuestionsList(Parcel in) {
        numberOfQuestions = in.readInt();
        grade = in.readString();
        total = in.readInt();
        testName = in.readString();
        difficulty = in.readString();
        difficultyColor = in.readString();
    }

    public static final Creator<QuizQuestionsList> CREATOR = new Creator<QuizQuestionsList>() {
        @Override
        public QuizQuestionsList createFromParcel(Parcel in) {
            return new QuizQuestionsList(in);
        }

        @Override
        public QuizQuestionsList[] newArray(int size) {
            return new QuizQuestionsList[size];
        }
    };

    public String getDifficultyColor() {
        return difficultyColor;
    }

    public void setDifficultyColor(String difficultyColor) {
        this.difficultyColor = difficultyColor;
    }

    public QuizQuestionsList() {
        questionList = new ArrayList<>();
        grade = "0%";


    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }



    public List<QuizQuestion> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<QuizQuestion> questionList) {
        this.questionList = questionList;
    }

    public int getNumberOfQuestions() {
        return questionList.size();
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getTotal() {
        return this.questionList.size();
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    @Override
    public String toString() {
        return "QuizQuestionsList{" +
                "questionList=" + questionList +
                ", numberOfQuestions=" + numberOfQuestions +
                ", grade=" + grade +
                ", total=" + total +
                ", testName='" + testName + '\'' +
                ", difficulty='" + difficulty + '\'' +
                '}';
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
     * the return value of this method must include the
     * {@link #CONTENTS_FILE_DESCRIPTOR} bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(numberOfQuestions);
        dest.writeString(grade);
        dest.writeInt(total);
        dest.writeString(testName);
        dest.writeString(difficulty);
        dest.writeString(difficultyColor);
    }
}
