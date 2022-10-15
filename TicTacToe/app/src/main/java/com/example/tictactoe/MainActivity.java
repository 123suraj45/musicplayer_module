package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
//player representation
    //0-x
    //1-o


public class MainActivity extends AppCompatActivity {
    boolean gameActive=true;
    int activePlayer=0;
    int[] gameState ={2,2,2,2,2,2,2,2,2};
    //    gameState meaning
    //    0-x   1-o  2-blank;
    int [][] winPositions={{0,1,2},{3,4,5},{6,7,8},
                          {0,3,6},{1,4,7},{2,5,8},
                          {0,4,8},{2,4,6}};

    public void playerTap(View view){
        ImageView img =(ImageView) view;
        int tappedImage=Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset(view);

            return;
        }
        if(gameState[tappedImage]==2 && gameActive){
            gameState[tappedImage]=activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer==0){
                img.setImageResource(R.drawable.x);
                activePlayer=1;
                TextView status =findViewById(R.id.status);
                status.setText("O's Turn-Tap to Play");
            }
            else{
                img.setImageResource(R.drawable.o);
                activePlayer=0;
                TextView status =findViewById(R.id.status);
                status.setText("X's Turn-Tap to Play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
       // check if any player has won
       for(int [] winPosition : winPositions){
           if(gameState[winPosition[0]]==gameState[winPosition[1]] && gameState[winPosition[0]]==gameState[winPosition[2]] && gameState[winPosition[0]]!=2){
               //somebody has won
               String winnerStr;
               if(gameState[winPosition[0]]==0){
                 winnerStr="X has won";
                   Toast.makeText(this, "X has won", Toast.LENGTH_SHORT).show();
               }
               else{
                   winnerStr="O has won";
                   Toast.makeText(this, "O has won", Toast.LENGTH_SHORT).show();
               }
               TextView status =findViewById(R.id.status);
               status.setText(winnerStr);
               gameActive=false;
           }

       }
       // check for draw
        boolean draw =true;
        for(int i=0;i<9;i++){
            if(gameState[i]==2){
                draw=false;
                break;
            }
        }
        if(draw && gameActive){
            gameActive=false;
            TextView status =findViewById(R.id.status);
            status.setText("Draw Game");
            Toast.makeText(this, "DRAW GAME", Toast.LENGTH_SHORT).show();
        }

    }
    public void gameReset(View view){
        gameActive=true;
        activePlayer=0;
        for(int i=0;i<gameState.length;i++)
            gameState[i]=2;
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        TextView status =findViewById(R.id.status);
        status.setText("X's Turn-Tap to Play");

        return;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}