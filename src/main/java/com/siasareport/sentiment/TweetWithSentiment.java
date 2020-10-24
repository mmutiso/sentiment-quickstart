package com.siasareport.sentiment;

public class TweetWithSentiment 
{
    public long tweetId;
    public String OriginalSentiment;
    public String tweetText;
    public String AssignedSentiment;

    public TweetWithSentiment() {
        super();
    }

    public TweetWithSentiment(long tweetId, String sentiment, String tweetText) {
        this.tweetId = tweetId;
        this.OriginalSentiment = sentiment;
        this.tweetText = tweetText;
    }

    public void setAssignedSentiment(String assignedSentiment)
    {
        this.AssignedSentiment = assignedSentiment;
    }

}
