package censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public abstract class CensusAdapter {
    public abstract Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws CensusAnalyserException;
    public Map<String, CensusDAO> csvFileMap = new HashMap();
    public <T> Map<String, CensusDAO> loadCensusData(Class<T> censusCSVClass, String csvFilePath) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            OpenCSVBuilder openCSVBuilder = new OpenCSVBuilder();
            Iterator<T> csvFileIterator = openCSVBuilder.getCSVFileIterator(reader, censusCSVClass);
            Iterable<T> csvFileIterable = () -> csvFileIterator;
            if (censusCSVClass.getName().contains("IndiaCensusCSV"))
            {
                while (csvFileIterator.hasNext()) {
                    CensusDAO censusDAO = new CensusDAO((IndiaCensusCSV) csvFileIterator.next());
                    csvFileMap.put(censusDAO.getState(), censusDAO);
                }
              StreamSupport.stream(csvFileIterable.spliterator(), false)
                       .map(IndiaCensusCSV.class::cast)
                        .forEach(censusCSV -> csvFileMap.put(censusCSV.getState(), new CensusDAO(censusCSV)));
            } else if (censusCSVClass.getName().contains("USCensusCSV"))
            {
                StreamSupport.stream(csvFileIterable.spliterator(), false)
                        .map(USCensusCSV.class::cast)
                        .forEach(censusCSV -> csvFileMap.put(censusCSV.getStateName(), new CensusDAO(censusCSV)));
            }
            return csvFileMap;
        } catch (RuntimeException e) {
            e.printStackTrace();
           /* if (e.getMessage().contains("header!"))
                throw new CensusAnalyserException("Invalid Header", CensusAnalyserException.ExceptionType.WRONG_FILE_HEADER);
            throw new CensusAnalyserException("Invalid Delimiters", CensusAnalyserException.ExceptionType.WRONG_FILE_DELIMITER);*/
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(),CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        return csvFileMap;
    }
}
