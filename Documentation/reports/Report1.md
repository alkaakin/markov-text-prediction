### Weekly report 1

I've started the course over again this week - I tried doing it last period, but couldn't finish the algorithm. 

## What's been done this week

This week I've mainly been trying to find out what went wrong last time and how I should approach the problem this time. I've tried to document it down already in the documentation/specification document.

I'll be approaching the problem using n-gram input and trie storing. This will most likely be a good compromise between using single letters and full words. I was able to implement the program earlier using full words, but that wouldn't give the program the level of granularity needed. 

## The implementation

I haven't implemented the program this week, but I've been planning it more thoroughly this time. I've also searched for information on how to store the data efficiently. Using "running" n-grams (If the given input was "as", it would first look for children of "ca", and if the most probable child was "at", it would then do the same for "at" etc.) is clearer to me now - during my first run with the course I wasn't able to understand this and this is what drove me towards implementing the whole thing with words instead of letters.

## Problems and questions ahead

I see two main problems/unclarities ahead:

1) Implementing the search method for tree. If I had to look for the children of "ca", it is possible that "ca" is found under the root of the trie - which will make it rather straightforward at first. But what if "ca" is hidden somewhere under the other 'leaves' of the Trie? Let's say we have the word "Abracadabra" and "Avocado" in the Trie alongside "Catenate". We get to "Catenate" fast from the root, but how do I make sure the program also searches for Avocado and Abracadabra? 
2) Filling the Markov Model probability table. I guess this won't be a problem after all, but I just haven't thought about it that much. 

## Next week

Next week, I'll try to complete the basic algorithm for filling the Trie with the source text and also the MarkovModel class for filling in the probabilities. I will also need to think thoroughly of the search method I'll be using to look for the letters.