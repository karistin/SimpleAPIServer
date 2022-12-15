import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import config.ApplicationConfig;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;


/**
 * packageName    : PACKAGE_NAME fileName       : dummySettingTest author         : lucas date
 *     : 2022-12-15 description    : ===========================================================
 * DATE              AUTHOR             NOTE
 * ----------------------------------------------------------- 2022-12-15        lucas       최초 생성
 */
public class dummySettingTest {

    private static final String settingName = "./dummy.yml";

    private static final Path path = Paths.get(settingName);

    @Test
    public void settingTest() throws IOException {
        if (!Files.exists(path)) {
            Files.createFile(path);
        }

        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        ApplicationConfig config = objectMapper.readValue(path.toFile(), ApplicationConfig.class);
        System.out.println("Application config info " + config.toString());
    }
}
