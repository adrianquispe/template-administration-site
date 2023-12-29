import com.proyecto.model.socio.Rol;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.proyecto.model.usuario.Usuario;

public class TestUsuario {

    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        usuario = new Usuario();
    }

    @Test
    public void testSetNombreUsuario() {
        usuario.setNombreUsuario("usuario1");
        Assertions.assertEquals("usuario1", usuario.getNombreUsuario());
    }

    @Test
    public void testSetContrasenia() {
        usuario.setContrasenia("password123");
        Assertions.assertEquals("password123", usuario.getContrasenia());
    }

    @Test
    public void testSetRol() {
        usuario.setRol(Rol.ADMINISTRADOR);
        Assertions.assertEquals(Rol.ADMINISTRADOR, usuario.getRol());
    }
}