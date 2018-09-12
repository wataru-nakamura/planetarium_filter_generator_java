
import com.opencsv.CSVReader;

import java.io.*;

/**
 * Created by wataru on 16/10/07.
 */
public class StarDataAdapter {

    static private int line;
    private String rawFileName;
    private static final String UTF_8 = "UTF-8";
    private CSVReader csvReader;

    public StarDataAdapter(String fileName) {
        this.line = 0;
        this.rawFileName = fileName;
        try {
            this.csvReader = new CSVReader(new FileReader(this.rawFileName));
        }catch (FileNotFoundException e) {}
    }

    public StarData getData() throws IOException {
        StarData starData = new StarData();
        String[] starDataArray;

        //星一つ当たりのデータの取り出し
        starDataArray = this.csvReader.readNext();

        String[] longitude = starDataArray[0].split(" ");
        double longitude_deg = Double.parseDouble(longitude[0]);
        double longitude_min = Double.parseDouble(longitude[1]);
        double longitude_sec = Double.parseDouble(longitude[2]);
        starData.longitude = longitude_deg * 15
                + ((longitude_deg < 0)? (-1)*longitude_min : longitude_min)/60 * 15
                + ((longitude_deg < 0)? (-1)*longitude_sec : longitude_sec)/3600 * 15;

        String[] latitude = starDataArray[1].split(" ");
        double latitude_deg = Double.parseDouble(latitude[0]);
        double latitude_min = Double.parseDouble(latitude[1]);
        double latitude_sec = Double.parseDouble(latitude[2]);
        starData.latitude = latitude_deg
                + ((latitude_deg < 0)? (-1)*latitude_min : latitude_min)/60
                + ((latitude_deg < 0)? (-1)*latitude_sec : latitude_sec)/3600;


        starData.magnitude = Float.parseFloat(starDataArray[2]);

        try {
            starData.bvColor = Float.parseFloat(starDataArray[3]);

        } catch (NumberFormatException e) {
            starData.bvColor = 0.000;
        }

        this.line++;
        return starData;
    }

    public int getLine() {
        return line;
    }
}
