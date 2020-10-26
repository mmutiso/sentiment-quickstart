package com.siasareport.sentiment;

public class SentimentResponse 
{
    private String Sentiment;
    private double Confidence;
    
    public SentimentResponse() {
        super();
    }

    public SentimentResponse(String sentiment, double confidence) {
        super();
        this.Sentiment = sentiment;
        this.Confidence = confidence;
    }

    public void setSentiment(String sentiment)
    {
        this.Sentiment = sentiment;
    }

    public void setConfidence(double confidence)
    {
        this.Confidence = confidence;
    }

    public String getSentiment()
    {
        return this.Sentiment;
    }

    public double getConfidence()
    {
        return this.Confidence;
    }
}
