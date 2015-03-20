package mapComponents;

/**
 * Exception intended to signify invalid path creation, assignment or manipulation
 * @author Vlad
 *
 */
public class PathException extends Exception{
	public PathException(String s){
		super("Custom Exception: " + s);
	}
}