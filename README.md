# Design a Lucene Search Engine

To find a book in a library, there are two methods:

- Method 1:Forward Index => use index to find book, too slow

![](https://s3-us-west-2.amazonaws.com/donot-delete-github-image/Screen+Shot+2019-01-26+at+4.13.43+PM.png)

- Method 2: Inverted Index => use keyword to find the list of book id

![](https://s3-us-west-2.amazonaws.com/donot-delete-github-image/Screen+Shot+2019-01-26+at+4.14.20+PM.png)


this project uses the mapreduce method to create inverted index for a given text

mapper split each word to pairs < word, location>

![](https://s3-us-west-2.amazonaws.com/donot-delete-github-image/Screen+Shot+2019-01-26+at+4.16.55+PM.png)


 reducer merge all the same word
 
 
![](https://s3-us-west-2.amazonaws.com/donot-delete-github-image/Screen+Shot+2019-01-26+at+4.17.04+PM.png)

