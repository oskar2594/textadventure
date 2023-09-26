package de.oskar.world.objects.dialogue;

import java.util.LinkedList;

import com.fasterxml.jackson.databind.JsonNode;

import de.oskar.utils.Pair;

public class DialogueBuilder {

    /**
     * Builds a dialogue from a json node
     * @param jsonNode The json node
     * @return The dialogue
     */
    public static Sequence buildSequence(JsonNode jsonNode) {
        String text = jsonNode.get("text").asText();
        boolean isEnd = jsonNode.has("end") ? jsonNode.get("end").asBoolean() : false;
        LinkedList<Pair<String, Sequence>> options = new LinkedList<>();
        if (jsonNode.has("options")) {
            for (JsonNode option : jsonNode.get("options")) {
                options.add(new Pair<String, Sequence>(option.get("name").asText(), buildSequence(option.get("sequence"))));
            }
        }
        return new Sequence(text, isEnd, options);        
    }

}
