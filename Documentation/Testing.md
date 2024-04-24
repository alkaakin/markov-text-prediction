### AppTest

There are two tests for the main app itself: the LengthRequirement() and the testPromptNumber(). LengthRequirement() creates a NameGenerator with given parametres (order 2, volume 500, wordlength 5). It tests whether the given name is at least 5 characters long with the given parametres. The testPromptNumber() test tests if the promptNumber class works - in other words whether the given number is within the allowed limits. 

### TrieTest

The trie tests test for inserting a certain nGram and whether the right letters succeed the given ones (testInsertAndFind), whether the given nGrams are too short or too long for the method (testBoundaries), possible followers of a given nGram (testForFollowers) and whether there are no followers to a node (testNoFollowers). The class also tests for possible output after delivering an empty input (testEmptyInput).

### InputHandler

Needs implementation.

### NameGenerator

Needs implementation.

### Results of testing

All of the tests implemented so far are passing. Especially the Trie class testing covers the class thoroughly.