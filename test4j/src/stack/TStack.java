package stack;

import java.util.Iterator;
import java.util.Stack;

/**
 * adfafafaf<br>
 * 
 * @author houshengyong
 * 
 */
public class TStack {

	/**
	 * main entrance
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Õ»ÖÐÕ»
		Stack<Stack<String>> stackOfStack = new Stack<Stack<String>>();
		{
			Stack<String> stackOfString = new Stack<String>();
			stackOfString.push("1");
			stackOfString.push("2");
			stackOfString.push("3");
			stackOfStack.push(stackOfString);
		}
		{
			Stack<String> stackOfString = new Stack<String>();
			stackOfString.push("11");
			stackOfString.push("22");
			stackOfString.push("33");
			stackOfStack.push(stackOfString);
		}
		for (Stack<String> stack : stackOfStack) {
			for (Iterator<?> iterator = stack.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				System.out.println(string);
			}
		}
		// stackOfStack.remove(0);
		while (!stackOfStack.empty()) {
			Stack<String> stackOfStackItem = stackOfStack.pop();

			while (!stackOfStackItem.empty()) {
				String item = stackOfStackItem.pop();
				System.out.println(item);

			}
		}

	}

}
