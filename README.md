# Design a Lucene Search Engine



## tl&dr
- This job program implements an inverted-index search. you can use it to search ![wikipedia data](http://snap.stanford.edu/data/wikispeedia/wikispeedia_articles_plaintext.tar.gz) or any big data. I use AWS EMR to run this but its portable with any hadoop platform. 


## what is inverted index 
To find a book in a library, there are two methods:

- Method 1:Forward Index => use index to find book, too slow

![](https://s3-us-west-2.amazonaws.com/donot-delete-github-image/Screen+Shot+2019-01-26+at+4.13.43+PM.png)

- Method 2: Inverted Index => use keyword to find the list of book id

![](https://s3-us-west-2.amazonaws.com/donot-delete-github-image/Screen+Shot+2019-01-26+at+4.14.20+PM.png)


this project uses the mapreduce framework to create inverted index for a given text

mapper split each word to pairs < word, location>

![](https://s3-us-west-2.amazonaws.com/donot-delete-github-image/Screen+Shot+2019-01-26+at+4.16.55+PM.png)


 reducer merge all the same word
 
 
![](https://s3-us-west-2.amazonaws.com/donot-delete-github-image/Screen+Shot+2019-01-26+at+4.17.04+PM.png)

## what is ignoreWords.txt
these are the list of words that you choose to ignore as search result. you can choose to supply or not supply it

## Pre-req

### Compile the jar
1. clone this repo
2. maven clean, maven install 

## method 1: run on AWS EMR
1. Create a EC2 keypair PEM file to used for EMR
2. Create a S3 bucket 
3. Upload the jar in this repo to your s3 bucket ( You can make change and compile your own as well)
4. Upload the input files to the same s3 bucket 
5. Create a EMR cluster, choose EMR version that uses hadoop version 2.7.3 (to use a different hadoop version, change the pom.xml)
6. After EMR provision finish, add a step for custom jar
- for JAR location, point to the jar in the s3 bucket
- for argument:
us the following if wish to supply ignorewords:
```
s3://<your-bucket>/<your-input-foldere> s3://<your-bucket>/out s3://<your-bucket>/ignorewords.txt
```
us the following if not:
```
s3://<your-bucket>/<your-input-foldere> s3://<your-bucket>/out 
```
7. check output on s3 folder

