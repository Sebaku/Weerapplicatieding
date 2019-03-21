<html>
<head>
    <title>Table and Graphs</title>
</head>
<body>
<?php
if(isset($_GET['station'])){
  $var = $_GET['station']; //some_value
} ?>
<h1> Weatherstation <?php echo $var;?></h1>
<table border={1}>
    <tr><th>Date</th><th>Time</th><th>Airpressure</th><th>Rainfall</th></tr>
    <?php $table = grafiek($var);
    $i=0;
    for ($i=0;$i<10;$i++){
    ?>
    <tr><th><?php echo $table[0][$i];?></th>
    <th><?php echo $table[1][$i];?></th>
    <th><?php echo $table[2][$i];?></th>
    <th><a href=""><?php echo $table[3][$i];?></a></th>
    <?php } ?>
</table>
<?php
//Grafiek:
function grafiek($weerstation)
{
    $lines = array();
    $fp = fopen("C:/xampp/htdocs/db/".$weerstation.".txt", "r");
    while (!feof($fp)) {
        $line = fgets($fp, 4096);
        array_push($lines, $line);
        if (count($lines) > 12)
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
        //echo $lines[$i];
        $i = $i + 1;
        $temp = (explode(" ", $lines[$i]));
		if(count($temp) != 0){
			$result = array($temp[1], $temp[3], $temp[5], $temp[7]);
			array_push($datum, $temp[1]);
			array_push($tijd, $temp[3]);
			array_push($luchtdruk, $temp[5]);
			array_push($neerslag, $temp[7]);
		}

    }
	$result = array($datum, $tijd, $luchtdruk, $neerslag);
    //print_r($result[1][5]);
	return $result;
}
?>
</body>
</html>

