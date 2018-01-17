import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class test {

    public static void main(String[] args) {


        if (args.length != 0) {

            Scanner in = new Scanner(System.in);
            in.useDelimiter("\n");
            while (in.hasNext()) {

                String inputString = in.next();
                System.out.println("Input from KeyBoard: " + inputString);
            }
        } else {

            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));
            chooser.showOpenDialog(null);
            File choose  = chooser.getSelectedFile();
            try (Scanner in = new Scanner(new FileInputStream(choose))) {

                in.useDelimiter("\n");
                while (in.hasNext()) {

                    String inputString = in.next();
                    System.out.println("Input from File: " + inputString);
                }
            }
            catch (FileNotFoundException e){
                System.out.println("File Not Found!");
            }
        }
    }
}

