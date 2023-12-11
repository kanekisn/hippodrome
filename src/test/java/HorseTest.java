
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class HorseTest {
    @Test
    public void constructorReceivedNullTest(){
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 0)
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "      "})
    public void constructorReceivedEmptyStrTest(String source){
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(source, 0)
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }
    @Test
    public void constructorReceivedNegSecondParamTest(){
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Name", -1)
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }
    @Test
    public void constructorReceivedNegThirdParamTest(){
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Name", 0, -1)
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }
    @Test
    public void getNameTest(){
        String param = "Name";
        Horse horse = new Horse(param, 0);
        assertEquals(param, horse.getName());
    }
    @Test
    public void getSpeedTest(){
        double param = 0d;
        Horse horse = new Horse("Name", param);
        assertEquals(param, horse.getSpeed());
    }
    @Test
    public void getDistanceTest(){
        double param = 1d;
        Horse horse = new Horse("Name", param, param);
        assertEquals(param, horse.getDistance());
        assertEquals(0d, new Horse("Name", 0).getDistance());
    }
    @Test
    public void moveTest(){
        try(MockedStatic<Horse> horse = Mockito.mockStatic(Horse.class)){
            new Horse("Name", 1, 2).move();
            horse.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.3})
    public void moveIfMethodCalculateCorrect(double fakeVal){
        Horse hor = new Horse("Name", 0.1, 0.5);
        double expectedVal = 0.5 + 0.1 * fakeVal;
        try(MockedStatic<Horse> horse = Mockito.mockStatic(Horse.class)){
            horse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(fakeVal);
            hor.move();
        }
        assertEquals(expectedVal, hor.getDistance());
    }
}
