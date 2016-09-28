google collections 推荐尽量使用 Iterable。

为何如此强调Iterators 和 Iterables 
Why so much emphasis on Iterators and Iterables?

In general, our methods do not require a Collection to be passed in when an Iterable or Iterator would suffice. This 
distinction is important to us, as sometimes at Google we work with very large quantities of data, which may be too large
to fit in memory, but which can be traversed from beginning to end in the course of some computation.Such data 
structures can be implemented as collections, but most of their methods would have to either throw an exception, return a 
wrong answer, or perform abysmally. For these situations, Collection is a very poor fit; a square peg in a round hole.

An Iterator represents a one-way scrollable "stream" of elements, and an Iterable is anything which can spawn 
independent iterators. A Collection is much, much more than this, so we only require it when we need to.