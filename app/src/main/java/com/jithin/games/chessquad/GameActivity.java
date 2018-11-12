package com.jithin.games.chessquad;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewTreeObserver;
import com.jithin.games.chessquad.chess.Board;

public class GameActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.jithin.games.chessquadMESSAGE";
    public Board board;
    public ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        layout = (ConstraintLayout) findViewById(R.id.board);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay()
                .getMetrics(displayMetrics);

        board = new Board(layout, displayMetrics.widthPixels/12);
        board.init();
    }

}

