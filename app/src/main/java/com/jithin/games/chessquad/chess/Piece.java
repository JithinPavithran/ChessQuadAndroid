package com.jithin.games.chessquad.chess;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.widget.AppCompatImageView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import com.jithin.games.chessquad.GameActivity;
import com.jithin.games.chessquad.R;

import static com.jithin.games.chessquad.chess.Utls.intOf;
import static com.jithin.games.chessquad.chess.Utls.next;

/**
 * Created by jithin on 13/1/18.
 */

@SuppressLint("AppCompatCustomView")
public class Piece extends ImageView{
    /* Piece String: <Xcoord><Y><color><Piece type>
     *
     */
    public static final char PAWN    = 'p';
    public static final char ROOK    = 'r';
    public static final char KNIGHt  = 'n';
    public static final char BISHOP  = 'b';
    public static final char QUEEN   = 'q';
    public static final char KING    = 'k';
    public static final char DUMMY   = 'z';

    protected char X;
    protected char Y;
    protected char color;
    protected char type;
    protected char side;
    protected Board board;

    public Piece(final Board board, String piece, char side, int id){
        super(board.layout.getContext());
        this.board = board;
        X = piece.charAt(0);
        Y = piece.charAt(1);
        color = piece.charAt(2);
        type = piece.charAt(3);
        this.side = side;

        Context context = board.layout.getContext();
        this.setLayoutParams(new ConstraintLayout.LayoutParams(board.unit,board.unit));
        setImage(context);
        attachToBoard(board, this, id, X, Y);
    }

    public void setImage(Context context) {
        int image_id = context.getResources().getIdentifier(
                ""+type+color, "drawable", context.getPackageName());
        this.setImageResource(image_id);
    }

    public void attachToBoard(final Board board, final Piece piece,
                              final int view_id, final char x, final char y){
        ((GameActivity)getContext()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                board.layout.addView(piece);
                if(piece.side==Board.MINE && piece.type!=Piece.DUMMY){
                    piece.setOnClickListener(board.pieceListener);
                }
                piece.setId(view_id);
                piece.setX(board.unit*intOf(x));
                piece.setY(board.unit*intOf(y));
            }
        });
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
        this.setX(intOf(x)*board.unit);
        this.setY(intOf(y)*board.unit);
    }

    public void addHighlight(){

    }

}
