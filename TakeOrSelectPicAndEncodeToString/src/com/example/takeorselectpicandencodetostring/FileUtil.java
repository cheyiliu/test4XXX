package com.example.takeorselectpicandencodetostring;

import java.io.File;

public class FileUtil {

    /**
     * Note, the delete file the file can be a directory
     * 
     * @param file
     */
    public static void deleteFile(File file) {
        if (file == null) {
            return;
        }
        if (file.exists()) {
            if (file.isDirectory()) {
                File files[] = file.listFiles();
                if (files == null)
                    return;
                for (File f : files) {
                    f.delete();
                }
            }
            file.delete();
        }
    }
}
