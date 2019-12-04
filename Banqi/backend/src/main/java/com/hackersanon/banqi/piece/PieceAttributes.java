package com.hackersanon.banqi.piece;

import com.hackersanon.banqi.board.Coordinate;

import javax.persistence.Embeddable;
import java.util.ArrayList;

@Embeddable
public enum PieceAttributes {
    GENERAL(){
    },
    CHARIOT(){
    },
    HORSE(){
    },
    CANNON(){
    },
    ADVISOR(){
    },
    MINSTER(){
    },
    SOLDIER(){
    },
    EMPTY(){
    };

    public String toString(){
        return this.name();
    }

    public boolean isValidMove(Coordinate origin, Coordinate destination){
        return legalMoves(origin).contains(destination);
    }

    public ArrayList<Coordinate> legalMoves(Coordinate origin){
        ArrayList<Coordinate> legalMoves = new ArrayList<>();
        addLegalMove(new Coordinate(origin.getRow()+1,origin.getColumn()), legalMoves);
        addLegalMove(new Coordinate(origin.getRow()-1,origin.getColumn()), legalMoves);
        addLegalMove(new Coordinate(origin.getRow(),origin.getColumn()+1), legalMoves);
        addLegalMove(new Coordinate(origin.getRow(),origin.getColumn()-1), legalMoves);
        return legalMoves;
    }

    private static void addLegalMove(Coordinate destination, ArrayList<Coordinate> legalMoves){
        if(destination.isValid()){
            legalMoves.add(destination);
        }
    }

}
