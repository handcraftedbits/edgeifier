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

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.stream.Stream;

import com.handcraftedbits.edgeifier.api.RandomnessProvider;
import com.handcraftedbits.edgeifier.api.value.ValueBuilder;
import com.handcraftedbits.edgeifier.api.value.primitive.DateValueAnySpec;
import com.handcraftedbits.edgeifier.api.value.primitive.DateValueSpec;

class DateValueImpl implements DateValueAnySpec, DateValueSpec {
     private LocalDateTime[] allowedValues;
     private LocalDateTime latest = LocalDateTime.MAX;
     private LocalDateTime earliest = LocalDateTime.MIN;
     private final RandomnessProvider randomnessProvider;

     DateValueImpl (final RandomnessProvider randomnessProvider) {
          this.randomnessProvider = randomnessProvider;
     }

     @Override
     public DateValueAnySpec any () {
          return this;
     }

     @Override
     public ValueBuilder<LocalDateTime> anyOf (final LocalDateTime... values) {
          if ((values == null) || (values.length == 0)) {
               throw new IllegalArgumentException(TypeMessages.getMessageMissingDates());
          }

          for (final LocalDateTime value : values) {
               if (value == null) {
                    throw new IllegalArgumentException(TypeMessages.getMessageDateMustNotBeNull());
               }
          }

          this.allowedValues = values;

          return this;
     }

     @Override
     public ValueBuilder<LocalDateTime> before (final LocalDateTime latest) {
          if (latest == null) {
               throw new IllegalArgumentException(TypeMessages.getMessageDateMustNotBeNull());
          }

          this.latest = latest;

          return this;
     }

     @Override
     public ValueBuilder<LocalDateTime> between (final LocalDateTime earliest, final LocalDateTime latest) {
          if ((earliest == null) || (latest == null)) {
               throw new IllegalArgumentException(TypeMessages.getMessageDateMustNotBeNull());
          }

          if (latest.compareTo(earliest) <= 0) {
               throw new IllegalArgumentException(TypeMessages.getMessageInvalidMaximumDate(earliest, latest));
          }

          this.latest = latest;
          this.earliest = earliest;

          return this;
     }

     @Override
     public ValueBuilder<LocalDateTime> from (final LocalDateTime earliest) {
          if (earliest == null) {
               throw new IllegalArgumentException(TypeMessages.getMessageDateMustNotBeNull());
          }

          this.earliest = earliest;

          return this;
     }

     @Override
     public Stream<LocalDateTime> stream () {
          return Stream.generate( () -> {
               final long randomAmount;

               if (this.allowedValues != null) {
                    return this.allowedValues[this.randomnessProvider.nextInt(this.allowedValues.length)];
               }

               randomAmount = this.randomnessProvider.nextLong(this.earliest.toEpochSecond(ZoneOffset.UTC),
                    this.latest.toEpochSecond(ZoneOffset.UTC));

               return LocalDateTime.ofInstant(Instant.ofEpochSecond(randomAmount), ZoneOffset.UTC);
          });
     }

     @Override
     public ValueBuilder<LocalDateTime> thisOne (final LocalDateTime value) {
          return anyOf(value);
     }
}
