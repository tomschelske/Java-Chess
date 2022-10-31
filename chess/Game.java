import java.util.Scanner;

public class Game{

  public static void main(String args[]){
    Scanner s = new Scanner(System.in);
    System.out.println("Would you like to play chess?");
    String answer = s.nextLine();
    if ((answer.equals("yes")) || (answer.equals("Yes"))){

      Board userBoard = new Board();

      Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", userBoard);


      //System.out.println(userBoard.isGameOver());

      while (!(userBoard.isGameOver())){
        try{
          System.out.println(userBoard);
          System.out.println("Make a move of form: startrow startcol endrow endcol (any other input to quit)");
          s = new Scanner(System.in);
          int[] moveCoords = new int[4];
          for(int i = 0; i < 4; i++){
            moveCoords[i] = s.nextInt();
          }
          int sRow = moveCoords[0];
          int sCol = moveCoords[1];
          int eRow = moveCoords[2];
          int eCol = moveCoords[3];

          if(userBoard.movePiece(sRow,sCol,eRow,eCol)){
            Piece currentPiece = userBoard.getPiece(eRow,eCol);
            currentPiece.checkPawnPromotion();
            System.out.println("Successful move, other player's turn");
          } else {
            System.out.println("Invalid move, please try again");
          }

        }
        catch(Exception e){
          System.out.println("Alternate input detected, quitting game.");
          break;
        }

      }
      System.out.println(userBoard);
      System.out.println("Gameover!");



    } else {
      System.out.println("Okay then.");
    }

    }



  }
