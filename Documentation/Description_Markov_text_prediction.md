### Descriptive document: Markov text generator
Aleksi Kaakinen <alkaakin@helsinki.fi>

### Text generation algorithm

The topic for my Algorithms and AI course exercise program will be implementing a text generation program. The text generation algorithm will be based on natural language processing (NLP) - the procession of natural language datasets such as speeches or corpora of text. The implementation has its basis in rule- and probability-based approach.

## Specification

The program will first take as an input a text corpora such as a .txt file and split this source text into 3-grams (n-grams of 3 letters). These 3-grams will then be split into two - the first part being the first two letters and the latter the last two letters. The first two letters will be stores as a node in a Trie data structure, and the latter ones as a child node to the first node. The program will also save information about this occurrence (as in the third letter following the first two letter sequence) as a probability to the MarkovModel class, which holds information about such probabilities. As a result, the whole source text will be codified to the Trie structure holding also information about the probability of all the occurrences.

As a result, the program will be able to take as an input a given n-gram (2-gram for this course) in form of sequential letters of the alphabet. The program will look for letters that follow the given n-gram and choose the letter with the highest occurrence probability among these letters. After the initial inquiry, the program will next look for letters in succession for the second letter of the initial n-gram and the letter chosen at that time of the process (such as "ca" as an input, the program would find "t" as the succession letter, so it would next be looking for the followers of "at"). 

Given a large enough input source, the program should be fairly good at mimicking natural languages (restricted to the Latin alphabet for the needs of this course).  

## Time complexity

The Trie is a k-ary search tree used for storing and searching specific keys (letters and letter combinations) from a set. 

-- this part will be completed later --

## Other information

I'm a degree student at University of Helsinki (Kandidaatin tutkinto)