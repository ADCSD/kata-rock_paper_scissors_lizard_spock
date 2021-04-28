package com.capgemini.adcsd.codingdojo.rock_paper_scissors_lizard_spock;

/**
 * Kata Rock Paper Scissors Lizard Spock
 */
@SuppressWarnings("javadoc")
public class RuleEngine {

  public PlayResult play(PlayMove playerOne, PlayMove playerTwo) {

    if (isPlayerOneWinner(playerOne, playerTwo))
      return PlayResult.WIN_PLAYER_ONE;

    if (isPlayerTwoWinner(playerOne, playerTwo))
      return PlayResult.WIN_PLAYER_TWO;

    return PlayResult.DRAW;

  }

  private boolean isPlayerOneWinner(PlayMove playerOne, PlayMove playerTwo) {

    return isPlayWinner(playerOne, playerTwo);

  }

  private boolean isPlayerTwoWinner(PlayMove playerOne, PlayMove playerTwo) {

    return isPlayWinner(playerTwo, playerOne);

  }

  private boolean isPlayWinner(PlayMove moveOne, PlayMove moveTwo) {

    if (moveOne.equals(PlayMove.SCISSORS) && (moveTwo.equals(PlayMove.PAPER) || moveTwo.equals(PlayMove.LIZARD)))
      return true;

    if (moveOne.equals(PlayMove.PAPER) && (moveTwo.equals(PlayMove.ROCK) || moveTwo.equals(PlayMove.SPOCK)))
      return true;

    if (moveOne.equals(PlayMove.ROCK) && (moveTwo.equals(PlayMove.LIZARD) || moveTwo.equals(PlayMove.SCISSORS)))
      return true;

    if (moveOne.equals(PlayMove.LIZARD) && (moveTwo.equals(PlayMove.SPOCK) || moveTwo.equals(PlayMove.PAPER)))
      return true;

    if (moveOne.equals(PlayMove.SPOCK) && (moveTwo.equals(PlayMove.SCISSORS) || moveTwo.equals(PlayMove.ROCK)))
      return true;

    return false;

  }

}
