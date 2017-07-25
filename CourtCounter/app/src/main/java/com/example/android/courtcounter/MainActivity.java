package com.example.android.courtcounter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int scoreTeamA = 0;
    private int scoreTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
     * Increase the score of team A by 3
     */
    public void threeA(View view) {
        scoreTeamA += 3;
        displayForTeamA(scoreTeamA);
    }

    /*
     * Increase the score of team A by 2
     */
    public void twoA(View view) {
        scoreTeamA += 2;
        displayForTeamA(scoreTeamA);
    }

    /*
     * Increase the score of team A by 21
     */
    public void freeThrowA(View view) {
        scoreTeamA += 1;
        displayForTeamA(scoreTeamA);
    }

    public void displayForTeamA(int number) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(number));
    }

    /*
     * Increase the score of team B by 3
     */
    public void threeB(View view) {
        scoreTeamB += 3;
        displayForTeamB(scoreTeamB);
    }

    /*
     * Increase the score of team A by 2
     */
    public void twoB(View view) {
        scoreTeamB += 2;
        displayForTeamB(scoreTeamB);
    }

    /*
     * Increase the score of team A by 21
     */
    public void freeThrowB(View view) {
        scoreTeamB += 1;
        displayForTeamB(scoreTeamB);
    }

    public void displayForTeamB(int number) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(number));
    }

    public void reset(View view) {
        scoreTeamA = scoreTeamB = 0;
        displayForTeamA(0);
        displayForTeamB(0);
    }
}
