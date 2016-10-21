package se254.a4.q1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.*;

/**
 * SE254 Assignment 4 Question 1 Main. This class should be implemented to contain the functionality described in the
 * assignment handout. you may implement additional classes if you wish, but this class should be the main program entry
 * point.
 * @author: elee353, Mike Lee.
 */
public class Q1Main {
	static Class<?> clazz;
	static Object o;
	public static void main(String[] args) throws IOException {

		String[] tempArry = new String[100];
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		boolean repeatFlag = true;

		while (repeatFlag) {
			try {
				//while (repeatFlag) {
				//se254.a4.q1.Counter
				System.out.println("Full Class Name?");
				tempArry[0] = br.readLine();

				repeatFlag = false;
				clazz = Class.forName(tempArry[0]);
				//}			
				o = clazz.newInstance();

				printField(clazz, o);

				printMethod(clazz);

				// Let the user execute methods
				while (true) {
					System.out.println("\nType a method to run:\tOr \"q\" to Quit");

					String temp = br.readLine(); 
					if (temp.equals("q")) {
						break;
					}

					Method tempMethod;
					try {
						tempMethod = clazz.getDeclaredMethod(temp);

						String result = (String) tempMethod.invoke(o);

					} catch (NoSuchMethodException e) {
						System.out.println(e.getMessage()+" doesn't exist!!!");
					}

					printField(clazz, o);

					printMethod(clazz);
				}


			} catch ( InstantiationException | IllegalAccessException |  SecurityException | IllegalArgumentException | InvocationTargetException e ) {
				e.printStackTrace();
			} catch ( ClassNotFoundException cnfe) {
				repeatFlag = true;
				System.out.println(cnfe.getMessage()+" doesn't exist!!!");
			}
		}
	}

	private static void printField(@SuppressWarnings("rawtypes") Class clazz, Object o) throws IllegalArgumentException, IllegalAccessException {

		Field[] fieldArray = clazz.getFields();

		// Print field name and values	
		System.out.println("Fields:");	
		for (Field f : fieldArray) {
			if (Modifier.isPublic(f.getModifiers())) {
				System.out.println(f.toString()+" = "+f.get(o));	
			}
		}
	}

	private static void printMethod(@SuppressWarnings("rawtypes") Class clazz) {
		// Print public methods without arguments
		System.out.println("\nPublic methods without arguments:");
		Method[] methodArray = clazz.getDeclaredMethods();

		for (Method m : methodArray) {
			if (m.getParameterCount() == 0) {
				if (Modifier.isPublic(m.getModifiers())) {
					System.out.println(m.toGenericString() );
				}
			}
		}
	}
}