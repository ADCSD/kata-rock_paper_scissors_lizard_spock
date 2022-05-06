package com.ccsw.adcsd.codingdojo.rock_paper_scissors_lizard_spock;

import org.junit.Assert;
import org.junit.Test;

/**
 * jUnit Test Kata Rock Paper Scissors Lizard Spock
 * Rules:
 *   - Scissors cuts Paper
 *   - Paper covers Rock
 *   - Rock crushes Lizard
 *   - Lizard poisons Spock
 *   - Spock smashes Scissors
 *
 *   - Scissors decapitates Lizard
 *   - Lizard eats paper
 *   - Paper disproves Spock
 *   - Spock vaporizes Rock
 *   - Rock crushes Scissors
 */
@SuppressWarnings("javadoc")
public class TestRuleEngine {

  RuleEngine ruleEngine = new RuleEngine();

  @Test
  public void equals_move_result_draw() {

    invokeAndCheckResult(PlayMove.ROCK, PlayMove.ROCK, PlayResult.DRAW);
    invokeAndCheckResult(PlayMove.PAPER, PlayMove.PAPER, PlayResult.DRAW);
    invokeAndCheckResult(PlayMove.SCISSORS, PlayMove.SCISSORS, PlayResult.DRAW);
    invokeAndCheckResult(PlayMove.LIZARD, PlayMove.LIZARD, PlayResult.DRAW);
    invokeAndCheckResult(PlayMove.SPOCK, PlayMove.SPOCK, PlayResult.DRAW);

  }

  @Test
  public void scissors_cuts_paper() {

    invokeAndCheckResult(PlayMove.SCISSORS, PlayMove.PAPER, PlayResult.WIN_PLAYER_ONE);
    invokeAndCheckResult(PlayMove.PAPER, PlayMove.SCISSORS, PlayResult.WIN_PLAYER_TWO);

  }

  @Test
  public void paper_cover_rock() {

    invokeAndCheckResult(PlayMove.PAPER, PlayMove.ROCK, PlayResult.WIN_PLAYER_ONE);
    invokeAndCheckResult(PlayMove.ROCK, PlayMove.PAPER, PlayResult.WIN_PLAYER_TWO);

  }

  @Test
  public void rock_crushes_lizard() {

    invokeAndCheckResult(PlayMove.ROCK, PlayMove.LIZARD, PlayResult.WIN_PLAYER_ONE);
    invokeAndCheckResult(PlayMove.LIZARD, PlayMove.ROCK, PlayResult.WIN_PLAYER_TWO);

  }

  @Test
  public void lizard_poisons_spock() {

    invokeAndCheckResult(PlayMove.LIZARD, PlayMove.SPOCK, PlayResult.WIN_PLAYER_ONE);
    invokeAndCheckResult(PlayMove.SPOCK, PlayMove.LIZARD, PlayResult.WIN_PLAYER_TWO);

  }

  @Test
  public void spock_smashes_scissors() {

    invokeAndCheckResult(PlayMove.SPOCK, PlayMove.SCISSORS, PlayResult.WIN_PLAYER_ONE);
    invokeAndCheckResult(PlayMove.SCISSORS, PlayMove.SPOCK, PlayResult.WIN_PLAYER_TWO);

  }

  @Test
  public void scissors_decapitates_lizard() {

    invokeAndCheckResult(PlayMove.SCISSORS, PlayMove.LIZARD, PlayResult.WIN_PLAYER_ONE);
    invokeAndCheckResult(PlayMove.LIZARD, PlayMove.SCISSORS, PlayResult.WIN_PLAYER_TWO);

  }

  @Test
  public void lizard_eats_paper() {

    invokeAndCheckResult(PlayMove.LIZARD, PlayMove.PAPER, PlayResult.WIN_PLAYER_ONE);
    invokeAndCheckResult(PlayMove.PAPER, PlayMove.LIZARD, PlayResult.WIN_PLAYER_TWO);

  }

  @Test
  public void paper_disproves_spock() {

    invokeAndCheckResult(PlayMove.PAPER, PlayMove.SPOCK, PlayResult.WIN_PLAYER_ONE);
    invokeAndCheckResult(PlayMove.SPOCK, PlayMove.PAPER, PlayResult.WIN_PLAYER_TWO);

  }

  @Test
  public void spock_vaporizes_rock() {

    invokeAndCheckResult(PlayMove.SPOCK, PlayMove.ROCK, PlayResult.WIN_PLAYER_ONE);
    invokeAndCheckResult(PlayMove.ROCK, PlayMove.SPOCK, PlayResult.WIN_PLAYER_TWO);

  }

  @Test
  public void rock_crushes_scissors() {

    invokeAndCheckResult(PlayMove.ROCK, PlayMove.SCISSORS, PlayResult.WIN_PLAYER_ONE);
    invokeAndCheckResult(PlayMove.SCISSORS, PlayMove.ROCK, PlayResult.WIN_PLAYER_TWO);

  }

  private void invokeAndCheckResult(PlayMove playerOne, PlayMove playerTwo, PlayResult expectedResult) {

    Assert.assertEquals(expectedResult, this.ruleEngine.play(playerOne, playerTwo));
  }
}
