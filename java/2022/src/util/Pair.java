package util;


/**Simple pair class to store two objects.*/
public class Pair<T, U> {
	
	/*******************/
	/*    Variables    */
	/*******************/
	
	/**First object.*/
	public T first;
	/**Second object.*/
	public U second;
	

	/*********************/
	/*    Constructor    */
	/*********************/
	
	/**Creates a new pair with the given objects.
	 * @param first The first object.
	 * @param second The second object.*/
	public Pair(T first, U second) {
		
		//init
		
		this.first = first;
		this.second = second;
		
	}


	public boolean equalsPair(Pair<T, U> other) {
		return first.equals(other.first) && second.equals(other.second);
	}

}
