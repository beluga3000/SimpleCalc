package com.epam.calc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Calc -- simple calculator
 * Can perform actions with two numbers and four actions [+-*\/\]
 */

public class Calc {

    /**
     * Find out type of operation and return expression solving
     * @param sFirstElement first element of math expression
     * @param delimeter math expression delimeter
     * @param sSecondElement second element of math expression
     * @return the result of solving math expression
     */
    public static String calculateResult(String sFirstElement, String delimeter, String sSecondElement){
        String result = "";
        float fFirstElement = Float.valueOf(sFirstElement);
        float fSecondElement = Float.valueOf(sSecondElement);

        if (delimeter.equals("+")){
            result = Float.toString(fFirstElement + fSecondElement);
        }
        else if (delimeter.equals("-")){
            result = Float.toString(fFirstElement - fSecondElement);
        }
        else if (delimeter.equals("*")){
            result = Float.toString(fFirstElement * fSecondElement);
        }
        else if (delimeter.equals("/")){
            result = Float.toString(fFirstElement / fSecondElement);
        }
        return result;
    }

    /**
     * Produces result output
     * @param expression math expression to solve
     * @throws IOException
     */
    public static void printResult(String expression)  throws IOException {
        String trimmedExpression = expression.trim();
        Pattern pattern = Pattern.compile("^(\\-*\\d+\\.*\\d*)\\s*([+\\-*\\/])\\s*(\\-*\\d+\\.*\\d*)$");
        Matcher matcher = pattern.matcher(trimmedExpression);
        if (matcher.find()) {
            String firstElement = matcher.group(1);
            String delimeter = matcher.group(2);
            String secondElement = matcher.group(3);
            String sResult = calculateResult(firstElement, delimeter, secondElement);
            String resultTail = sResult.substring(sResult.indexOf('.'));
            if (resultTail.equals(".0")){
                Integer iResult = Integer.valueOf(sResult.substring(0, sResult.indexOf('.')));
                System.out.println("The result is:\t" + iResult);
            }  else {
                System.out.println("The result is:\t" + sResult);
            }
        } else {
            System.out.println("You've entered invalid expression. Please, try again.");
        }
    }

    /**
     * Reading user console input
     * @return input string
     * @throws IOException
     */
    public static String readInput() throws IOException{
        String inputString = null;
        try {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            inputString = bufferRead.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputString;
    }

    public static void main(String[] args)  throws IOException {
        String inputString;

        while(true) {
            System.out.println("Enter math expression below or 'q' to exit:");
            inputString  = readInput();
            if (inputString.toLowerCase().equals("q")) break;
            else printResult(inputString);

        }
    }
}
