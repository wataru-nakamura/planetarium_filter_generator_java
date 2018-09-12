import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by wataru on 16/10/07.
 */
public class SystemAgent {

    public static void main (String args[]) {
        Properties conf = new Properties();

        try {
            conf.load(new FileInputStream("./plotdata/conf"));
        }catch(IOException e) {
            System.err.print(e);
            System.out.println(new File(".").getAbsoluteFile().getParent());
            System.exit(-1);
        }

        String inputFileName = conf.getProperty("RAW_FILE_NAME");
        String outputDirectoryName = conf.getProperty("OUTPUT_DIRECTORY");

        System.out.println("Input file name  : " + inputFileName);
        System.out.println("Output file name : " + outputDirectoryName);

        try {
            new PlotDataFileGenerator(inputFileName).start();

        }catch (IOException e) {}

    }

}
