package com.example.jithin.chessquad.chess;

import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;

import static com.example.jithin.chessquad.chess.Utls.pre;

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
        if(side=='b'){
            board.addHighlight(this.X, pre(this.Y));
            if(this.Y=='a'){
                board.addHighlight(this.X, pre(pre(this.Y)));
            }
        }
    }
}
