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

class ByteValueImplTest {
     @Test
     void testAnyAtLeastValid () {
          final ValueBuilder<Byte> builder = ValueHelper.edgeifier.makeBytesLike().any().atLeast((byte) 10);

          Assertions.assertTrue(ValueHelper.allOfStreamAtLeast(builder.stream(), (byte) 10));
     }

     @Test
     void testAnyBetweenInvalid () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeBytesLike().any().between((byte) 10, (byte) 9);
          });

          Assertions.assertEquals(TypeMessages.getMessageInvalidMaximumValue(10, 9), e.getMessage());
     }

     @Test
     void testAnyBetweenSame () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeBytesLike().any().between((byte) -3, (byte) -3);
          });

          Assertions.assertEquals(TypeMessages.getMessageInvalidMaximumValue(-3, -3), e.getMessage());
     }

     @Test
     void testAnyBetweenValid () {
          final ValueBuilder<Byte> builder = ValueHelper.edgeifier.makeBytesLike().any().between((byte) -3, (byte) 3);

          Assertions.assertTrue(ValueHelper.streamOnlyHasValuesBetween(builder.stream(), (byte) -3, (byte) 3));
     }

     @Test
     void testAnyLessThanValid () {
          final ValueBuilder<Byte> builder = ValueHelper.edgeifier.makeBytesLike().any().lessThan((byte) 10);

          Assertions.assertTrue(ValueHelper.allOfStreamLessThan(builder.stream(), (byte) 10));
     }

     @Test
     void testAnyOfMissingValues () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeBytesLike().anyOf(new byte[0]);
          });

          Assertions.assertEquals(TypeMessages.getMessageMissingValues(), e.getMessage());
     }

     @Test
     void testAnyOfNullValues () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeBytesLike().anyOf(null);
          });

          Assertions.assertEquals(TypeMessages.getMessageMissingValues(), e.getMessage());
     }

     @Test
     void testAnyOfValid () {
          final ValueBuilder<Byte> builder = ValueHelper.edgeifier.makeBytesLike().anyOf((byte) -10, (byte) 0,
               (byte) 10);

          Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), (byte) -10, (byte) 0, (byte) 10));
     }

     @Test
     void testAnyUnbounded () {
          final ValueBuilder<Byte> builder = ValueHelper.edgeifier.makeBytesLike().any();

          Assertions.assertTrue(ValueHelper.streamOnlyHasValuesBetween(builder.stream(), Byte.MIN_VALUE,
               Byte.MAX_VALUE));
     }

     @Test
     void testThisOneValid () {
          final ValueBuilder<Byte> builder = ValueHelper.edgeifier.makeBytesLike().thisOne((byte) 1);

          Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), (byte) 1));
     }
}
