import baltinfocom.BaltInfoComApplication;
import org.junit.Test;

public class Test1 {

    @Test
    public void test() {
        BaltInfoComApplication b = new BaltInfoComApplication("C://lng.csv", "D://text.txt");
        b.start();
    }
}