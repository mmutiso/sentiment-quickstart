package com.siasareport.sentiment;

import java.util.List;




/**
 * Hello world!
 *
 */
public class App 
{
    
    public static void main( String[] args ) throws Exception
    {
        CsvFileProcessor processor = new CsvFileProcessor();
        List<TweetWithSentiment> originalSentiments = processor.sentimentsFromFile("data/Tweets.csv");
        SentimentTester tester = new SentimentTester(originalSentiments);
        tester.runTester();
    }

    
}
