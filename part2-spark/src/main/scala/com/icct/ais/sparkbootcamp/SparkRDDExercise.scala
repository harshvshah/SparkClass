package com.icct.ais.sparkbootcamp

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author ${user.name}
  */
object SparkRDDExercise {

  def foo(x: Array[String]) = x.foldLeft("")((a, b) => a + b)

  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("RDD Application").setMaster("local")
    val sc = new SparkContext(conf)

    val dataFile = "/data/syntheticdata.csv"
    val lines = sc.textFile(dataFile).sample(false, 0.05D, 12345678L)
    //wordcount
    val words= lines.flatMap(line => line.split("\\s+"))
    val wordPairs=words.map(word => (word,1L))
    val wordCount=wordPairs.reduceByKey((sum,value)=>sum+value)
    val repartitioned = wordCount.repartition(3)
    System.out.println
    System.out.println
    System.out.println("uniqueWords: " + repartitioned.count)
    System.out.println
    System.out.println

    //line count

    //make line pairs
    val pairs = lines
      //.yourcodehere

    // add up the all lines with the same key
    val counts = pairs
      //.yourcode here

      // call an action to trigger the actual work...
    val result = counts
      //.yourcodehere

    //how can you tell how many unique lines there are?
    val uniqueLines = result
        //.yourcodehere

    val totalLines = lines.count


    System.out.println
    System.out.println
    System.out.println("TotalLines: " + totalLines + ", uniqueLines: " + uniqueLines)
    System.out.println
    System.out.println

    sc.stop
  }

}
