package com.example.jithin.chessquad;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.jithin.chessquad.chess.Board;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.jithin.chessquad.MESSAGE";
    public Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.board);
        board = new Board(layout);
        board.init();
    }


}
