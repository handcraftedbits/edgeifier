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

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.handcraftedbits.edgeifier.api.value.ValueBuilder;

class ListValueImplTest {
     @Test
     void testAnyNoLength () {
          final ValueBuilder<List<Integer>> builder;
          final ValueBuilder<Integer> intBuilder = ValueHelper.edgeifier.makeIntsLike().any().between(0, 10);

          builder = ValueHelper.edgeifier.makeListsLike().any(intBuilder);

          builder.stream().limit(ValueHelper.STREAM_LENGTH).forEach(item -> {
               Assertions.assertAll(
                    () -> Assertions.assertNotNull(item),
                    () -> Assertions.assertTrue(item.size() == 0));
          });
     }

     @Test
     void testAnyNullBuilder () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeListsLike().any(null);
          });

          Assertions.assertEquals(TypeMessages.getMessageValueBuilderMustNotBeNull(), e.getMessage());
     }

     @Test
     void testAnyWithLengthBetweenInvalid () {
          final ValueBuilder<Integer> builder = ValueHelper.edgeifier.makeIntsLike().any().between(0, 10);
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeListsLike().any(builder).withLengthBetween(5, 2);
          });

          Assertions.assertEquals(TypeMessages.getMessageInvalidMaximumLength(5, 2), e.getMessage());
     }

     @Test
     void testAnyWithLengthBetweenNegativeMaximum () {
          final ValueBuilder<Integer> builder = ValueHelper.edgeifier.makeIntsLike().any().between(0, 10);
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeListsLike().any(builder).withLengthBetween(5, -1);
          });

          Assertions.assertEquals(TypeMessages.getMessageNegativeLength(5, -1), e.getMessage());
     }

     @Test
     void testAnyWithLengthBetweenNegativeMinimum () {
          final ValueBuilder<Integer> builder = ValueHelper.edgeifier.makeIntsLike().any().between(0, 10);
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeListsLike().any(builder).withLengthBetween(-1, 2);
          });

          Assertions.assertEquals(TypeMessages.getMessageNegativeLength(-1, 2), e.getMessage());
     }

     @Test
     void testAnyWithLengthBetweenValid () {
          final ValueBuilder<List<Integer>> builder;
          final ValueBuilder<Integer> intBuilder = ValueHelper.edgeifier.makeIntsLike().any().between(0, 10);

          builder = ValueHelper.edgeifier.makeListsLike().any(intBuilder).withLengthBetween(0, 10);

          builder.stream().limit(ValueHelper.STREAM_LENGTH).forEach(item -> {
               Assertions.assertAll(
                    () -> Assertions.assertNotNull(item),
                    () -> Assertions.assertTrue(item.size() >= 0 && item.size() < 10),
                    () -> Assertions.assertTrue(item.stream().allMatch(value -> value >= 0 && value < 10)));
          });
     }

     @Test
     void testAnyWithLengthFixedInvalid () {
          final ValueBuilder<Integer> builder = ValueHelper.edgeifier.makeIntsLike().any().between(0, 10);
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeListsLike().any(builder).withLength(-1);
          });

          Assertions.assertEquals(TypeMessages.getMessageNegativeLength(-1, -1), e.getMessage());
     }

     @Test
     void testAnyWithLengthFixedValid () {
          final ValueBuilder<List<Integer>> builder;
          final ValueBuilder<Integer> intBuilder = ValueHelper.edgeifier.makeIntsLike().any().between(0, 10);

          builder = ValueHelper.edgeifier.makeListsLike().any(intBuilder).withLength(10);

          builder.stream().limit(ValueHelper.STREAM_LENGTH).forEach(item -> {
               Assertions.assertAll(
                    () -> Assertions.assertNotNull(item),
                    () -> Assertions.assertTrue(item.size() == 10),
                    () -> Assertions.assertTrue(item.stream().allMatch(value -> value >= 0 && value < 10)));
          });
     }

     @Test
     void testAnyWithLengthLessThanInvalid () {
          final ValueBuilder<Integer> builder = ValueHelper.edgeifier.makeIntsLike().any().between(0, 10);
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeListsLike().any(builder).withLengthLessThan(-1);
          });

          Assertions.assertEquals(TypeMessages.getMessageInvalidMaximumLength(0, -1), e.getMessage());
     }

     @Test
     void testAnyWithLengthLessThanValid () {
          final ValueBuilder<List<Integer>> builder;
          final ValueBuilder<Integer> intBuilder = ValueHelper.edgeifier.makeIntsLike().any().between(0, 10);

          builder = ValueHelper.edgeifier.makeListsLike().any(intBuilder).withLengthLessThan(10);

          builder.stream().limit(ValueHelper.STREAM_LENGTH).forEach(item -> {
               Assertions.assertAll(
                    () -> Assertions.assertNotNull(item),
                    () -> Assertions.assertTrue(item.size() >= 0 && item.size() < 10),
                    () -> Assertions.assertTrue(item.stream().allMatch(value -> value >= 0 && value < 10)));
          });
     }

     @Test
     void testAnyWithLengthLessThanZero () {
          final ValueBuilder<Integer> builder = ValueHelper.edgeifier.makeIntsLike().any().between(0, 10);
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeListsLike().any(builder).withLengthLessThan(0);
          });

          Assertions.assertEquals(TypeMessages.getMessageInvalidMaximumLength(0, 0), e.getMessage());
     }
}
