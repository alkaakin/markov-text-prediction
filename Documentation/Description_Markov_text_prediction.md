### Descriptive document: Markov text generator
Aleksi Kaakinen <alkaakin@helsinki.fi>

### Text generation algorithm

The topic for my Algorithms and AI course exercise program will be implementing a text generation program (first name generation to be specific). The text generation algorithm will be based on natural language processing (NLP) - the procession of natural language datasets such as speeches or corpora of text. The implementation has its basis in rule- and probability-based approach.

## Specification

The program will first take as an input a corpora such as a .txt or .xlsx file. Individual words in the initial file will be processed in Trie method named wordProcessor(), that will go through each n-gram (initially implemented only for bigrams, 2-letter letterpairs). The individual nGrams will be given to the Insert method, where they are inserted to the Trie along with updating a specific object/class FrequencyTable. 

Insertation of nGrams happens by adding new TrieNodes to the Trie. Insertation starts at root; if root does not contain as its child the first letter of the letterpair, it will be inserted. Then the program will move to this childnode and find out whether that one contains as its child the second letter of the pair. This will be duly inserted, if not. 

The TrieNodes contain information about 1) child nodes of the TrieNode in question (a HashMap<Character, TrieNode> as an attribute of the TrieNode); and 2) frequencies of the children (HashMap<Character, Integer> as an attribute of the FrequencyTable object that is an attribute of the TrieNode). FrequencyTable contains the method updateFrequency for tracking probabilities of individual letters following other letters, i.e. how many times a certain letter succeeds the first letter. 

The program will finally generate name(s) following this logic: a random letter (character) is allotted out fo the trie.root children and that will be the first letter of the name. The next letter of the word will be based on the FrequencyTable of the first letter, and this chain will continue until a certain amount of letters is achieved. The final logic of forming words may change during development.

Given a large enough input source, the program should be fairly good at mimicking natural languages (restricted to the Latin alphabet for the needs of this course).  

The final implementation will work for higher orders of nGrams than just bigrams - also trigrams and quadrigrams. The implementation will initially be only for bigrams.

## Time complexity

The Trie is a k-ary search tree used for storing and searching specific keys (letters and letter combinations) from a set. 

Time complexity of the algorithm is based on the search operation time complexity, which is O(dm), where m is the size of the string parameter key, and d corresponds to the alphabet size. Since alphabet size in Finnish language is 29, the time complexity here is O(29m).

## Other information

I'm a degree student at University of Helsinki (Kandidaatin tutkinto)