package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CensusDAO {
    private String srNo;
    private String state;
    private String stateNameOfIndia;
    private int population;
    private int areaInSqKm;
    private int densityPerSqKm;
    private String stateCode;
    private String tin;
    private String stateID;
    private String stateName;
    private String populationDensity;
    private String USPopulation;
    private String area;
    private String housingUnits;
    private String waterArea;
    private String landArea;
    private String housingDensity;

    public CensusDAO(IndiaCensusCSV indiaCensusCSV) {
        this.state = indiaCensusCSV.state;
        this.population = indiaCensusCSV.population;
        this.areaInSqKm = indiaCensusCSV.areaInSqKm;
        this.densityPerSqKm = indiaCensusCSV.densityPerSqKm;
    }

    public CensusDAO(IndiaStateCodeCSV indiaStateCodeCSV) {
        this.srNo = indiaStateCodeCSV.srNo;
        this.stateNameOfIndia = indiaStateCodeCSV.stateNameOfIndia;
        this.stateCode = indiaStateCodeCSV.stateCode;
        this.tin = indiaStateCodeCSV.tin;
    }

    public CensusDAO(USCensusCSV usCensusCSV) {
        this.stateID = usCensusCSV.getStateID();
        this.stateName = usCensusCSV.getStateName();
        this.USPopulation = usCensusCSV.getUSPopulation();
        this.area=usCensusCSV.getArea();
        this.populationDensity=usCensusCSV.getPopulationDensity();
        this.housingDensity = usCensusCSV.getHousingDensity();
    }

    public String getSrNo() {
        return srNo;
    }

    public void setSrNo(String srNo) {
        this.srNo = srNo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getAreaInSqKm() {
        return areaInSqKm;
    }

    public void setAreaInSqKm(int areaInSqKm) {
        this.areaInSqKm = areaInSqKm;
    }

    public int getDensityPerSqKm() {
        return densityPerSqKm;
    }

    public void setDensityPerSqKm(int densityPerSqKm) {
        this.densityPerSqKm = densityPerSqKm;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }
    public String getStateID() {
        return stateID;
    }

    public void setStateID(String stateID) {
        this.stateID = stateID;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getPopulationDensity() {
        return populationDensity;
    }

    public void setPopulationDensity(String populationDensity) {
        this.populationDensity = populationDensity;
    }

    public String getUSPopulation() {
        return USPopulation;
    }

    public void setPopulation(String USPopulation) {
        this.USPopulation = USPopulation;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getHousingUnits() {
        return housingUnits;
    }

    public void setHousingUnits(String housingUnits) {
        this.housingUnits = housingUnits;
    }

    public String getWaterArea() {
        return waterArea;
    }

    public void setWaterArea(String waterArea) {
        this.waterArea = waterArea;
    }

    public String getLandArea() {
        return landArea;
    }

    public void setLandArea(String landArea) {
        this.landArea = landArea;
    }

    public String getHousingDensity() {
        return housingDensity;
    }

    public void setHousingDensity(String housingDensity) {
        this.housingDensity = housingDensity;
    }

}
