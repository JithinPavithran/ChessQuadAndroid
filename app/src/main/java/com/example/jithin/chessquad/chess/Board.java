package com.example.jithin.chessquad.chess;

import android.content.Context;
import android.content.res.Resources;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.example.jithin.chessquad.chess.Utls.hex;
import static com.example.jithin.chessquad.chess.Utls.intOf;
import static com.example.jithin.chessquad.chess.Utls.next;

/**
 * Created by jithin on 16/1/18.
 */

public class Board{
    protected Piece mtx[][];
    protected List<ImageView> highlights = new ArrayList<ImageView>();
    protected ConstraintLayout layout;
    public char color(char side){
        switch (side){
            case 't': return 'r';
            case 'l': return 'g';
            case 'r': return 'y';
            case 'b': return 'b';
        }
        return 'b';
    }
    protected int maxId;
    protected int maxHighId;
    protected Resources resources;
    protected HighlighListener highlighListener;
    protected PieceListener pieceListener;
    protected Piece activePiece;


    public Board(ConstraintLayout layout){
        this.layout = layout;
        this.resources = layout.getContext().getResources();
        this.highlighListener = new HighlighListener(this);
        this.pieceListener = new PieceListener(this);
        this.maxId = 1000;
        this.maxHighId = 2000;

        mtx = new Piece[12][12];
    }

    public void init(){
        set_top();
        set_right();
        set_left();
        set_bottom();
    }

    protected void set_top(){
        addPiece(2,0,'r', 't');
        addPiece(3,0,'n', 't');
        addPiece(4,0,'b', 't');
        addPiece(5,0,'q', 't');
        addPiece(6,0,'k', 't');
        addPiece(7,0,'b', 't');
        addPiece(8,0,'n', 't');
        addPiece(9,0,'r', 't');
        addPiece(2,1,'p', 't');
        addPiece(3,1,'p', 't');
        addPiece(4,1,'p', 't');
        addPiece(5,1,'p', 't');
        addPiece(6,1,'p', 't');
        addPiece(7,1,'p', 't');
        addPiece(8,1,'p', 't');
        addPiece(9,1,'p', 't');
    }
    protected void set_right(){
        addPiece(11,2,'r', 'r');
        addPiece(11,3,'n', 'r');
        addPiece(11,4,'b', 'r');
        addPiece(11,5,'q', 'r');
        addPiece(11,6,'k', 'r');
        addPiece(11,7,'b', 'r');
        addPiece(11,8,'n', 'r');
        addPiece(11,9,'r', 'r');
        addPiece(10,2,'p', 'r');
        addPiece(10,3,'p', 'r');
        addPiece(10,4,'p', 'r');
        addPiece(10,5,'p', 'r');
        addPiece(10,6,'p', 'r');
        addPiece(10,7,'p', 'r');
        addPiece(10,8,'p', 'r');
        addPiece(10,9,'p', 'r');
    }
    protected void set_left(){
        addPiece(0,2,'r', 'l');
        addPiece(0,3,'n', 'l');
        addPiece(0,4,'b', 'l');
        addPiece(0,5,'q', 'l');
        addPiece(0,6,'k', 'l');
        addPiece(0,7,'b', 'l');
        addPiece(0,8,'n', 'l');
        addPiece(0,9,'r', 'l');
        addPiece(1,2,'p', 'l');
        addPiece(1,3,'p', 'l');
        addPiece(1,4,'p', 'l');
        addPiece(1,5,'p', 'l');
        addPiece(1,6,'p', 'l');
        addPiece(1,7,'p', 'l');
        addPiece(1,8,'p', 'l');
        addPiece(1,9,'p', 'l');

    }
    protected void set_bottom(){
        addPiece(2,11,'r', 'b');
        addPiece(3,11,'n', 'b');
        addPiece(4,11,'b', 'b');
        addPiece(5,11,'q', 'b');
        addPiece(6,11,'k', 'b');
        addPiece(7,11,'b', 'b');
        addPiece(8,11,'n', 'b');
        addPiece(9,11,'r', 'b');
        addPiece(2,10,'p', 'b');
        addPiece(3,10,'p', 'b');
        addPiece(4,10,'p', 'b');
        addPiece(5,10,'p', 'b');
        addPiece(6,10,'p', 'b');
        addPiece(7,10,'p', 'b');
        addPiece(8,10,'p', 'b');
        addPiece(9,10,'p', 'b');
    }


    public void addPiece(int x, int y, char type, char side){
        // TODO: add return Type
        char color = this.color(side);
        maxId += 1;
        switch (type){
            case 'p':
                mtx[x][y] = new Pawn(this,
                        ""+hex(x)+hex(y)+color+type, side, maxId);
                break;
            default:
                mtx[x][y] = new Piece(this,
                        ""+hex(x)+hex(y)+color+type, side, maxId);
        }
    }

    public void addHighlight(char x, char y){
        maxHighId = maxHighId + 1;
        HighlightView h = new HighlightView(x, y, this, maxHighId);
        highlights.add(h);
    }

    public void clearHighlights(){
        for(Iterator<ImageView> iter = highlights.iterator(); iter.hasNext();){
            layout.removeView(iter.next());
            iter.remove();
            maxHighId = 2000;
        }

    }

    public void pieceClicked(Piece piece){
        this.clearHighlights();
        this.activePiece = piece;
        piece.addHighlight();
    }

    public void highlightClicked(HighlightView hv){
        this.moveActivePiece(hv.Xcoord, hv.Ycoord);
        this.clearHighlights();
    }

    public void moveActivePiece(char x, char y){
        Log.d("Moving", "piece");
        this.activePiece.moveTo(x, y);
        mtx[intOf(x)][intOf(y)] = this.activePiece;
        mtx[intOf(this.activePiece.X)][intOf(this.activePiece.Y)] = null;
    }

    public void setPosition(int id, char x, char y){
        Context context = layout.getContext();
        ConstraintSet conSet = new ConstraintSet();
        conSet.clone(layout);
        conSet.connect(id, ConstraintSet.LEFT, resources.getIdentifier("glx"+x, "id", context.getPackageName()), ConstraintSet.LEFT, 0);
        conSet.connect(id, ConstraintSet.END, resources.getIdentifier("glx"+next(x), "id", context.getPackageName()), ConstraintSet.END, 0);
        conSet.connect(id, ConstraintSet.TOP, resources.getIdentifier("gly"+y, "id", context.getPackageName()), ConstraintSet.TOP, 0);
        conSet.connect(id, ConstraintSet.BOTTOM, resources.getIdentifier("gly"+next(y), "id", context.getPackageName()), ConstraintSet.BOTTOM, 0);
        conSet.applyTo(layout);
    }

}
