package com.ccsw.adcsd.codingdojo.rock_paper_scissors_lizard_spock;

import static com.ccsw.adcsd.codingdojo.rock_paper_scissors_lizard_spock.PlayMove.LIZARD;
import static com.ccsw.adcsd.codingdojo.rock_paper_scissors_lizard_spock.PlayMove.PAPER;
import static com.ccsw.adcsd.codingdojo.rock_paper_scissors_lizard_spock.PlayMove.ROCK;
import static com.ccsw.adcsd.codingdojo.rock_paper_scissors_lizard_spock.PlayMove.SCISSORS;
import static com.ccsw.adcsd.codingdojo.rock_paper_scissors_lizard_spock.PlayMove.SPOCK;
import static com.ccsw.adcsd.codingdojo.rock_paper_scissors_lizard_spock.PlayResult.DRAW;
import static com.ccsw.adcsd.codingdojo.rock_paper_scissors_lizard_spock.PlayResult.WIN_PLAYER_ONE;
import static com.ccsw.adcsd.codingdojo.rock_paper_scissors_lizard_spock.PlayResult.WIN_PLAYER_TWO;

/**
 * Kata Rock Paper Scissors Lizard Spock
 */
@SuppressWarnings("javadoc")
public class RuleEngine {

  public PlayResult play(PlayMove player1, PlayMove player2) {

    if (player1.equals(player2))
      return DRAW;

    if (player1.equals(SCISSORS) && (player2.equals(PAPER) || player2.equals(LIZARD))) {
      return WIN_PLAYER_ONE;
    }

    if (player1.equals(PAPER) && player2.equals(ROCK)) {
      return WIN_PLAYER_ONE;
    }

    if (player1.equals(ROCK) && player2.equals(LIZARD)) {
      return WIN_PLAYER_ONE;
    }

    if (player1.equals(LIZARD) && player2.equals(SPOCK)) {
      return WIN_PLAYER_ONE;
    }

    if (player1.equals(SPOCK) && player2.equals(SCISSORS)) {
      return WIN_PLAYER_ONE;
    }

    return WIN_PLAYER_TWO;

  }

}
