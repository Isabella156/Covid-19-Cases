package comp1721.cwk1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CovidDataset {
    // date filed
    private ArrayList <CaseRecord> cases;
    // constructor
    public CovidDataset (){
        this.cases = new ArrayList<CaseRecord>();
    }


    // TODO: Write stub for size()
    public int size(){
        return cases.size();
    }

    // TODO: Write stub for getRecord()
    public CaseRecord getRecord(int index) throws DatasetException{
        if(index < 0 || index >= cases.size()){
            throw new DatasetException("Index must be larger or equal than zero");
        }else{
            return cases.get(index);
        }
    }

    // TODO: Write stub for addRecord()
    public void addRecord(CaseRecord rec) throws DatasetException{
        cases.add(rec);
    }

    // TODO: Write stub for dailyCasesOn()
    public CaseRecord dailyCasesOn(LocalDate day) throws DatasetException{
        int i = 0;
        while(i < cases.size()){
            if(cases.get(i).getDate() == day){
                break;
            }
            i ++;
        }
        if(i == cases.size()){
            throw new DatasetException("No such cases!");
        }
        return cases.get(i);
    }

    // TODO: Write stub for readDailyCases()
    public void readDailyCases(String filename) throws IOException {
        // find the file according to the path and filename
        File csv = new File( filename);
        // throw the FileNotFoundException if the csv file does not exist
        if(! csv.exists()){
            throw new FileNotFoundException();
        }else{
            // clear all the data in the CovidDataset
            cases.clear();
            // read text from the character input stream, buffering individual characters
            BufferedReader textFile = new BufferedReader(new FileReader(csv));
            // read the header of the csv file
            String lineData = textFile.readLine();
            // read the data line by line
            while((lineData = textFile.readLine()) != null){
                // create a new string list to store the data
                String[] data =  lineData.split(",");
                // throw DatasetException if the column is missing
                if(data.length != 4){
                    throw new DatasetException("Missing columns!");
                }
                // format the local date
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(data[0], formatter);
                int staff = Integer.parseInt(data[1]);
                int student = Integer.parseInt(data[2]);
                int other = Integer.parseInt(data[3]);
                CaseRecord rec = new CaseRecord(date, staff, student, other);
                if(!cases.add(rec)){
                    throw new DatasetException("Dataset is too small!");
                }

            }
        }
    }

    // TODO: Write stub for writeActiveCases()
    public void writeActiveCases(String filename) throws IOException {
        // set the output file path and pass the parameter
        File writeFile = new File(filename);
        // create a buffered character output
        BufferedWriter writeText = new BufferedWriter(new FileWriter(writeFile));
        if(cases.size() < 10){
            throw new DatasetException("The number of CaseRecord is less than ten!");
        }else{
            int staff = 0;
            int student = 0;
            int other = 0;
            writeText.write("Date,Staff,Students,Other");
            for(int i = 9; i < cases.size(); i++){
                staff = 0;
                student = 0;
                other = 0;
                for(int j = i; j >= i - 9; j --){
                    staff += cases.get(j).getStaffCases();
                    student += cases.get(j).getStudentCases();
                    other += cases.get(j).getOtherCases();
                }
                LocalDate date = cases.get(i).getDate();
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String dateStr = date.format(fmt);
                writeText.newLine();
                writeText.write(dateStr + "," + staff + "," + student + "," + other);
            }
            writeText.flush();
            writeText.close();
        }
    }

}
