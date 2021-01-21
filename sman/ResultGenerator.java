
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

	public static void addDataToCSV(String[] review, String[] user, String[] date, String[] usage, String[] rating) {
		// first create file object for file placed at location
		// specified by filepath
		File file = new File("./Review_now.csv");
		try (FileWriter outputfile = new FileWriter(file)) {
			// create FileWriter object with file as parameter

			outputfile.append("Date;User;Rating;Usage;Review");
			outputfile.append("\n");
			System.out.println("Enter Data");
			for (int i = 0; i < review.length; i++) {
				outputfile.append(date[i] + ";" + user[i] + ";" + rating[i] + ";" + usage[i] + ";" + review[i]);
				outputfile.append("\n");

			}
			System.out.println("ALL DONE");
			outputfile.close();

			// closing writer connection

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
