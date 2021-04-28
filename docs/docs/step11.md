# Desarrollo del requisito 11

## Requisito

!!! success "La piedra aplasta a las tijeras"

## Desarrollo

Nuevo requisito que podemos agrupar.

=== "TestRuleEngine"
    ``` Java linenums="1"
    @Test
    public void rock_crushes_scissors() {

      invokeAndCheckResult(PlayMove.ROCK, PlayMove.SCISSORS, PlayResult.WIN_PLAYER_ONE);
      invokeAndCheckResult(PlayMove.SCISSORS, PlayMove.ROCK, PlayResult.WIN_PLAYER_TWO);

    }  
    ```
=== "RuleEngine"
    ``` Java linenums="1"
    private boolean isPlayWinner(PlayMove moveOne, PlayMove moveTwo) {

      if (moveOne.equals(PlayMove.SCISSORS) && (moveTwo.equals(PlayMove.PAPER) || moveTwo.equals(PlayMove.LIZARD)))
        return true;

      if (moveOne.equals(PlayMove.PAPER) && (moveTwo.equals(PlayMove.ROCK) || moveTwo.equals(PlayMove.SPOCK)))
        return true;

      if (moveOne.equals(PlayMove.ROCK) && (moveTwo.equals(PlayMove.LIZARD) || moveTwo.equals(PlayMove.SCISSORS)))
        return true;

      if (moveOne.equals(PlayMove.LIZARD) && (moveTwo.equals(PlayMove.SPOCK) || moveTwo.equals(PlayMove.PAPER)))
        return true;

      if (moveOne.equals(PlayMove.SPOCK) && moveTwo.equals(PlayMove.SCISSORS))
        return true;

      return false;

    }
    ```

## Resultado final

Ya hemos terminado la implementación y tenemos esto:

=== "TestRuleEngine"
    ``` Java linenums="1" 
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
    ```
=== "RuleEngine"
    ``` Java linenums="1" 
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
    ```

## Futuros pasos

Aunque la clase y los métodos sean cortos, el método `isPlayWinner` sigue teniendo 5 condiciones `if` que además son complejas porque tienen tres comparaciones cada una.

Estamos ante el típico `smell` que nos aparecerá muchísimas veces a lo largo de nuestra vida **`Switch Statement`**.