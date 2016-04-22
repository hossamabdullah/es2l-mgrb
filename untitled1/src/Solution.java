import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int count = sc.nextInt();
        int[] coins = new int[count];
        for(int i=0; i<count; i++){
            coins[i] = sc.nextInt();
        }
        System.out.println(calChanges(coins,count,num));
    }

    private static BigInteger calChanges(int[] coins, int count, int num){
        System.out.println(count+","+num);
        // If n is 0 then there is 1 solution (do not include any coin)
        if (num == 0)
            return BigInteger.valueOf(1);

        // If n is less than 0 then no solution exists
        if (num < 0)
            return BigInteger.valueOf(0);

        // If there are no coins and n is greater than 0, then no solution exist
        if (count <=0 && num >= 1)
            return BigInteger.valueOf(0);

        // count is sum of solutions (i) including S[m-1] (ii) excluding S[m-1]
        return calChanges( coins, count - 1, num ).add(calChanges( coins, count, num-coins[count-1] ));
    }
}