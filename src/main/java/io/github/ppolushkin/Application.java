package io.github.ppolushkin;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private DocxTemplateService docxTemplateService;

    @Autowired
    private ExcelReadService excelReadService;

    @Override
    public void run(String... args) throws IOException, InvalidFormatException {
//        docxTemplateService.fillTemplate();
        excelReadService.read();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}