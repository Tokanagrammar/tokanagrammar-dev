//catagory: Array, loop, string
//http://www.freejavaguide.com/arrays.htm

public class Arrays1{ 

	public static void main(String args[]){

		// this declares an array named x with the type "array of int" and of 
		// size 10, meaning 10 elements, x[0], x[1] , ... , x[9] ; the first term
		// is x[0] and the last term x[9] NOT x[10].
		int x[] = new int[10];

		// print out the values of x[i] and they are all equal to 0.
		for(int i=0; i<=9; i++)
			System.out.println("x["+i+"] = "+x[i]);

		// assign values to x[i] 
		for(int i=0; i<=9; i++)
			x[i] = i; // for example

		// print the assigned values of x[i] : 1,2,......,9
		for(int i=0; i<=9; i++)
			System.out.println("x["+i+"] = "+x[i]);

		// this declares an array named st the type "array of String"
		// and initializes it
		String st[]={"first","second","third"};

		// print out st[i]
		for(int i=0; i<=2; i++)
			System.out.println("st["+i+"] = "+st[i]);

	}
}