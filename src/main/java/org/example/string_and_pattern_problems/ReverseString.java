package org.example.string_and_pattern_problems;

public class ReverseString {
    public static String reverseStr(String str){
        String result = "";
        for(int i = str.length()-1;i>=0;i--)
            result += str.charAt(i);
        return result;

//        Other method of solving using the String Builder to avoid the multiple object creation
//        StringBuilder result = new StringBuilder();
//        for(int i = str.length()-1;i>=0;i--)
//            result.append(str.charAt(i));
//        return result.toString();

//        Other method using the array approach
//        char[] arr = str.toCharArray();
//        int left = 0, right = arr.length - 1;
//        while (left < right) {
//            char temp = arr[left];
//            arr[left] = arr[right];
//            arr[right] = temp;
//            left++;
//            right--;
//        }
//        return new String(arr);




    }

    static void main() {
        System.out.println(reverseStr("abcd"));
    }
}
