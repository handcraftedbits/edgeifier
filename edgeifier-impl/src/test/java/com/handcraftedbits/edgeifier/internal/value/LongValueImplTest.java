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

class LongValueImplTest {
     @Test
     void testAnyAtLeastValid () {
          final ValueBuilder<Long> builder = ValueHelper.edgeifier.makeLongsLike().any().atLeast(10L);

          Assertions.assertTrue(ValueHelper.allOfStreamAtLeast(builder.stream(), 10L));
     }

     @Test
     void testAnyBetweenInvalid () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeLongsLike().any().between(10L, 9L);
          });

          Assertions.assertEquals(TypeMessages.getMessageInvalidMaximumValue(10L, 9L), e.getMessage());
     }

     @Test
     void testAnyBetweenSame () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeLongsLike().any().between(-3L, -3L);
          });

          Assertions.assertEquals(TypeMessages.getMessageInvalidMaximumValue(-3L, -3L), e.getMessage());
     }

     @Test
     void testAnyBetweenValid () {
          final ValueBuilder<Long> builder = ValueHelper.edgeifier.makeLongsLike().any().between(-3L, 3L);

          Assertions.assertTrue(ValueHelper.streamOnlyHasValuesBetween(builder.stream(), -3L, 3L));
     }

     @Test
     void testAnyLessThanValid () {
          final ValueBuilder<Long> builder = ValueHelper.edgeifier.makeLongsLike().any().lessThan(10L);

          Assertions.assertTrue(ValueHelper.allOfStreamLessThan(builder.stream(), 10L));
     }

     @Test
     void testAnyOfMissingValues () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeLongsLike().anyOf(new long[0]);
          });

          Assertions.assertEquals(TypeMessages.getMessageMissingValues(), e.getMessage());
     }

     @Test
     void testAnyOfNullValues () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeLongsLike().anyOf(null);
          });

          Assertions.assertEquals(TypeMessages.getMessageMissingValues(), e.getMessage());
     }

     @Test
     void testAnyOfValid () {
          final ValueBuilder<Long> builder = ValueHelper.edgeifier.makeLongsLike().anyOf(-10L, 0L, 10L);

          Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), -10L, 0L, 10L));
     }

     @Test
     void testAnyUnbounded () {
          final ValueBuilder<Long> builder = ValueHelper.edgeifier.makeLongsLike().any();

          Assertions.assertTrue(ValueHelper.streamOnlyHasValuesBetween(builder.stream(), Long.MIN_VALUE,
               Long.MAX_VALUE));
     }

     @Test
     void testThisOneValid () {
          final ValueBuilder<Long> builder = ValueHelper.edgeifier.makeLongsLike().thisOne(1L);

          Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), 1L));
     }
}
