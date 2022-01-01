package output;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;

public final class JsonWriter {
    private final String outputFile;

    public JsonWriter(String outputFile) {
        this.outputFile = outputFile;
    }

    /**
     * Saves the output data in a json file
     * @param output
     * @throws IOException
     */
    public void writeOutput(final Output output) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        objectWriter.writeValue(new File(outputFile), output);
    }
}
