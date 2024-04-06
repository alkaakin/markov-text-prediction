### Weekly report 3 (created 30th March, updted 6th April)


## What's been done this week

I've implemented the main functionalities this week. We had a discussion with the course instructor and I feel more confident about the implementation now. I still need to change the name of the project from text predictor to text (name) generation.

Update: During the week 30th March - 6th April, I've made significant progress with the program. All the main functionalities should now be in their place.

## The implementation

I've created the main classes (Trie, TrieNode, ExcelReader, FrequencyTable). I feel like being stuck in the former implementations has really helped me see the big picture and I've been able to code pretty straightforwardly. What's changed is that I'm not storing full words as individual characters/letters in the Trie anymore, since the instructor pointed out that the meaning of the Trie is to store the letters are n-grams (such as "su", "uc", and "ch", and operate with these) and use n number of letters to 'predict' the most probable next letter. This has really helped me to understand the structure of the Trie and word generation. The old implementation would not have worked in the end, since performing a DFS on the full-word Trie would have been at best very arduous and most likely impossible. Now the program only needs to search for up to the nth letter and then return to root again.

Update: During the week 30th March - 6th April, I implemented the scaling of ngram processing, meaning that the program is now able to store (theoretically) nGrams of any length and create words based upon these having been stored in Trie. I've also implemented a more user-friendly interface in the App class (asking first for the order of the ngram to be used and next for the size of the dataset to be used).

## Problems and questions for next week

I have certain things to be implemented next week and further on, with the following prioritization:

1) I need to make sure that the word generation follows the implied logic, so that the logic is precise enough. Some of the words I keep getting seem to be a bit off, so this needs to be investigated.
2) I need to think of the need to implement a word.terminal tracker to stop the word generator adding letters to a word if the String in question already forms a name that's in the original list.
3) I need to implement the needed testing methods before the peer reviews start. 
4) Implementation document and testing document have to be created.
5) build.gradle document needs to be reviewed.

After these tweaks, the algorithm should be close to being in its final state.