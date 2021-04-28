# Desarrollo del requisito 6

## Requisito

!!! success "Spock rompe las tijeras"

## Desarrollo

De nuevo, un requisito muy sencillo.

=== "TestRuleEngine"
    ``` Java linenums="1"
    @Test
    public void spock_smashes_scissors() {

      invokeAndCheckResult(PlayMove.SPOCK, PlayMove.SCISSORS, PlayResult.WIN_PLAYER_ONE);
      invokeAndCheckResult(PlayMove.SCISSORS, PlayMove.SPOCK, PlayResult.WIN_PLAYER_TWO);

    }
    ```
=== "RuleEngine"
    ``` Java linenums="1"
    public PlayResult play(PlayMove playerOne, PlayMove playerTwo) {

      if (playerOne.equals(PlayMove.SCISSORS) && playerTwo.equals(PlayMove.PAPER))
        return PlayResult.WIN_PLAYER_ONE;

      if (playerOne.equals(PlayMove.PAPER) && playerTwo.equals(PlayMove.SCISSORS))
        return PlayResult.WIN_PLAYER_TWO;

      if (playerOne.equals(PlayMove.PAPER) && playerTwo.equals(PlayMove.ROCK))
        return PlayResult.WIN_PLAYER_ONE;

      if (playerOne.equals(PlayMove.ROCK) && playerTwo.equals(PlayMove.PAPER))
        return PlayResult.WIN_PLAYER_TWO;

      if (playerOne.equals(PlayMove.ROCK) && playerTwo.equals(PlayMove.LIZARD))
        return PlayResult.WIN_PLAYER_ONE;

      if (playerOne.equals(PlayMove.LIZARD) && playerTwo.equals(PlayMove.ROCK))
        return PlayResult.WIN_PLAYER_TWO;

      if (playerOne.equals(PlayMove.LIZARD) && playerTwo.equals(PlayMove.SPOCK))
        return PlayResult.WIN_PLAYER_ONE;

      if (playerOne.equals(PlayMove.SPOCK) && playerTwo.equals(PlayMove.LIZARD))
        return PlayResult.WIN_PLAYER_TWO;

      if (playerOne.equals(PlayMove.SPOCK) && playerTwo.equals(PlayMove.SCISSORS))
        return PlayResult.WIN_PLAYER_ONE;

      if (playerOne.equals(PlayMove.SCISSORS) && playerTwo.equals(PlayMove.SPOCK))
        return PlayResult.WIN_PLAYER_TWO;

      return PlayResult.DRAW;

    }
    ```

Bueno, ya está bien, vamos a ver si podemos hacer algo con el método. Es demasiado grande y con demasiados `if`. Vamos a intentar hacer algo aunque no sepamos todavía como eliminar esos `if`, por lo menos darle algo de legibilidad.



=== "RuleEngine"
    ``` Java hl_lines="4 10 16 22 28" linenums="1"
    public PlayResult play(PlayMove playerOne, PlayMove playerTwo) {

      if (playerOne.equals(PlayMove.SCISSORS) && playerTwo.equals(PlayMove.PAPER))
        return PlayResult.WIN_PLAYER_ONE;  /*(1)*/

      if (playerOne.equals(PlayMove.PAPER) && playerTwo.equals(PlayMove.SCISSORS))
        return PlayResult.WIN_PLAYER_TWO;

      if (playerOne.equals(PlayMove.PAPER) && playerTwo.equals(PlayMove.ROCK))
        return PlayResult.WIN_PLAYER_ONE;  /*(1)*/

      if (playerOne.equals(PlayMove.ROCK) && playerTwo.equals(PlayMove.PAPER))
        return PlayResult.WIN_PLAYER_TWO;

      if (playerOne.equals(PlayMove.ROCK) && playerTwo.equals(PlayMove.LIZARD))
        return PlayResult.WIN_PLAYER_ONE;  /*(1)*/

      if (playerOne.equals(PlayMove.LIZARD) && playerTwo.equals(PlayMove.ROCK))
        return PlayResult.WIN_PLAYER_TWO;

      if (playerOne.equals(PlayMove.LIZARD) && playerTwo.equals(PlayMove.SPOCK))
        return PlayResult.WIN_PLAYER_ONE;  /*(1)*/

      if (playerOne.equals(PlayMove.SPOCK) && playerTwo.equals(PlayMove.LIZARD))
        return PlayResult.WIN_PLAYER_TWO;

      if (playerOne.equals(PlayMove.SPOCK) && playerTwo.equals(PlayMove.SCISSORS))
        return PlayResult.WIN_PLAYER_ONE;  /*(1)*/

      if (playerOne.equals(PlayMove.SCISSORS) && playerTwo.equals(PlayMove.SPOCK))
        return PlayResult.WIN_PLAYER_TWO;

      return PlayResult.DRAW;

    }
    ```
=== "RuleEngine-Ref1"
    ``` Java hl_lines="3-16 18-31" linenums="1"
    public PlayResult play(PlayMove playerOne, PlayMove playerTwo) {

      if (playerOne.equals(PlayMove.SCISSORS) && playerTwo.equals(PlayMove.PAPER)) /*(2)*/
        return PlayResult.WIN_PLAYER_ONE;                                          /*(2)*/

      if (playerOne.equals(PlayMove.PAPER) && playerTwo.equals(PlayMove.ROCK))     /*(2)*/
        return PlayResult.WIN_PLAYER_ONE;                                          /*(2)*/

      if (playerOne.equals(PlayMove.ROCK) && playerTwo.equals(PlayMove.LIZARD))    /*(2)*/
        return PlayResult.WIN_PLAYER_ONE;                                          /*(2)*/

      if (playerOne.equals(PlayMove.LIZARD) && playerTwo.equals(PlayMove.SPOCK))   /*(2)*/
        return PlayResult.WIN_PLAYER_ONE;                                          /*(2)*/

      if (playerOne.equals(PlayMove.SPOCK) && playerTwo.equals(PlayMove.SCISSORS)) /*(2)*/
        return PlayResult.WIN_PLAYER_ONE;                                          /*(2)*/

      if (playerOne.equals(PlayMove.PAPER) && playerTwo.equals(PlayMove.SCISSORS)) /*(3)*/
        return PlayResult.WIN_PLAYER_TWO;                                          /*(3)*/

      if (playerOne.equals(PlayMove.ROCK) && playerTwo.equals(PlayMove.PAPER))     /*(3)*/
        return PlayResult.WIN_PLAYER_TWO;                                          /*(3)*/

      if (playerOne.equals(PlayMove.LIZARD) && playerTwo.equals(PlayMove.ROCK))    /*(3)*/
        return PlayResult.WIN_PLAYER_TWO;                                          /*(3)*/

      if (playerOne.equals(PlayMove.SPOCK) && playerTwo.equals(PlayMove.LIZARD))   /*(3)*/
        return PlayResult.WIN_PLAYER_TWO;                                          /*(3)*/

      if (playerOne.equals(PlayMove.SCISSORS) && playerTwo.equals(PlayMove.SPOCK)) /*(3)*/
        return PlayResult.WIN_PLAYER_TWO;                                          /*(3)*/

      return PlayResult.DRAW;

    }
    ```
=== "RuleEngine-Ref2"
    ``` Java hl_lines="3 6 13-32 34-53" linenums="1"
    public PlayResult play(PlayMove playerOne, PlayMove playerTwo) {

      if (isPlayerOneWinner(playerOne, playerTwo))                                  /*(4)*/
        return PlayResult.WIN_PLAYER_ONE;

      if (isPlayerTwoWinner(playerOne, playerTwo))                                  /*(4)*/
        return PlayResult.WIN_PLAYER_TWO;

      return PlayResult.DRAW;

    }

    private boolean isPlayerOneWinner(PlayMove playerOne, PlayMove playerTwo) {      /*(4)*/

      if (playerOne.equals(PlayMove.SCISSORS) && playerTwo.equals(PlayMove.PAPER))   /*(4)*/
        return true;

      if (playerOne.equals(PlayMove.PAPER) && playerTwo.equals(PlayMove.ROCK))       /*(4)*/
        return true;

      if (playerOne.equals(PlayMove.ROCK) && playerTwo.equals(PlayMove.LIZARD))      /*(4)*/
        return true;

      if (playerOne.equals(PlayMove.LIZARD) && playerTwo.equals(PlayMove.SPOCK))     /*(4)*/
        return true;

      if (playerOne.equals(PlayMove.SPOCK) && playerTwo.equals(PlayMove.SCISSORS))   /*(4)*/
        return true;

      return false;

    }

    private boolean isPlayerTwoWinner(PlayMove playerOne, PlayMove playerTwo) {

      if (playerOne.equals(PlayMove.PAPER) && playerTwo.equals(PlayMove.SCISSORS))   /*(4)*/
        return true;

      if (playerOne.equals(PlayMove.ROCK) && playerTwo.equals(PlayMove.PAPER))       /*(4)*/
        return true;

      if (playerOne.equals(PlayMove.LIZARD) && playerTwo.equals(PlayMove.ROCK))      /*(4)*/
        return true;

      if (playerOne.equals(PlayMove.SPOCK) && playerTwo.equals(PlayMove.LIZARD))     /*(4)*/
        return true;

      if (playerOne.equals(PlayMove.SCISSORS) && playerTwo.equals(PlayMove.SPOCK))   /*(4)*/
        return true;

      return false;

    }
    ```
=== "RuleEngine-Ref3"
    ``` Java linenums="1"
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

      if (moveOne.equals(PlayMove.SCISSORS) && moveTwo.equals(PlayMove.PAPER))
        return true;

      if (moveOne.equals(PlayMove.PAPER) && moveTwo.equals(PlayMove.ROCK))
        return true;

      if (moveOne.equals(PlayMove.ROCK) && moveTwo.equals(PlayMove.LIZARD))
        return true;

      if (moveOne.equals(PlayMove.LIZARD) && moveTwo.equals(PlayMove.SPOCK))
        return true;

      if (moveOne.equals(PlayMove.SPOCK) && moveTwo.equals(PlayMove.SCISSORS))
        return true;

      return false;

    }
    ```

??? tip inline end "Refactoring"
    ***(1)*** Vamos a agrupar todas las clausulas donde el jugar uno gana, y todas donde el jugador dos gana.

    ***(2)*** Prácticamente se hace lo mismo en todo el bloque de líneas, salvo que se le pasan dos entradas diferentes y se obtiene una salida diferente. Quizá podríamos encapsularlo todo eso en un método que fuera más legible.

    ***(3)*** Lo mismo que para el punto anterior, podemos extraer un método. 

    ***(4)*** Fijándonos mucho, podemos intuir que los dos métodos hacen lo mismo, es código duplicado, solo que el primer método lo hace en un sentido, y el segundo método lo hace en el sentido inverso. Podemos crearnos un método  


Ahora ya tenemos un código un poco más arreglado, aunque seguimos teniendo algunos `if` que vamos a dejar de momento, vamos a ver como progresa la implementación.



## Resultado final

Después de todos estos tests y `refactor` deberíamos tener el siguiente código:


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

        if (moveOne.equals(PlayMove.SCISSORS) && moveTwo.equals(PlayMove.PAPER))
          return true;

        if (moveOne.equals(PlayMove.PAPER) && moveTwo.equals(PlayMove.ROCK))
          return true;

        if (moveOne.equals(PlayMove.ROCK) && moveTwo.equals(PlayMove.LIZARD))
          return true;

        if (moveOne.equals(PlayMove.LIZARD) && moveTwo.equals(PlayMove.SPOCK))
          return true;

        if (moveOne.equals(PlayMove.SPOCK) && moveTwo.equals(PlayMove.SCISSORS))
          return true;

        return false;

      }

    }
    ```




