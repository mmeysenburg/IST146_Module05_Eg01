import java.util.*;
import java.io.*;

/**
 * First example for IST 146 Module 5, illustrating simple file
 * I/O. This combines Examples 11.2, 11.5, and 11.8-9 into one
 * program.
 */
public class Main {

  /**
   * Code from text's Example 11.2
   */
  private static void readMovies() {
    try (Scanner file = new Scanner(new File("movies.txt"))) {
      while (file.hasNext()) // test for the end of the file
      {
        String movieTitle = file.nextLine();

        if (!file.hasNextInt()) {
          System.out.println("Invalid file format");
          String invalidData = file.nextLine(); // skip the line
        } else {
          int runningTime = file.nextInt();
          String newLine = file.nextLine(); // read newline character

          System.out.println(movieTitle + ", " + runningTime + " minutes");
        }
      }
    } catch (FileNotFoundException fnfe) {
      System.out.println("Unable to find movies.txt, exiting");
    } catch (NoSuchElementException nsee) {
      System.out.println("Attempt to read past the end of the file");
    } catch (IllegalStateException ise) {
      System.out.println("Attempt to read after the file is closed");
    }
  }

  /**
   * Code from text's Example 11.5
   */
  private static void writeItalianFlagData() {
    int HEIGHT = 10;

    try {
      FileWriter fw = new FileWriter("ItalianFlag.txt", false);
      PrintWriter pw = new PrintWriter(fw);
      pw.println(30); // width of GIF
      // write HEIGHT lines of color and pixels data
      for (int i = 0; i < HEIGHT; i++) {
        pw.print(10);           // 10 green pixels
        pw.print(' ');          // white space character
        pw.print(0.0);          // red color component
        pw.println(" 1.0 0.0"); // green and blue color components

        pw.println("10 1.0 1.0 1.0"); // 10 white pixels
        pw.println("10 1.0 0.0 0.0"); // 10 red pixels
      }

      pw.close();
    } catch (IOException ioe) {
      System.out.println(ioe.getMessage());
    }
  }

  /**
   * Code from text's Example 11.9
   */
  private static void readFlightData() {
    // instantiate ArrayList to hold FlightRecord objects
    List<FlightRecord> listFlightRecords = new ArrayList<>();

    try {
      Scanner file = new Scanner(new File("flights.txt"));

      while (file.hasNext()) // test for the end of the file
      {
        // read a line
        String stringRead = file.nextLine();

        // process the line read
        Scanner parse = new Scanner(stringRead);
        parse.useDelimiter(",");
        String flightNumber = parse.next();
        String origin = parse.next();
        String destination = parse.next();

        try {
          int numPassengers = parse.nextInt();
          double avgTicketPrice = parse.nextDouble();

          FlightRecord frTemp = new FlightRecord(
              flightNumber, origin, destination, numPassengers, avgTicketPrice);

          // add FlightRecord obj to listFlightRecords
          listFlightRecords.add(frTemp);
        } catch (InputMismatchException ime) {
          System.out.println("Error in flight record: " + stringRead +
                             "; record ignored");
        }
      }

      // release resources associated with flights.txt
      file.close();
    } catch (FileNotFoundException fnfe) {
      System.out.println("Unable to find flights.txt");
    } catch (Exception e) {
      e.printStackTrace();
    }

    // print the FlightRecords read
    for (FlightRecord flight : listFlightRecords)
      System.out.println(flight);
  }

  public static void main(String[] args) {
    readMovies();
    writeItalianFlagData();
    readFlightData();
  }
}
