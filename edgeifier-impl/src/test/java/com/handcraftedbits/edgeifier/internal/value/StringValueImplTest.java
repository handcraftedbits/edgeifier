/**
 * Copyright (C) 2018 HandcraftedBits
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.handcraftedbits.edgeifier.internal.value;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.handcraftedbits.edgeifier.api.value.ValueBuilder;

class StringValueImplTest {
     @Test
     void testAnyInAlphabetMissingValues () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeStringsLike().anyInAlphabet(new int[0]);
          });

          Assertions.assertEquals(TypeMessages.getMessageMissingValues(), e.getMessage());
     }

     @Test
     void testAnyInAlphabetNullValues () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeStringsLike().anyInAlphabet(null);
          });

          Assertions.assertEquals(TypeMessages.getMessageMissingValues(), e.getMessage());
     }

     @Test
     void testAnyInAlphabetOptional () {
          final ValueBuilder<String> builder = ValueHelper.edgeifier.makeStringsLike().anyInAlphabet('a').optional();

          Assertions.assertAll(
               () -> Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), "a", "")),
               () -> Assertions.assertFalse(ValueHelper.streamOnlyContains(builder.stream(), "a")));
     }

     @Test
     void testAnyInAlphabetPlus () {
          final ValueBuilder<String> builder = ValueHelper.edgeifier.makeStringsLike()
               .anyInAlphabet('a', 'b')
               .plus()
               .thisOne("xyz");

          Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), "axyz", "bxyz"));
     }

     @Test
     void testAnyInAlphabetRepeatFixed () {
          final ValueBuilder<String> builder = ValueHelper.edgeifier.makeStringsLike().anyInAlphabet('a', 'b').repeat(
               2);

          Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), "aa", "ab", "ba", "bb"));
     }

     @Test
     void testAnyInAlphabetRepeatRandom () {
          final ValueBuilder<String> builder = ValueHelper.edgeifier.makeStringsLike().anyInAlphabet('a', 'b').repeat(1,
               2);

          Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), "a", "b", "aa", "ab", "ba", "bb"));
     }

     @Test
     void testAnyInAlphabetValid () {
          final ValueBuilder<String> builder = ValueHelper.edgeifier.makeStringsLike().anyInAlphabet('a', 'b', 'c');

          Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), "a", "b", "c"));
     }

     @Test
     void testAnyInRangeInvalid () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeStringsLike().anyInRange('c', 'a');
          });

          Assertions.assertEquals(TypeMessages.getMessageInvalidRange('c', 'a'), e.getMessage());
     }

     @Test
     void testAnyInRangeOptional () {
          final ValueBuilder<String> builder = ValueHelper.edgeifier.makeStringsLike().anyInRange('a', 'c').optional();

          Assertions.assertAll(
               () -> Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), "a", "b", "c", "")),
               () -> Assertions.assertFalse(ValueHelper.streamOnlyContains(builder.stream(), "a", "b", "c")));
     }

     @Test
     void testAnyInRangePlus () {
          final ValueBuilder<String> builder = ValueHelper.edgeifier.makeStringsLike()
               .anyInRange('a', 'c')
               .plus()
               .thisOne("xyz");

          Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), "axyz", "bxyz", "cxyz"));
     }

     @Test
     void testAnyInRangeRepeatFixed () {
          final ValueBuilder<String> builder = ValueHelper.edgeifier.makeStringsLike().anyInRange('a', 'c').repeat(2);

          Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), "aa", "ab", "ac", "ba", "bb", "bc",
               "ca", "cb",
               "cc"));
     }

     @Test
     void testAnyInRangeRepeatRandom () {
          final ValueBuilder<String> builder = ValueHelper.edgeifier.makeStringsLike().anyInRange('a', 'c').repeat(1,
               2);

          Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), "a", "b", "c", "aa", "ab", "ac", "ba",
               "bb", "bc",
               "ca", "cb", "cc"));
     }

     @Test
     void testAnyInRangeValid () {
          final ValueBuilder<String> builder = ValueHelper.edgeifier.makeStringsLike().anyInRange('0', '3');

          Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), "0", "1", "2", "3"));
     }

     @Test
     void testAnyOfMissingValues () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeStringsLike().anyOf(new String[0]);
          });

          Assertions.assertEquals(TypeMessages.getMessageMissingValues(), e.getMessage());
     }

     @Test
     void testAnyOfNullValue () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeStringsLike().anyOf((String) null);
          });

          Assertions.assertEquals(TypeMessages.getMessageValueMustNotBeNull(), e.getMessage());
     }

     @Test
     void testAnyOfNullValues () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeStringsLike().anyOf((String[]) null);
          });

          Assertions.assertEquals(TypeMessages.getMessageMissingValues(), e.getMessage());
     }

     @Test
     void testAnyOfOptional () {
          final ValueBuilder<String> builder = ValueHelper.edgeifier.makeStringsLike().anyOf("a", "9", "!").optional();

          Assertions.assertAll(
               () -> Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), "a", "9", "!", "")),
               () -> Assertions.assertFalse(ValueHelper.streamOnlyContains(builder.stream(), "a", "9", "!")));
     }

     @Test
     void testAnyOfPlus () {
          final ValueBuilder<String> builder = ValueHelper.edgeifier.makeStringsLike()
               .anyOf("abc", "def")
               .plus()
               .thisOne("xyz");

          Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), "abcxyz", "defxyz"));
     }

     @Test
     void testAnyOfRepeatFixed () {
          final ValueBuilder<String> builder = ValueHelper.edgeifier.makeStringsLike().anyOf("a", "9").repeat(2);

          Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), "aa", "a9", "9a", "99"));
     }

     @Test
     void testAnyOfRepeatRandom () {
          final ValueBuilder<String> builder = ValueHelper.edgeifier.makeStringsLike().anyOf("a", "9").repeat(1, 2);

          Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), "a", "9", "aa", "a9", "9a", "99"));
     }

     @Test
     void testAnyOfValid () {
          final ValueBuilder<String> builder = ValueHelper.edgeifier.makeStringsLike().anyOf("a", "9", "!");

          Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), "a", "9", "!"));
     }

     @Test
     void testBuilderNullValue () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeStringsLike().builder(null);
          });

          Assertions.assertEquals(TypeMessages.getMessageValueBuilderMustNotBeNull(), e.getMessage());
     }

     @Test
     void testBuilderOptional () {
          final ValueBuilder<Integer> intBuilder = ValueHelper.edgeifier.makeIntsLike().thisOne(5);
          final ValueBuilder<String> stringBuilder = ValueHelper.edgeifier.makeStringsLike().builder(intBuilder)
               .optional();

          Assertions.assertAll(
               () -> Assertions.assertTrue(ValueHelper.streamOnlyContains(stringBuilder.stream(), "5", "")),
               () -> Assertions.assertFalse(ValueHelper.streamOnlyContains(stringBuilder.stream(), "5")));
     }

     @Test
     void testBuilderPlus () {
          final ValueBuilder<Integer> intBuilder = ValueHelper.edgeifier.makeIntsLike().thisOne(5);
          final ValueBuilder<String> stringBuilder = ValueHelper.edgeifier.makeStringsLike()
               .builder(intBuilder)
               .plus()
               .thisOne("xyz");

          Assertions.assertTrue(ValueHelper.streamOnlyContains(stringBuilder.stream(), "5xyz"));
     }

     @Test
     void testBuilderRepeatFixed () {
          final ValueBuilder<Integer> intBuilder = ValueHelper.edgeifier.makeIntsLike().thisOne(5);
          final ValueBuilder<String> stringBuilder = ValueHelper.edgeifier.makeStringsLike().builder(intBuilder).repeat(
               2);

          Assertions.assertTrue(ValueHelper.streamOnlyContains(stringBuilder.stream(), "55"));
     }

     @Test
     void testBuilderRepeatRandom () {
          final ValueBuilder<Integer> intBuilder = ValueHelper.edgeifier.makeIntsLike().thisOne(5);
          final ValueBuilder<String> stringBuilder = ValueHelper.edgeifier.makeStringsLike().builder(intBuilder).repeat(
               1, 2);

          Assertions.assertTrue(ValueHelper.streamOnlyContains(stringBuilder.stream(), "5", "55"));
     }

     @Test
     void testBuilderValid () {
          final ValueBuilder<Integer> intBuilder = ValueHelper.edgeifier.makeIntsLike().thisOne(5);
          final ValueBuilder<String> stringBuilder = ValueHelper.edgeifier.makeStringsLike().builder(intBuilder);

          Assertions.assertTrue(ValueHelper.streamOnlyContains(stringBuilder.stream(), "5"));
     }

     @Test
     void testRepeatMaxLessThanMin () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeStringsLike().anyOf("a", "b", "c").repeat(3, 1);
          });

          Assertions.assertEquals(TypeMessages.getMessageInvalidMaximumRepetitionAmount(3, 1), e.getMessage());
     }

     @Test
     void testRepeatMaxNegative () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeStringsLike().anyOf("a", "b", "c").repeat(1, -1);
          });

          Assertions.assertEquals(TypeMessages.getMessageRepetitionAmountMustNotBeNegative(), e.getMessage());
     }

     @Test
     void testRepeatMinNegative () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeStringsLike().anyOf("a", "b", "c").repeat(-1, 1);
          });

          Assertions.assertEquals(TypeMessages.getMessageRepetitionAmountMustNotBeNegative(), e.getMessage());
     }

     @Test
     void testRepeatNegative () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeStringsLike().anyOf("a", "b", "c").repeat(-1);
          });

          Assertions.assertEquals(TypeMessages.getMessageRepetitionAmountMustNotBeNegative(), e.getMessage());
     }

     @Test
     void testThisOneNullValue () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeStringsLike().thisOne((String) null);
          });

          Assertions.assertEquals(TypeMessages.getMessageValueMustNotBeNull(), e.getMessage());
     }

     @Test
     void testThisOneOptional () {
          final ValueBuilder<String> builder = ValueHelper.edgeifier.makeStringsLike().thisOne("abc").optional();

          Assertions.assertAll(
               () -> Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), "abc", "")),
               () -> Assertions.assertFalse(ValueHelper.streamOnlyContains(builder.stream(), "abc")));
     }

     @Test
     void testThisOnePlus () {
          final ValueBuilder<String> builder = ValueHelper.edgeifier.makeStringsLike()
               .thisOne("abc")
               .plus()
               .thisOne("xyz");

          Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), "abcxyz"));
     }

     @Test
     void testThisOneRepeatFixed () {
          final ValueBuilder<String> builder = ValueHelper.edgeifier.makeStringsLike().thisOne("abc").repeat(2);

          Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), "abcabc"));
     }

     @Test
     void testThisOneRepeatRandom () {
          final ValueBuilder<String> builder = ValueHelper.edgeifier.makeStringsLike().thisOne("abc").repeat(1, 2);

          Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), "abc", "abcabc"));
     }

     @Test
     void testThisOneValid () {
          final ValueBuilder<String> builder = ValueHelper.edgeifier.makeStringsLike().thisOne("abc");

          Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), "abc"));
     }
}
