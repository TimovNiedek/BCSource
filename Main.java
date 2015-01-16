import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.converters.TextDirectoryLoader;
import weka.core.tokenizers.NGramTokenizer;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

public class Main {

	public static void main(String[] args) {
		try {
			loadTextFiles();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void loadTextFiles() throws Exception{
		File trainFile = new File("C:\\Users\\Timo\\Documents\\GitHub\\Data Mining\\BCSource\\traintxt");
		File testFile = new File("C:\\Users\\Timo\\Documents\\GitHub\\Data Mining\\BCSource\\testtxt");
		
		Instances trainDataRaw = null;
		Instances testDataRaw = null;
		TextDirectoryLoader tdl = new TextDirectoryLoader();
		try {
			tdl.setSource(trainFile);
			trainDataRaw = tdl.getDataSet();
			tdl.setSource(testFile);
			testDataRaw = tdl.getDataSet();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringToWordVector filter = new StringToWordVector();
		Instances trainDataFiltered = null;
		Instances testDataFiltered = null;
		
		// Set the tokenizer 
		NGramTokenizer tokenizer = new NGramTokenizer(); 
		tokenizer.setNGramMinSize(1); 
		tokenizer.setNGramMaxSize(1); 
		tokenizer.setDelimiters("\\W");
		
		filter.setInputFormat(trainDataRaw);
		filter.setWordsToKeep(1000000); 
		filter.setDoNotOperateOnPerClassBasis(true);
		filter.setTokenizer(tokenizer);
		trainDataFiltered = Filter.useFilter(trainDataRaw, filter);
		testDataFiltered = Filter.useFilter(testDataRaw, filter);
		
		
		//System.out.println("Filtered data:\n " + dataFiltered + "\n\n Instance count: \n" + dataFiltered.numInstances());
		String[] options = new String[1];
		options[0] = "-U"; 
		J48 tree = new J48();
		tree.setOptions(options);
		
    	tree.buildClassifier(trainDataFiltered);
		
	    Evaluation eval = null;
		
		eval = new Evaluation(trainDataFiltered);
		eval.evaluateModel(tree, testDataFiltered);
		
	    System.out.println(eval.toSummaryString("\nResults\n======\n", false));
	    
	    System.out.println(tree + "\n");
	}

}
