### Implementation document: Markov text generator

### General Structure 

The structure of the program is divided in three classes within one package: the class TextGenerator, class InputHandler and class Trie. The Trie class is the backbone of the program, providing a data structure for storing nGrams. nGrams are sequences of words. InputHandler class is being used to extracting data from an excel file and splitting this data (names) to such nGrams. The nGrams (length 2-5 according to Trie order parametre) are stored to Trie. The TextGenerator class generates the names based on the data stored to Trie.

The general logic of the algorithm is as follows:

1) New Trie(order) generated based on order parametre (the length of the nGrams to be stored; the longer the nGram, the better the results - if the length of the word is short enough). Trie holds TrieNodes with attributes HashMap children for storing the child nodes, as well as int frequency for checking how frequently the node has been passed in the Trie.
2) InputHandler splits excel file words into nGrams and inserts them into the Trie. The insert follows this logic: 
    Check for first letter of the nGram under trie root node. If no node exists, create one. Move to this node. Check for the next letter. Repeat until nGram has been stored.
3) TextGenerator generates the words. It used the trieFind method as many times as desired for the correct length of the word. If there are no followers to the node in the Trie, the method will add a space to the name's end. The method's logic is the following:
    While word.length < length chosen by the user, check if the length of the word is 0. If it's 0, choose a letter randomly under the root, and add it to the word. The length should now be 1. For lengths more than 0, look for the last n(order) letters added to the word, and choose one of the followers of the last letter (node.children) randomly. Add this letter to the word. Repeat until word.length = length chosen by user. 

### Achieved complexities


### Deficiencies and ideas for improvement

It seems that as of now, the order chosen by the user will affect the algorithm in a bit of an imbalanced way. The higher the order, the closer the results will be to real names - but this only applies if the dataset is high enough (>5000) and the desired length of the word is at max 5. In a bit of the same way low orders (1-2) and smaller datasets will yield inapproximate and nonsensical results. The optimal combination of data and orders should thus be optimized.

### Use of LLMs

I've used ChatGPT 4 for debugging my code and some ideas regarding the trie structure and pseudocode. However, most of the times I've tried to get some help from ChatGPT, it's ended into a worse situation than I had with my code earlier. One of the biggest reasons for struggling with the code has been that the logic of the end product's been lacking - and that hasn't had anything to do with the code itself.

### References