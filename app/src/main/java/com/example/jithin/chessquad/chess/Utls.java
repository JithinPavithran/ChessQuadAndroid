package com.example.jithin.chessquad.chess;

/**
 * Created by jithin on 17/1/18.
 */

public class Utls {
    public static char hex(int n){
        return (char) (n<10? n+48 : n+87);
    }

    public static char next(char n){
        return (char) (n!='9'? n+1 : n+40);
    }

    public static char pre(char n){
        return (char) (n!='a'? n-1 : n-40);
    }

    public static int intOf(char n){
        return (int) (n<'a' ? n-'0' : n-'a');
    }

}
