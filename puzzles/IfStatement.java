# if statement
# output
# This simple program shows the use of If-Else statement
class IfStatement
{
	public static void main (String[] args)
	{
		int num;
		num = -357;

		if ( !(num > 0) )  // true-branch
		{
	      System.out.println("The number " + num + " is negative");
		   System.out.println("negative number are less than zero"); 
		}
		else   // false-branch
		{
		   System.out.println("The number " + num + " is positive");
		   System.out.print ("positive numbers are greater ");
		   System.out.println("or equal to zero ");
		}
		System.out.println("End of program"); // always executed
	}
}
       