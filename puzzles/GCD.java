<<<<<<< HEAD
package easy;

public class GCD {
	
	public static void main(String[] args){
		System.out.println("GCD: " + gcd(1024, 768));
	}
	
	public static int gcd(int x, int y){
		if(y == 0){
			return x;
		}
		return gcd(y, x % y);
	}

}
=======
package easy;

public class GCD {
	
	public static void main(String[] args){
		System.out.println("GCD: " + gcd(1024, 768));
	}
	
	public static int gcd(int x, int y){
		if(y == 0){
			return x;
		}
		return gcd(y, x % y);
	}

}
>>>>>>> 5a6106b3a6463da93b18e999e358671166370f2b
