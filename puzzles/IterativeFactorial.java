//catagory: loop
public class IterativeFactorial {
	//iterative function calculates n!
	static int factorial(int n)
	{
	    int sum = 1;
	    if (n <= 1) return sum;
	    while (n > 1)
	    {
	        sum *= n;
	        n--;
	    }
	    return sum;
	}
	
	public static void main(String[] args)
	{
		System.out.println(factorial(5)); //should be 120
	}
}
