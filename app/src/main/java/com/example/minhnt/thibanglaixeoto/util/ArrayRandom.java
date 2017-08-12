package com.example.minhnt.thibanglaixeoto.util;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class ArrayRandom {

    public static int[] get(int[] array) {
        int len = array.length;
        int[] randomArray = new int[len];
        int count = 0;

        Vector<Integer> tempArray = new Vector<Integer>();
        for(int i = 0; i < array.length; i++)
        {
            tempArray.add(array[i]);
        }

        for(int i = 0; i < len; i++)
        {
            Random ran = new Random();
            int random = ran.nextInt(tempArray.size());
            randomArray[count++] = tempArray.get(random);
            tempArray.remove(random);
        }
        return randomArray;
    }

    public static <T> ArrayList<T> get(ArrayList<T> array) {
        ArrayList<T> randomArray = new ArrayList<>();
        int len = array.size();
        ArrayList<T> tempArray = new ArrayList<>();
        for(int i = 0; i < array.size(); i++){
            tempArray.add(array.get(i));
        }

        for(int i = 0; i < len; i++){
            Random ran = new Random();
            int random = ran.nextInt(tempArray.size());
            randomArray.add(tempArray.get(random));
            tempArray.remove(random);
        }
        return randomArray;
    }

    public static int[] getQuestionIndices(int listSize, int currentQuestion){
        int[] array = new int[listSize - 1];
        for(int i = 0; i < array.length; i++){
            if(i < currentQuestion) {
                array[i] = i;
            }
            else{
                array[i] = i + 1;
            }
        }
        return get(array);
    }
}
