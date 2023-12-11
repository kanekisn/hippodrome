import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HippodromeTest {
    public static List<Horse> initializeHorses(){
        List<Horse> list = new ArrayList<>();
        for(int i = 0; i < 31; i++){
            list.add(new Horse(String.format("Horse #%d", i), 0.1 + i, 0.1 + i));
        }
        return list;
    }

    @Test
    public void constructorReceivedNullTest(){
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null)
        );
        assertEquals("Horses cannot be null.", exception.getMessage());
    }
    @Test
    public void constructorReceivedEmptyListTest(){
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>())
        );
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }
    @Test
    public void getHorsesTest(){
        List<Horse> list = initializeHorses();
        Hippodrome hippodrome = new Hippodrome(list);
        assertEquals(list, hippodrome.getHorses());
    }
    @Test
    public void moveInitializedForAllHorses(){
        List<Horse> list = new ArrayList<>();
        for(int i = 0; i < 51; i++){
            list.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(list);
        hippodrome.move();

        for(Horse horse : list){
            Mockito.verify(horse, Mockito.times(1)).move();
        }
    }
    @Test
    public void getWinnerTest(){
        List<Horse> list = new ArrayList<>();
        list.add(new Horse("H1", 0.1, 2.0));
        list.add(new Horse("H2", 0.2, 1.0));
        list.add(new Horse("H3", 0.2, 10.0));
        Hippodrome hippodrome = new Hippodrome(list);
        assertEquals("H3", hippodrome.getWinner().getName());
    }
}
