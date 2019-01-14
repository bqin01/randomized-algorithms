# randomized-algorithms
An independent project focusing on runtimes of certain algorithms, optimizations involving randomizations, and a lot of research. 

## Why should we care?
In computer science, we often think about the creation of algorithms to perform certain tasks, and we are always looking to improve these algorithms both in terms of time and resources. Attempting to sort an array using Quicksort or mergesort has a runtime of ```O(n log n)```, a significant improvement from other classical sorting methods that run in ```O(n^2)``` time.

There is, however, an extra criterion that does not get discussed and that's the accuracy or success rate of an algorithm. Of course, this is often not considered because for most algorithms, this number is 1. It's 100%. But what if we produced an algorithm that didn't need to have a 100% success rate? This is the key to Randomized Algorithms: algorithms designed to use randomness to improve runtimes at the cost of having a sub-100%  success rate. Quicksort, for example, chooses a pivot point at random before proceeding. While this means that runtimes may differ between every instance of Quicksort, the overall runtime of Quicksort is dramatically improved because of this.

The study of randomized algorithms is important because many existing algorithms are likely going to have randomizable elements that can be exploited to get a better best-case performance that may result in an overall better average-case performance with negligible drawbacks. Improvements on certain algorithms will allow for easier and quicker handling of big data as well as faster computations for all.
