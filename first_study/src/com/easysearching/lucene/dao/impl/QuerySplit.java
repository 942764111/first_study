package com.easysearching.lucene.dao.impl;

import java.io.IOException;

import jeasy.analysis.MMAnalyzer;

public class QuerySplit {

	 public static String getQuerySplit(String text) 
     {
		 String result = null;
         MMAnalyzer analyzer = new MMAnalyzer();
         try 
         {
               result=analyzer.segment(text, " ");
         } 
         catch (IOException e) 
         {
                 e.printStackTrace();
         }
		return result;
     }
}
