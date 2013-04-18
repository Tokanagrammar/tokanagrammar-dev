//catagory: relational expression
//http://www.freejavaguide.com/relational_operators.htm

public class RelationalExpression {
	public static void main(String[] args) {

	//a few numbers
	int i = 37;
	int j = 42;
	int k = 42;

	//greater than
	System.out.println("Greater than...");
	System.out.println(" i > j = " + (i > j)); //false
	System.out.println(" j > i = " + (j > i)); //true
	System.out.println(" k > j = " + (k > j)); //false
	//(they are equal)

	//greater than or equal to
	//System.out.println("Greater than or equal to...");
	//System.out.println(" i >= j = " + (i >= j)); //false
	//System.out.println(" j >= i = " + (j >= i)); //true
	//System.out.println(" k >= j = " + (k >= j)); //true

	//less than
	//System.out.println("Less than...");
	//System.out.println(" i < j = " + (i < j)); //true
	//System.out.println(" j < i = " + (j < i)); //false
	//System.out.println(" k < j = " + (k < j)); //false

	//less than or equal to
	System.out.println("Less than or equal to...");
	System.out.println(" i <= j = " + (i <= j)); //true
	System.out.println(" j <= i = " + (j <= i)); //false
	System.out.println(" k <= j = " + (k <= j)); //true

	//equal to
	System.out.println("Equal to...");
	System.out.println(" i == j = " + (i == j)); //false
	System.out.println(" k == j = " + (k == j)); //true

	//not equal to
	System.out.println("Not equal to...");
	System.out.println(" i != j = " + !(i == j)); //true
	System.out.println(" k != j = " + !(k == j)); //false
	}
}