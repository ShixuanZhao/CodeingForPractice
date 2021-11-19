package A_MyPractice;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ExcProb {
    public static void main(String[] args) {
        try {
            ArrayList<String> lines = readFile();
            int maxSoFar = 0;
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).length() > maxSoFar) {
                    maxSoFar = lines.get(i).length();
                }
            }
            System.out.println("max line is length" + maxSoFar);
        } catch (IOException exc) {
            System.out.println("error #1");
        }
    }

    private static ArrayList<String> readFile() throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        try (Scanner inFile = new Scanner(new File("../data"))) {
            for (int i = 0; i < 3; i++) {
                lines.add(inFile.nextLine());
            }
            if (inFile.hasNext()) {
                throw new IOException();
            }
        } catch (FileNotFoundException e) {
            System.out.println("erroe #3");
        }
        return lines;
    }
}
