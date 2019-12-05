package com.hackersanon.banqi.game;


import com.hackersanon.banqi.database.model.Square;

public enum MoveType {
    TRAVEL(){
        @Override
        public boolean calculateMoveType(Square origin, Square destination) {
            return (origin.checkOccupied() && origin.getPiece().getFaceUp()) &&
                    (!destination.checkOccupied());
        }
    },CAPTURE(){
        @Override
        public boolean calculateMoveType(Square origin, Square destination) {
            return ((origin.checkOccupied()&& origin.getPiece().getFaceUp()) &&
                    (destination.checkOccupied() && destination.getPiece().getFaceUp()) &&
                    origin.getPiece().canCapture(destination.getPiece()));
        }
    },FLIP(){
        @Override
        public boolean calculateMoveType(Square origin, Square destination) {
            return ( origin.checkOccupied() && !origin.getPiece().getFaceUp() ) &&
                    ( destination.getCoordinate().equals(origin.getCoordinate()) &&
                            !destination.getPiece().getFaceUp() );
        }
    }, INVALID() {
        @Override
        public boolean calculateMoveType(Square origin, Square destination) {
            return !TRAVEL.calculateMoveType(origin,destination) && !CAPTURE.calculateMoveType(origin,destination)
                    && !FLIP.calculateMoveType(origin,destination);
        }
    };
    MoveType(){}

    public static MoveType translateToAction (Square origin, Square destination){
        for (MoveType action : MoveType.values()) {
            if (action.calculateMoveType(origin, destination)) {
                return action;
            }
        }
        return INVALID;
    }

    abstract boolean calculateMoveType(Square origin, Square destination);
}
