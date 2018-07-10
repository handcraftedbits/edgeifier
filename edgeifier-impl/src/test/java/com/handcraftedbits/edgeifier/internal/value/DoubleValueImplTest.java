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

class DoubleValueImplTest {
     @Test
     void testAnyAtLeastValid () {
          final ValueBuilder<Double> builder = ValueHelper.edgeifier.makeDoublesLike().any().atLeast(10.0D);

          Assertions.assertTrue(ValueHelper.allOfStreamAtLeast(builder.stream(), 10.0D));
     }

     @Test
     void testAnyBetweenInvalid () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeDoublesLike().any().between(10.0D, 9.0D);
          });

          Assertions.assertEquals(TypeMessages.getMessageInvalidMaximumValue(10.0D, 9.0D), e.getMessage());
     }

     @Test
     void testAnyBetweenSame () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeDoublesLike().any().between(-3.0D, -3.0D);
          });

          Assertions.assertEquals(TypeMessages.getMessageInvalidMaximumValue(-3.0D, -3.0D), e.getMessage());
     }

     @Test
     void testAnyBetweenValid () {
          final ValueBuilder<Double> builder = ValueHelper.edgeifier.makeDoublesLike().any().between(-3.0D, 3.0D);

          Assertions.assertTrue(ValueHelper.streamOnlyHasValuesBetween(builder.stream(), -3.0D, 3.0D));
     }

     @Test
     void testAnyLessThanValid () {
          final ValueBuilder<Double> builder = ValueHelper.edgeifier.makeDoublesLike().any().lessThan(10.0D);

          Assertions.assertTrue(ValueHelper.allOfStreamLessThan(builder.stream(), 10.0D));
     }

     @Test
     void testAnyOfMissingValues () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeDoublesLike().anyOf(new double[0]);
          });

          Assertions.assertEquals(TypeMessages.getMessageMissingValues(), e.getMessage());
     }

     @Test
     void testAnyOfNullValues () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeDoublesLike().anyOf(null);
          });

          Assertions.assertEquals(TypeMessages.getMessageMissingValues(), e.getMessage());
     }

     @Test
     void testAnyOfValid () {
          final ValueBuilder<Double> builder = ValueHelper.edgeifier.makeDoublesLike().anyOf(-10.0D, 0.0D, 10.0D);

          Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), -10.0D, 0.0D, 10.0D));
     }

     @Test
     void testAnyUnbounded () {
          final ValueBuilder<Double> builder = ValueHelper.edgeifier.makeDoublesLike().any();

          Assertions.assertTrue(ValueHelper.streamOnlyHasValuesBetween(builder.stream(), Double.MIN_VALUE,
               Double.MAX_VALUE));
     }

     @Test
     void testThisOneValid () {
          final ValueBuilder<Double> builder = ValueHelper.edgeifier.makeDoublesLike().thisOne(1.0D);

          Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), 1.0D));
     }
}
