import java.util.Scanner;

public class Main {
	

	public static void main(String[] args) {
		BookClassifier bc = new BookClassifier();

		System.out.println("======Which algorithm do you want to use for classifying the books?======\n1: J48\n2: RandomForest\n3: NaiveBayes");
		
		Scanner scanIn = new Scanner(System.in);
		int algorithm = scanIn.nextInt();
		
		System.out.println("======Do you want to use Attribute Selection Filtering?======\n1: Yes\n2: No");
		int attributeSel = scanIn.nextInt();
		
		BookClassifier.ClassifierMethod clMethod = BookClassifier.ClassifierMethod.RandomForest;
		boolean useAttrSelect = false;
		
		switch (algorithm){
		case 1:
			clMethod = BookClassifier.ClassifierMethod.J48;
			break;
		case 2:
			clMethod = BookClassifier.ClassifierMethod.RandomForest;
			break;
		case 3:
			clMethod = BookClassifier.ClassifierMethod.NaiveBayes;
			break;
		}
		
		useAttrSelect = (attributeSel == 1) ? true : false;
		
		scanIn.close();
		
		try {
			bc.loadTextFiles();
			bc.classifyBooks(clMethod, useAttrSelect);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
