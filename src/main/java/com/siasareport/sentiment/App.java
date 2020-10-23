package com.siasareport.sentiment;

import java.util.Properties;
import java.util.Timer;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

/**
 * Hello world!
 *
 */
public class App 
{
    
    public static void main( String[] args )
    {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        System.out.println( "Hello World!" );
        String text = args[0];
        long start = System.nanoTime();
        computeSentiment(pipeline, text);
        long stop = System.nanoTime();
        System.out.println("Took "+ (stop-start)/1000000+" milliseconds");
    }

    static void computeSentiment(StanfordCoreNLP pipeline, String text)
    {
        
        int mainSentiment = 0;
        int longestSentence = 0;
        Annotation annotation = pipeline.process(text);
        for(CoreMap sentence: annotation.get(CoreAnnotations.SentencesAnnotation.class))
        {
            Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
            int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
            
            String partText = sentence.toString();

            if(partText.length() > longestSentence)
            {
                mainSentiment = sentiment;
                longestSentence = partText.length();

                System.out.println("Sentiment: "+ sentiment);
                System.out.println("Longest: "+ partText);
            }
        }

    }
}
