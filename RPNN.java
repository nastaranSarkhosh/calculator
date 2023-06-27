import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;
public class RPNN extends EmptyStackException {
    static Stack<String> stack = new Stack<String>();
    static String ans;
    static void R_pof(String postfix) {
        String arr[];
        arr= postfix.split(" ");
        for (int i = 1; i <arr.length; i++) {
            if ((arr[i].compareTo("+") != 0) && (arr[i].compareTo("-") != 0) && (arr[i].compareTo("*") != 0) && (arr[i].compareTo("/") != 0)&&(arr[i].compareTo("^") != 0)) {
                stack.push(arr[i]);
            } else {
                Operations(arr[i]);
            }
        }
        System.out.println(stack.pop());
        //System.out.println(stack.isEmpty());
        //System.out.println(stack.pop());
    }

    public static void Operations(String operator) {
        double a, b, value;
        String a1="",b2="";
        try {
            a1=stack.pop();
            b2=stack.pop();
            switch (operator) {
                case "+":
                    a = Double.valueOf(a1);
                    b = Double.valueOf(b2);
                    value = a + b;
                    stack.push(String.valueOf(value));
                    break;

                case "-":
                    a = Double.valueOf(a1);
                    b = Double.valueOf(b2);
                    value = b - a;
                    stack.push(String.valueOf(value));
                    break;
                case "*":
                    a = Double.valueOf(a1);
                    b = Double.valueOf(b2);
                    value = a * b;
                    stack.push(String.valueOf(value));
                    break;

                case "/":
                    a = Double.valueOf(a1);
                    b = Double.valueOf(b2);
                    value = b / a;
                    stack.push(String.valueOf(value));
                    break;
                case "^":
                    a = Double.valueOf(a1);
                    b = Double.valueOf(b2);
                    value = Math.pow(b,a);
                    stack.push(String.valueOf(value));
                    break;
            }
        }catch (EmptyStackException e){
            System.out.println("Error");
            System.exit(0);
        }
    }
}
