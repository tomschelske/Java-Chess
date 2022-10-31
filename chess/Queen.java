public class Queen{

  int row;
  int col;
  boolean isBlack;

  public Queen(int row, int col, boolean isBlack){
    this.row = row;
    this.col = col;
    this.isBlack = isBlack;
  }
//TODO
  public boolean isMoveLegal(Board board, int endRow, int endCol){
    return (board.verifySourceAndDestination(this.row,this.col,endRow,endCol,this.isBlack) && (board.verifyHorizontal(this.row,this.col,endRow,endCol) || board.verifyVertical(this.row,this.col,endRow,endCol) || board.verifyDiagonal(this.row,this.col,endRow,endCol)));
  }

}
