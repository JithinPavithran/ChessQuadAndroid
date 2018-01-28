package com.jithin.games.chessquad.chess;

import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;

import static com.jithin.games.chessquad.chess.Utls.intOf;
import static com.jithin.games.chessquad.chess.Utls.pre;

/**
 * Created by jithin on 17/1/18.
 */

public class Pawn extends Piece {

    public Pawn(Board board, String piece, char side, int id){
        super(board, piece, side, id);
    }

    @Override
    public void addHighlight(){
        Log.d("Adding Highlight", "Pawn");
        if(this.Y > '0'){
            if(board.mtx[intOf(X)][intOf(pre(Y))] == null) {
                board.addHighlight(X, pre(Y));
                if (Y == 'a' && board.mtx[intOf(X)][intOf(pre(pre(Y)))] == null) {
                    board.addHighlight(X, pre(pre(Y)));
                }
            }
        }
    }
}
