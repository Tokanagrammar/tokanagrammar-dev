# SIMPLE
# 256
# This computes the GCD of two given numbers
package easy;

public class GCD
{

    public static void main(String[] args)
    {
        System.out.print("GCD: " + gcd(1024, 768));
    }

    public static int gcd(int x, int y)
    {
        if (y == 0)
        {
            return x;
        }
        return gcd(y, x % y);
    }
}