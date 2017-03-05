import acm.program.ConsoleProgram;
import acm.util.*;
import java.util.*;

public class testtest extends ConsoleProgram implements NameSurferConstants {
	public void run(){
		NameSurferEntry test = new NameSurferEntry ("sam 10 12 13 141 15 16 17 18 19 300 29");
		println (test.toString());
		//println (test.getName());
		//println (test.getRank(2000));
	}
}
