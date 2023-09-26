package de.oskar.utils;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class Reader {
    /**
     * Reads a YAML file and returns the object
     * @param <T> The type of the object
     * @param file The file to read
     * @param valueType The type of the object
     * @return The object
     * @throws StreamReadException 
     * @throws DatabindException
     * @throws IOException
     */
    public static <T> T readYAML(File file, Class<T> valueType) throws StreamReadException, DatabindException, IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        T object = mapper.readValue(file, valueType);
        return object;
    }

    /**
     * Reads a YAML file and returns the object
     * @param <T> The type of the object
     * @param file The file to read
     * @param valueType The type of the object
     * @param subpoint  The subpoint of the object
     * @return  The object
     * @throws StreamReadException
     * @throws DatabindException
     * @throws IOException
     */

    public static <T> T readYAML(File file, Class<T> valueType, String subpoint) throws StreamReadException, DatabindException, IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        JsonNode rootNode = mapper.readTree(file);
        JsonNode subpointNode = rootNode.get(subpoint);
        T object = mapper.treeToValue(subpointNode, valueType);
        return object;
    }
}
