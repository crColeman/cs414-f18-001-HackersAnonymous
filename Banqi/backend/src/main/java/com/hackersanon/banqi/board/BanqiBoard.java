package com.hackersanon.banqi.board;

import com.hackersanon.banqi.piece.Piece;
import com.hackersanon.banqi.piece.TeamColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static com.hackersanon.banqi.piece.PieceAttributes.*;

public class BanqiBoard {
    private Square[][] board;
    private int colDimension = 8;
    private int rowDimension = 4;
    private boolean gameOver;


    public BanqiBoard(){
        this.board = new Square[rowDimension][colDimension];
    }

    public void initialize(){
        initSquares(initAllPieces());
    }

    private void initSquares(ArrayList<Piece> allPieces){
        for (int i = 0; i<board.length;++i){
            for(int j = 0; j<board[i].length;++j){
                board[i][j] = new Square(new Coordinate(i,j), allPieces.remove(0));
            }
        }
    }

    private ArrayList<Piece> initAllPieces(){
        ArrayList<Piece> allPieces = new ArrayList<>(initTeamPieces(TeamColor.RED));
        allPieces.addAll(initTeamPieces(TeamColor.BLACK));
        for(int i=0;i<5;++i){
            Collections.shuffle(allPieces);
        }
        return allPieces;
    }

    private ArrayList<Piece> initTeamPieces(TeamColor color){
        return new ArrayList<>(Arrays.asList( new Piece(GENERAL, color),
                new Piece(CHARIOT, color), new Piece(CHARIOT, color),
                new Piece(HORSE, color), new Piece(HORSE, color),
                new Piece(CANNON, color), new Piece(CANNON, color),
                new Piece(ADVISOR, color), new Piece(ADVISOR, color),
                new Piece(MINSTER, color), new Piece(MINSTER, color),
                new Piece(SOLDIER, color), new Piece(SOLDIER, color),
                new Piece(SOLDIER, color), new Piece(SOLDIER, color),new Piece(SOLDIER, color)));
    }


    public Square[][] getBoard() {
        return board;
    }

    public Square getSquare(Coordinate coordinate){
        if(coordinate.isValid()){
            return board[coordinate.getRow()][coordinate.getColumn()];
        }else{
            return null; //TODO throw new exception?
        }
    }

    public Piece getPieceAt(Coordinate coordinate){
        return this.getSquare(coordinate).getStoredPiece();
    }

    public String toString(){
        StringBuilder boardString = new StringBuilder();
        for(Square[] row: board){
            for(Square square: row){
                boardString.append(" ").append(square.toString()).append(" " ); // TODO format to print actual sized board with squares
            }
            boardString.append('\n');
        }
        return boardString.toString();
    }




}