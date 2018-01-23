package com.example.jithin.chessquad.chess;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import static com.example.jithin.chessquad.chess.Utls.intOf;
import static com.example.jithin.chessquad.chess.Utls.next;

/**
 * Created by jithin on 13/1/18.
 */

@SuppressLint("AppCompatCustomView")
public class Piece extends ImageView{
    /* Piece String: <Xcoord><Y><color><Piece type>
     *
     */
    protected char X;
    protected char Y;
    protected char color;
    protected char type;
    protected char side;
    protected Board board;

    public Piece(Board board, String piece, char side, int id){
        super(board.layout.getContext());
        this.board = board;
        ConstraintLayout layout = board.layout;
        X = piece.charAt(0);
        Y = piece.charAt(1);
        color = piece.charAt(2);
        type = piece.charAt(3);
        this.side = side;
        Context context = layout.getContext();

        setImage(context);
        this.setLayoutParams(new ConstraintLayout.LayoutParams(0,0));
        layout.addView(this);
        setOnClickListener(board.pieceListener);
        this.setId(id);
        board.setPosition(this.getId(), X, Y);
    }

    public void setImage(Context context) {
        int id = context.getResources().getIdentifier(
                ""+type+color, "drawable", context.getPackageName());
        this.setImageResource(id);
    }

    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }

    public void moveTo(char x, char y){
        this.X = x;
        this.Y = y;
        this.board.setPosition(this.getId(), this.X, this.Y);
    }

    public void addHighlight(){

    }

}
