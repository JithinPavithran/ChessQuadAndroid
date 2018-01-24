package com.example.jithin.chessquad.chess;

import android.annotation.SuppressLint;
import android.support.constraint.ConstraintLayout;
import android.widget.ImageView;
import com.example.jithin.chessquad.R;

/**
 * Created by jithin on 23/1/18.
 */

@SuppressLint("AppCompatCustomView")
public class HighlightView extends ImageView {

    protected char Xcoord;
    protected char Ycoord;

    public HighlightView(char x, char y, Board board, int id){
        super(board.layout.getContext());
        this.setImageResource(board.highlightImage);
        this.setLayoutParams(new ConstraintLayout.LayoutParams(0,0));
        board.layout.addView(this);
        this.setOnClickListener(board.highlighListener);
        Xcoord = x;
        Ycoord = y;
        this.setId(id);
        board.setPosition(this.getId(), Xcoord, Ycoord);
    }
}
