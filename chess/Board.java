public class Board {

    // Instance variables
    private Piece[][] board;

    
    // Construct an object of type Board using given arguments.
    public Board() {
      board = new Piece[8][8];
      this.board = board;

    }
    //added another constructor for convenience when I need to pass in a Board object with the current board config.
    public Board(Piece[][] gameboard) {
      this.board = gameboard;
    }

    // Accessor Methods

    
    // Return the Piece object stored at a given row and column
    public Piece getPiece(int row, int col) {
      return this.board[row][col];
    }

    
    // Update a single cell of the board to the new piece.
    public void setPiece(int row, int col, Piece piece) {
      this.board[row][col] = piece;
    }

    // Game functionality methods

    
    // Moves a Piece object from one cell in the board to another, provided that
    // this movement is legal. Returns a boolean to signify success or failure.
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
      Piece temp = this.getPiece(startRow,startCol); //finding piece user wants to move
      if (temp.isMoveLegal(new Board(this.board), endRow, endCol)){
        this.setPiece(endRow, endCol, temp);
        temp.checkPawnPromotion();
        temp.setPosition(endRow, endCol);
        this.setPiece(startRow, startCol, null);
        return true;
      }
      return false;
    }

   
    // Determines whether the game has been ended, i.e., if one player's King
    // has been captured.
    public boolean isGameOver() {
      int kingCounter = 0; // If the board has only one king on the board, the game is over
      for (int i = 0; i < 8; i++){
        for (int j = 0; j < 8; j++){
          Piece temp = this.getPiece(i,j);
          if (temp != null){
            if (temp.getCharacter() == '\u265a' || temp.getCharacter() == '\u2654'){
              kingCounter++;
            }
          }
        }
      }
      if (kingCounter == 2){
        return false;
      } else {
        return true;
      }

    }

   
    // Construct a String that represents the Board object's 2D array. Return
    // the fully constructed String.
    public String toString() {
      String outputString = "   0    1   2   3   4   5   6   7 " + "\n";
      for (int i = 0; i < 8; i++){
        outputString = outputString + i + "| ";
        for (int j = 0; j < 8; j++){
          Piece loopPiece = getPiece(i,j);
          if (loopPiece == null){
            outputString = outputString + "   |";
          } else {
            outputString = outputString + loopPiece.getCharacter() + "  |";
          }
          if (j == 7){
            outputString = outputString + "\n";
          }
        }
      }
      return outputString;
    }


    
    // Sets every cell of the array to null. For debugging and grading purposes.
    public void clear() {
      for (int i = 0; i < 8; i++){
        for (int j = 0; j < 8; j++){
          this.setPiece(i,j,null);
        }
      }
    }

    // Movement helper functions

    
    // Ensure that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - Both contain a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - Destination contains either no Piece or a Piece of the opposite color.
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        // this block determines if value is a valid place on a chess board
        if (startRow < 0 || startCol < 0){
          return false;
        } else if (startRow > 7 || startCol > 7){
            return false;
        }
        if (endRow < 0 || endCol < 0){
          return false;
        } else if (endRow > 7 || endCol > 7){
            return false;
        }
        // colors and null cases
        Piece startPiece = getPiece(startRow, startCol);
        Piece endPiece = getPiece(endRow, endCol);
        if (startPiece == null){
          return false;
        }
        if (startPiece.getIsBlack() != isBlack){
          return false;
        }
        if (endPiece == null){
          return true;
        }
        if (startPiece.getIsBlack() == endPiece.getIsBlack()){
          return false;
        }
        return true;
    }

    
    // Check whether the 'start' position and 'end' position are adjacent to each other
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
      boolean current;
      int rise;
      int run;
      rise = endRow - startRow;
      run = endCol - startCol;
      // cases
      if (((rise == 1) || (rise == -1)) && (run == 0 )){
        return true;
      } else if (((run == 1) || (run == -1)) && (rise == 0 )){
        return true;
      } else if (((rise == 1) || (rise == -1)) && (run == 1)){
        return true;
      } else if (((rise == 1) || (rise == -1)) && (run == -1)){
        return true;
      }
      return false;
    }

    
    // Checks whether a given 'start' and 'end' position are a valid horizontal move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one row.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
      if (!(startRow == endRow) && (startCol != endCol)){
        return false;
      }
      if (endCol > startCol){
        for (int i = startCol + 1; i < endCol; i++){
          if (getPiece(startRow, i) != null){
            return false;
          }
        }
      } else if (endCol < startCol){
        for (int i = startCol - 1; i > endCol; i--){
          if (getPiece(startRow, i) != null){
            return false;
          }
        }
      }
      return true;
    }


    
    // Checks whether a given 'start' and 'end' position are a valid vertical move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one column.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
      if (!(startCol == endCol) && (startRow != endRow)){
        return false;
      }
      if (endRow > startRow){
        for (int i = startRow + 1; i < endRow; i++){
          if (getPiece(i, startCol) != null){
            return false;
          }
        }
      } else if (endRow < startRow){
        for (int i = startRow - 1; i > endRow; i--){
          if (getPiece(i, startCol) != null){
            return false;
          }
        }
      }
      return true;
    }

    
    // Checks whether a given 'start' and 'end' position are a valid diagonal move.
    // Returns a boolean to signify whether:
    // - The path from 'start' to 'end' is diagonal... change in row and col.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
      // I am approaching this by using four cases for diagonals
      // it could be up and to the right, up and to the left, back and to the right, or back and to the left
      // up and right and back and left are the same but reversed, and in both cases, the sum of row + col must be equal
      // back and right is the same as up and left and in this case, col - row must be equal for both the start and end location
      // also want to make sure there are no pieces in the diagonal path
        if (endRow < startRow && endCol > startCol){
          //up and to the right
          if((endCol + endRow) != (startCol + startRow)){
            return false;
          } else {
            for (int i = startRow - 1; i > endRow; i--){
              for (int j = startCol + 1; j < endCol; j++ ){
                if ((j + i) == (endCol + endRow)){
                  if (getPiece(i,j) != null){
                    return false;
                  }
                }
              }
            }
          }
        }
        else if((endRow < startRow) && (endCol < startCol)){
          // up and left
          if((endCol - endRow) != (startCol - startRow)){
            return false;
          } else {
            for (int i = startRow - 1; i > endRow; i--){
              for (int j = startCol - 1; j > endCol; j--){
                if ((j - i) == (endCol - endRow)){
                  if (getPiece(i,j) != null){
                    return false;
                  }
                }
              }
            }
          }
        }
        else if ((endRow > startRow) && (endCol < startCol)){
          // back and left
          if((endCol + endRow) != (startCol + startRow)){
            return false;
          } else {
            for (int i = startRow + 1; i < endRow; i++){
              for (int j = startCol - 1; i > endCol; j--){
                if ((j + i) == (endCol + endRow)){
                  if (getPiece(i,j) != null){
                    return false;
                  }
                }
              }
            }
          }
        }
        else if ((endRow > startRow) && (endCol > startCol)){
          // back and right
          if((endCol - endRow) != (startCol - startRow)){
            return false;
          } else{
            for (int i = startRow + 1; i < endRow; i++){
              for (int j = startCol + 1; j < endRow; j++){
                if ((j - i) == (endCol - endRow)){
                  if (getPiece(i,j) != null){
                    return false;
                  }
                }
              }
            }
          }
        }
      return true;
      }



    public static void main(String args[]){
      Board myBoard = new Board();
      Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", myBoard);
      System.out.println(myBoard);
      System.out.println(myBoard.isGameOver());
    }


}
