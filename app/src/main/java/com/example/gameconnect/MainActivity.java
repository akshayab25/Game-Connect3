package com.example.gameconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 0-yellow, 1-red, 2-empty
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int [][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int activePlayer = 0;
    boolean gameActive=true;
    public void dropIn(View view){
        ImageView counter=(ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2 && gameActive) {
            gameState[tappedCounter] = activePlayer;
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.setTranslationY(-1500);
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    String winner = "";
                    gameActive=false;
                    if (activePlayer == 0) {
                        winner = "RED";
                    } else winner = "YELLOW";

                    Button playAgain = (Button) findViewById(R.id.playAgainView);
                    TextView textview=(TextView) findViewById(R.id.winnerTextView);
                    textview.setText(winner + " has won!");
                    textview.setVisibility(View.VISIBLE);
                    playAgain.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view){
        Button playAgain=(Button) findViewById(R.id.playAgainView);
        TextView textview=(TextView) findViewById(R.id.winnerTextView);
        textview.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        GridLayout grid=(GridLayout) findViewById(R.id.gridLayout);
        for(int i=0;i<grid.getChildCount();i++){
            ImageView current=(ImageView) grid.getChildAt(i);
            current.setImageDrawable(null);
        }
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        activePlayer = 0;
        gameActive=true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}