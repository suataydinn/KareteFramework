package examples;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class ParallelTest {

    @BeforeAll
    static void cleanUp() throws IOException {
        Path reportDir = Paths.get("target");
        if (Files.exists(reportDir)) {
            Files.walk(reportDir)
                    .filter(path -> path.toString().contains("karate-reports_"))
                    .forEach(path -> deleteDirectoryRecursively(path.toFile()));
        }
    }

    private static void deleteDirectoryRecursively(File file) {
        if (file.isDirectory()) {
            File[] contents = file.listFiles();
            if (contents != null) {
                for (File f : contents) {
                    deleteDirectoryRecursively(f);
                }
            }
        }
        file.delete();
    }

    @Test
    void testParallel() {
        Results results = Runner.path("classpath:examples")
                .parallel(5);
        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }
}
