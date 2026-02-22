class Solution {
    public String minRemoveToMakeValid(String s) {
        StringBuilder sb = new StringBuilder();
        int open = 0;
        
        // First pass: remove invalid ')'
        for (char c : s.toCharArray()) {
            if (c == '(') {
                open++;
                sb.append(c);
            } else if (c == ')') {
                if (open > 0) {
                    open--;
                    sb.append(c);
                }
                // else skip this ')'
            } else {
                sb.append(c);
            }
        }
        
        // Second pass: remove excess '(' from the end
        StringBuilder result = new StringBuilder();
        open = 0;
        
        for (int i = sb.length() - 1; i >= 0; i--) {
            char c = sb.charAt(i);
            if (c == '(') {
                if (open > 0) {
                    open--;
                    result.append(c);
                }
                // else skip this '('
            } else if (c == ')') {
                open++;
                result.append(c);
            } else {
                result.append(c);
            }
        }
        
        return result.reverse().toString();
    }
}