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

class FloatValueImplTest {
     @Test
     void testAnyAtLeastValid () {
          final ValueBuilder<Float> builder = ValueHelper.edgeifier.makeFloatsLike().any().atLeast(10.0F);

          Assertions.assertTrue(ValueHelper.allOfStreamAtLeast(builder.stream(), 10.0F));
     }

     @Test
     void testAnyBetweenInvalid () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeFloatsLike().any().between(10.0F, 9.0F);
          });

          Assertions.assertEquals(TypeMessages.getMessageInvalidMaximumValue(10.0F, 9.0F), e.getMessage());
     }

     @Test
     void testAnyBetweenSame () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeFloatsLike().any().between(-3.0F, -3.0F);
          });

          Assertions.assertEquals(TypeMessages.getMessageInvalidMaximumValue(-3.0F, -3.0F), e.getMessage());
     }

     @Test
     void testAnyBetweenValid () {
          final ValueBuilder<Float> builder = ValueHelper.edgeifier.makeFloatsLike().any().between(-3.0F, 3.0F);

          Assertions.assertTrue(ValueHelper.streamOnlyHasValuesBetween(builder.stream(), -3.0F, 3.0F));
     }

     @Test
     void testAnyLessThanValid () {
          final ValueBuilder<Float> builder = ValueHelper.edgeifier.makeFloatsLike().any().lessThan(10.0F);

          Assertions.assertTrue(ValueHelper.allOfStreamLessThan(builder.stream(), 10.0F));
     }

     @Test
     void testAnyOfMissingValues () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeFloatsLike().anyOf(new float[0]);
          });

          Assertions.assertEquals(TypeMessages.getMessageMissingValues(), e.getMessage());
     }

     @Test
     void testAnyOfNullValues () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeFloatsLike().anyOf(null);
          });

          Assertions.assertEquals(TypeMessages.getMessageMissingValues(), e.getMessage());
     }

     @Test
     void testAnyOfValid () {
          final ValueBuilder<Float> builder = ValueHelper.edgeifier.makeFloatsLike().anyOf(-10.0F, 0.0F, 10.0F);

          Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), -10.0F, 0.0F, 10.0F));
     }

     @Test
     void testAnyUnbounded () {
          final ValueBuilder<Float> builder = ValueHelper.edgeifier.makeFloatsLike().any();

          Assertions.assertTrue(ValueHelper.streamOnlyHasValuesBetween(builder.stream(), Float.MIN_VALUE,
               Float.MAX_VALUE));
     }

     @Test
     void testThisOneValid () {
          final ValueBuilder<Float> builder = ValueHelper.edgeifier.makeFloatsLike().thisOne(1.0F);

          Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), 1.0F));
     }
}
