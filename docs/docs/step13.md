# Refactor del `Switch Statement` en nuestra kata

Ahora que ya hemos visto que acciones se pueden realizar para mitigar el `code smell` vamos a aplicarlas sobre la implementación que tenemos de la kata.

## Código de origen

Recordemos que nuestro código debería estar así:


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

## Tip 1. Decompose Conditional

<div style="text-align: right">
    <div class="md-button button-tag-mini light-green">Extract Method</div>
    <div class="md-button button-tag-mini light-green">Extract Variable</div>
    <div class="md-button button-tag-mini light-green">Magic Number</div>
</div>

Esta acción trata de darle un valor más semántico a las condiciones de nuestro `if-else`. Así el cebrero no tiene que almacenar las condiciones como tal sino un texto más semántico.

=== "Original"
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

      if (moveOne.equals(PlayMove.SPOCK) && (moveTwo.equals(PlayMove.SCISSORS) || moveTwo.equals(PlayMove.ROCK)))
        return true;

      return false;

    }
    ```
=== "Refactorizado"
    ``` Java linenums="1"
    private boolean isPlayWinner(PlayMove moveOne, PlayMove moveTwo) {

      if (moveOne.equals(PlayMove.SCISSORS) && isScissorsWin(moveTwo))
        return true;

      if (moveOne.equals(PlayMove.PAPER) && isPaperWin(moveTwo))
        return true;

      if (moveOne.equals(PlayMove.ROCK) && isRockWin(moveTwo))
        return true;

      if (moveOne.equals(PlayMove.LIZARD) && isLizardWin(moveTwo))
        return true;

      if (moveOne.equals(PlayMove.SPOCK) && isSpockWin(moveTwo))
        return true;

      return false;

    }
    ```

## Tip 2. Use Guard Clauses

<div style="text-align: right">
    <div class="md-button button-tag-mini light-green">Add return</div>
    <div class="md-button button-tag-mini light-green">Add throws</div>
</div>

En realidad esto ya lo habíamos hecho en la refactorización del `Requisito 2. Tijeras cortan Papel`. En lugar de tener clausulas `if-else` lo hemos simplificado con puntos de return.

=== "Original"
    ``` Java linenums="1"
    public PlayResult play(PlayMove playerOne, PlayMove playerTwo) {

      PlayResult result = null;

      if (playerOne.equals(PlayMove.SCISSORS) && playerTwo.equals(PlayMove.PAPER)) {
        result = PlayResult.WIN_PLAYER_ONE;
      } else if (playerOne.equals(PlayMove.PAPER) && playerTwo.equals(PlayMove.SCISSORS)) { 
        result = PlayResult.WIN_PLAYER_TWO;
      } else {                                                                              
        result = PlayResult.DRAW;
      }

      return result;

    }
    ```
=== "Refactorizado"
    ``` Java linenums="1"
    public PlayResult play(PlayMove playerOne, PlayMove playerTwo) {

      if (playerOne.equals(PlayMove.SCISSORS) && playerTwo.equals(PlayMove.PAPER))
        return PlayResult.WIN_PLAYER_ONE;

      if (playerOne.equals(PlayMove.PAPER) && playerTwo.equals(PlayMove.SCISSORS))
        return PlayResult.WIN_PLAYER_TWO;

      return PlayResult.DRAW;

    }
    ```


## Tip 3. Substitute Algorithm

<div style="text-align: right">
    <div class="md-button button-tag-mini light-green">Maps</div>
    <div class="md-button button-tag-mini light-green">Lists</div>
    <div class="md-button button-tag-mini light-green">Functions</div>
</div>

En este caso, al tratarse de una condiciones simples podemos extraer esa correlación a una estructura de datos.
Tenemos que cada movimiento gana a un conjunto de movimientos, es decir, cada `PlayMove` tiene asociado un `List<PlayMove>` sobre los que gana.

Podríamos tener un `Map` donde para cada `PlayMove` almacenemos su conjunto de sus `PlayMove` sobre los que gana.


=== "Original"
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

      if (moveOne.equals(PlayMove.SPOCK) && (moveTwo.equals(PlayMove.SCISSORS) || moveTwo.equals(PlayMove.ROCK)))
        return true;

      return false;

    }
    ```
=== "Refactorizado"
    ``` Java linenums="1"
    Map<PlayMove, List<PlayMove>> WIN_MOVES = new HashMap<PlayMove, List<PlayMove>>() {
      {
        put(PlayMove.SCISSORS, Arrays.asList(new PlayMove[] { PlayMove.PAPER,    PlayMove.LIZARD }));
        put(PlayMove.PAPER,    Arrays.asList(new PlayMove[] { PlayMove.ROCK,     PlayMove.SPOCK }));
        put(PlayMove.ROCK,     Arrays.asList(new PlayMove[] { PlayMove.LIZARD,   PlayMove.SCISSORS }));
        put(PlayMove.LIZARD,   Arrays.asList(new PlayMove[] { PlayMove.SPOCK,    PlayMove.PAPER }));
        put(PlayMove.SPOCK,    Arrays.asList(new PlayMove[] { PlayMove.SCISSORS, PlayMove.ROCK }));
      }
    };

    private boolean isPlayWinner(PlayMove moveOne, PlayMove moveTwo) {

      return this.WIN_MOVES.get(moveOne).contains(moveTwo);

    }
    ```


## Tip 4. Use Polymorphism

<div style="text-align: right">
    <div class="md-button button-tag-mini light-green">State Pattern</div>
    <div class="md-button button-tag-mini light-green">Strategy Pattern</div>
</div>

También se puede utilizar patrones de diseño como el `State Pattern` y `Strategy Pattern` y hacer uso de la herencia.

Para ello, crearíamos una clase `Base` abstracta que tuviera el método que comprueba si un movimiento gana a otro. Luego crearíamos tantas especificaciones de esa clase como diferentes movimientos existen en el juego.
Si aparecen nuevas reglas o movimientos solo hay que crear o modificar las especificaciones.

### Con subclases

=== "Original"
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

      if (moveOne.equals(PlayMove.SPOCK) && (moveTwo.equals(PlayMove.SCISSORS) || moveTwo.equals(PlayMove.ROCK)))
        return true;

      return false;

    }
    ```
=== "Refactorizado"
    ``` Java linenums="1"
    public abstract class PlayMove {
      public static PlayMove ROCK = new MoveRock();

      public static PlayMove PAPER = new MovePaper();

      public static PlayMove SCISSORS = new MoveScissors();

      public static PlayMove LIZARD = new MoveLizard();

      public static PlayMove SPOCK = new MoveSpock();

      abstract boolean isPlayWinner(PlayMove move);

    }  

    public class MoveRock extends PlayMove {

      @Override
      boolean isPlayWinner(PlayMove move) {

        return move.equals(PlayMove.LIZARD) || move.equals(PlayMove.SCISSORS);
      }

    }

    public class MovePaper extends PlayMove {

      @Override
      boolean isPlayWinner(PlayMove move) {

        return move.equals(PlayMove.ROCK) || move.equals(PlayMove.SPOCK);
      }

    }

    public class MoveScissors extends PlayMove {

      @Override
      boolean isPlayWinner(PlayMove move) {

        return move.equals(PlayMove.PAPER) || move.equals(PlayMove.LIZARD);
      }

    }

    public class MoveLizard extends PlayMove {

      @Override
      boolean isPlayWinner(PlayMove move) {

        return move.equals(PlayMove.SPOCK) || move.equals(PlayMove.PAPER);
      }

    }

    public class MoveSpock extends PlayMove {

      @Override
      boolean isPlayWinner(PlayMove move) {

        return move.equals(PlayMove.SCISSORS) || move.equals(PlayMove.ROCK);
      }

    }


    public class RuleEngine {
      ...

      private boolean isPlayWinner(PlayMove moveOne, PlayMove moveTwo) {

        return moveOne.isPlayWinner(moveTwo);

      }

      ...
    }

    ```

### Con Enums

Incluso esto también podría implementarse con los `enum` ya que permiten implementar código dentro del `enum`.


``` Java
public enum PlayMove {

  ROCK {
    @Override
    boolean isPlayWinner(PlayMove move) {

      return move.equals(PlayMove.LIZARD) || move.equals(PlayMove.SCISSORS);
    }

  },

  PAPER {
    @Override
    boolean isPlayWinner(PlayMove move) {

      return move.equals(PlayMove.ROCK) || move.equals(PlayMove.SPOCK);
    }

  },

  SCISSORS {
    @Override
    boolean isPlayWinner(PlayMove move) {

      return move.equals(PlayMove.PAPER) || move.equals(PlayMove.LIZARD);
    }

  },

  LIZARD {
    @Override
    boolean isPlayWinner(PlayMove move) {

      return move.equals(PlayMove.SPOCK) || move.equals(PlayMove.PAPER);
    }

  },

  SPOCK {
    @Override
    boolean isPlayWinner(PlayMove move) {

      return move.equals(PlayMove.SCISSORS) || move.equals(PlayMove.ROCK);
    }
  };

  abstract boolean isPlayWinner(PlayMove move);
}


public class RuleEngine {
  ...

  private boolean isPlayWinner(PlayMove moveOne, PlayMove moveTwo) {

    return moveOne.isPlayWinner(moveTwo);

  }

  ...
}

```

