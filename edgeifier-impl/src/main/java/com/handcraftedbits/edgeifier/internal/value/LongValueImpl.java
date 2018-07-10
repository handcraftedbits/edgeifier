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

import java.util.stream.Stream;

import com.handcraftedbits.edgeifier.api.RandomnessProvider;
import com.handcraftedbits.edgeifier.api.value.ValueBuilder;
import com.handcraftedbits.edgeifier.api.value.primitive.LongValueAnySpec;
import com.handcraftedbits.edgeifier.api.value.primitive.LongValueSpec;

class LongValueImpl implements LongValueAnySpec, LongValueSpec {
     private long[] allowedValues;
     private long maximum = Long.MAX_VALUE;
     private long minimum = Long.MIN_VALUE;
     private final RandomnessProvider randomnessProvider;

     LongValueImpl (final RandomnessProvider randomnessProvider) {
          this.randomnessProvider = randomnessProvider;
     }

     @Override
     public LongValueAnySpec any () {
          return this;
     }

     @Override
     public ValueBuilder<Long> anyOf (final long... values) {
          if ((values == null) || (values.length == 0)) {
               throw new IllegalArgumentException(TypeMessages.getMessageMissingValues());
          }

          this.allowedValues = values;

          return this;
     }

     @Override
     public ValueBuilder<Long> atLeast (final long minimum) {
          this.minimum = minimum;

          return this;
     }

     @Override
     public ValueBuilder<Long> between (final long minimum, final long maximum) {
          if (maximum <= minimum) {
               throw new IllegalArgumentException(TypeMessages.getMessageInvalidMaximumValue(minimum, maximum));
          }

          this.maximum = maximum;
          this.minimum = minimum;

          return this;
     }

     @Override
     public ValueBuilder<Long> lessThan (final long maximum) {
          this.maximum = maximum;

          return this;
     }

     @Override
     public Stream<Long> stream () {
          return Stream.generate( () -> {
               if (this.allowedValues != null) {
                    return this.allowedValues[this.randomnessProvider.nextInt(this.allowedValues.length)];
               }

               return this.randomnessProvider.nextLong(this.minimum, this.maximum);
          });
     }

     @Override
     public ValueBuilder<Long> thisOne (final long value) {
          return anyOf(value);
     }
}
