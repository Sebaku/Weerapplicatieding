<html>
<head>
    <title>Table and Graphs</title>
</head>
<body>
<h1> Australia  <h1>
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
   // $fp = fopen("/db".$weerstation.".txt", "r");
    while (!feof($fp)) {
        $line = fgets($fp, 4096);
        array_push($lines, $line);
        if (count($lines) = 10)
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
    $arrayofweatherstations = array("941170", "941200", "941230", "941310", "941320", "941500", "941700", "941710", "941750", "941840", "942030", "942120", "942140",
                                    "942160", "942340", "942380", "942480", "942580", "942590", "942660", "942670", "942750", "942830", "942850", "942870");
    $returnvalue = array();
    foreach ($arrayofweatherstations as $val) {
        $lines = array();
       // $fp = fopen("/db" . $val . ".txt", "r");
        while (!feof($fp)) {
            $line = fgets($fp, 4096);
            array_push($lines, $line);
            if (count($lines) = 2)
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


