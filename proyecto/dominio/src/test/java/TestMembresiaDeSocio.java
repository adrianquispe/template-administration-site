import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import com.proyecto.model.socio.membresiaDeSocio.MembresiaDeSocio;
import com.proyecto.model.socio.membresiaDeSocio.Membresia;
public class TestMembresiaDeSocio {

    @Test
    public void testToString() {

        Membresia membresia = new Membresia();
        MembresiaDeSocio membresiaDeSocio = new MembresiaDeSocio(100.0, 12, LocalDate.now(), 30, 10, membresia);

        String expected = "MembresiaDeSocio{cuotaSocal=100.0, duracion=12, fechaDeAsociacion=" + LocalDate.now() +
                ", plazoAntesDeVencer=30, diasParaVencer=10, membresia=" + membresia + '}';

        assertEquals(expected, membresiaDeSocio.toString());
    }
}
