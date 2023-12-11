import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Disabled;

public class MainTest {
    @Test
    @Disabled
    @Timeout(value = 22)
    public void mainTimeoutTest() throws Exception{
        Main.main(new String[]{});
    }
}
