// Team The Don't Cafe -- Jannie Li and Matthew So
// APCS1 pd10
// HW36 -- Some Folks Call It a Memory
// 2015-11-24


import cs1.Keyboard;  //if comfortable with Scanner, you may comment this out

		
public class Concentration {

    //instance variables
    private Tile[][] _board;     //storage for 4x4 grid of Tiles.
    private int _numberFaceUp;   //count of Tiles with faces visible
    private String[] _words;     //list of unique Strings used for Tile vals
    private static int _numRows = 4;

    
    //========================================    
    //constructor
    public Concentration() {
        _numberFaceUp = 0;
	_board = new Tile[_numRows][_numRows];
	_words = new String[] {"aaa", "bbb", "ccc", "ddd",
			       "eee", "fff", "ggg", "hhh"};
	populate();
    }

    
    //========================================
    //methods

    //populate board-- call constructor on each tile
    //use double x and increment by .5 to go twice
    //since each word is only in _words once
    public void populate() {
	double x = 0;
	
	for (int r = 0; r < _board.length; r++) {
	    for (int c = 0; c < _board[r].length; c++) {
	        _board[r][c] = new Tile(_words[(int)x]);	
	        x += 0.5;
	    } 
	}	
    }
    

    //swap tiles
    public static void swap(Tile[][] a, int r1, int r2, int c1, int c2) {
	//bc if reset, would be lost
        Tile origTile = a[r1][c1];

	//orig spot set to other string
	a[r1][c1] = a[r2][c2];

	//other set to orig
	a[r2][c2] = origTile;
    }

    
    //random int generator
    public static int randInt(int lowerB, int upperB) {
        int ret = (((int)(Math.random()*(upperB-lowerB))) + lowerB);
	return ret;
    }

    
    //shuffle tiles-- swap each tile with a random other tile
    public void shuffle() {
	for (int r = 0; r < _board.length; r++) {
	    for (int c = 0; c < _board[r].length; c++) {
		swap (_board, r, c,
		      randInt(0, _numRows-1),
		      randInt(0, _numRows-1));
	    } 
	}
    }


    //flip tiles
    public void flip(int r1, int r2, int c1, int c2) {

	//check to make sure tiles have not been flipped already,
	//if not, flip
	if  ( (_board[r1][c1].isFaceUp() || _board[r2][c2].isFaceUp()) ) {
	    System.out.println("\ntile already matched. choose again.");
	}
	else {
	    //flip tiles
	    _board[r1][c1].flip();
	    _board[r2][c2].flip();

	    //board post-flip
	    System.out.println("\n\n\t now: \n");
	    this.print();

	    //check for equality
	    //if yes, match and add to _numberFaceUp
	    //if no, flip back
	    if (_board[r1][c1].equals(_board[r2][c2])) {
		System.out.println("match!");
	        _numberFaceUp += 2;
	    }
	    else {
		_board[r1][c1].flip();
		_board[r2][c2].flip();
		System.out.println("no match.");
	    }
	}
	
    }


    //print board 
    public void print() {
	for (Tile[] i : this._board) {
	    for (Tile x : i) {
		System.out.print(x + "\t");
	    }
	    System.out.print("\n");
	}
    }

    
    //play game
    public void play() {
	System.out.println("\n\n\t start game \n");

	//shuffle first
        this.shuffle();

	//keep trying until all are face up
	while (this._numberFaceUp != (this._numRows * this._numRows)) {

	    //print board
	    System.out.println("\n\n\t next round: \n");
	    this.print();
	    //print # matches so far
	    System.out.println("# matches: " + _numberFaceUp);

	    //prompt user
	    //subtract one (i.e. allow user to enter 1-4 instead of 0-3)
	    System.out.print("row1: ");
	    int r1 = Keyboard.readInt() - 1;

	    System.out.print("col1: ");
	    int c1 = Keyboard.readInt() - 1;

	    System.out.print("row2: ");
	    int r2 = Keyboard.readInt() - 1;

	    System.out.print("col2: ");
	    int c2 = Keyboard.readInt() - 1;


	    //make sure indices entered not out of range
	    //and not same tile
	    //before flipping
	    //if not, print error message and prompt again
	    while ((r1 == r2 && c1 == c2) ||
		   !(r1 < this._numRows && r1 >= 0 &&
		     r2 < this._numRows && r2 >= 0 &&
		     c1 < this._numRows && c1 >= 0 &&
		     c2 < this._numRows && c2 >= 0)) {		

		//error msg
	        System.out.println("\nyou entered the same tiles...");
		System.out.println("or you are out of range (from 1 up)");

		//prompt again
		System.out.print("row1: ");
		r1 = Keyboard.readInt() - 1;

		System.out.print("col1: ");
	        c1 = Keyboard.readInt() - 1;

		System.out.print("row2: ");
	        r2 = Keyboard.readInt() - 1;

		System.out.print("col2: ");
	        c2 = Keyboard.readInt() - 1;
	    }

	    //indices all good... flip
	    this.flip(r1,r2,c1,c2);

	}

	System.out.println("\n\n\tyou win!");
	System.out.println("\n\n\tend of game");

    }

    
		
    //DO NOT MODIFY main()
    public static void main(String[] args){
	Concentration game = new Concentration();
	game.play();
    }

}//end class Concentration
