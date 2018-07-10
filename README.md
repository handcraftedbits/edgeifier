# Edgeifier [![Maven](https://img.shields.io/maven-metadata/v/http/central.maven.org/maven2/com/handcraftedbits/edgeifier/edgeifier/maven-metadata.xml.svg)](https://mvnrepository.com/artifact/com.handcraftedbits.edgeifier/edgeifier/1.0.1) [![Build Status](https://travis-ci.org/handcraftedbits/edgeifier.svg?branch=master)](https://travis-ci.org/handcraftedbits/edgeifier) [![Coverage Status](https://coveralls.io/repos/github/handcraftedbits/edgeifier/badge.svg)](https://coveralls.io/github/handcraftedbits/edgeifier) [![Javadocs](https://javadoc.io/badge/com.handcraftedbits.edgeifier/edgeifier-api.svg)](https://javadoc.io/doc/com.handcraftedbits.edgeifier/edgeifier-api)

A Java library for generating test values, particularly for edge cases.

# Background

A regular occurrence in testing is the need to generate values that match a _specification_ (for example, "all valid
email addresses") and also values that violate that specification.  In both cases, particular attention must be paid to
_edge cases_, or values that are at the limit (or _edge_) of a specification.  Edgeifier helps generate these values
with a simple, fluent Java API.

# Features

* Simple, fluent Java API that generates infinite streams of values
* Generates primitive, String, and date values
* Can generate custom value types
* No runtime dependencies

# Requirements

Edgeifier requires Java 8 or later.

# Usage

## First Steps

Add the following dependencies to your `pom.xml` file:

```xml
<dependency>
  <groupId>com.handcraftedbits.edgeifier</groupId>
  <artifactId>edgeifier-api</artifactId>
  <version>1.0.1</version>
</dependency>
<dependency>
  <groupId>com.handcraftedbits.edgeifier</groupId>
  <artifactId>edgeifier-impl</artifactId>
  <version>1.0.1</version>
  <scope>runtime</scope>
</dependency>
```

Then, create an `Edgeifier` instance:

```java
Edgeifier edgeifier = new Edgeifier();
```

By default, this will use the current time as a random seed.  In general though, you'll want to use a specific seed
value to ensure that the same values are generated during every test run.  In that case, simply pass the seed value to
the `Edgeifier` constructor:

```java
Edgeifier edgeifier = new Edgeifier(123456L);
```

# Examples

## Primitives

You can use the `Edgeifier` object to declare a _specification_ for any value type.  Once you have a specification, you
can then create an infinite [`Stream`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html) of
values.

For example, let's say we want to test that our `checkValue()` function can only accept values between `0` and `100`.
First, we need to make a `Stream` of integers matching that specification:

```java
Stream<Integer> matching = edgeifier.makeIntsLike().any().between(0, 101).stream(); // Note that maximum value is exclusive.
```

Also, we'll want to make a `Stream` of integers that violate that specification:

```java
Stream<Integer> violating = edgeifier.makeAnyOf(
     edgeifier.makeIntsLike().any().lessThan(0),
     edgeifier.makeIntsLike().any().atLeast(101)).stream();
```

Finally, we can use these `Stream`s to test our function (assume that `checkValue()` returns `true` if the value is
within our defined range and `false` otherwise):

```java
@Test
public void testCheckValue () {
     matching.limit(100).forEach(value -> Assertions.assertTrue(checkValue(value)));
     violating.limit(100).forEach(value -> Assertions.assertFalse(checkValue(value)));
}
```

Note that we use [`Stream.limit()`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#limit-long-)
to limit the number of values generated; the `Stream`s created by Edgeifier are infinite by default.  You should use a
limit that makes sense in your situation.

Consult the [Javadoc](https://javadoc.io/doc/com.handcraftedbits.edgeifier/edgeifier-api/1.0.1/com/handcraftedbits/edgeifier/api/value/primitive/package-summary.html)
for additional information on generating primitive types.

## Strings

Edgefier excels in creating `String`s of arbitrary complexity.  For example, let's assume that we have a URL validator
that will accept any URL accepted by `java.net.URL` with a maximum length of `1024` characters.  We want to test this
validator with a variety of URLs, so let's use the following specification:

* The scheme can be either `http` or `https`
* The hostname can optionally start with `www`
* The hostname can be between `4` and `20` characters in length and can end with `.com`, `.edu`, or `.net`
* The port can be between `80` and `9000` or missing altogether
* There can be between `1` and `4` path segments with length between `1` and `32` characters

The Edgefier API makes it easy to create this specification in code.  First, let's make a `ValueBuilder` for the port
value:

```java
ValueBuilder<String> ports = edgeifier.makeStringsLike()
     .thisOne(":")
     .plus()
     .builder(edgeifier.makeIntsLike().any().between(80, 9001));
```

Notice what we're doing: concatenating the port specifier ("`:`") with the output of another `ValueBuilder` (the one
generating random port values).

We'll also need to make a path segment `ValueBuilder`:

```java
ValueBuilder<String> segments = edgeifier.makeStringsLike()
     .thisOne("/")
     .plus()
     .anyInRange('a', 'z').repeat(1, 33);
```

Now, let's combine that `ValueBuilder` with the one that will generate the rest of the URL:

```java
ValueBuilder<String> urls = edgeifier.makeStringsLike()
     .anyOf("http://", "https://")
     .plus()
     .thisOne("www.").optional()
     .plus()
     .anyInRange('a', 'z').repeat(4, 21)
     .plus()
     .anyOf(".com", ".edu", ".net")
     .plus()
     .builder(ports).optional()
     .plus()
     .builder(segments).repeat(1, 5);
```

Notice that we can generate `String`s of arbitrary complexity by combining fragments consisting of either generated
`String` values or the output of other `ValueBuilder` objects via `plus()`.

What kind of values will this `ValueBuilder` generate?  Let's test it out, but for sake of readability we'll use
`Stream.filter()` to only return URLs that are exactly `60` characters in length:

```java
urls.stream().filter(value -> value.length() == 60).limit(5).forEach(System.out::println);
```

Here's what we get:

```
http://ztctgwxyiabvfwkkn.edu/so/uqhdcmsulkmo/fxaodnssqufyhpx
https://diabvmmmoduidpf.com/gfprsxg/dgshizrcabzkkhqeluspsnpn
http://rybzux.edu/kdheasaxgqmquq/uapvgcpucntrdfdrmqiojhyzadu
https://www.owvdy.com:5446/poarcmqikpbr/xznbnrjl/osfbigcvsrt
https://www.pbjn.com:7384/psh/h/xauvauxnsxtwpwdaeu/uvzdexmze
```

To _positively_ test our validator, we can simply use this `ValueBuilder` to generate any number of URLs up to `1024`
characters in length.  Keeping in mind edge cases though, we should also specifically test URLs that are exactly `1024`
characters in length.  To _negatively_ test our validator, we can use this same `ValueBuilder` and test with URLs of
length greater than `1024` characters.  We should also make a similar `ValueBuilder` that contains e.g., bad schemes,
invalid ports, etc.  More information about generating `String` values can be found in the
[Javadoc](https://javadoc.io/doc/com.handcraftedbits.edgeifier/edgeifier-api/1.0.1/com/handcraftedbits/edgeifier/api/value/string/package-summary.html).

As an added convenience, Edgeifier includes a utility class to help generate characters from all of the Unicode
character classes.  See the [`UnicodeBuilder` Javadoc](https://javadoc.io/doc/com.handcraftedbits.edgeifier/edgeifier-api/1.0.1/com/handcraftedbits/edgeifier/api/util/UnicodeBuilder.html)
for more information.

## Collections

Edgeifier can be used to create collections (via `java.util.List`) of values of arbitrary length.  For example, we could
create a `ValueBuilder` capable of generating lists of positive integers with lengths between `5` and `10` elements like
so:

```java
ValueBuilder<List<Integer>> lists = edgeifier.makeListsLike()
     .any(edgeifier.makeIntsLike().any().atLeast(0))
     .withLengthBetween(5, 11); // Note that maximum length is exclusive.
```

For more information, see the [Javadoc](https://javadoc.io/doc/com.handcraftedbits.edgeifier/edgeifier-api/1.0.1/com/handcraftedbits/edgeifier/api/value/collection/package-summary.html).

## Custom Types

Edgeifier can generate custom types via 
[`CustomValueProvider`](https://javadoc.io/doc/com.handcraftedbits.edgeifier/edgeifier-api/1.0.1/com/handcraftedbits/edgeifier/api/value/custom/CustomValueProvider.html)
instances.  As an example, let's use Edgeifier to generate random instances of the following bean:

```java
public class TestBean {
     private int value;

     public TestBean (int value) {
          this.value = value;
     }

     public int getValue () {
          return this.value;
     }
}
```

First, we need to create a `CustomValueProvider` capable of creating `TestBean` instances:

```java
public class TestBeanValueProvider implements CustomValueProvider<TestBean> {
     private int max = 10;

     public TestBean generateValue (Edgeifier edgeifier) {
          return new TestBean(edgeifier.makeIntsLike().any().between(0, this.max).stream().findFirst().get());
     }

     @Override
     public void setProperty (String name, Object value) {
          if (name.equals("max")) {
               this.max = (int) value;
          }
     }
}
```

Notice the `setProperty` method: this is used to set arbitrary custom properties which control the generated value.  In
this case, if a property named `max` is set, we'll use that to control the maximum value associated with the generated
`TestBean` instance.  The `generateValue` method is used to create our `TestBean` instance.  Notice that the current
Edgeifier is provided.  With it, you can generate any type of value required to populate your bean.  In this example we
are generating an infinite stream of integers between `0` and `max` and selecting the first element as the value for
`TestBean.value`.

Next, we need to create a 
[`CustomValueProviderFactory`](https://javadoc.io/doc/com.handcraftedbits.edgeifier/edgeifier-api/1.0.1/com/handcraftedbits/edgeifier/api/value/custom/CustomValueProviderFactory.html)
class that will help Edgeifier create instances of our `CustomValueProvider`:

```java
public class TestBeanValueProviderFactory implements CustomValueProviderFactory<TestBean> {
     @Override
     public CustomValueProvider<TestBean> newCustomValueProvider () {
          return new TestBeanValueProvider();
     }

     @Override
     public Class<TestBean> valueClass () {
          return TestBean.class;
     }
}
```

We can register this class programmatically:

```java
edgeifier.registerCustomValueProviderFactory(new TestBeanValueProviderFactory());
```

Or by including a file named
`META-INF/services/com.handcraftedbits.edgeifier.api.value.custom.CustomValueProviderFactory` containing the
fully-qualified name of our `TestBeanValueProviderFactory` on the classpath.

Finally, we can make use of this custom type with our Edgeifier instance:

```java
edgeifier.makeValuesLike().any(TestBean.class).stream() // Use the default max value of 10.
edgeifier.makeValuesLike().any(TestBean.class).withProperty("max", 100).stream(); // Use a custom max value.
```
