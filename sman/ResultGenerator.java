
// Java program to illustrate 
// for Writing Data in CSV file 
import java.io.*;
import java.util.*;
import com.opencsv.CSVWriter;

public class ResultGenerator {

	public static void main(String[] args) {
		// addDataToCSV( review, String [] user, String[] date, String[] usage,String[]
		// rating);
		System.out.println("Let it begin");
	}

	public static void addDataToCSV(String company, String model, String[] review, String[] user, String[] date,
			String[] usage, String[] rating) {
		// first create file object for file placed at location
		// specified by filepath
		File file = new File("review/" + company + "_" + model + ".csv");
		try (FileWriter outputfile = new FileWriter(file)) {
			// create FileWriter object with file as parameter

			outputfile.append("Company;Model;Date;User;Rating;Usage;Review");
			outputfile.append("\n");
			System.out.println("Enter Data");
			for (int i = 0; i < review.length; i++) {
				outputfile.append(company + ";" + model + ";" + date[i] + ";" + user[i] + ";" + rating[i] + ";"
						+ usage[i] + ";" + review[i]);

			}
			System.out.println("ALL DONE");
			outputfile.close();

			// closing writer connection

		} catch (IOException e) {
			//
			e.printStackTrace();
		}
	}
}
