import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.Stack;

public class calculator1 {
    static String result ="";
    static Deque<Character> deque = new ArrayDeque<Character>();
    static String checkParentheses(String order){
        Stack <String>stack=new Stack<>();
        for (int i = 0; i < order.length(); ++i) {
            if(order.charAt(i)=='(')
                stack.push("(");
            if(order.charAt(i)==')'){
                if(stack.size()==0)
                    return "Invalid Expression";
                else
                    stack.pop();
            }
        }
        if(stack.size()!=0)
            return "Invalid Expression";
        return "";
    }
    static boolean decimalNumber(boolean added,char character,char beforeC,int i){
        if(character=='.'){
            result+=character;
            added=true;
        }
        return added;
    }
    static boolean multiNumber(boolean multiDigit,char character,char beforeC,int i){
        if(Character.isLetterOrDigit(character)&& beforeC=='.')
            multiDigit=true;
        if(Character.isLetterOrDigit(beforeC) &&Character.isLetterOrDigit(character))
            multiDigit=true;
        return multiDigit;

    }
    static boolean negativeNumber(boolean negative,char character,char beforeC,int i){
        if(i==0){
            if(character=='-') {
                negative = true;
                result += " " + character;
            }
        }else {
            if (character == '-')
                if (!Character.isLetterOrDigit(beforeC) || beforeC == '(') {
                    negative = true;
                    result += " " + character;
                }
        }
        return negative;
    }
    static boolean postfix(boolean multiDigit,char character, boolean added,boolean negative){
        if (Character.isLetterOrDigit(character)&&added==false) {
            if (multiDigit == true) {
                result += character;
            } else
                result += " " + character;
        }
        else if (character == '('&&added==false)
            deque.push(character);

        else if (character == ')' && added==false) {
            if(deque.isEmpty()){
                return false;
            }else {
                while (!deque.isEmpty() && deque.peek() != '(') {
                    result += " " + deque.peek();
                    deque.pop();
                }
                if (!deque.isEmpty())
                    deque.pop();
            }
        }
        else if(negative==false &&added==false )
        {
            while (!deque.isEmpty() && priority(character) <= priority(deque.peek())) {
                result +=" "+ deque.peek();
                deque.pop();
            }
            deque.push(character);
        }
        return true;
    }
    static int priority(char ch)
    {
        switch (ch) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }
    static String infixToPostfix(String order)
    {
        String checkParentheses=checkParentheses(order);
        if(checkParentheses.compareTo("Invalid Expression")==0)
            return "Invalid Expression";
        while (deque.size()!=0)
            deque.remove();
        boolean negative=false;
        order=order.replaceAll(" ","");
        for (int i = 0; i < order.length(); ++i) {
            char character = order.charAt(i);

            char beforeC ='n';
            boolean multiDigit=false;
            boolean added=false;
            if (Character.isLetterOrDigit(character)) {
                if (negative == true) {
                    result +=character;
                    added=true;
                    negative=false;
                }
            }
            if(i-1>=0) {
                beforeC = order.charAt(i -1);
                multiDigit=multiNumber(multiDigit,character,beforeC,i);
                added=decimalNumber(added,character,beforeC,i);
            }
            negative=negativeNumber(negative,character,beforeC,i);
            if(!postfix(multiDigit,character,added,negative))
                return "Invalid Expression";
            if (i!=0){
                if (order.charAt(i)=='(' && (order.charAt(i-1)<58 && order.charAt(i-1)>47))
                    return "Invalid Expression";
            }
            if (i!=order.length()-1){
                if (order.charAt(i)==')' && (order.charAt(i+1)<58 && order.charAt(i+1)>47))
                    return "Invalid Expression";
            }
        }
        while (!deque.isEmpty()) {
            if (deque.peek() == '(' ){
                return "Invalid Expression";
            }
            result += " "+deque.peek();
            deque.pop();
        }
        while (!deque.isEmpty()) {
            if (deque.peek() == ')' ){
                return "Invalid Expression";
            }
            result += " "+deque.peek();
            deque.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        int option=-1;
        String order;
        do {
            System.out.println("Enter the operation ");
            Scanner input=new Scanner(System.in);
            order= input.nextLine();
            String postfixOrder=infixToPostfix(order);
            if(postfixOrder.compareTo("Invalid Expression")==0){
                System.out.println("Invalid Expression");
                result="";
                if (deque.size()!=0)
                    deque.remove();
            }
            else {
                //System.out.println(postfixOrder);
                RPNN.R_pof(postfixOrder);
            }
            System.out.println("Do you want to close the program? 1-yes 2-no");
            option=input.nextInt();
        }while (option!=1);
    }
}
