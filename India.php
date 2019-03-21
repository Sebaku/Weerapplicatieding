<html>
<head>
    <title>Table and Graphs</title>
</head>
<body>
<h1> India</h1>
<table border={1}>
    <tr><th>Weatherstation</th><th>Date</th><th>Time</th><th>Airpressure</th><th>Rainfall</th></tr>
    <?php $table = tabel();
    $i=0;
    for ($i=0;$i<25;$i++){
        ?>
        <tr><th><?php echo $table[$i][0];?></th>
            <th><?php echo $table[$i][1];?></th>
            <th><?php echo $table[$i][2];?></th>
            <th><?php echo $table[$i][3];?></th>
            <th><?php echo $table[$i][4];?></th></tr>
    <?php } ?>
</table>
<?php
//Grafiek:
function grafiek($weerstation)
{
    $lines = array();
    //$fp = fopen("/home/ITV2F05/db/".$weerstation.".txt", "r");
    while (!feof($fp)) {
        $line = fgets($fp, 4096);
        array_push($lines, $line);
        if (count($lines) > 10)
            array_shift($lines);
    }
    fclose($fp);

    $temp = array();
    $datum = array();
    $tijd = array();
    $luchtdruk = array();
    $neerslag = array();
    $i = 0;
    while ($i < 10) {
        echo $lines[$i];
        $i = $i + 1;
        $temp = (explode(" ", $lines[$i]));
        $result = array($temp[1], $temp[3], $temp[5], $temp[7]);
        array_push($datum, $temp[1]);
        array_push($tijd, $temp[3]);
        array_push($luchtdruk, $temp[5]);
        array_push($neerslag, $temp[7]);

    }
    print_r($luchtdruk);
}
//Tabel:
function tabel(){
    $arrayofweatherstations = array("420710", "421110", "421310", "421650", "421810", "421820", "422600", "423280", "423390", "423480", "423690", "423790", "424520",
        "424750", "425590", "425910", "426340", "426470", "426670", "426750", "427010", "427370", "427540", "428090", "428400");
    $returnvalue = array();
    foreach ($arrayofweatherstations as $val) {
        $lines = array();
       // $fp = fopen("/home/ITV2F05/db/" . $val . ".txt", "r");
        while (!feof($fp)) {
            $line = fgets($fp, 4096);
            array_push($lines, $line);
            if (count($lines) > 2)
                array_shift($lines);
        }
        fclose($fp);
        $temp = explode(" ",$lines[0]);
        $result = array($val, $temp[1], $temp[3], $temp[5], $temp[7]);
        //print_r($result);

        array_push($returnvalue, $result);

    }
    return $returnvalue;
}
?>
</body>
</html>

