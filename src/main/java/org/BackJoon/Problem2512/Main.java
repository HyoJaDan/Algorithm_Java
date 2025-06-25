package org.BackJoon.Problem2512;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        int operand1 = 633;
        int operand2 = 78;
        int userAnswer = 454;
        System.out.println(computeErroneousAddition2(operand1, operand2, userAnswer));
    }

    private static boolean computeErroneousAddition(int operand1, int operand2, int submittedResult) {
        int carry = 0;

        while (operand1 > 0) {
            int currentOperand1 = operand1 % 10;
            int currentOperand2 = operand2 % 10;
            int currentSubmitResult = submittedResult % 10;
            operand1 /= 10;
            operand2 /= 10;
            submittedResult/=10;

            if( (currentOperand1 + currentOperand2)%10 == currentSubmitResult && carry!=0)
                return true;

            int sum = currentOperand1 + currentOperand2 + carry;
            carry = sum / 10;
        }
        return false;
    }
    private static boolean computeErroneousAddition2(int operand1, int operand2, int submittedResult) {
        int currentIdx = 0;

        boolean twoCarry = true;
        boolean threeCarry = true;

        if(twoCarry) {
            int currentOperand1 = (operand1 / 10) % 10;
            int currentOperand2 = (operand2 / 10) % 10;
            int currentSubmitResult = (submittedResult / 10) % 10;

            if(currentOperand2 > currentOperand1) {
                currentOperand1 += 10;
            }

            if(currentOperand1 - currentOperand2 == currentSubmitResult)
                return true;
        }
        if(threeCarry) {
            int currentOperand1 = (operand1 / 100) % 10;
            int currentOperand2 = (operand2 / 100) % 10;
            int currentSubmitResult = (submittedResult / 100) % 10;

            if(currentOperand2 > currentOperand1) {
                currentOperand1 += 10;
            }

            if(currentOperand1 - currentOperand2 == currentSubmitResult)
                return true;
        }
        return false;
    }
}
