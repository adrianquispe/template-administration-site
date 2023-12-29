import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.proyecto.model.socio.Socio;
import java.time.LocalDate;

public class TestSocio {

    private Socio socio;

    @BeforeEach
    void setUp() {
        socio = new Socio();
    }

    @Test
    void testActivo() {
        assertTrue(socio.getActivo());
    }

    @Test
    void testDenominacion() {
        socio.setDenominacion("Mi Denominación");
        assertEquals("Mi Denominación", socio.getDenominacion());
    }

    @Test
    void testFechaDeAsociacion() {
        LocalDate fecha = LocalDate.now();
        socio.setFechaDeAsociacion(fecha);
        assertEquals(fecha, socio.getFechaDeAsociacion());
    }

}

