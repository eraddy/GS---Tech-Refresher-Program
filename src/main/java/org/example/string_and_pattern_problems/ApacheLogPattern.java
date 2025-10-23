package org.example.string_and_pattern_problems;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApacheLogPattern {
    public static String findTopIpAddress(String[] lines)
    {
        Map<String,Integer> ipHitCount = new HashMap<>();
        for(String line : lines){
            String ip = line.split(" ")[0];
            ipHitCount.put(ip,ipHitCount.getOrDefault(ip,0)+1);
        }

        int maxCount = 0;
        for(Map.Entry<String,Integer> entry : ipHitCount.entrySet()){
            maxCount = Integer.max(maxCount, entry.getValue());
        }

        List<String> topIps = new ArrayList<>();
        for(Map.Entry<String,Integer> entry : ipHitCount.entrySet()){
            if(entry.getValue() == maxCount)
                topIps.add(entry.getKey());
        }
        Collections.sort(topIps);
        return String.join(",",topIps);


    }

    static void main() {
        String[] lines1 = {
                "10.0.0.1 - frank [10/Dec/2000:12:34:56 -0500] \"GET /a.gif HTTP/1.0\" 200 234",
                "10.0.0.1 - frank [10/Dec/2000:12:34:57 -0500] \"GET /b.gif HTTP/1.0\" 200 234",
                "10.0.0.2 - nancy [10/Dec/2000:12:34:58 -0500] \"GET /c.gif HTTP/1.0\" 200 234"
        };

        String[] lines2 = {
                "10.0.0.1 - frank [10/Dec/2000:12:34:56 -0500] \"GET /a.gif HTTP/1.0\" 200 234",
                "10.0.0.1 - frank [10/Dec/2000:12:34:57 -0500] \"GET /b.gif HTTP/1.0\" 200 234",
                "10.0.0.2 - nancy [10/Dec/2000:12:34:58 -0500] \"GET /c.gif HTTP/1.0\" 200 234",
                "10.0.0.2 - nancy [10/Dec/2000:12:34:59 -0500] \"GET /d.gif HTTP/1.0\" 200 234",
                "10.0.0.3 - logan [10/Dec/2000:12:34:59 -0500] \"GET /e.gif HTTP/1.0\" 200 234"
        };

        System.out.println(findTopIpAddress(lines1));
        System.out.println(findTopIpAddress(lines2));
    }
}
