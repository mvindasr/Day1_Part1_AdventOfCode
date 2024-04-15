import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    static PrintStream out = System.out;

    // Local Calibration FilePath
    static final String calibrationFilePath = "src/calibrationDocument/input.txt";

    // Regex pattern for getting a digit between 0-9
    static final Pattern regexPattern = Pattern.compile("\\d");

    public static void main(String[] args) {

        out.println("-------------------------------------------------------------");
        out.println("         Elves' Snow Operation - Calibration System         ");
        out.println("-------------------------------------------------------------");

        out.println("Reading provided input file...");


        // Add calibration phrases into an array to process...
        ArrayList<String> calibrationDocument = createCalibrationDocument();


        out.println("Calculating sum of calibration values...");
        // Determine calibration value by analyzing calibration document
        int calibrationValue = getCalibrationValue(calibrationDocument);

        // Print calibration value
        out.println("***************************************************");
        out.println("Your calibration value is: "+calibrationValue);
        out.println("***************************************************");
    }

    /**
     * Function that process .txt file and create an ArrayList of calibration lines
     * @return ArrayList of calibration Strings
     */
    static ArrayList<String> createCalibrationDocument () {
        ArrayList<String> calibrationDocument = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(calibrationFilePath))) {
            String line;
            // Reading each line...
            while ((line = br.readLine()) != null) {
                // Adding each line to ArrayList...
                calibrationDocument.add(line);
            }
            out.println("File uploaded!");

        } catch (IOException e) {
            out.println("Calibration document not found!");
        }
        return calibrationDocument;
    }

    /**
     * Function that returns the sum of all calibration values
     * @param pDocument Official Calibration document
     * @return Sum of calibration integers
     */
    static int getCalibrationValue (ArrayList<String> pDocument) {
        int calibrationValue = 0;
        for (String word : pDocument) {
            // Find digits in the specific word...
            Matcher matcher = regexPattern.matcher(word);

            // Variable for first and last number of each line
            int firstDigit = -1;
            int lastDigit = -1;

            // Find first and last digits...
            while (matcher.find()) {
                int digit = Integer.parseInt(matcher.group());
                if (firstDigit == -1) {
                    firstDigit = digit;
                }
                lastDigit = digit;
            }

            // Combine first and last digit to form individual calibration number...
            int calibrationNumber = firstDigit * 10 + lastDigit;

            // Add calibration number to calibration value...
            calibrationValue = calibrationValue + calibrationNumber;
        }
        return calibrationValue;
    }
}
