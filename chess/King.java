public class King{

  int row;
  int col;
  boolean isBlack;

  public King(int row, int col, boolean isBlack){
    this.row = row;
    this.col = col;
    this.isBlack = isBlack;
  }
  //TODO
  public boolean isMoveLegal(Board board, int endRow, int endCol){
    return board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack) && board.verifyAdjacent(this.row, this.col, endRow, endCol);
  }

}
