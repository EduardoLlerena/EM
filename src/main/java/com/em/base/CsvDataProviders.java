package com.em.base;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.testng.annotations.DataProvider;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class CsvDataProviders {

    @DataProvider(name = "csvReader")
    public static Iterator<Object[]> csvReader(Method method) {
        List<Object[]> listLines = new ArrayList<Object[]>();
        String pathname = "src" + File.separator + "test" + File.separator + "resources" + File.separator
                + "dataproviders" + File.separator + method.getDeclaringClass().getSimpleName() + File.separator
                + method.getName() + ".csv";
        File file = new File(pathname);
        try {
            CSVReader reader = new CSVReader(new FileReader(file));

            //Reads first row, with the headers
            String[] keys = reader.readNext();
            if (keys != null) {
                String[] dataParts;
                while ((dataParts = reader.readNext()) != null) {
                    Map<String, String> testData = new HashMap<String, String>();
                    for (int i = 0; i < keys.length; i++) {
                        testData.put(keys[i], dataParts[i]);
                    }
                    listLines.add(new Object[] { testData });
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File " + pathname + " was not found.\n" + e.getStackTrace().toString());
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException("Could not read " + pathname + " file.\n" + e.getStackTrace().toString());
        }

        return listLines.iterator();
    }

}
