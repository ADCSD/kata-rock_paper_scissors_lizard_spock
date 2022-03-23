package com.capgemini.adcsd.codingdojo.rock_paper_scissors_lizard_spock;

import static com.capgemini.adcsd.codingdojo.rock_paper_scissors_lizard_spock.PlayMove.LIZARD;
import static com.capgemini.adcsd.codingdojo.rock_paper_scissors_lizard_spock.PlayMove.PAPER;
import static com.capgemini.adcsd.codingdojo.rock_paper_scissors_lizard_spock.PlayMove.ROCK;
import static com.capgemini.adcsd.codingdojo.rock_paper_scissors_lizard_spock.PlayMove.SCISSORS;
import static com.capgemini.adcsd.codingdojo.rock_paper_scissors_lizard_spock.PlayMove.SPOCK;
import static com.capgemini.adcsd.codingdojo.rock_paper_scissors_lizard_spock.PlayResult.DRAW;
import static com.capgemini.adcsd.codingdojo.rock_paper_scissors_lizard_spock.PlayResult.WIN_PLAYER_ONE;
import static com.capgemini.adcsd.codingdojo.rock_paper_scissors_lizard_spock.PlayResult.WIN_PLAYER_TWO;

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

  public RuleEngine ruleEngine = new RuleEngine();

  @Test
  public void SameMovesDraw() {

    Assert.assertTrue(this.ruleEngine.play(ROCK, ROCK) == DRAW);
    Assert.assertTrue(this.ruleEngine.play(PAPER, PAPER) == DRAW);
    Assert.assertTrue(this.ruleEngine.play(LIZARD, LIZARD) == DRAW);
    Assert.assertTrue(this.ruleEngine.play(SCISSORS, SCISSORS) == DRAW);
    Assert.assertTrue(this.ruleEngine.play(SPOCK, SPOCK) == DRAW);
  }

  @Test
  public void ScissorsWinPaper() {

    Assert.assertTrue(this.ruleEngine.play(SCISSORS, PAPER) == WIN_PLAYER_ONE);
    Assert.assertTrue(this.ruleEngine.play(PAPER, SCISSORS) == WIN_PLAYER_TWO);
  }

  @Test
  public void PaperWinRock() {

    Assert.assertTrue(this.ruleEngine.play(PAPER, ROCK) == WIN_PLAYER_ONE);
    Assert.assertTrue(this.ruleEngine.play(ROCK, PAPER) == WIN_PLAYER_TWO);
  }

  @Test
  public void RockWinLizard() {

    Assert.assertTrue(this.ruleEngine.play(ROCK, LIZARD) == WIN_PLAYER_ONE);
    Assert.assertTrue(this.ruleEngine.play(LIZARD, ROCK) == WIN_PLAYER_TWO);
  }

  @Test
  public void LizardWinSpock() {

    Assert.assertTrue(this.ruleEngine.play(LIZARD, SPOCK) == WIN_PLAYER_ONE);
    Assert.assertTrue(this.ruleEngine.play(SPOCK, LIZARD) == WIN_PLAYER_TWO);
  }

  @Test
  public void SpockWinScissor() {

    Assert.assertTrue(this.ruleEngine.play(SPOCK, SCISSORS) == WIN_PLAYER_ONE);
    Assert.assertTrue(this.ruleEngine.play(SCISSORS, SPOCK) == WIN_PLAYER_TWO);
  }

  @Test
  public void ScissorWinLizard() {

    Assert.assertTrue(this.ruleEngine.play(SCISSORS, LIZARD) == WIN_PLAYER_ONE);
    Assert.assertTrue(this.ruleEngine.play(LIZARD, SCISSORS) == WIN_PLAYER_TWO);
  }

}
