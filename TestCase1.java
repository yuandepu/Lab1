import static org.junit.Assert.*;
import org.junit.Test;
public class TestCase1 {

	@Test
	public void test() {
		project calcuator=new project();
		calcuator.ShowDirectedGraph("D:/1.txt/");
		String result=calcuator.QueryBridgeWords("went","the");
		assertEquals("to ",result);
	}
}
