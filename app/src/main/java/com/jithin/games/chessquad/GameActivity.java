package com.jithin.games.chessquad;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.jithin.games.chessquad.chess.Board;

public class GameActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.jithin.games.chessquadMESSAGE";
    public Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.board);
        board = new Board(layout);
        board.init();
    }


}
