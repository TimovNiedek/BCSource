# On the classification of English literature

## <center> or the prediction of authors based on their other works by means of text mining </center>

## Abstract

From a collection of English literary works by certain authors, we have constructed a text-based classifier. Given an unknown book by one of the aforementioned authors, the classifier can predict by whom this book was written.

Our classifier can predict the author based on three different classification algorithms: *Naive Bayes[^NaiveBayes]*, *J48 (decision tree)[^J48]* and *Random Forest*[^RandomForest].

When constructed with the works of three authors (the first five chapters of five books per author), the *Random Forest* classification algorithm can predict the author of an unknown book with 100% accuracy.

Our classifier is written in Java[^Java], making heavy use of the Weka[^Weka] data mining tools developed at the University of Waikato[^Waikato], New Zealand.

[^NaiveBayes]:Naive Bayes classifier. (n.d.). Retrieved January 23, 2015, from http://en.wikipedia.org/wiki/Naive_Bayes_classifier
[^J48]:http://en.wikipedia.org/wiki/C4.5_algorithm
[^RandomForest]:Random forest. (n.d.). Retrieved January 23, 2015, from http://en.wikipedia.org/wiki/Random_forest
[^Java]:Java Software. (n.d.). Retrieved January 23, 2015, from https://www.oracle.com/java/index.html
[^Weka]:Weka 3: Data Mining Software in Java. (n.d.). Retrieved January 23, 2015, from http://www.cs.waikato.ac.nz/~ml/weka/
[^Waikato]:The University of Waikato. (n.d.). Retrieved January 23, 2015, from http://www.waikato.ac.nz

## Table of contents

## Body

### Application domain and research problem

The research problem underlying this project can be formulated as follows:

> Is it possible to construct and apply a text-based classifier, trained on works by English authors, in order to predict the author of an unseen book?

We have approached this problem from a text mining perspective, applying three different existing text classification algorithms to a data set of a fixed number of authors.

We have opted to classify English works because these are readily available (more on this in section <font color="red">XXX</font>). We have also opted to classify *only* English works because it is trivial to predict the author (from a possibility of three authors), if all three authors write in a different language. Last but not least, the language English is written using the Latin script, which is easily interpreted by us and readily supported by Weka.

Nevertheless, we strongly suspect our classifier will perform well on works in other languages, and even other scripts (provided Weka/Java support these).

### Related previous work

The choice of subject for this project was arrived at by first attempting to solve a similar problem, as proposed in the first draft of our project proposal. This proposal was to classify sentences from scientific papers based on word usage into one of five categories:

1. Aim: sentences about the specific research goal of of the paper.
2. Own: sentences about the author's own work, e.g. methods, results and conclusions.
3. Contrast: sentences describing contrast, comparison or critique of past work.
4. Basis: sentences about work that provide the basis for the work of the paper.
5. Misc: Any other sentences.

For the full project proposal, refer to the additional files.

Based on feedback from Prof. dr. Tom Heskes we decided against continuing with this problem. Instead, we chose to create a similar problem (text classification) with a larger data set. This resulted in the research problem as presented in this report.

An inspiration for this type of problem came from Simone Teufel's PhD thesis on Argumentative Zoning[^ArgumentativeZoning].

Finally, we cite a classical text mining problem, namely the problem of predicting the sentiment of IMDB movie reviews based on 1,000 positive and 1,000 negative reviews, as an inspiration and introduction to this type of problem.[^Sentiment]

[^ArgumentativeZoning]: Teufel, S. (1999). Argumentative Zoning. Retrieved January 23, 2015, from http://www.cl.cam.ac.uk/~sht25/az.html
[^Sentiment]:WEKA Text Classification for First Time & Beginner Users. (n.d.). Retrieved January 23, 2015, from https://www.youtube.com/watch?v=IY29uC4uem8

### Data set / data set collection

Our data set consists of classic English literary works in the public domain. Many works of English authors can be found online, via initiatives such as the Gutenberg project[^Gutenberg], Wikisource[^Wikisource], and Wikilivres[^Wikilivres]. It is, perhaps unsurprisingly, from these sources that we have compiled our data set. 

We have created two datasets of three authors, six books per author, first five chapters per book. This set was then split into a training set of five books (per author) and a test set of one book (per author). The reasoning behind creating two datasets was twofold:

1. To be able to apply the same classifier to two different sets so as to confirm the classifier's validity. For example, Sir Arthur Conan Doyle has a very distinct subject in many of his books: Sherlock Holmes. It is thus not very hard to recognise his writings. To rule out the possibility that our classifier only performed well because a certain dataset was too easy, we have opted to use another.
2. To be able to determine the classifier's scalability. After applying the classifier to each of our datasets separately, we have merged the datasets and applied to classifier to a dataset twice as large. This allowed us to determine in part whether our classifier only performed well on a limited amount of authors.

What follows are the authors and their books included in our dataset.

#### Dataset 1

- Training set:
	1. Charles Dickens[^CD]
		1. Bleak House
		2. Great Expectations
		3. Hard Times
		4. Nicholas Nickleby
		5. The Pickwick Papers
	2. Mark Twain[^MT]
		1. A Connecticut Yankee in King Arthur's Court
		2. The Adventures of Huckleberry Finn
		3. The American Claimant
		4. The Mysterious Stranger
		5. The Prince and the Pauper
	3. Sir Arthur Conan Doyle[^SACD]
		1. A Study in Scarlet
		2. The Great Shadow
		3. The Hound of the Baskervilles
		4. The Mystery of Clobber
		5. The Valley of Fear
- Test set:
	1. Oliver Twist by Charles Dickens
	2. The Adventures of Tom Sawyer by Mark Twain
	3. The Sign of the Four by Sir Arthur Conan Doyle

#### Dataset 2

- Training set:
	1. George Orwell[^GO]
		1. A Clergyman's Daughter
		2. Animal Farm
		3. Down and Out in Paris and London
		4. Homage to Wigan Pier
		5. Nineteen Eighty-Four
	2. Herman Melville[^HM]
		1. Israel
		2. Moby Dick
		3. Pierre
		4. The Confidence Man
		5. White-Jacket
	3. Jane Austen[^JA]
		1. Emma
		2. Mansfield Park
		3. Northanger Abbey
		4. Pride and Prejudice
		5. Sense and Sensibility
- Test set:
	1. Keep the Aspidistra Flying by George Orwell
	2. Redburn by Herman Melville
	3. Persuasion by Jane Austen

[^Gutenberg]:Project Gutenberg. (n.d.). Retrieved January 23, 2015, from http://www.gutenberg.org
[^Wikisource]:Main Page. (n.d.). Retrieved January 23, 2015, from http://en.wikisource.org/wiki/Main_Page
[^Wikilivres]:Main Page. (n.d.). Retrieved January 23, 2015, from http://wikilivres.ca/wiki/Main_Page
[^CD]:Author:Charles Dickens. (n.d.). Retrieved January 23, 2015, from http://en.wikisource.org/wiki/Author:Charles_Dickens
[^MT]:Author:Samuel Langhorne Clemens. (n.d.). Retrieved January 23, 2015, from http://en.wikisource.org/wiki/Author:Samuel_Langhorne_Clemens
[^SACD]:Author:Arthur Conan Doyle. (n.d.). Retrieved January 23, 2015, from http://en.wikisource.org/wiki/Author:Arthur_Conan_Doyle
[^GO]:Author:George Orwell. (n.d.). Retrieved January 23, 2015, from http://en.wikisource.org/wiki/Author:George_Orwell
[^HM]:Author:Herman Melville. (n.d.). Retrieved January 23, 2015, from http://en.wikisource.org/wiki/Author:Herman_Melville
[^JA]:Author:Jane Austen. (n.d.). Retrieved January 23, 2015, from http://en.wikisource.org/wiki/Author:Jane_Austen

### Preprocessing methods

### Approach/method & rationale behind decisions

### Main results

### Analysis of results & evaluation of project outcome

### Future directions to improve results