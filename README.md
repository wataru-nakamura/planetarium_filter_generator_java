# プラネタリウムの光源生成機
　プラネタリウムの光源用フィルタを生成するJavaプログラム

　以下のpdfファイルを生成します。
- [HIPPARCOS Main Catalogue](https://heasarc.gsfc.nasa.gov/w3browse/all/hipparcos.html)
- 10等星まで、合計役１０万個ほど
- 六角柱型の光源フィルタを生成し、OHPシート等の印刷可能な透明フィルタに印刷する

## 出力される画像
- 北極周辺フィルタ
![北極周辺フィルタ](https://github.com/wataru-nakamura/my_images_wataru_nakamura/blob/master/planetarium_filter_generator_java/images_north.jpg)

- 0~180度までのサイドフィルタ
![0~180度までのサイドフィルタ](https://github.com/wataru-nakamura/my_images_wataru_nakamura/blob/master/planetarium_filter_generator_java/images_0_180.jpg)

- 180-360度までのサイドフィルタ
![180-360度までのサイドフィルタ](https://github.com/wataru-nakamura/my_images_wataru_nakamura/blob/master/planetarium_filter_generator_java/images_180_360.jpg)

- 南極周辺フィルタ
![南極周辺フィルタ](https://github.com/wataru-nakamura/my_images_wataru_nakamura/blob/master/planetarium_filter_generator_java/images_south.jpg)

## 備考
　構成された六角柱構造体の中心部分にフィラメントの入った豆電球を設置する。光源は点光源であることが望ましく、店で市販されているような豆電球では、投影されるドーム面に対してフィラント型の星が投影される。
　比較的簡単に手に入る豆電球としては、ミニマグライトがある。
　一番理想なのは、後藤光学研究所のプラネタリウム用の豆電球である。
