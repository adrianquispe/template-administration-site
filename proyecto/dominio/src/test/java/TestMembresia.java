import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.proyecto.model.socio.membresiaDeSocio.Membresia;
public class TestMembresia {

    private Membresia membresia;

    @BeforeEach
    public void setUp() {
        membresia = new Membresia();
    }

    @Test
    public void testNombreNoEsNulo() {
        membresia.setNombre(null);
        assertThrows(NullPointerException.class, () -> membresia.getNombre());
    }

    @Test
    public void testNombreNoEsVacio() {
        membresia.setNombre("");
        assertThrows(IllegalArgumentException.class, () -> membresia.getNombre());
    }

    @Test
    public void testNombreValido() {
        membresia.setNombre("Membresía de Prueba");
        assertEquals("Membresía de Prueba", membresia.getNombre());
    }
}