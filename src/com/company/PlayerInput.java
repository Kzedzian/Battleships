package com.company;

import java.util.Locale;

public class PlayerInput {

    public static boolean validateInput(String input){
        char one = input.charAt(0);
        char two = input.charAt(1);

        if(input.length()==3 && input.charAt(2) != '0'&& input.charAt(1) != '1'){
            return false;
        }

        if(input.length()>3 || !Character.isDigit(two) || !Character.isAlphabetic(one)){
            System.out.println("Invalid input!");
            return false;
        }

        return true;

    }

    public static int[] inputTranslator(String input){
        int[] result = new int[2];

        String collumn = input.charAt(0)+"";
        Character coll = collumn.toUpperCase(Locale.ROOT).charAt(0);
        int collValue = coll.charValue() - 65;
        System.out.println(collValue);
        String row = input.charAt(1)+"";
        if(input.length() == 3){
            row += input.charAt(2);
        }
        int rowValue = Integer.parseInt(row) -1;
        System.out.println(rowValue);

        result[1] = collValue;
        result[0] = rowValue;

        return result;
    }
}
