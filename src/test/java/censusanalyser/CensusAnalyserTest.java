package censusanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_TYPE = "./src/test/resources/CensusData.txt";
    private static final String WRONG_CSV_FILE_DELIMITER = "./src/test/resources/CensusDataInvalidDelimiter.csv";
    private static final String WRONG_CSV_FILE_HEADER = "./src/test/resources/CensusDataInvalidHeader.csv";
    private static final String INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
    private static final String STATE_WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCode.csv";
    private static final String STATE_WRONG_CSV_FILE_TYPE = "./src/test/resources/IndianStateCodeData.txt";
    private static final String STATE_WRONG_CSV_FILE_DELIMITER = "./src/test/resources/IndiaStateCodeInvalidDelimiter.csv";
    private static final String STATE_WRONG_CSV_FILE_HEADER = "./src/test/resources/IndiaStateCodeInvalidHeader.csv";
    private static final String US_CENSUS_CSV_FILE_PATH = "src/test/resources/USCensusFile.csv";

    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29,numOfRecords);
        } catch (CensusAnalyserException e) { }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }
    @Test
    public void givenIndianCensusData_WithWrongFileType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_FILE_TYPE, e.type);
        }
    }
    @Test
    public void givenIndianCensusData_WithWrongFileDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,WRONG_CSV_FILE_DELIMITER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_FILE_DELIMITER, e.type);
        }
    }

    @Test
    public void givenIndianCensusData_WithWrongFileHeader_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,WRONG_CSV_FILE_HEADER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_FILE_HEADER, e.type);
        }
    }
    @Test
    public void givenIndiaCensusData_WhenSortedOnState_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            String populationWiseSortedCensusData = censusAnalyser.getPopulationWiseSortedCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH);
            System.out.println(populationWiseSortedCensusData);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(populationWiseSortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(199812341, censusCSV[0].population);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }
   @Test
   public void givenIndianCensusData_withRandomStateNames_ShouldReturnInDensitySortedOrder() {
       try {
           CensusAnalyser censusAnalyser = new CensusAnalyser();
           String densityWiseSortedCensusData = censusAnalyser.getDensityWiseSortedCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH);
           System.out.println(densityWiseSortedCensusData);
           IndiaCensusCSV[] censusCSV = new Gson().fromJson(densityWiseSortedCensusData, IndiaCensusCSV[].class);
           Assert.assertEquals(1102, censusCSV[0].getDensityPerSqKm());
       } catch (CensusAnalyserException e) {
           Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
       }
   }
    public void givenIndianCensusData_withRandomStateNames_ShouldReturnInAreaSortedOrder() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            String areaWiseSortedCensusData = censusAnalyser.getAreaWiseSortedCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH);
            System.out.println(areaWiseSortedCensusData);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(areaWiseSortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(342239, censusCSV[0].getAreaInSqKm());
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }
    @Test
    public void givenIndiaStateCodeCSVFile_ShouldReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(37, numOfRecords);
        } catch (CensusAnalyserException e) {}
    }
    @Test
    public void givenIndiaStateCodeCSV_WhenWrongPath_ShouldReturnException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,STATE_WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }
    @Test
    public void givenIndianStateCodeCSV_WhenWrongType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,STATE_WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_FILE_TYPE, e.type);
        }
    }
    @Test
    public void givenIndiaStateCodeCSV_WhenWrongDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,STATE_WRONG_CSV_FILE_DELIMITER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_FILE_DELIMITER,e.type);
        }
    }
    @Test
    public void givenIndiaStateCodeCSV_WhenWrongHeader_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,STATE_WRONG_CSV_FILE_HEADER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_FILE_HEADER, e.type);
        }
    }
   /*@Test
    public void givenIndianStateCodeCSV_withRandomStateNames_ShouldReturnInStateNameSortedOrder() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            String jsonString =censusAnalyser.getStateWiseSortedCensusData(INDIA_STATE_CODE_CSV_FILE_PATH);
            System.out.println(jsonString);
            IndiaStateCodeCSV[] indiaStateCodeCSV = new Gson().fromJson(jsonString, IndiaStateCodeCSV[].class);
            Assert.assertEquals("AD", indiaStateCodeCSV[0].stateCode);
        } catch (CensusAnalyserException e) {
           e.printStackTrace();
        }
    }*/

    @Test
    public void givenUSCensusCSVFile_ShouldReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadCensusData(CensusAnalyser.Country.US,US_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(51, numOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }
    @Test
    public void givenUSCensusCSVFile_withRandomStateNames_ShouldReturnInNameSortedOrder() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            String stateNameWiseSortedCensusData = censusAnalyser.getStateNameWiseSortedCensusData(CensusAnalyser.Country.US, US_CENSUS_CSV_FILE_PATH);
            System.out.println(stateNameWiseSortedCensusData);
            USCensusCSV[] censusCSV = new Gson().fromJson(stateNameWiseSortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("Alabama", censusCSV[0].getStateName());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void givenTheUSCensusCSVFile_WhenSortedOnStateCode_ShouldReturnSortedList() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            String stateIDWiseSortedCensusData = censusAnalyser.getStateIDWiseSortedCensusData(CensusAnalyser.Country.US, US_CENSUS_CSV_FILE_PATH);
            System.out.println(stateIDWiseSortedCensusData);
            USCensusCSV[] censusCSV = new Gson().fromJson(stateIDWiseSortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("AK", censusCSV[0].getStateID());
            Assert.assertEquals("WY",censusCSV[50].getStateID());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenTheUSCensusCSVFile_WhenSortedOnArea_ShouldReturnSortedList(){
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            String stateAreaWiseSortedCensusData = censusAnalyser.getStateAreaWiseSortedCensusData(CensusAnalyser.Country.US, US_CENSUS_CSV_FILE_PATH);
            System.out.println(stateAreaWiseSortedCensusData);
            USCensusCSV[] censusCSV = new Gson().fromJson(stateAreaWiseSortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("104655.80", censusCSV[0].getArea());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void givenUSCensusCSVFile_WhenSortedPopulousDensity_ShouldReturnSortedList() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            String stateDensityWiseSortedCensusData = censusAnalyser.getStateDensityWiseSortedCensusData(CensusAnalyser.Country.US, US_CENSUS_CSV_FILE_PATH);
            System.out.println(stateDensityWiseSortedCensusData);
            USCensusCSV[] censusCSV = new Gson().fromJson(stateDensityWiseSortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("0.46", censusCSV[0].getPopulationDensity());
            Assert.assertEquals("92.32", censusCSV[50].getPopulationDensity());
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void givenIndiaCensusData_WhenSortedOnPopulousDensity_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            String populousDensityWiseSortedCensusData = censusAnalyser.getPopulousDensityWiseSortedCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH);
            System.out.println(populousDensityWiseSortedCensusData);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(populousDensityWiseSortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(50, censusCSV[0].getDensityPerSqKm());
            Assert.assertEquals(1102, censusCSV[28].getDensityPerSqKm());
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }
}
