package com.adi.transitionjava;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    boolean show = false;
    ConstraintLayout constraint;
    ImageView ivBackGround;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circuit);

        constraint = findViewById(R.id.constraint);
        ivBackGround = findViewById(R.id.backgroundImage);

        ivBackGround.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(show){
                    hideComponents();
                }else{
                    showComponents();
                }
            }
        });
    }

    private void showComponents(){
        show = true;

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this,R.layout.circuit_detail);

        ChangeBounds transition = new ChangeBounds();
        transition.setInterpolator(new AnticipateOvershootInterpolator(1.0f));
        transition.setDuration(1200);

        TransitionManager tm = new TransitionManager();
        tm.beginDelayedTransition(constraint,transition);
        constraintSet.applyTo(constraint);
    }

    private void hideComponents(){
        show = false;

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this,R.layout.circuit);

        ChangeBounds transition = new ChangeBounds();
        transition.setInterpolator(new AnticipateOvershootInterpolator(1.0f));
        transition.setDuration(1200);

        TransitionManager tm = new TransitionManager();
        tm.beginDelayedTransition(constraint,transition);
        constraintSet.applyTo(constraint);
    }
}
