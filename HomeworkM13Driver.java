import java.util.*;

public class HomeworkM13Driver {

	public static void main(String[] args) {
		System.out.println("******TESTING STACK REVERSE");
		System.out.println("Stacks are printed \t[Bottom to Top]\n");

		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		System.out.println("Original stack is \t[1, 2, 3, 4]: \t" + stack);
		Stack<Integer> reverseStack = createReverseStack(stack);
		System.out.println("Reverse stack is \t[4, 3, 2, 1]: \t" + reverseStack);
		System.out.println("Original stack still \t[1, 2, 3, 4]:\t" + stack);

		stack.clear();
		System.out.println("\nOriginal stack is \t[]: \t" + stack);
		reverseStack = createReverseStack(stack);
		System.out.println("Reverse stack is \t[]: \t" + reverseStack);
		System.out.println("Original stack still \t[]:\t" + stack);

		stack.push(7);
		System.out.println("\nOriginal stack is \t[7]: \t" + stack);
		reverseStack = createReverseStack(stack);
		System.out.println("Reverse stack is \t[7]: \t" + reverseStack);
		System.out.println("Original stack still \t[7]:\t" + stack);

		stack.push(5);
		stack.push(3);
		System.out.println("\nOriginal stack is \t[7, 5, 3]: \t" + stack);
		reverseStack = createReverseStack(stack);
		System.out.println("Reverse stack is \t[3, 5, 7]: \t" + reverseStack);
		System.out.println("Original stack still \t[7, 5, 3]:\t" + stack);

		System.out.println("\n******TESTING REMOVE WORDS WITH KEY");
		List<String> wordList = new ArrayList<String>();
		wordList.addAll(Arrays.asList(new String[] { "apple", "banana", "cat", "apple", "dolphin", "elephant",
				"abracadabra", "melon", "the", "ninja", "and", "a", "a", "apple", "via" }));
		;
		System.out.println("\nOriginal list (size 15:" + wordList.size()
				+ ")\n[apple, banana, cat, apple, dolphin, elephant, abracadabra, melon, the, ninja, and, a, a, apple, via]"
				+ "\n" + wordList);
		Set<String> wordsRemoved = removeWordsContainingKey(wordList, "a");
		System.out.println(
				"\nList after method (size 3:" + wordList.size() + ")\n[dolphin, melon, the]" + "\n" + wordList);
		System.out.println("\nSet of unique removed words (size 9:" + wordsRemoved.size()
				+ ")\n[banana, apple, a, ninja, and, cat, abracadabra, elephant, via]" + "\n" + wordsRemoved);

		System.out.println();
		wordList.clear();
		wordList.addAll(Arrays.asList(new String[] { "apple", "banana", "cat", "apple", "dolphin", "elephant",
				"abracadabra", "melon", "the", "ninja", "and", "a", "a", "apple", "via" }));
		;
		System.out.println("\nOriginal list (size 15:" + wordList.size()
				+ ")\n[apple, banana, cat, apple, dolphin, elephant, abracadabra, melon, the, ninja, and, a, a, apple, via]"
				+ "\n" + wordList);
		wordsRemoved = removeWordsContainingKey(wordList, "z");
		System.out.println("\nList after method (size 15:" + wordList.size()
				+ ")\n[apple, banana, cat, apple, dolphin, elephant, abracadabra, melon, the, ninja, and, a, a, apple, via]"
				+ "\n" + wordList);
		System.out.println(
				"\nSet of unique removed words (size 0:" + wordsRemoved.size() + ")\n[]" + "\n" + wordsRemoved);

		System.out.println();
		wordList.clear();
		wordList.addAll(Arrays.asList(new String[] { "apple", "apple", "apple" }));
		;
		System.out.println("\nOriginal list (size 3:" + wordList.size() + ")\n[apple, apple, apple]" + "\n" + wordList);
		wordsRemoved = removeWordsContainingKey(wordList, "a");
		System.out.println("\nList after method (size 0:" + wordList.size() + ")\n[]" + "\n" + wordList);
		System.out.println(
				"\nSet of unique removed words (size 1:" + wordsRemoved.size() + ")\n[apple]" + "\n" + wordsRemoved);

		System.out.println();
		wordList.clear();
		System.out.println("\nOriginal list (size 0:" + wordList.size() + ")\n[]" + "\n" + wordList);
		wordsRemoved = removeWordsContainingKey(wordList, "a");
		System.out.println("\nList after method (size 0:" + wordList.size() + ")\n[]" + "\n" + wordList);
		System.out.println(
				"\nSet of unique removed words (size 0:" + wordsRemoved.size() + ")\n[]" + "\n" + wordsRemoved);

		System.out.println("\n******TESTING STACK REVERSE");

		// UN-COMMENT TO TEST YOUR TRIO CLASS
		
		 System.out.println("\n******TESTING TRIO");
		  Trio<Integer> numberTrio = new Trio<Integer>(3, 4, 5);
		 // testing toString
		  System.out.println("Should print a text representation of the Trio that contains 3, 4, 5:");
		  System.out.println(numberTrio);
		 
		  // test getters and setters
		  System.out.println("\nItem 1 should be 3: " + numberTrio.getItem1());
		  System.out.println("Item 2 should be 4: " + numberTrio.getItem2());
		  System.out.println("Item 3 should be 5: " + numberTrio.getItem3()); 
		  numberTrio.setItem1(6); numberTrio.setItem2(7);
		  numberTrio.setItem3(8);
		  System.out.println("Item 1 should be 6: " + numberTrio.getItem1());
		  System.out.println("Item 2 should be 7: " + numberTrio.getItem2());
		  System.out.println("Item 3 should be 8: " + numberTrio.getItem3());
		 
		  // test triplicate method
		  System.out.println("\nTrio is a triplicate? false: " + numberTrio.isATriplicate());
		  numberTrio.setItem2(6);
		  System.out.println("Trio is a triplicate? false: " + numberTrio.isATriplicate());
		  numberTrio.setItem3(6);
		  System.out.println("Trio is a triplicate? true:  " + numberTrio.isATriplicate());
		 // testing with strings
		 Trio<String> wordTrio = new Trio<String>("hello","hello","hello");
		 wordTrio.setItem2("goodbye");
		 wordTrio.setItem3("nice knowing you");
		 System.out.println("\n\nShould print a text representation of the Trio that contains hello, goodbye, and nice knowing you."); 
		 System.out.println(wordTrio);
		 wordTrio.setItem2(new String("hello"));
		 wordTrio.setItem3(new String("hello"));
		 System.out.println("\nTrio is a triplicate? true:  " +
		 wordTrio.isATriplicate());
		 
		/*
		 * un-comment the lines of code below and they should cause a compiler error
		 * because wordTrio should only accept Strings and numberTrio should only accept
		 * integers
		 */
		//numberTrio.setItem2("apple");
		//wordTrio.setItem2(3);

	}

	public static Set<String> removeWordsContainingKey(List<String> list, String key) {
		HashSet<String> set = new HashSet<String>();
		Iterator<String> iterator = list.iterator();

		for (int i = 0; i < list.size(); i++) {

			while (iterator.hasNext()) {

				String token = iterator.next();

				if (token.contains(key)) {

					set.add(token);
					iterator.remove();
				}
			}
		}
		
		return set;
	}

	public static Stack<Integer> createReverseStack(Stack<Integer> originalStack) {

		Stack<Integer> reversedStack = new Stack<Integer>();
		Stack<Integer> tempStack = new Stack<Integer>();
		// tempStack is a placeholder stack to temporarily hold original stack while
		// reversing order,
		// and then transfer back to originalStack in correct order

		while (!originalStack.isEmpty()) {

			tempStack.push(originalStack.peek());
			reversedStack.push(originalStack.pop());

		}

		while (!tempStack.isEmpty()) {
			originalStack.push(tempStack.pop());
		}

		return reversedStack;
	}

}
