
/**
 * Created by wataru on 16/10/19.
 */
public class PlotDataConverter {
    private StarData starData;

    public PlotDataConverter (StarData starData) {
        this.starData = starData;
    }

    public PlotData getPlotData () {
        PlotData plotData = new PlotData();

        plotData.plotMap =getPlotMap();

        if(plotData.plotMap.equals(PlotData.TOP)) {
            plotData.x = getX_top();
            plotData.y = getY_top();

        }else if (plotData.plotMap.equals(PlotData.BELOW)) {
            plotData.x = getX_below();
            plotData.y = getY_below();

        }else if (plotData.plotMap.equals(PlotData.SIDE_180)) {
            plotData.x = getX_side();
            plotData.y = getY_side();

        }else if (plotData.plotMap.equals(PlotData.SIDE_360)) {
            plotData.x = getX_side();
            plotData.y = getY_side();

        }

        plotData.size = getPlotSize();
        plotData.rgb = getRGB();

        return plotData;
    }

    /********************************************/
    /*  プロットするためのXY座標を取得              */
    /********************************************/
    private double getX_top () {return 8 * tan(90.0000 - starData.latitude) * cos(starData.longitude);}
    private double getY_top () {return (-1) * 8 * tan(90.0000 - starData.latitude) * sin(starData.longitude);}

    private double getX_below () {return 8 * tan(90.0000 + starData.latitude) * cos(starData.longitude);}
    private double getY_below () {return 8 * tan(90.0000 + starData.latitude) * sin(starData.longitude);}

    private double getX_side () {
        double x = -8 * cos(30) * tan(this.starData.longitude % 60 -30);

        if (( ((int)starData.longitude) / 60) == 0 || ((int)(starData.longitude) / 60) == 3) {
            return x+8;
        }else if (( ((int)starData.longitude) / 60) == 1 || ((int)(starData.longitude) / 60) == 4) {
            return x;
        }else if (( ((int)starData.longitude) / 60) == 2 || ((int)(starData.longitude) / 60) == 5) {
            return x-8;
        }
        return 0;
    }
    private double getY_side () {return 8 * cos(30) * tan(this.starData.latitude);}

    /********************************************/
    /*  プロットする図面を判定                     */
    /********************************************/
    private String getPlotMap () {
        Point a = new Point(0, 0);
        Point b = new Point(8, 0);
        Point c = new Point(4, -6.928);

        // 判定用の点Pを導出
        double x = 8 * tan(90 - Math.abs(this.starData.latitude)) * cos(starData.longitude % 60);
        double y = (-1) * 8 * tan(90 - Math.abs(starData.latitude)) * sin(starData.longitude % 60);
//        System.out.println("longitude(経度)" + starData.longitude + " latitude(緯度)" + starData.latitude);
//        System.out.println("px" + x + " py" + y);
        Point p = new Point(x, y);

        Point ab = new Point(b.x - a.x, b.y - a.y);
        Point bc = new Point(c.x - b.x, c.y - b.y);
        Point ca = new Point(a.x - c.x, a.y - c.y);

//        System.out.println("ab" + ab.x + " " + ab.y);
//        System.out.println("bc" +bc.x + " " + bc.y);
//        System.out.println("ca" +ca.x + " " + ca.y);

        Point ap = new Point(p.x - a.x, p.y - a.y);
        Point bp = new Point(p.x - b.x, p.y - b.y);
        Point cp = new Point(p.x - c.x, p.y - c.y);

        double c1 = ab.x * bp.y - ab.y * bp.x;
        double c2 = bc.x * cp.y - bc.y * cp.x;
        double c3 = ca.x * ap.y - ca.y * ap.x;

//        System.out.println(c1 + " " + c2 + " " + c3);

        if (c1<0 && c2<0 && c3<0) {
            if(this.starData.latitude >0) {
                return PlotData.TOP;
            }else {
                return PlotData.BELOW;
            }
        }else {
            if(0 < starData.longitude && starData.longitude <= 180) {
                return PlotData.SIDE_180;
            }else {
                return PlotData.SIDE_360;
            }
        }
    }

    /********************************************/
    /*  プロットする点のサイズを指定                */
    /********************************************/
    private double getPlotSize () {
        double Sirius = -1.44;

        if (starData.magnitude <= 4.5) {
            return 10 * Math.pow(Math.pow(100, (Sirius - this.starData.magnitude)/5), 0.3);

        }else if(starData.magnitude > 7) {
            return 0.0026;
        }

        return 6.32770766178 * Math.pow(Math.pow(100, (Sirius - this.starData.magnitude)/5), 1);
    }

    /********************************************/
    /*  プロットする点のRGBカラーを設定             */
    /********************************************/
    private String getRGB () {

        double temperature = 4600 * ((1 / ((0.92 * this.starData.bvColor) + 1.7)) +(1 / ((0.92 * this.starData.bvColor) + 0.62)) );

        // t to xyY
        double x = 0, y = 0;

        if (temperature>=1667 && temperature<=4000) {
            x = ((-0.2661239 * Math.pow(10,9)) / Math.pow(temperature,3))
                    + ((-0.2343580 * Math.pow(10,6)) / Math.pow(temperature,2))
                    + ((0.8776956 * Math.pow(10,3)) / temperature) + 0.179910;
        } else if (temperature > 4000 && temperature <= 25000) {
            x = ((-3.0258469 * Math.pow(10,9)) / Math.pow(temperature,3))
                    + ((2.1070379 * Math.pow(10,6)) / Math.pow(temperature,2))
                    + ((0.2226347 * Math.pow(10,3)) / temperature) + 0.240390;
        }

        if (temperature >= 1667 && temperature <= 2222) {
            y = -1.1063814 * Math.pow(x,3)
                    - 1.34811020 * Math.pow(x,2)
                    + 2.18555832 * x
                    - 0.20219683;
        } else if (temperature > 2222 && temperature <= 4000) {
            y = -0.9549476 * Math.pow(x,3)
                    - 1.37418593 * Math.pow(x,2)
                    + 2.09137015 * x
                    - 0.16748867;
        } else if (temperature > 4000 && temperature <= 25000) {
            y = 3.0817580 * Math.pow(x,3)
                    - 5.87338670 * Math.pow(x,2)
                    + 3.75112997 * x
                    - 0.37001483;
        }

        double Y = (y == 0)? 0:1;
        double X = (y == 0)? 0 : (x * Y) / y;
        double Z = (y == 0)? 0 : ((1 - x - y) * Y) / y;

        double r = 3.2406 * X - 1.5372 * Y - 0.4986 * Z;
        double g = -0.9689 * X + 1.8758 * Y + 0.0415 * Z;
        double b = 0.0557 * X - 0.02040 * Y + 1.0570 * Z;

        r = (r > 1) ? 1 :r;
        g = (g > 1) ? 1 :g;
        b = (b > 1) ? 1 :b;

        //RGB to sRGB
        double R = (r <= 0.0031308)? 12.92*r : 1.055*Math.pow(r,1/0.5)-0.055;
        double G = (g <= 0.0031308)? 12.92*g : 1.055*Math.pow(g,1/0.5)-0.055;
        double B = (b <= 0.0031308)? 12.92*b : 1.055*Math.pow(b,1/0.5)-0.055;

//        double R =r;
//        double G = (g*1.05 > 1)? 1 : (g*1.05);
//        double B = b;


        long RCode =  Math.round(R * 255);
        long GCode =  Math.round(G * 255);
        long BCode =  Math.round(B * 255);

        long alpha = (starData.magnitude > 7) ? (long)(255 - 60 / Math.pow(100, (7 - this.starData.magnitude)/5)): 255;
        if(alpha < 0) alpha =0;

        // RGB Code
        return "#"
                + Long.toHexString(alpha)
                + Long.toHexString(RCode)
                + Long.toHexString(GCode)
                + Long.toHexString(BCode);
    }

    private class Point {
        double x;
        double y;
        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static private double cos (double degree) {
        return Math.cos(Math.toRadians(degree));
    }
    static private double sin (double degree) {
        return Math.sin(Math.toRadians(degree));
    }
    static private double tan (double degree) {
        return Math.tan(Math.toRadians(degree));
    }

}
