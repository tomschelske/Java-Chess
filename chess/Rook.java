public class Rook{

int row;
int col;
boolean isBlack;

  public Rook(int row, int col, boolean isBlack){
    this.row = row;
    this.col = col;
    this.isBlack = isBlack;
  }
  //TODO
  public boolean isMoveLegal(Board board, int endRow, int endCol){
    return board.verifySourceAndDestination(this.row,this.col,endRow,endCol, this.isBlack) && (board.verifyVertical(this.row,this.col,endRow,endCol) || board.verifyHorizontal(this.row,this.col,endRow,endCol));
  }

}
