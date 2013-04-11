//catagory: two dimensional aray, nested array

public class Arrays3{ 

public static void main(String args[]){

// this declares a 2-dimensional array named x[i][j] of size 4 (4 elements)
// its elements are x[0][0], x[0][1], x[1][0] and x[1][1].
// the first index i indicates the row and the second index indicates the
// column if you think of this array as a matrix.


int x[][] = new int[2][2];

// print out the values of x[i][j] and they are all equal to 0.0.
for(int i=0; i<=1; i++)
for(int j=0; j<=1; j++)
System.out.println("x["+i+","+j+"] = "+x[i][j]);

// assign values to x[i] 
for(int i=0; i<=1; i++)
for(int j=0; j<=1; j++)
x[i][j] = i+j; // for example

// print the assigned values to x[i][j] 
for(int i=0; i<=1; i++)
for(int j=0; j<=1; j++)
System.out.println("x["+i+","+j+"] = "+x[i][j]);

// this declares a 2-dimensional array of type String
// and initializes it
String st[][]={{"row 0 column 0","row 0 column 1"}, // first row 
{"row 1 column 0","row 1 column 1"}}; // second row 

// print out st[i]
for(int i=0; i<=1; i++)
for(int j=0; j<=1; j++)
System.out.println("st["+i+","+j+"] = "+st[i][j]);

}
}