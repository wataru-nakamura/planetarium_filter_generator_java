set encoding 'utf8'

set terminal pdfcairo size 29.7cm, 21.0cm font "Meiryo, 20" enhanced
set output 'plot.pdf'

set datafile separator ","

set lmargin 0
set tmargin 0
set rmargin 0
set bmargin 0
set format xy ""
unset xtics
unset ytics
set border 0
set xrange [-14.85:14.85]
set yrange [-10.5:10.5]
unset key

alpha(colorstring)=colorstring[2:3]
#pick the red color hex string out
red(colorstring)= colorstring[4:5]
#pick the green color hex string out
green(colorstring)=colorstring[6:7]
#pick the blue color hex string out
blue(colorstring)=colorstring[8:9]
#convert a hex string to its dec format
hex2dec(hex)=gprintf("%0.f",int('0X'.hex))
#calculate the rgb value from the r,g,b weight
rgb(r,g,b) = 65536*int(r)+256*int(g)+int(b)

argb(a,r,g,b) = 16777216*int(a)+65536*int(r)+256*int(g)+int(b)
#convert the hex color string to its rgb value.
hex2rgbvalue(color)=rgb(hex2dec(red(color)), hex2dec(green(color)), hex2dec(blue(color)))

hex2argbvalue(color)=argb(hex2dec(alpha(color)),hex2dec(red(color)), hex2dec(green(color)), hex2dec(blue(color))) 

set label 10 at first -14.0, first 9.6 "注）正面を内側に"

set label 1 at first 4.6, first -6.90    "4h                  正面(北)                  0h" rotate by 60
set label 2 at first -3.8, first -7.428  "8h                   正面(北)                  4h"
set label 3 at first -8.3, first -0.3    "12h                  正面(北)                  8h" rotate by -60
set label 4 at first -4.5, first 7.00    "16h                 正面(北)                 12h" rotate by -120
set label 5 at first 3.8, first 7.428  "20h                 正面(北)                16h" rotate by -180
set label 6 at first 8.3, first 0.4    "24h                 正面(北)                20h" rotate by 120

plot './plotdata/frame_top.dat' with filledcurves lt rgb '#000000' ,\
     './plotdata/frame_top.dat' with lines dt (10, 10) lt rgb '#ff0000' ,\
     './plotdata/overlap_top.dat' with lines lt rgb '#000000' ,\
     './plotdata/plotData_top.csv' using 1:2:($3 / 13):($3 > 0.0027 ? hex2rgbvalue(stringcolumn(4)):hex2argbvalue(stringcolumn(4))) with points pt 20 ps variable lc rgb variable

set label 1 at first 4.6, first -6.90    "20h                 正面(南)                24h" rotate by 60
set label 2 at first -3.8, first -7.428  "16h                  正面(南)                20h"
set label 3 at first -8.3, first -0.3    "12h                 正面(南)                16h" rotate by -60
set label 4 at first -4.5, first 7.00    "8h                  正面(南)                 12h" rotate by -120
set label 5 at first 3.8, first 7.428  "4h                  正面(南)                 8h" rotate by -180
set label 6 at first 8.3, first 0.4    "0h                  正面(南)                 4h" rotate by 120
     
plot './plotdata/frame_top.dat' with filledcurves lt rgb '#000000' ,\
     './plotdata/frame_top.dat' with lines dt (10, 10) lt rgb '#ff0000' ,\
     './plotdata/overlap_top.dat' with lines lt rgb '#000000' ,\
     './plotdata/plotData_below.csv' using 1:2:($3 / 13):($3 > 0.0027 ? hex2rgbvalue(stringcolumn(4)):hex2argbvalue(stringcolumn(4))) with points pt 20 ps variable lc rgb variable


 set label 1 at first -4.2, first 8.5    "8h                  正面(北)               12h" rotate by 180
 set label 2 at first -11.6, first -8.5  "12h                 正面(南)                8h" rotate by 0
 set label 3 at first 3.8, first 8.5    "4h                 正面(北)                8h" rotate by 180
 set label 4 at first -3.8, first -8.5    "8h                  正面(南)                 4h" rotate by 0
 set label 5 at first 11.8, first 8.5  "0h                  正面(北)                 4h" rotate by 180
 set label 6 at first 4.2, first -8.5    "4h                  正面(南)                 0h" rotate by 0

plot './plotdata/frame_side.dat' with filledcurves lt rgb '#000000' ,\
     './plotdata/frame_side.dat' with lines dt (10, 10) lt rgb '#ff0000' ,\
     './plotdata/overlap_side.dat' with lines lt rgb '#000000' ,\
     './plotdata/plotData_0_180.csv' using 1:2: ($3 / 13):($3 > 0.0027 ? hex2rgbvalue(stringcolumn(4)):hex2argbvalue(stringcolumn(4))) with points pt 20 ps variable lc rgb variable 


 set label 1 at first -4.2, first 8.5    "20h                 正面(北)               24h" rotate by 180
 set label 2 at first -11.6, first -8.5  "24h                 正面(南)               20h" rotate by 0
 set label 3 at first 3.8, first 8.5    "16h                 正面(北)                20h" rotate by 180
 set label 4 at first -3.8, first -8.5    "20h                 正面(南)                16h" rotate by 0
 set label 5 at first 11.8, first 8.5  "12h                 正面(北)                16h" rotate by 180
 set label 6 at first 4.2, first -8.5    "16h                 正面(南)                12h" rotate by 0

plot './plotdata/frame_side.dat' with filledcurves lt rgb '#000000' ,\
     './plotdata/frame_side.dat' with lines dt (10, 10) lt rgb '#ff0000' ,\
     './plotdata/overlap_side.dat' with lines lt rgb '#000000' ,\
     './plotdata/plotData_180_360.csv' using 1:2: ($3 / 13):($3 > 0.0027 ? hex2rgbvalue(stringcolumn(4)):hex2argbvalue(stringcolumn(4))) with points pt 20 ps variable lc rgb variable 
