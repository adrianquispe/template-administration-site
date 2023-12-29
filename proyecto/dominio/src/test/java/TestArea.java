import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.proyecto.model.Area.Area;
public class TestArea {

    private Area area;

    @Before
    public void setUp() {
        area = new Area();
    }

    @Test
    public void testGetNombre() {
        assertEquals("EjemploNombre", area.getNombre());
    }

    @Test
    public void testConstructorVacio() {
        Area areaVacia = new Area();
        assertNull(areaVacia.getNombre());
    }
}

