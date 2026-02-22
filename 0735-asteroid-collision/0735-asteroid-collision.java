import java.util.*;

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        
        for (int ast : asteroids) {
            // We only care about collisions when:
            //   - Current asteroid is moving LEFT (negative)
            //   - AND there is something on stack moving RIGHT (positive)
            while (!stack.isEmpty() 
                   && stack.peek() > 0               // right-moving on stack
                   && ast < 0) {                     // current is left-moving
            
                // Compare absolute values (sizes)
                if (Math.abs(stack.peek()) < Math.abs(ast)) {
                    // Previous (right-moving) explodes → pop it
                    stack.pop();
                    continue;  // check next one on stack
                } 
                else if (Math.abs(stack.peek()) == Math.abs(ast)) {
                    // Both same size → both explode
                    stack.pop();
                    ast = 0;   // current also explodes → won't be pushed
                    break;
                } 
                else {
                    // Current (left-moving) explodes → don't push it
                    ast = 0;
                    break;
                }
            }
            
            // If current asteroid didn't explode, push it
            if (ast != 0) {
                stack.push(ast);
            }
        }
        
        // Convert stack to array (note: stack has left-to-right order)
        int[] result = new int[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        
        return result;
    }
}