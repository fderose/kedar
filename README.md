# kedar

This repository contains the original file I sent you, Trooly.java.

It also contains an additional file, TroolyGeneralized.java, which is a "generalization" of Trooly.java. TroolyGeneralized takes a single argument, the name of a file containing input data. In this file, each line defines a record representing an entity. Each record is made up of a list of attributes (delimited by ,). Each attribute consists of an attribute type and an attribute value (delimited by :). Consider, for example, the following line:

&nbsp;&nbsp;name:f,email:e5,email:e6,address:a2 // I chose this simple format, but this could be, say, xml.

This line represents the entity with the following attributes:

&nbsp;&nbsp;name == f<br>
&nbsp;&nbsp;email == e5<br>
&nbsp;&nbsp;email == e6 // it is possible to have several attributes of the same type<br>
&nbsp;&nbsp;address == a2<br>

TroolyGeneralized creates clusters (transitive closures) of entities that are connected by any one of multiple attributes.

So, for example, if you run TroolyGeneralized on the file data/input, it produces the following output:

&nbsp;&nbsp;name:b,email:e2,address:a1
&nbsp;&nbsp;name:c,email:e3,email:e2<br>
&nbsp;&nbsp;name:d,email:e3,email:e4<br>
&nbsp;&nbsp;name:e,email:e5,email:e4<br>
&nbsp;&nbsp;name:f,email:e5,email:e6,address:a2<br>
&nbsp;&nbsp;name:h,address:a2<br>
&nbsp;&nbsp;name:j,address:a2<br>
&nbsp;&nbsp;name:l,address:a1<br>

&nbsp;&nbsp;name:a,email:e1,address:a3<br>
&nbsp;&nbsp;name:i,address:a3<br>
&nbsp;&nbsp;name:k,address:a3<br>

&nbsp;&nbsp;name:g,email:e7<br>

&nbsp;&nbsp;name:m,foo:bar<br>
&nbsp;&nbsp;name:n,foo:bar<br>

where each group of lines represents a cluster of entities that is the transitive closure over various attributes. For example, in the first group of lines,

&nbsp;&nbsp;b is related to c in that they share the same email attribute, e2<br>
&nbsp;&nbsp;c is related to d in that they share the same email attribute, e3<br>
&nbsp;&nbsp;...<br>
&nbsp;&nbsp;e is related to f in that they share the same email attribute, e5<br>
&nbsp;&nbsp;f is related to h in that they share the same address attribute, a2<br>

and so on.
