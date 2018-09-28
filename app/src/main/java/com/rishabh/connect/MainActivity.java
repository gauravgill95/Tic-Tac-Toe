package com.rishabh.connect;

import android.icu.text.BreakIterator;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // activeplayer = 1 >> cross ;;;;; activeplayer = 0 >> Circle
    int activePlayer = 0;
    int count = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] win = {{1,2,3},{4,5,6},{7,8,9},{1,4,7},{2,5,8},{3,6,9},{1,5,9},{3,5,7}};

    public void dropin(View view) {
        ImageView imageView = (ImageView) view;
        int a = Integer.parseInt(imageView.getTag().toString());
        if (gameState[a - 1] == 2 && count != 1) {
            gameState[a-1] = activePlayer;
            imageView.setTranslationY(-1000f);
            if (activePlayer == 0) {
                imageView.setImageResource(R.drawable.circular);
                activePlayer = 1;
            } else {
                imageView.setImageResource(R.drawable.cross);
                activePlayer = 0;
            }
            imageView.animate().translationYBy(1000f).setDuration(300);
            for (int[] winning : win ){
                if (gameState[winning[0]-1] == gameState[winning[1]-1] &&
                gameState[winning[1]-1] == gameState[winning[2]-1] &&
                gameState[winning[0]-1] != 2) {
                        //System.out.println( gameState[winning[0]-1] );
                    //Toast.makeText(MainActivity.this, "Player "+ (gameState[winning[0]-1] + 1) + " WON!!!", Toast.LENGTH_LONG).show();
                    TextView text = (TextView) findViewById(R.id.textView);
                    text.setText(" Player "+ (gameState[winning[0]-1] + 1) + " WON!!!");
                    count = count +1;
                }

            }int gameCounter=0;
            for (int i : gameState){
                if (i != 2) gameCounter = gameCounter + 1;

            }if (gameCounter == 9 && count != 1){
                TextView text = (TextView) findViewById(R.id.textView);
                text.setText(" It's a DRAW!!");
            }
        }
    }public void Replay (View view){
        int i;
        activePlayer = 0;
        count = 0;
        for(i=0;i<gameState.length;i++){
            gameState[i]=2;
        }TextView text = (TextView)findViewById(R.id.textView);
        text.setText("");
        GridLayout gridlayout = (GridLayout)findViewById(R.id.gridLayout);
        for(i=0;i< gridlayout.getChildCount();i++){
            ((ImageView) gridlayout.getChildAt(i)).setImageResource(0);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
