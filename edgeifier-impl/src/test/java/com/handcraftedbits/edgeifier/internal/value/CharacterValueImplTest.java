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

class CharacterValueImplTest {
     @Test
     void testAnyAtLeastValid () {
          final ValueBuilder<Character> builder = ValueHelper.edgeifier.makeCharsLike().any().atLeast('d');

          Assertions.assertTrue(ValueHelper.allOfStreamAtLeast(builder.stream(), 'd'));
     }

     @Test
     void testAnyBetweenInvalid () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeCharsLike().any().between('c', 'a');
          });

          Assertions.assertEquals(TypeMessages.getMessageInvalidRange('c', 'a'), e.getMessage());
     }

     @Test
     void testAnyBetweenSame () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeCharsLike().any().between('a', 'a');
          });

          Assertions.assertEquals(TypeMessages.getMessageInvalidMaximumValue("'a'", "'a'"), e.getMessage());
     }

     @Test
     void testAnyBetweenValid () {
          final ValueBuilder<Character> builder = ValueHelper.edgeifier.makeCharsLike().any().between('a', 'd');

          Assertions.assertTrue(ValueHelper.streamOnlyHasValuesBetween(builder.stream(), 'a', 'd'));
     }

     @Test
     void testAnyLessThanValid () {
          final ValueBuilder<Character> builder = ValueHelper.edgeifier.makeCharsLike().any().lessThan('e');

          Assertions.assertTrue(ValueHelper.allOfStreamLessThan(builder.stream(), 'e'));
     }

     @Test
     void testAnyOfMissingValues () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeCharsLike().anyOf(new char[0]);
          });

          Assertions.assertEquals(TypeMessages.getMessageMissingValues(), e.getMessage());
     }

     @Test
     void testAnyOfNullValues () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeCharsLike().anyOf(null);
          });

          Assertions.assertEquals(TypeMessages.getMessageMissingValues(), e.getMessage());
     }

     @Test
     void testAnyOfValid () {
          final ValueBuilder<Character> builder = ValueHelper.edgeifier.makeCharsLike().anyOf('a', 'b', 'c');

          Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), 'a', 'b', 'c'));
     }

     @Test
     void testAnyUnbounded () {
          final ValueBuilder<Character> builder = ValueHelper.edgeifier.makeCharsLike().any();

          Assertions.assertTrue(ValueHelper.streamOnlyHasValuesBetween(builder.stream(), Character.MIN_VALUE,
               Character.MAX_VALUE));
     }

     @Test
     void testThisOneValid () {
          final ValueBuilder<Character> builder = ValueHelper.edgeifier.makeCharsLike().thisOne('a');

          Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), 'a'));
     }
}
