public class Exception1
{
	public static void main(String args[])
	{
		int i =0 ;
		//Declare an array of strings
		String Box [] = {"Book", "Pen", "Pencil"};
		while(i<4)
		{
			try
			{
				System.out.println(Box[i]);
				
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				System.out.println("Subscript Problem " + e);
			i++;
			}
			finally
			{
				System.out.println("This is always printed.");
			}
			i++;
		}
	}
}