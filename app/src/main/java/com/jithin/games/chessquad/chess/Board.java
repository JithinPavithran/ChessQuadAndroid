package com.jithin.games.chessquad.chess;

import android.content.Context;
import android.content.res.Resources;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.util.Log;
import android.widget.ImageView;
import com.jithin.games.chessquad.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static com.jithin.games.chessquad.chess.Utls.hex;
import static com.jithin.games.chessquad.chess.Utls.intOf;
import static com.jithin.games.chessquad.chess.Utls.next;

/**
 * Created by jithin on 16/1/18.
 */

public class Board{
    protected Piece mtx[][];
    protected List<ImageView> highlights = new ArrayList<ImageView>();
    protected ConstraintLayout layout;
    protected char[] colors = {'b', 'y', 'r', 'g'};
    public char color(char side){
        switch (side){
            case 'b': return this.colors[0];
            case 'r': return this.colors[1];
            case 't': return this.colors[2];
            case 'l': return this.colors[3];
            case 'z': return 'z';
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

    public Board(ConstraintLayout layout){
        this.layout = layout;
        this.resources = layout.getContext().getResources();
        this.playerInit('b');
        this.highlighListener = new HighlighListener(this);
        this.pieceListener = new PieceListener(this);
        this.maxId = 1000;
        this.maxHighId = 2000;

        mtx = new Piece[12][12];
        for (Piece[] row: this.mtx){
            Arrays.fill(row, null);
        }
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
        set_inactive();
        set_top();
        set_right();
        set_left();
        set_bottom();
    }
    protected void set_inactive(){
        addPiece(0,0,'z', 'z');
        addPiece(1,0,'z', 'z');
        addPiece(0,1,'z', 'z');
        addPiece(1,1,'z', 'z');

        addPiece(10,0,'z', 'z');
        addPiece(11,0,'z', 'z');
        addPiece(10,1,'z', 'z');
        addPiece(11,1,'z', 'z');

        addPiece(0,10,'z', 'z');
        addPiece(1,10,'z', 'z');
        addPiece(0,11,'z', 'z');
        addPiece(1,11,'z', 'z');

        addPiece(10,10,'z', 'z');
        addPiece(11,10,'z', 'z');
        addPiece(10,11,'z', 'z');
        addPiece(11,11,'z', 'z');
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
            case 'r':
                mtx[x][y] = new Rook(this,
                        ""+hex(x)+hex(y)+color+type, side, maxId);
                break;
            case 'b':
                mtx[x][y] = new Bishop(this,
                        ""+hex(x)+hex(y)+color+type, side, maxId);
                break;
            case 'q':
                mtx[x][y] = new Queen(this,
                        ""+hex(x)+hex(y)+color+type, side, maxId);
                break;
            case 'n':
                mtx[x][y] = new Knight(this,
                        ""+hex(x)+hex(y)+color+type, side, maxId);
                break;
            case 'k':
                mtx[x][y] = new King(this,
                        ""+hex(x)+hex(y)+color+type, side, maxId);
                break;
            case 'z':
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
        if(piece.side == 'b'){
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
        conSet.connect(id, ConstraintSet.END, resources.getIdentifier("glx"+next(x), "id", context.getPackageName()), ConstraintSet.END, 0);
        conSet.connect(id, ConstraintSet.TOP, resources.getIdentifier("gly"+y, "id", context.getPackageName()), ConstraintSet.TOP, 0);
        conSet.connect(id, ConstraintSet.BOTTOM, resources.getIdentifier("gly"+next(y), "id", context.getPackageName()), ConstraintSet.BOTTOM, 0);
        conSet.applyTo(layout);
    }

    public boolean validCell(char x, char y) {
        return !((x == '0' || x == '1' || x == 'a' || x == 'b')
                        && (y == '0' || y == '1'))
                && !((x == '0' || x == '1' || x == 'a' || x == 'b')
                        && (y == 'a' || y == 'b'));
    }

}
