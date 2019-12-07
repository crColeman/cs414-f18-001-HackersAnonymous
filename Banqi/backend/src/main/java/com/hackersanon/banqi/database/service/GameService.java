package com.hackersanon.banqi.database.service;

import com.hackersanon.banqi.board.InvalidCoordinateException;
import com.hackersanon.banqi.board.InvalidMoveException;
import com.hackersanon.banqi.database.dao.GameDAO;
import com.hackersanon.banqi.database.model.*;
import com.hackersanon.banqi.game.MoveFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class GameService implements IGameService
{

	private GameDAO gameDAO;
	
	
	@Override
	public Game findById(Long id)
	{
		return gameDAO.findById(id).orElse(null);
	}

	@Override
	public Game createGame() {
		Game game = new Game();
		game.setBoard(new Board());
		return gameDAO.save(game);
	}

	@Override
	public Game createGame(Long p1id, Long p2id)
	{
		Game game = new Game();
		game.setPlayerOneId(p1id);
		game.setPlayerTwoId(p2id);
		game.setBoard(new Board());
		return gameDAO.save(game);
	}

	@Override
	public Move executeMoveOnGame(Long gameId, Move move) throws InvalidMoveException {
		assert gameDAO.findById(gameId).isPresent();
		Game game = gameDAO.findById(gameId).get();
		Board board = game.getBoard();
		Move attemptedMove =  MoveFunctions.makeMove(board,move);
		if(attemptedMove.isExecuted()) {
			game.getMoveHistory().add(attemptedMove);
		}
		return attemptedMove;

	}

	@Override
	public ArrayList<Coordinate> validMoves(Long gameId, Coordinate coordinate) throws InvalidCoordinateException {
		assert gameDAO.findById(gameId).isPresent();
		Game game = gameDAO.findById(gameId).get();
		Board board = game.getBoard();
        Piece piece = board.getSquare(coordinate).getPiece();
		ArrayList<Coordinate> possibleTrips = piece.getValidTripCoordinates(coordinate);
		ArrayList<Coordinate> validTrips = new ArrayList<>();
		possibleTrips.forEach(coordinate1 -> {
			try {
				if (piece.canCapture(board.getSquare(coordinate1).getPiece())){
					validTrips.add(coordinate1);
				}
			} catch (InvalidCoordinateException e) {
				e.printStackTrace();
			}
		});
		return validTrips;
	}

	@Override
	public List<Game> findGamesByPlayerId(Long id){
		return gameDAO.findGamesByPlayerTwoIdOrPlayerOneId(id,id);
	}

	@Override
	public List<Game> findAllExistingGames(){
		return gameDAO.findAll();
	}

	@Override
	public void deleteGameById(Long gameId){
		gameDAO.deleteById(gameId);
	}

    @Override
    public void deleteGameByUserId(Long userId) {
		List<Game> games = gameDAO.findGamesByPlayerTwoIdOrPlayerOneId(userId,userId);
		games.forEach(game -> gameDAO.deleteById(game.getId()));
    }

    @Autowired
	public void setGameDAO(final GameDAO gameDAO){
		this.gameDAO = gameDAO;
	}
	
}