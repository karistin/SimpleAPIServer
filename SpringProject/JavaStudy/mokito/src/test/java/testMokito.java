import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * packageName    : PACKAGE_NAME
 * fileName       : testMokito
 * author         : lucas
 * date           : 2022-11-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-23        lucas       최초 생성
 */
public class testMokito {

    @Test
    public void mockTesting(){
        List mockedList = mock(List.class);

        mockedList.add("one");
        mockedList.clear();

        verify(mockedList).add("one");
        verify(mockedList).clear();

        when(mockedList.get(0)).thenReturn("first");

        assertEquals("first", mockedList.get(0));

        assertNull(mockedList.get(999));
    }
}
