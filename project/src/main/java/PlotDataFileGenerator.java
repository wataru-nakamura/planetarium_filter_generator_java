import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by wataru on 16/10/07.
 */
public class PlotDataFileGenerator {

    private String inputFileName;

    public PlotDataFileGenerator(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public void start() throws IOException {
        StarDataAdapter adapter = new StarDataAdapter(this.inputFileName);

        CSVWriter plotData_top;
        CSVWriter plotData_below;
        CSVWriter plotData_side_180;
        CSVWriter plotData_side_360;

        plotData_top = new CSVWriter(new FileWriter("./plotdata/plotData_top.csv"));
        plotData_below = new CSVWriter(new FileWriter("./plotdata/plotData_below.csv"));
        plotData_side_180 = new CSVWriter(new FileWriter("./plotdata/plotData_0_180.csv"));
        plotData_side_360 = new CSVWriter(new FileWriter("./plotdata/plotData_180_360.csv"));


        int i = 0;
        while (i < 108003){  // max is 108003 16000
            System.out.print((i+1) + "  ");

            StarData starData = adapter.getData();
//            System.out.println("経度=" + starData.longitude + " 緯度=" + starData.latitude);

            PlotData plotData = new PlotDataConverter(starData).getPlotData();

//            System.out.println(plotData.plotMap + " " + plotData.x + " " + plotData.y + " " + plotData.size + " " + plotData.rgb);
//            System.out.println();
//            System.out.println(plotData.rgb);

            String[] csvLine = new String[4];
            csvLine[0] = String.valueOf(plotData.x);
            csvLine[1] = String.valueOf(plotData.y);
            csvLine[2] = String.valueOf(plotData.size);
            csvLine[3] = String.valueOf(plotData.rgb);

            if (plotData.plotMap.equals(PlotData.TOP)) {
                plotData_top.writeNext(csvLine, false);

            }else if (plotData.plotMap.equals(PlotData.BELOW)) {
                plotData_below.writeNext(csvLine, false);

            }else if (plotData.plotMap.equals(PlotData.SIDE_180)) {
                plotData_side_180.writeNext(csvLine, false);
            }else if (plotData.plotMap.equals(PlotData.SIDE_360)) {
                plotData_side_360.writeNext(csvLine, false);

            }

            i++;
        }

        System.out.println(adapter.getLine() + "行取得");

        plotData_top.close();
        plotData_below.close();
        plotData_side_180.close();
        plotData_side_360.close();
    }
}


