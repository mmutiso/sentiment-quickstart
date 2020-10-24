package com.siasareport.sentiment;

import java.util.List;
import java.util.ArrayList;

public class SentimentTester 
{
    StanfordNlpWrapper nlpWrapper;
    List<TweetWithSentiment> sentiments;
    
    public SentimentTester(List<TweetWithSentiment> sentiments) 
    {
        this.nlpWrapper = new StanfordNlpWrapper();
        this.sentiments = sentiments;
    }

    public void runTester()
    {
        int counter = 1;
        List<TweetWithSentiment> candidates = new ArrayList<>();
        for(TweetWithSentiment tweet: this.sentiments)
        {
            String sentiment = this.nlpWrapper.computeSentiment(tweet.tweetText);
            tweet.setAssignedSentiment(sentiment);
            candidates.add(tweet);
            counter++;

            if(counter > 200)
            {
                break;
            }            
        }
        System.out.println("Completed assigning sentiments");
        compareSentiments(candidates);
    }

    private void compareSentiments(List<TweetWithSentiment> sentiments)
    {
        int totalTweets = sentiments.size();
        int matchCounter = 0;
        for(TweetWithSentiment tweet: sentiments)
        {
            if(tweet.OriginalSentiment.equalsIgnoreCase(tweet.AssignedSentiment))
            {
                matchCounter++;
            }
        }
        System.out.println("of "+ totalTweets+ " we have "+ matchCounter+ " matches. "+ (matchCounter*1.0/totalTweets*1.0)*100 + "% match rate");
    }
}
