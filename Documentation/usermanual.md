### Usermanual

## Name Generator

The program implements a name generator. The source input for the program is a list of approximately 10 000 names. Any other excel file could be used as an input, but right now the mod.xsls file holds Finnish male first names.

Using the program is very straightforward:

1) Run the app in any IDE or terminal. The program will ask you for the following parameters:

a) Order. The order given will tell the program the length of the "n-grams" stored in the Trie model, translating to how complex the outputs will be. The higher the order, the better (theoretically) the results will be - but this will also depend on the volume of the source material used.

b) Size of the dataset between 1 - 10,000. For a dataset of 100 names, you get rather popular Finnish names, but the output might be a very short name because of how the Trie is structured. For a dataset of several thousands, the given name should usually fulfil the length requirement given in point 3), but it might not resemble a known name very much. But attention - the dataset is not weighed according to which first name is the most popular.

c) The length of the name, between 3-10 letters. Mind you - it is only the desirable length. The final name's length depends on the other parameters. On larger datasets and lower orders you usually get longer names, but they might not resemble real names. Compare this to low-mid size datasets and higher orders with shorter name requirement and you get names that resemble actual Finnish first names for males.

Some of the outputs given with different parameters:

1) Order 4 - Size 5000 - Length 5: MITRI
2) Order 2 - Size 300 - Length 6: TAPAAT
3) Order 4 - Size 6000 - Length 6: ALLE-J
4) Order 1 - Size 1000 - Length 9: LEVIMPEKU
5) Order 4- Size 200 - Length 4: KRIS 
6) Order 3 - Size 3000 - Length 6: ENNARD
7) Order 3 - Size 3000 - Length 6: PEKANP
