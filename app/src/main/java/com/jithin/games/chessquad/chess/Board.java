package com.jithin.games.chessquad.chess;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.util.Log;
import android.util.TimingLogger;
import android.widget.ImageView;
import com.jithin.games.chessquad.GameActivity;
import com.jithin.games.chessquad.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static com.jithin.games.chessquad.chess.Utls.hex;
import static com.jithin.games.chessquad.chess.Utls.intOf;

/**
 * Created by jithin on 16/1/18.
 */

public class Board extends AsyncTask<Object, Void, Void>{
    public static final char RIGHT  = 'r';
    public static final char BOTTOM = 'b';
    public static final char TOP    = 't';
    public static final char LEFT   = 'l';
    public static final char DUMMY  = 'z';
    public static final char RED    = 'r';
    public static final char BLUE   = 'b';
    public static final char YELLOW = 'y';
    public static final char GREEN  = 'g';
    public static final char MINE   = BOTTOM;

    protected Piece mtx[][];
    protected List<ImageView> highlights = new ArrayList<ImageView>();
    protected ConstraintLayout layout;
    protected int unit;                             // side of a cell
    protected char[] colors = {BLUE, YELLOW, RED, GREEN};
    public char color(char side){
        switch (side){
            case BOTTOM : return this.colors[0];
            case RIGHT  : return this.colors[1];
            case TOP    : return this.colors[2];
            case LEFT   : return this.colors[3];
            case DUMMY  : return 'z';
            default: Log.e("Unknown side", "side '"+side+"' is not recognised");
        }
        return 'b';
    }
    protected int maxId;
    protected int maxHighId;
    protected Resources resources;
    protected HighlighListener highlighListener;
    protected PieceListener pieceListener;
    protected Piece activePiece;
    protected int highlightImage;

    public Board(ConstraintLayout layout, int unit){
        this.layout = layout;
        this.unit = unit;
        this.resources = layout.getContext().getResources();
        this.playerInit(BLUE);
        this.highlighListener = new HighlighListener(this);
        this.pieceListener = new PieceListener(this);
        this.maxId = 1000;
        this.maxHighId = 2000;
        this.mtx = new Piece[12][12];
    }

    protected Void doInBackground(Object ... object){
        Method method = (Method)object[0];
        try {
            method.invoke(object[1]);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void playerInit(char mycolor){
        this.highlightImage = R.drawable.highlight;
        char[] colors = this.colors.clone();
        int j=0;
        while (mycolor!=colors[j]){ j++; }
        for(int i=0; i<4; ++i){
            this.colors[i] = colors[(i+j)%4];
        }
    }
    public void init(){
        try {
            Method method = Board.class.getMethod("set_top");
            doInBackground(method, this);
            method = Board.class.getMethod("set_right");
            doInBackground(method, this);
            method = Board.class.getMethod("set_left");
            doInBackground(method, this);
            method = Board.class.getMethod("set_bottom");
            doInBackground(method, this);
            method = Board.class.getMethod("set_inactive");
            doInBackground(method, this);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
//        set_bottom();
//        set_inactive();
//        set_left();
//        set_right();
//        set_top();
    }

    protected void set_inactive(){
        addPiece(0,0,Piece.DUMMY, Board.MINE);
        addPiece(0,1,Piece.DUMMY, Board.MINE);
        addPiece(1,0,Piece.DUMMY, Board.MINE);
        addPiece(1,1,Piece.DUMMY, Board.MINE);

        addPiece(10,0,Piece.DUMMY, Board.MINE);
        addPiece(11,0,Piece.DUMMY, Board.MINE);
        addPiece(10,1,Piece.DUMMY, Board.MINE);
        addPiece(11,1,Piece.DUMMY, Board.MINE);

        addPiece(0,10,Piece.DUMMY, Board.MINE);
        addPiece(1,10,Piece.DUMMY, Board.MINE);
        addPiece(0,11,Piece.DUMMY, Board.MINE);
        addPiece(1,11,Piece.DUMMY, Board.MINE);

        addPiece(10,10,Piece.DUMMY, Board.MINE);
        addPiece(11,10,Piece.DUMMY, Board.MINE);
        addPiece(10,11,Piece.DUMMY, Board.MINE);
        addPiece(11,11,Piece.DUMMY, Board.MINE);
    }
    protected void set_bottom(){
        addPiece(2,0,Piece.ROOK,   Board.BOTTOM);
        addPiece(3,0,Piece.KNIGHt, Board.BOTTOM);
        addPiece(4,0,Piece.BISHOP, Board.BOTTOM);
        addPiece(5,0,Piece.QUEEN,  Board.BOTTOM);
        addPiece(6,0,Piece.KING,   Board.BOTTOM);
        addPiece(7,0,Piece.BISHOP, Board.BOTTOM);
        addPiece(8,0,Piece.KNIGHt, Board.BOTTOM);
        addPiece(9,0,Piece.ROOK, Board.BOTTOM);
        addPiece(2,1,Piece.PAWN, Board.BOTTOM);
        addPiece(3,1,Piece.PAWN, Board.BOTTOM);
        addPiece(4,1,Piece.PAWN, Board.BOTTOM);
        addPiece(5,1,Piece.PAWN, Board.BOTTOM);
        addPiece(6,1,Piece.PAWN, Board.BOTTOM);
        addPiece(7,1,Piece.PAWN, Board.BOTTOM);
        addPiece(8,1,Piece.PAWN, Board.BOTTOM);
        addPiece(9,1,Piece.PAWN, Board.BOTTOM);
    }
    protected void set_right(){
        addPiece(11,2,Piece.ROOK,   Board.RIGHT);
        addPiece(11,3,Piece.KNIGHt, Board.RIGHT);
        addPiece(11,4,Piece.BISHOP, Board.RIGHT);
        addPiece(11,5,Piece.QUEEN,  Board.RIGHT);
        addPiece(11,6,Piece.KING,   Board.RIGHT);
        addPiece(11,7,Piece.BISHOP, Board.RIGHT);
        addPiece(11,8,Piece.KNIGHt, Board.RIGHT);
        addPiece(11,9,Piece.ROOK, Board.RIGHT);
        addPiece(10,2,Piece.PAWN, Board.RIGHT);
        addPiece(10,3,Piece.PAWN, Board.RIGHT);
        addPiece(10,4,Piece.PAWN, Board.RIGHT);
        addPiece(10,5,Piece.PAWN, Board.RIGHT);
        addPiece(10,6,Piece.PAWN, Board.RIGHT);
        addPiece(10,7,Piece.PAWN, Board.RIGHT);
        addPiece(10,8,Piece.PAWN, Board.RIGHT);
        addPiece(10,9,Piece.PAWN, Board.RIGHT);
    }
    protected void set_left(){
        addPiece(0,2,Piece.ROOK,   Board.LEFT);
        addPiece(0,3,Piece.KNIGHt, Board.LEFT);
        addPiece(0,4,Piece.BISHOP, Board.LEFT);
        addPiece(0,5,Piece.QUEEN,  Board.LEFT);
        addPiece(0,6,Piece.KING,   Board.LEFT);
        addPiece(0,7,Piece.BISHOP, Board.LEFT);
        addPiece(0,8,Piece.KNIGHt, Board.LEFT);
        addPiece(0,9,Piece.ROOK, Board.LEFT);
        addPiece(1,2,Piece.PAWN, Board.LEFT);
        addPiece(1,3,Piece.PAWN, Board.LEFT);
        addPiece(1,4,Piece.PAWN, Board.LEFT);
        addPiece(1,5,Piece.PAWN, Board.LEFT);
        addPiece(1,6,Piece.PAWN, Board.LEFT);
        addPiece(1,7,Piece.PAWN, Board.LEFT);
        addPiece(1,8,Piece.PAWN, Board.LEFT);
        addPiece(1,9,Piece.PAWN, Board.LEFT);

    }
    protected void set_top(){
        addPiece(2,11,Piece.ROOK,   Board.TOP);
        addPiece(3,11,Piece.KNIGHt, Board.TOP);
        addPiece(4,11,Piece.BISHOP, Board.TOP);
        addPiece(5,11,Piece.QUEEN,  Board.TOP);
        addPiece(6,11,Piece.KING,   Board.TOP);
        addPiece(7,11,Piece.BISHOP, Board.TOP);
        addPiece(8,11,Piece.KNIGHt, Board.TOP);
        addPiece(9,11,Piece.ROOK, Board.TOP);
        addPiece(2,10,Piece.PAWN, Board.TOP);
        addPiece(3,10,Piece.PAWN, Board.TOP);
        addPiece(4,10,Piece.PAWN, Board.TOP);
        addPiece(5,10,Piece.PAWN, Board.TOP);
        addPiece(6,10,Piece.PAWN, Board.TOP);
        addPiece(7,10,Piece.PAWN, Board.TOP);
        addPiece(8,10,Piece.PAWN, Board.TOP);
        addPiece(9,10,Piece.PAWN, Board.TOP);
    }

    public void addPiece(int x, int y, char type, char side){
        char color = this.color(side);
        maxId += 1;
        switch (type){
            case Piece.PAWN:
                mtx[x][y] = new Pawn(this,
                        ""+hex(x)+hex(y)+color+type, side, maxId);
                break;
            case Piece.ROOK:
                mtx[x][y] = new Rook(this,
                        ""+hex(x)+hex(y)+color+type, side, maxId);
                break;
            case Piece.BISHOP:
                mtx[x][y] = new Bishop(this,
                        ""+hex(x)+hex(y)+color+type, side, maxId);
                break;
            case Piece.QUEEN:
                mtx[x][y] = new Queen(this,
                        ""+hex(x)+hex(y)+color+type, side, maxId);
                break;
            case Piece.KNIGHt:
                mtx[x][y] = new Knight(this,
                        ""+hex(x)+hex(y)+color+type, side, maxId);
                break;
            case Piece.KING:
                mtx[x][y] = new King(this,
                        ""+hex(x)+hex(y)+color+type, side, maxId);
                break;
            case Piece.DUMMY:
                mtx[x][y] = new Piece(this,
                        ""+hex(x)+hex(y)+color+type, side, maxId);
                break;
            default:
                Log.e("Unknown Piece", "Piece type '"+type+"' is not recognized");
        }
    }

    public void addHighlight(char x, char y){
        if(this.validCell(x,y)){
            maxHighId = maxHighId + 1;
            HighlightView h = new HighlightView(x, y, this, maxHighId);
            highlights.add(h);
        }
    }

    public void clearHighlights(){
        for(Iterator<ImageView> iter = highlights.iterator(); iter.hasNext();){
            layout.removeView(iter.next());
            iter.remove();
            maxHighId = 2000;
        }

    }

    public void pieceClicked(Piece piece){
        if(piece.side == Board.MINE){
            this.clearHighlights();
            if(this.activePiece != null &&
                    this.activePiece.getId() == piece.getId()) {
                this.activePiece = null;
                return;
            }
            this.activePiece = piece;
            piece.addHighlight();
        }
    }

    public void highlightClicked(HighlightView hv){
        this.moveActivePiece(hv.Xcoord, hv.Ycoord);
        this.clearHighlights();
        this.activePiece = null;
    }

    public void moveActivePiece(char x, char y){
        Log.d("Moving", "piece");
        mtx[intOf(this.activePiece.X)][intOf(this.activePiece.Y)] = null;
        if(mtx[intOf(x)][intOf(y)]!=null){
            this.layout.removeView(mtx[intOf(x)][intOf(y)]);
            mtx[intOf(x)][intOf(y)] = null;
            System.gc();
        }
        this.activePiece.moveTo(x, y);
        mtx[intOf(x)][intOf(y)] = this.activePiece;
    }

    public void setPosition(int id, char x, char y){
        Context context = layout.getContext();
        ConstraintSet conSet = new ConstraintSet();
        conSet.clone(layout);
        conSet.connect(id, ConstraintSet.LEFT, resources.getIdentifier("glx"+x, "id", context.getPackageName()), ConstraintSet.LEFT, 0);
        conSet.connect(id, ConstraintSet.BOTTOM, resources.getIdentifier("gly"+y, "id", context.getPackageName()), ConstraintSet.BOTTOM, 0);
        conSet.applyTo(layout);
//        final ConstraintSet _conSet = conSet;
//
//        ((GameActivity)layout.getContext()).runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                _conSet.applyTo(layout);
//            }
//        });

    }

    public void setPosition(ConstraintSet constraintSet){
        constraintSet.applyTo(layout);
    }


    public boolean validCell(char x, char y) {
        return !((x == '0' || x == '1' || x == 'a' || x == 'b')
                        && (y == '0' || y == '1'))
                && !((x == '0' || x == '1' || x == 'a' || x == 'b')
                        && (y == 'a' || y == 'b'));
    }

}
