import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * UVa 642 - WordAmalgamation
 *
 * Objective: Given a set of dictionary words and scrambled words, output all dictionary terms
 *            that can be formed from the scrambled words
 *
 *
 * COMPLETED
 *      Time: .050
 */
class Main {

    //sort the characters of s, return as new string
    public static String sort(String s){
        char[] c = s.toCharArray();
        Arrays.sort(c);
        return new String(c);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //dictionary and the sorted dictionary
        String s;
        ArrayList<String> dictionary = new ArrayList<>();
        ArrayList<String> sortedDic = new ArrayList<>();

        //add words to dictionary
        while (!(s = br.readLine()).equals("XXXXXX")) {
            dictionary.add(s);
        }

        //sort it
        Collections.sort(dictionary);

        //sort chars of words in dictionary and add to sortedDic
        for(int i = 0; i < dictionary.size(); i++){
            String sorted = sort(dictionary.get(i));
            sortedDic.add(sorted);
        }

        //go through scrambled words, output all dictionary terms that can be
        //formed, output "NOT A VALID WORD" if no such word exists.
        while (!(s = br.readLine()).equals("XXXXXX")) {
            String scramble = sort(s);
            int count = 0;
            for(int i = 0; i < sortedDic.size(); i++){
                String word = sortedDic.get(i);

                //if the lengths aren't the same, skip to next iteration
                if(word.length() != scramble.length()) continue;

                boolean isMatch = true;
                for(int j = 0; j < word.length(); j++){
                    if(scramble.charAt(j) == word.charAt(j)){
                        continue;
                    } else {
                        isMatch = false;
                    }
                }
                if(isMatch){
                    System.out.println(dictionary.get(i));
                    count++;
                }
            }
            if(count == 0) System.out.println("NOT A VALID WORD");
            System.out.println("******");
        }
    }
}
