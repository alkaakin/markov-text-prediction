### Weekly report 3


## What's been done this week

I've implemented the main functionalities this week. We had a discussion with the course instructor and I feel more confident about the implementation now. I still need to change the name of the project from text predictor to text (name) generation.

## The implementation

I've created the main classes (Trie, TrieNode, ExcelReader, FrequencyTable) yesterday and today on Saturday. I feel like being stuck in the former implementations has really helped me see the big picture and I've been able to code pretty straightforwardly. What's changed is that I'm not storing full words as individual characters/letters in the Trie anymore, since the instructor pointed out that the meaning of the Trie is to store the letters are n-grams (such as "su", "uc", and "ch", and operate with these) and use n number of letters to 'predict' the most probable next letter. This has really helped me to understand the structure of the Trie and word generation. The old implementation would not have worked in the end, since performing a DFS on the full-word Trie would have been at best very arduous and most likely impossible. Now the program only needs to search for up to the nth letter and then return to root again.

## Problems and questions for next week

I'm going to need to find a way to scale the problem from only handling bigrams to being able to handle any n-gram. Fourth order ngrams are the ones that need to be included (letters based on three preceding letters), so the depth of the Trie will be 4 + root at the maximum.

I'll also need to implement JUnit testing next week. I'm optimistic about my project's future, but a lot still needs to be done.