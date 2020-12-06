import com.mashibing.tank.ResourceMngr;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertNotNull;

public class ImageTest {
    @Test
    public void test() throws IOException {
        InputStream inputStream = ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif");
        ImageIO.read(inputStream);
        }
    }


