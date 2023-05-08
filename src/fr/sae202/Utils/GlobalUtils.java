package fr.sae202.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GlobalUtils {
    public static List<String> arrayMatches(String str, String regex)
    {
        List<String> allMatches = new ArrayList<String>();
        Matcher m = Pattern.compile(regex)
            .matcher(str);
            
        while (m.find()) {
            allMatches.add(m.group());
        }

        return allMatches;
    }
}
