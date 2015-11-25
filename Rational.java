// Team The Don't Cafe - Matthew So & Jannie Li
// APCS1 pd10
// HW37 -- Rational Equality
// 2015-11-25



public class Rational {

    private int num;
    private int den;


    // CONSTRUCTORS
    // ===========================================
    //default constructor
    public Rational(){
	num = 0;
	den = 1;
    }
	
    //overloaded constructor: checks for division by 0
    public Rational(int n, int d){
	this();
	if (d == 0) {
	    System.out.print ("MathError: Division by 0.");
	}
	else {
	    num = n;
	    den = d;
	}
    }




    // METHODS
    // ===========================================
	
    //prints rational number with newlines
    public String toString(){
	return num +
	    "\n" + "-" +
	    "\n" + den +
	    "\n";
    }

    
    //float approximation of rational number
    public float floatValue(){
	return (float)(num)/(den);
	//Casting has precedence to division.
    }

    
    //multiplication
    public void multiply (Rational n){
        num *= n.num;
	den *= n.den;
	//Needn't worry about division by 0, param is also rational.
    }
    
	
    //division
    public void divide(Rational n){
	if (n.num == 0){
	    System.out.println("MathError: Division by 0");
	    return;
	}
	num *= n.den;
	den *= n.num;
    }


    
    
    // HW33 ADDITIONS
    // ===========================================

    // calc gcd of two ints
    public static int gcd(int a, int b){
	// deal with negatives
		a = Math.abs(a);
		b = Math.abs(b);
		
        while (b != 0) {
            int num = b;
            b = a % b;
            a = num;
        }
        return a;
    }


    // return gcd of numerator and denominator
    public int gcd(){
	return gcd(num,den);
    }


    // simplify the numerator and denominator of a Rational object
    public void reduce(){
	int gcd = gcd(num, den);
	num /= gcd;
	den /= gcd;
	if(den < 0){
		num *= -1;
		den *= -1;
		//How in the world is it so compact? Well, if den (and num) is (are) < 0
		//This will make n all positive. If only den is less than 0, sign flips to numerator.
		//If both are positive, w/e.
	}
    }


    // add to another Rational object
    public void add(Rational a){
	num = num * a.den + den * a.num;
	den *= a.den;
        this.reduce();
    }


    // subtract another Rational object
    public void subtract(Rational a){
	num = num * a.den - den * a.num;
	den *= a.den;
        this.reduce();
    }


    //  returns diff of two Rational objects
    public int compareTo(Rational a){
	Rational c = new Rational(num,den);
        c.reduce();
	a.reduce();
	c.subtract(a);
	return c.num;
    }
    
        
    // equals
    // Takes 1 Object as input
    // Returns true if input is of class Rational and of
    //equal value to calling instance of Rational
    // Returns false otherwise
    public boolean equals(Object other){
	if (other instanceof Rational){
	    //compares the instance variables in the two objects
	    
	    Rational newother = (Rational)other;
	    //create a rational instance of the input
	    
	    newother.reduce(); //reduce both
	    this.reduce();
	    if (this.num == newother.num &&
		this.den == newother.den)
		{ return true;}} //return true if the same
	
	return false;} //false in all other situations

    
    public static void main(String[] args){

	Rational z = new Rational(1,2);
	Rational y = new Rational(1,3);
	Rational x = new Rational(6,9);
        x.reduce();
	System.out.println(x);
	z.add(y);
	System.out.println(z);
	z.subtract(y);
	System.out.println(z);

	Rational d = new Rational(2,4);
	Rational e = new Rational(3,6);
	System.out.println(d.compareTo(e));
	
	Rational a = new Rational(-3,7);
	Rational b = new Rational(2,-4);
	System.out.println(a.compareTo(b));
	
	
	//HW37 TESTING
  	//testing equals()
	Rational test11 = new Rational(1,2);
	Rational test12 = new Rational(5,10);
	Rational test13 = new Rational(3,4);
	System.out.println(test11.equals(test12)); //return true
	System.out.println(test11.equals(test13)); //return false
	System.out.println(test13.equals(test12)); //return false
	
    }

}
