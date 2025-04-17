/*
 * TC: O(n * 2^n)
 * SC: O(n)
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BraceExpansion {
    public String[] expand(String s) {
        // first parse the string into a list of lists
        // e.g. "{a,b}c{d,e}f" -> [[a, b], [c], [d, e], [f]]
        List<List<Character>> sList = new ArrayList<>();
        int i = 0;
        while(i < s.length()) {
            if(s.charAt(i) == '{') {
                List<Character> subList = new ArrayList<>();
                i++;
                while(s.charAt(i) != '}') {
                    if(s.charAt(i) == ',') {
                        i++;
                        continue;
                    }
                    subList.add(s.charAt(i));
                    i++;
                }
                subList.sort(Character::compareTo);
                sList.add(subList);
            } else {
                sList.add(Collections.singletonList(s.charAt(i)));
            }
            i++;
        }
        List<String> result = new ArrayList<>();
        // backtrack to generate all combinations
        backtrack(sList, 0, new StringBuilder(), result);
        return result.toArray(new String[0]);
    }
    void backtrack(List<List<Character>> sList, int index, StringBuilder sb, List<String> result) {
        if(index == sList.size()) {
            result.add(sb.toString());
            return;
        }
        for(Character c: sList.get(index)) {
            // action
            sb.append(c);
            // recurse
            backtrack(sList, index + 1, sb, result);
            // backtrack
            sb.deleteCharAt(index);
        }
    }
}
