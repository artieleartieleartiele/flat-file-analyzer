package com.hicx.solutions;

import com.hicx.solutions.dto.FileStatistics;
import com.hicx.solutions.service.FileStatisticsService;
import com.hicx.solutions.service.parser.FileParserFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Client {
    private static final String PROCESSED_DIR = "/processed";
    String stringPath = "/Users/artiele/Downloads/myfolder";

    public static void main(String[] args) {

        System.out.println("Enter the path to folder...");
        Scanner input = new Scanner(System.in);
        try {
            process(input);
        } catch (IOException e) {
            System.out.println("Exception message: " + e.getMessage());
            System.out.println("Exception cause: " + e.getCause());
            e.printStackTrace();
        }
    }

    private static void process(Scanner input) throws IOException {
        FileParserFactory factory = new FileParserFactory();
        String folderPath = input.next();
        File processedDir = new File(folderPath.concat(PROCESSED_DIR));
        File folder = new File(folderPath);
        if (folder.isDirectory()) {
            File[] listOfFiles = folder.listFiles();
            if (listOfFiles.length < 1) {
                System.out.println("There is no File inside Folder");
            } else {
                for (File file : listOfFiles) {
                    if(!file.isDirectory()) {
                        FileStatisticsService fileService = factory.getFileStatisticsService(file);
                        if (ObjectUtils.isEmpty(fileService)) continue;
                        FileStatistics stats = fileService.process(file);
                        FileUtils.moveFileToDirectory(file, processedDir, true);
                        System.out.println("[Statistics]---" + stats.toString());
                    }
                }
            }
        }
        else System.out .println("There is no Folder @ given path :" + folderPath);
    }
}
