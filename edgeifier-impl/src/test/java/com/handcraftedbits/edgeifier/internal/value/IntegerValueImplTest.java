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

class IntegerValueImplTest {
     @Test
     void testAnyAtLeastValid () {
          final ValueBuilder<Integer> builder = ValueHelper.edgeifier.makeIntsLike().any().atLeast(10);

          Assertions.assertTrue(ValueHelper.allOfStreamAtLeast(builder.stream(), 10));
     }

     @Test
     void testAnyBetweenInvalid () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeIntsLike().any().between(10, 9);
          });

          Assertions.assertEquals(TypeMessages.getMessageInvalidMaximumValue(10, 9), e.getMessage());
     }

     @Test
     void testAnyBetweenSame () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeIntsLike().any().between(-3, -3);
          });

          Assertions.assertEquals(TypeMessages.getMessageInvalidMaximumValue(-3, -3), e.getMessage());
     }

     @Test
     void testAnyBetweenValid () {
          final ValueBuilder<Integer> builder = ValueHelper.edgeifier.makeIntsLike().any().between(-3, 3);

          Assertions.assertTrue(ValueHelper.streamOnlyHasValuesBetween(builder.stream(), -3, 3));
     }

     @Test
     void testAnyLessThanValid () {
          final ValueBuilder<Integer> builder = ValueHelper.edgeifier.makeIntsLike().any().lessThan(10);

          Assertions.assertTrue(ValueHelper.allOfStreamLessThan(builder.stream(), 10));
     }

     @Test
     void testAnyOfMissingValues () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeIntsLike().anyOf(new int[0]);
          });

          Assertions.assertEquals(TypeMessages.getMessageMissingValues(), e.getMessage());
     }

     @Test
     void testAnyOfNullValues () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeIntsLike().anyOf(null);
          });

          Assertions.assertEquals(TypeMessages.getMessageMissingValues(), e.getMessage());
     }

     @Test
     void testAnyOfValid () {
          final ValueBuilder<Integer> builder = ValueHelper.edgeifier.makeIntsLike().anyOf(-10, 0, 10);

          Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), -10, 0, 10));
     }

     @Test
     void testAnyUnbounded () {
          final ValueBuilder<Integer> builder = ValueHelper.edgeifier.makeIntsLike().any();

          Assertions.assertTrue(ValueHelper.streamOnlyHasValuesBetween(builder.stream(), Integer.MIN_VALUE,
               Integer.MAX_VALUE));
     }

     @Test
     void testThisOneValid () {
          final ValueBuilder<Integer> builder = ValueHelper.edgeifier.makeIntsLike().thisOne(1);

          Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), 1));
     }
}
