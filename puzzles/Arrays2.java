public class Arrays2{ 

	public static void main(String args[]){

		// this declares an array named fl with the type "array of int" and
		// initialize its elements

		float fl[] = {2.5f, 4.5f, 8.9f, 5.0f, 8.9f};

		// find the sum by adding all elements of the array fl
		float sum = 0.0f;
		for(int i=0; i<= 4; i++)
			sum = sum + fl[i];

		// displays the sum
		System.out.println("sum = "+sum);
	}
}
