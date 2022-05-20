package com.sii.sii_recruitment_task;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileHandler {
    File f;
    FileOutputStream fStream;

    public void openAndCreateIfNotExists() throws IOException{
        f = new File("powiadomienia.txt");
        fStream = new FileOutputStream(f, true);
    }
    public void writeToFile(String content) throws IOException{
        fStream.write(content.getBytes(StandardCharsets.UTF_8));
        fStream.write("\n".getBytes(StandardCharsets.UTF_8));
    }
    public void close() throws IOException{
        fStream.close();
    }
}
