package com.jithin.games.chessquad;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import com.jithin.games.chessquad.chess.Board;

public class GameActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.jithin.games.chessquadMESSAGE";
    public Board board;
    public ConstraintLayout layout;

    private class LoadBoard extends AsyncTask<Object, Void, Void> {
        protected Void doInBackground(Object... objects) {
            ((Board)objects[0]).init();
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        layout = findViewById(R.id.board);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay()
                .getMetrics(displayMetrics);

        board = new Board(layout, displayMetrics.widthPixels/12);
        LoadBoard loadBoard = new LoadBoard();
        loadBoard.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, this.board);
    }

}

