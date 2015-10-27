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
     * @param firstElem first element of math expression
     * @param delim math expression delimeter
     * @param secondElem second element of math expression
     * @return the result of solving math expression
     */
    public static String calc(String firstElem, String delim, String secondElem){
        String result = "";
        float first = Float.valueOf(firstElem);
        float second = Float.valueOf(secondElem);

        if (delim.equals("+")){
            result = Float.toString(first + second);
        }
        else if (delim.equals("-")){
            result = Float.toString(first - second);
        }
        else if (delim.equals("*")){
            result = Float.toString(first * second);
        }
        else if (delim.equals("/")){
            result = Float.toString(first / second);
        }
        return result;
    }

    /**
     * Produces result output
     * @param Expression math expression to solve
     * @throws IOException
     */
    public static void resultOutput(String Expression)  throws IOException {
        Pattern pattern = Pattern.compile("(\\-*\\d+\\.*\\d*)\\s*([+\\-*\\/])\\s*(\\-*\\d+\\.*\\d*)");
        Matcher matcher = pattern.matcher(Expression);
        if (matcher.find()) {
            String firstElement = matcher.group(1);
            String delimeter = matcher.group(2);
            String secondElement = matcher.group(3);
            /** For debug purposes
             *System.out.println("First Element:\t" + firstElement);
             *System.out.println("Delimeter:\t\t" + delimeter);
             *System.out.println("Second Element:\t" + secondElement);
             */
            System.out.println("The result is:\t" + calc(firstElement, delimeter, secondElement));
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

        //exit flag
        boolean repeat = true;

        //Cycle user input request
        while(repeat) {
            System.out.println("Enter math expression below:");

            resultOutput(readInput());

            System.out.println("Type 'q' to exit or enter any other key to continue");

            repeat = !readInput().toLowerCase().equals("q");
        }
    }
}