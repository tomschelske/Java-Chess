public class Knight{

  int row;
  int col;
  boolean isBlack;

  public Knight(int row, int col, boolean isBlack){
    this.row = row;
    this.col = col;
    this.isBlack = isBlack;
  }
//TODO
  public boolean isMoveLegal(Board board, int endRow, int endCol){
    int rise;
    int run;
    rise = endCol - this.col;
    run = endRow - this.row;
    if (!(board.verifySourceAndDestination(this.row,this.col,endRow,endCol,this.isBlack))){
      return false;
    }
    if((rise == 1) && ((run == 2) || (run == -2))){
      return true;
    } else if ((rise == -1) && ((run == 2) || (run == -2))){
      return true;
    } else if ((rise == 2) && ((run == 1) || (run == -1))){
      return true;
    } else if ((rise == -2) && ((run == 1) || (run == -1))){
      return true;
    }
    return false;
  }

}
