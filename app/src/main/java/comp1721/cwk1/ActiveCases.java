package comp1721.cwk1;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ActiveCases {
    public static void main(String[] args) throws IOException {
        // create the dataset
        if(args.length != 2){
            System.out.println("Please enter two filename!");
            System.exit(1);
        }
        CovidDataset dataset = new CovidDataset();
        try {
            dataset.readDailyCases(args[0]);
        }catch (FileNotFoundException e){
            System.out.println("No such file");
            System.exit(1);
        }
        try{
            // write the active cases from the file to the dataset
            dataset.writeActiveCases(args[1]);
        }catch (IOException e){
            System.out.println("Cannot write the data");
            System.exit(1);
        }
        // print the number of records in the CovidDataset
        System.out.println(dataset.size());
    }
}
