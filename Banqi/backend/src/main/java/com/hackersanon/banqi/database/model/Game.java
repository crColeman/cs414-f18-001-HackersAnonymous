package com.hackersanon.banqi.database.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "game")
public class Game extends ModelBase
{

	@Transient
	private static final long serialVersionUID = -3621010469526215357L;

	@Embedded
	private Board board;

	@ElementCollection
	@AttributeOverride(name = "valid", column = @Column(name = "validCoord"))
	private Collection<Move> moveHistory = new ArrayList<>();

	@Column
	private Long playerOneId;

	@Column
	private Long playerTwoId;

	public Game(){
	}

	public Game(Board board, Long playerOneId, Long playerTwoId){
		this.board = board;
		this.playerOneId = playerOneId;
		this.playerTwoId = playerTwoId;
	}

	public void setBoard(Board board){
		this.board = board;
	}

	public Board getBoard(){
		return this.board;
	}

	public Collection<Move> getMoveHistory() {
		return moveHistory;
	}

	public void setMoveHistory(Collection<Move> moveHistory) {
		this.moveHistory = moveHistory;
	}

	public void setPlayerOneId(Long playerOneId){
		this.playerOneId = playerOneId;
	}
	public Long getPlayerOneId(){
		return this.playerOneId;
	}

	public void setPlayerTwoId(Long playerTwoId){
		this.playerTwoId = playerTwoId;
	}

	public Long getPlayerTwoId(){
		return this.playerTwoId;
	}

}