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
import com.handcraftedbits.edgeifier.api.value.primitive.IntegerValueAnySpec;
import com.handcraftedbits.edgeifier.api.value.primitive.IntegerValueSpec;

class IntegerValueImpl implements IntegerValueAnySpec, IntegerValueSpec {
     private int[] allowedValues;
     private int maximum = Integer.MAX_VALUE;
     private int minimum = Integer.MIN_VALUE;
     private final RandomnessProvider randomnessProvider;

     IntegerValueImpl (final RandomnessProvider randomnessProvider) {
          this.randomnessProvider = randomnessProvider;
     }

     @Override
     public IntegerValueAnySpec any () {
          return this;
     }

     @Override
     public ValueBuilder<Integer> anyOf (final int... values) {
          if ((values == null) || (values.length == 0)) {
               throw new IllegalArgumentException(TypeMessages.getMessageMissingValues());
          }

          this.allowedValues = values;

          return this;
     }

     @Override
     public ValueBuilder<Integer> atLeast (final int minimum) {
          this.minimum = minimum;

          return this;
     }

     @Override
     public ValueBuilder<Integer> between (final int minimum, final int maximum) {
          if (maximum <= minimum) {
               throw new IllegalArgumentException(TypeMessages.getMessageInvalidMaximumValue(minimum, maximum));
          }

          this.maximum = maximum;
          this.minimum = minimum;

          return this;
     }

     @Override
     public ValueBuilder<Integer> lessThan (final int maximum) {
          this.maximum = maximum;

          return this;
     }

     @Override
     public Stream<Integer> stream () {
          return Stream.generate( () -> {
               if (this.allowedValues != null) {
                    return this.allowedValues[this.randomnessProvider.nextInt(this.allowedValues.length)];
               }

               return this.randomnessProvider.nextInt(this.minimum, this.maximum);
          });
     }

     @Override
     public ValueBuilder<Integer> thisOne (final int value) {
          return anyOf(value);
     }
}
