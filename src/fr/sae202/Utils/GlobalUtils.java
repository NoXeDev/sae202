package fr.sae202.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class containing the global utils tools
 */
public class GlobalUtils {

    /**
     * Return all matches of a regex in a string
     * @param str the string
     * @param regex the regex
     * @return all matches of a regex in a string
     */
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
