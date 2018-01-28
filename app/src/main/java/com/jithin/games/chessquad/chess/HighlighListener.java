package com.jithin.games.chessquad.chess;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by jithin on 23/1/18.
 */

public class HighlighListener implements ImageView.OnClickListener {

    private Board board;

    public HighlighListener(Board board){
        this.board = board;
    }

    @Override
    public void onClick(View view){
        Log.d("Click", "Highlight");
        board.highlightClicked((HighlightView) view);
    }
}
