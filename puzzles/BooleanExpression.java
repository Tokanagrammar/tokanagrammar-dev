//http://www.freejavaguide.com/boolean_operators.htm
class BooleanExpression{ 
   public static void main(String args[]){

// these are boolean variables     
      boolean A = true;
      boolean B = false; 

      System.out.println("A|B = "+(A|B));
      System.out.println("A&B = "+(A&B));
      System.out.println("!A = "+(!A));
      System.out.println("A^B = "+(A^B));
      System.out.println("(A|B)&A = "+((A|B)&A));
    }
}
