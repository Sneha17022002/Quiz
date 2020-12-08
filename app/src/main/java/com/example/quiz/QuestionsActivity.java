package com.example.quiz;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.Animator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class QuestionsActivity extends AppCompatActivity {
    private TextView question, noIndicator;
    private FloatingActionButton bookmarkBtn;
    private LinearLayout optionsContainer;
    private Button shareBtn , nextBtn;
    private int count=0;
    private List<QuestionModel>list;
    private int position=0;
    private int score=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        question= findViewById(R.id.question);
        noIndicator= findViewById(R.id.no_indicator);
        bookmarkBtn= findViewById(R.id.bookmark_btn);
        optionsContainer= findViewById(R.id.options_container);
        shareBtn= findViewById(R.id.share_btn);
        nextBtn= findViewById(R.id.next_btn);
        list =new ArrayList<>();
        list.add(new QuestionModel("1. Which of these best describes an array?", "a A data structure that shows a hierarchical behavior","b Container of objects of similar types","c Arrays are immutable once initialised","d Array is not a data structure","b Container of objects of similar types"));
        list.add(new QuestionModel("question2", "a","b","c","d","b"));
        list.add(new QuestionModel("question3", "a","b","c","d","c"));
        list.add(new QuestionModel("question4", "a","b","c","d","d"));
        list.add(new QuestionModel("question5", "a","b","c","d","a"));
        list.add(new QuestionModel("question6", "a","b","c","d","b"));
        list.add(new QuestionModel("question7", "a","b","c","d","c"));
        list.add(new QuestionModel("question8", "a","b","c","d","d"));

        for(int i=0;i<4; i++){
            optionsContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
                checkAnswer((Button) v);
                }
            });

        }
       playAnim(question,0, list.get(position).getQuestion());

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextBtn.setEnabled(false);
                nextBtn.setAlpha(0.7f);
                position++;
                if(position ==list.size()){
                    //score activity
                    return;
                }
                count=0;
              playAnim(question,0, list.get(position).getQuestion());
            }
        });
    }
    private void playAnim(final View view, final int value, final String data){
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
             if(value==0 && count<4){
                 String option="";
                 if(count==0){
                  option=list.get(position).getOptionA();
                 }else if(count==1){
                     option=list.get(position).getOptionB();
                 }else if(count==2){
                     option=list.get(position).getOptionC();
                 }else if(count==3){
                     option=list.get(position).getOptionD();

                 }
            playAnim(optionsContainer.getChildAt(count),0,option);
            count++;
             }
            }

            @Override
            public void onAnimationEnd(Animator animation) {



            if(value==0){
                try{
                    ((TextView)view).setText(data);

                }catch(ClassCastException ex){
                    ((Button)view).setText(data);
                }
                view.setTag(data);
               playAnim(view,1, data);
            }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void checkAnswer(Button  selectedOption){
  enableOption(false);
  nextBtn.setEnabled(true);
  nextBtn.setAlpha(1);
  if(selectedOption.getText().toString().equals(list.get(position).getCorrectANS())){
      score++;
      selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
  }else{
      selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
      Button correctoption=(Button)optionsContainer.findViewWithTag(list.get(position).getCorrectANS());
      correctoption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));

  }

    }
    private void enableOption(boolean enable){
        for(int i=0;i<4; i++){
          optionsContainer.getChildAt(i).setEnabled(enable);

        }
    }

}