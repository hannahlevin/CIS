import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class HomeworkM15Driver {

	public static void main(String[] args) {

		System.out.println("*********************TESTING ARRAY SORTEDNESS METHOD");
		Integer[] array1 = { 1, 2, 3, 3, 5 };
		Integer[] array2 = { 1, 2, 3, 4, 5, 5 };
		Integer[] array3 = { 10, 8, 5, 3, 1 };
		Integer[] array4 = { 12, 11, 9, 3, 2, 1 };
		Integer[] array5 = { 2, 8, 3, 9, 6 };
		Integer[] array6 = { 7, 1, 6, 8, 5, 2 };
		String[] array7 = { "abc", "def", "ghi" };
		String[] array8 = { "cat", "apple", "dog", "elephant" };
		String[] array9 = { "blue" };
		String[] array10 = {};

		NumberFormat formatter = NumberFormat.getPercentInstance();
		System.out.println("Sortedness: Odd Length, Sorted\t\t100% \t" + formatter.format(sortedness(array1)));
		System.out.println("Sortedness: Even Length, Sorted\t\t100% \t" + formatter.format(sortedness(array2)));
		System.out.println("Sortedness: Odd Length, Unsorted\t0% \t" + formatter.format(sortedness(array3)));
		System.out.println("Sortedness: Even Length, Unsorted\t0% \t" + formatter.format(sortedness(array4)));
		System.out.println("Sortedness: Odd Length, Partial Sort\t50% \t" + formatter.format(sortedness(array5)));
		System.out.println("Sortedness: Even Length, Partial Sort\t40% \t" + formatter.format(sortedness(array6)));
		System.out.println("Sortedness: String, Odd Length\t\t100% \t" + formatter.format(sortedness(array7)));
		System.out.println("Sortedness: String, Even Length\t\t67% \t" + formatter.format(sortedness(array8)));
		System.out.println("Sortedness: Singleton\t\t\t100% \t" + formatter.format(sortedness(array9)));
		System.out.println("Sortedness: Empty\t\t\t100% \t" + formatter.format(sortedness(array10)));

		System.out.println("\n*********************TESTING LINEAR DUPLICATE METHOD");
		runDuplicatesMethods();

	}

	public static double sortedness(Comparable[] array) {
		int numberOfMatches = 0;

		if (array.length <= 1) {
			return 1;
		}

		for (int i = 0; i < array.length - 1; i++) {
			int comparison = array[i].compareTo(array[i + 1]);
			if (comparison <= 0) {
				numberOfMatches++;
			}
		}
		double percentage = (double) (numberOfMatches) / (array.length - 1);
		return percentage;

	}

	///
	// I recommend changing these to smaller numbers to start with!
	// Then once you think you have a good solution, you can increase them for more
	/// robust testing.
	public static final int LIST_SIZE = 100;
	public static final int SINGLE_DUPLICATE_NUMBERS = 10; // minimum number of numbers on the list that will appear
															// twice (have a "single duplicate"
	public static final int DOUBLE_DUPLICATE_NUMBERS = 5; // minimum number of numbers on the list that will appear
															// three times (have a "duplicate duplicate")

	public static void runDuplicatesMethods() {
		// set up an Integer list that is guaranteed to have some single and double
		// duplicates
		List<Integer> numbers = new ArrayList<>();
		Random generator = new Random();
		int max = 5 * LIST_SIZE;
		int min = 0;
		int randomRange = max - min + 1;

		// add the first round of numbers
		int originalNumbersToAdd = LIST_SIZE - SINGLE_DUPLICATE_NUMBERS - DOUBLE_DUPLICATE_NUMBERS;
		numbers.add(min); // adding the min and max number to help test for array out of bounds errors
		numbers.add(min);
		numbers.add(max);
		numbers.add(max);
		originalNumbersToAdd -= 4;
		for (int i = 0; i < originalNumbersToAdd; i++) {
			numbers.add(generator.nextInt(randomRange) + min);
		}

		// add duplicate numbers
		Collections.shuffle(numbers);
		for (int i = 0; i < SINGLE_DUPLICATE_NUMBERS; i++) {
			numbers.add(numbers.get(i));
		}
		for (int i = 0; i < DOUBLE_DUPLICATE_NUMBERS; i++) {
			numbers.add(numbers.get(i));
		}

		// print the list sorted (might help with testing)
		Collections.sort(numbers);
		System.out.println("The original list:");
		System.out.println(numbers);
		Collections.shuffle(numbers);

		// the results should be the same for both methods
		System.out.println("\nThe duplicate lists from both methods- these should match!");
		List<Integer> duplicateList = findDuplicatesBad(numbers);
		Collections.sort(duplicateList);
		System.out.println(duplicateList);

		duplicateList = findDuplicatesLinear(numbers);
		Collections.sort(duplicateList);
		System.out.println(duplicateList);

	}

	public static List<Integer> findDuplicatesBad(List<Integer> numbers) {
		List<Integer> duplicateList = new ArrayList<Integer>();
		for (int i = 0; i < numbers.size(); i++) {
			int numberEvaluating = numbers.get(i);
			boolean duplicateFound = false;

			for (int j = i + 1; j < numbers.size() && !duplicateFound; j++) {
				int numberChecking = numbers.get(j);

				if (numberEvaluating == numberChecking && !duplicateList.contains(numberEvaluating)) {
					duplicateFound = true;

					for (int k = j; k < numbers.size(); k++) {
						if (numberChecking == Integer.valueOf(numbers.get(k))) {
							duplicateList.add(numberChecking);
						}
					}
				}
			}
		}
		return duplicateList;
	}

	public static List<Integer> findDuplicatesLinear(List<Integer> numbers) {
		List<Integer> duplicateList = new ArrayList<Integer>();
		Collections.sort(numbers);
		int currentNumber;
		for (int i = 0; i < numbers.size() - 1; i++) {
			currentNumber = numbers.get(i);
			if (currentNumber == numbers.get(i + 1)) {
				duplicateList.add(currentNumber);
			}

		}

		return duplicateList;
	}

}
