package com.mcmiddleearth.mcmescripts.bossbattle.unused;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcmiddleearth.mcmescripts.bossbattle.BossBattle;

import java.io.File;
import java.io.IOException;

public class BossBattleReader {

    public static BossBattle readBossBattle(File sourceFile) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,false);

        return mapper.readValue(sourceFile, BossBattle.class);
    }
}
