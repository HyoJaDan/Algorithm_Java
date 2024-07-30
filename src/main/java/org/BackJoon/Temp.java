package org.BackJoon;

public class Temp {
    public static void main(String[] args) {

        int n=7;

        for(int i=0;i<n;i++)
        {
            for(int j=0;j<=i;j++)
            {
                System.out.printf("* ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();

        for(int i=0;i<=n;i++)
        {
            for(int j=n;j>i;j--)
                System.out.printf("  ");
            for(int j=0;j<=i;j++)
                System.out.printf("* ");
            System.out.println();
        }
        System.out.println();
        System.out.println();
        for(int i=0;i<=n;i++)
        {
            for(int j=n;j>i;j--)
                System.out.printf("  ");
            for(int j=0;j<=i;j++)
                System.out.printf("* ");
            for(int j=0;j<=i;j++)
            {
                System.out.printf("* ");
            }

            System.out.println();
        }
    }

}
