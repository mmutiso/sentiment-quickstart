package com.siasareport.sentiment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvFileProcessor
{
    
    String filePath;
    public CsvFileProcessor(String path) {
        this.filePath  = path;
    } 

    TweetWithSentiment getTweetFromString(String line) 
    {
        String[] parts = line.split(",");
        try
        {
            return new TweetWithSentiment(Long.parseLong(parts[0]), parts[1], parts[10]);
        }
        catch(Exception ex)
        {
            return new TweetWithSentiment();
        }        
    }

    public List<TweetWithSentiment> sentimentsFromFile() throws Exception
    {
        List<TweetWithSentiment> tweets = new ArrayList<>();
        int lineCounter = 0;
        try(Scanner scanner = new Scanner(new File(this.filePath));)
        {
            while(scanner.hasNextLine())
            {
                if(lineCounter > 0) // we need to skip the header
                {
                    TweetWithSentiment tws = getTweetFromString(scanner.nextLine());
                    if(tws.tweetId != 0L)
                    {
                        tweets.add(tws);  
                    }                      
                }
                lineCounter++;
            }
        }
        return tweets;
    } 
}
