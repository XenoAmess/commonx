# commonx

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.xenoamess/commonx/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.xenoamess/commonx)

Commonx, a package of Java utility classes for the classes that are in java's hierarchy, or are considered to be so standard as to justify existence in java.

License
----------
In short, this project is under the [GPLv2 with classpath exception](https://github.com/XenoAmess/commonx/blob/master/LICENSE).

We have to choose such a license because a lot of codes are modified from java core library, which follows [GPLv2 with classpath exception](https://github.com/XenoAmess/commonx/blob/master/LICENSE)

Since We copied code there we must follow [GPLv2 with classpath exception](https://github.com/XenoAmess/commonx/blob/master/LICENSE).

But if you only want to use this library commonly (but not copy the source code) then in most cases you have no need to care about [GPLv2 with classpath exception](https://github.com/XenoAmess/commonx/blob/master/LICENSE) because you are in the classpath exception.
 
In short, if in your use case you can use java core library, then you can use this library.

If a file in this project have a header of additional licenses, you shall follow it as well when you want to change that file.

If you see a file which you don't think it using other [GPLv2 with classpath exception](https://github.com/XenoAmess/commonx/blob/master/LICENSE) codes/libraries, and you just want a MIT licensed copy of it, please contact me (xenoamess@gmail.com).

Notice
----------
Some(Nearly all of) code of this project is from others, or wrapper of other libraries, or modified from other's code. 

Thanks for their effort. 

For the open source.

I will point codes that from others and obey their licenses.

brief introduction 
----------

In short this library are composed by two parts:

---------- 

#commons: 
some commonly used classes / utils.

###commons.as_final_field:
To mark a field as "AsFinal".

This annotation is mainly used when a field shall be final, but it can't.

For example, lazy build for a singleton factory, or a resource field which shall be final but have to wait aftersome other classes init.

###commons.code_generators:
Utils for generating codes of this library. No meaning for others.

###commons.primitive:
Primitive collections that compatible with generic interfaces.

For example, DoubleArrayList is an AbstractList<Double> and List<Double>, but in core it is primitive implement, and provides primitive method choice, and is very fast.

The basic usage of such classes is:
1. when you want to use a method that accept or return generic, use methodname + Primitive instead.
for example, add -> addPrimitive,set -> setPrimitive
2. do not use foreach, use iterator and nextPrimitive instead.

Now we only translated class about ArrayList, and primitive array lists can run 10 times faster than ArrayList(according to test data.).

We planned to translate at least LinkedList, HashSet, TreeSet, HashMap, TreeMap.

And if I still have time for this, ConcurrentHashMap will be considered.

![DoubleArrayList](pictures\DoubleArrayList.png)


---------- 

##commonx: 
Extensions of some libraries / utility of some java core classes.

###commonx.org.apache.commons.lang3
Some utils that too crazy / unsafe / unclear that refused by org.apache.commons.lang3.

But they are good functions... at least they are useful... sometimes... 

###commonx.java.util.Arraysx
Some extends for java.util.Arrays

###commonx.java.util.concurrent.atomic
Flip method for AtomicBoolean.

###commonx.java.lang.IllegalArgumentExceptionUtilsx
Util for detecting null parameters and creating IllegalArgumentExceptions

Notice that most methods of this classes are generated, so be careful when changing it.