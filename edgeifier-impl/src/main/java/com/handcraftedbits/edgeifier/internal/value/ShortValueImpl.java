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
import com.handcraftedbits.edgeifier.api.value.primitive.ShortValueAnySpec;
import com.handcraftedbits.edgeifier.api.value.primitive.ShortValueSpec;

class ShortValueImpl implements ShortValueAnySpec, ShortValueSpec {
     private short[] allowedValues;
     private short maximum = Short.MAX_VALUE;
     private short minimum = Short.MIN_VALUE;
     private final RandomnessProvider randomnessProvider;

     ShortValueImpl (final RandomnessProvider randomnessProvider) {
          this.randomnessProvider = randomnessProvider;
     }

     @Override
     public ShortValueAnySpec any () {
          return this;
     }

     @Override
     public ValueBuilder<Short> anyOf (final short... values) {
          if ((values == null) || (values.length == 0)) {
               throw new IllegalArgumentException(TypeMessages.getMessageMissingValues());
          }

          this.allowedValues = values;

          return this;
     }

     @Override
     public ValueBuilder<Short> atLeast (final short minimum) {
          this.minimum = minimum;

          return this;
     }

     @Override
     public ValueBuilder<Short> between (final short minimum, final short maximum) {
          if (maximum <= minimum) {
               throw new IllegalArgumentException(TypeMessages.getMessageInvalidMaximumValue(minimum, maximum));
          }

          this.maximum = maximum;
          this.minimum = minimum;

          return this;
     }

     @Override
     public ValueBuilder<Short> lessThan (final short maximum) {
          this.maximum = maximum;

          return this;
     }

     @Override
     public Stream<Short> stream () {
          return Stream.generate( () -> {
               if (this.allowedValues != null) {
                    return this.allowedValues[this.randomnessProvider.nextInt(this.allowedValues.length)];
               }

               return ((short) this.randomnessProvider.nextInt(this.minimum, this.maximum));
          });
     }

     @Override
     public ValueBuilder<Short> thisOne (final short value) {
          return anyOf(value);
     }
}
