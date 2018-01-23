package com.example.jithin.chessquad.chess;

import android.util.Log;
import android.view.View;

/**
 * Created by jithin on 23/1/18.
 */

public class PieceListener implements Piece.OnClickListener {

    Board board;
    public PieceListener(Board board){
        this.board = board;
    }

    @Override
    public void onClick(View view){
        Log.d("Click", "Piece Clicked");
        this.board.pieceClicked((Piece) view);
    }
}
