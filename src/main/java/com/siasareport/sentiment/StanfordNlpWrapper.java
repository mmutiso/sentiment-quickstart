package com.siasareport.sentiment;

import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

import org.ejml.simple.SimpleMatrix;

public class StanfordNlpWrapper 
{
    StanfordCoreNLP pipeline = null;
    public StanfordNlpWrapper() 
    {
        super();
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        pipeline = new StanfordCoreNLP(props);
    }
    public SentimentResponse computeSentiment( String text)
    {        
        int mainSentiment = -100;
        int longestSentenceLength = 0;
        Annotation annotation = pipeline.process(text);
        SimpleMatrix sm = null;
        
        for(CoreMap sentence: annotation.get(CoreAnnotations.SentencesAnnotation.class))
        {
            Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
            int sentiment = RNNCoreAnnotations.getPredictedClass(tree);            
            String partText = sentence.toString();

            if(partText.length() > longestSentenceLength)
            {
                mainSentiment = sentiment;
                longestSentenceLength = partText.length();
                sm = RNNCoreAnnotations.getPredictions(tree);
            }
        }        
        String mainSentimentStr = "indiscernible";
        double confidence = 0.0;
        if(mainSentiment != -100)
        {
            mainSentimentStr = parseSentiment(mainSentiment);
            confidence = sm.get(mainSentiment);
        }   
        SentimentResponse response = new SentimentResponse(mainSentimentStr, confidence);    
        return response;
    }

    String parseSentiment(int sentiment)
    {
        switch(sentiment)
        {
            case 0: return "negative"; //very negative
            case 1: return "negative"; //negative
            case 2: return "neutral"; //neutral
            case 3: return "positive"; // positive
            case 4: return "positive"; // very positive
            default: return "";
        }
    }   
}
