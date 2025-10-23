package org.example.string_and_pattern_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShortestDistance {
    public static double shortestDistance(String document, String word1, String word2) {
        document = document.toLowerCase();
        word1= word1.toLowerCase();
        word2 = word2.toLowerCase();
        List<Double> w1Pos = getWordMidPoints(document,word1);
        List<Double> w2Pos = getWordMidPoints(document,word2);

        double shortestDistance = Double.MAX_VALUE;
        for(double a : w1Pos){
            for(double b : w2Pos){
                shortestDistance = Double.min(shortestDistance,Math.abs(a-b));
            }
        }
        return shortestDistance;
    }
    private static List<Double> getWordMidPoints(String document,String word){
        List<Double> mids = new ArrayList<>();
        Pattern p = Pattern.compile("\\b" + word + "\\b",Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(document);
        while(m.find()){
            double mid = (m.start() + m.end() - 1) / 2.0;
            mids.add(mid);
        }
        return mids;
    }
    static void main() {
        String Document = "In publishing and graphic design, lorem ipsum is a filler text commonly used to demonstrate the graphic elements";
        double ans = shortestDistance(Document,"is","a");
        System.out.println(ans);
    }
}
