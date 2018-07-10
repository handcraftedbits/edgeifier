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

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.handcraftedbits.edgeifier.api.value.ValueBuilder;

class DateValueImplTest {
     private final LocalDateTime firstDate = LocalDateTime.of(2016, Month.NOVEMBER, 28, 4, 1);
     private final LocalDateTime secondDate = LocalDateTime.now();

     @Test
     void testAnyBeforeNullDate () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeDatesLike().any().before(null);
          });

          Assertions.assertEquals(TypeMessages.getMessageDateMustNotBeNull(), e.getMessage());
     }

     @Test
     void testAnyBeforeValid () {
          final ValueBuilder<LocalDateTime> builder = ValueHelper.edgeifier.makeDatesLike().any()
               .before(this.secondDate);

          Assertions.assertTrue(ValueHelper.allOfStreamBefore(builder.stream(), this.secondDate));
     }

     @Test
     void testAnyBetweenInvalid () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeDatesLike().any().between(this.secondDate, this.firstDate);
          });

          Assertions.assertEquals(TypeMessages.getMessageInvalidMaximumDate(this.secondDate, this.firstDate),
               e.getMessage());
     }

     @Test
     void testAnyBetweenNullDates () {
          final Exception e1 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeDatesLike().any().between(this.firstDate, null);
          });
          final Exception e2 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeDatesLike().any().between(null, this.secondDate);
          });

          Assertions.assertAll(
               () -> Assertions.assertEquals(TypeMessages.getMessageDateMustNotBeNull(), e1.getMessage()),
               () -> Assertions.assertEquals(TypeMessages.getMessageDateMustNotBeNull(), e2.getMessage()));
     }

     @Test
     void testAnyBetweenSame () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeDatesLike().any().between(this.firstDate, this.firstDate);
          });

          Assertions.assertEquals(TypeMessages.getMessageInvalidMaximumDate(this.firstDate, this.firstDate),
               e.getMessage());
     }

     @Test
     void testAnyBetweenValid () {
          final ValueBuilder<LocalDateTime> builder = ValueHelper.edgeifier.makeDatesLike().any()
               .between(this.firstDate, this.secondDate);

          Assertions.assertTrue(ValueHelper.streamOnlyHasDatesBetween(builder.stream(), this.firstDate,
               this.secondDate));
     }

     @Test
     void testAnyFromNullDate () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeDatesLike().any().from(null);
          });

          Assertions.assertEquals(TypeMessages.getMessageDateMustNotBeNull(), e.getMessage());
     }

     @Test
     void testAnyFromValid () {
          final ValueBuilder<LocalDateTime> builder = ValueHelper.edgeifier.makeDatesLike().any().from(this.firstDate);

          Assertions.assertTrue(ValueHelper.allOfStreamIsOrIsAfter(builder.stream(), this.firstDate));
     }

     @Test
     void testAnyOfMissingDates () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeDatesLike().anyOf(new LocalDateTime[0]);
          });

          Assertions.assertEquals(TypeMessages.getMessageMissingDates(), e.getMessage());
     }

     @Test
     void testAnyOfNullDate () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeDatesLike().anyOf((LocalDateTime) null);
          });

          Assertions.assertEquals(TypeMessages.getMessageDateMustNotBeNull(), e.getMessage());
     }

     @Test
     void testAnyOfNullDates () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeDatesLike().anyOf((LocalDateTime[]) null);
          });

          Assertions.assertEquals(TypeMessages.getMessageMissingDates(), e.getMessage());
     }

     @Test
     void testAnyOfValid () {
          final ValueBuilder<LocalDateTime> builder = ValueHelper.edgeifier.makeDatesLike().anyOf(this.firstDate,
               this.secondDate);

          Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), this.firstDate, this.secondDate));
     }

     @Test
     void testAnyUnbounded () {
          final ValueBuilder<LocalDateTime> builder = ValueHelper.edgeifier.makeDatesLike().any();

          Assertions.assertTrue(ValueHelper.streamOnlyHasDatesBetween(builder.stream(), LocalDateTime.MIN,
               LocalDateTime.MAX));
     }

     @Test
     void testThisOneValid () {
          final ValueBuilder<LocalDateTime> builder = ValueHelper.edgeifier.makeDatesLike().thisOne(this.firstDate);

          Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), this.firstDate));
     }
}
