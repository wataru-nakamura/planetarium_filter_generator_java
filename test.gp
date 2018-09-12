set encoding 'utf8'

set terminal pdfcairo size 29.7cm, 21.0cm font "Meiryo, 20" enhanced
set output 'test.pdf'

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
     './plotdata/plotData_top.csv' using 1:2:($3 / 10000) with points pt 20 ps variable lc rgb '#ffffff'
     
