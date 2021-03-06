# Sort algorithms
# 0 1 1 3 3 5 10 11
# This implements and uses the Bubble Sort algorithm to sort an integer array

public class BubbleSort
{
	public static void main(String[] args)
	{
		int[] ar = {0, 5, 1, 10, 11, 3, 1, 3};
		
		bubbleSortArray(ar);
		int n = 0;
		while (n < ar.length)
			System.out.print(ar[n] + " ");
		
	}
	
	public static void bubbleSortArray(int[] ar)
	{
		int len = ar.length;
		int n = 1;
		int m;
		
		while (n < len)
		{
			m = 0;
			while (m < len - n)
			{
				if (ar[m] > ar[m + 1])
				{
					int temp = ar[m];
					ar[m] = ar[m + 1];
					ar[m + 1] = temp;
				}
			}
			++n;
		}
	}
}