package com.jithin.games.chessquad.chess;

import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;

import static com.jithin.games.chessquad.chess.Utls.*;

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
        if(this.Y != '1'){
            /* UP move */
            if(board.mtx[intOf(X)][intOf(up(Y))] == null) {
                board.addHighlight(X, up(Y));
                if (Y == 'a' && board.mtx[intOf(X)][intOf(up(up(Y)))]==null) {
                    board.addHighlight(X, up(up(Y)));
                }
            }
            /* Cross moves */
            if(X!='b' && board.mtx[intOf(right(X))][intOf(up(Y))]!=null &&
                    board.mtx[intOf(right(X))][intOf(up(Y))].side!=Board.MINE){
                board.addHighlight(right(X), up(Y));
            }
            if(X!='0' && board.mtx[intOf(left(X))][intOf(up(Y))]!=null &&
                    board.mtx[intOf(left(X))][intOf(up(Y))].side!=Board.MINE){
                board.addHighlight(left(X), up(Y));
            }
        }
    }
}
