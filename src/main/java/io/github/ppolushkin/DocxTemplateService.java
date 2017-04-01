package io.github.ppolushkin;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Pavel Polushkin
 * 26.03.2017.
 */
@Component
public class DocxTemplateService {

    public void fillTemplate() throws InvalidFormatException, IOException {
        XWPFDocument doc = open("/template.docx");
        replaceText(doc, "$USER", "my Friend");
        save(doc, new File("C:\\TEMP\\output.docx"));
    }

    private XWPFDocument open(String resourcePath) throws InvalidFormatException, IOException {
        InputStream is = this.getClass().getResourceAsStream(resourcePath);
        return new XWPFDocument(is);
    }

    private XWPFDocument replaceText(XWPFDocument doc, String findText, String replaceText) {
        for (XWPFParagraph p : doc.getParagraphs()) {
            for (XWPFRun run : p.getRuns()) {
                String text = run.text();
                if (text != null && text.contains(findText)) {
                    run.setText(text.replace(findText, replaceText), 0);
                }
            }
        }
        for (XWPFTable tbl : doc.getTables()) {
            for (XWPFTableRow row : tbl.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph p : cell.getParagraphs()) {
                        for (XWPFRun r : p.getRuns()) {
                            String text = r.getText(0);
                            if (text.contains(findText)) {
                                r.setText(text.replace(findText, replaceText), 0);
                            }
                        }
                    }
                }
            }
        }
        return doc;
    }

    private void save(XWPFDocument document, File file) throws IOException {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            document.write(out);
        } finally {
            if (out != null)
                out.close();
        }
    }

}
