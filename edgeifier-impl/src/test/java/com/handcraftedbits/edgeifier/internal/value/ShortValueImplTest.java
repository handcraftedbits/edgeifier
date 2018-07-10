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

class ShortValueImplTest {
     @Test
     void testAnyAtLeastValid () {
          final ValueBuilder<Short> builder = ValueHelper.edgeifier.makeShortsLike().any().atLeast((short) 10);

          Assertions.assertTrue(ValueHelper.allOfStreamAtLeast(builder.stream(), (short) 10));
     }

     @Test
     void testAnyBetweenInvalid () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeShortsLike().any().between((short) 10, (short) 9);
          });

          Assertions.assertEquals(TypeMessages.getMessageInvalidMaximumValue(10, 9), e.getMessage());
     }

     @Test
     void testAnyBetweenSame () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeShortsLike().any().between((short) -3, (short) -3);
          });

          Assertions.assertEquals(TypeMessages.getMessageInvalidMaximumValue(-3, -3), e.getMessage());
     }

     @Test
     void testAnyBetweenValid () {
          final ValueBuilder<Short> builder = ValueHelper.edgeifier.makeShortsLike().any().between((short) -3,
               (short) 3);

          Assertions.assertTrue(ValueHelper.streamOnlyHasValuesBetween(builder.stream(), (short) -3, (short) 3));
     }

     @Test
     void testAnyLessThanValid () {
          final ValueBuilder<Short> builder = ValueHelper.edgeifier.makeShortsLike().any().lessThan((short) 10);

          Assertions.assertTrue(ValueHelper.allOfStreamLessThan(builder.stream(), (short) 10));
     }

     @Test
     void testAnyOfMissingValues () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeShortsLike().anyOf(new short[0]);
          });

          Assertions.assertEquals(TypeMessages.getMessageMissingValues(), e.getMessage());
     }

     @Test
     void testAnyOfNullValues () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeShortsLike().anyOf(null);
          });

          Assertions.assertEquals(TypeMessages.getMessageMissingValues(), e.getMessage());
     }

     @Test
     void testAnyOfValid () {
          final ValueBuilder<Short> builder = ValueHelper.edgeifier.makeShortsLike().anyOf((short) -10, (short) 0,
               (short) 10);

          Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), (short) -10, (short) 0, (short) 10));
     }

     @Test
     void testAnyUnbounded () {
          final ValueBuilder<Short> builder = ValueHelper.edgeifier.makeShortsLike().any();

          Assertions.assertTrue(ValueHelper.streamOnlyHasValuesBetween(builder.stream(), Short.MIN_VALUE,
               Short.MAX_VALUE));
     }

     @Test
     void testThisOneValid () {
          final ValueBuilder<Short> builder = ValueHelper.edgeifier.makeShortsLike().thisOne((short) 1);

          Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), (short) 1));
     }
}
