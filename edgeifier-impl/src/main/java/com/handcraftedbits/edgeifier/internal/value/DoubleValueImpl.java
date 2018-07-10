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
import com.handcraftedbits.edgeifier.api.value.primitive.DoubleValueAnySpec;
import com.handcraftedbits.edgeifier.api.value.primitive.DoubleValueSpec;

class DoubleValueImpl implements DoubleValueAnySpec, DoubleValueSpec {
     private double[] allowedValues;
     private double maximum = Double.MAX_VALUE;
     private double minimum = Double.MIN_VALUE;
     private final RandomnessProvider randomnessProvider;

     DoubleValueImpl (final RandomnessProvider randomnessProvider) {
          this.randomnessProvider = randomnessProvider;
     }

     @Override
     public DoubleValueAnySpec any () {
          return this;
     }

     @Override
     public ValueBuilder<Double> anyOf (final double... values) {
          if ((values == null) || (values.length == 0)) {
               throw new IllegalArgumentException(TypeMessages.getMessageMissingValues());
          }

          this.allowedValues = values;

          return this;
     }

     @Override
     public ValueBuilder<Double> atLeast (final double minimum) {
          this.minimum = minimum;

          return this;
     }

     @Override
     public ValueBuilder<Double> between (final double minimum, final double maximum) {
          if (maximum <= minimum) {
               throw new IllegalArgumentException(TypeMessages.getMessageInvalidMaximumValue(minimum, maximum));
          }

          this.maximum = maximum;
          this.minimum = minimum;

          return this;
     }

     @Override
     public ValueBuilder<Double> lessThan (final double maximum) {
          this.maximum = maximum;

          return this;
     }

     @Override
     public Stream<Double> stream () {
          return Stream.generate( () -> {
               if (this.allowedValues != null) {
                    return this.allowedValues[this.randomnessProvider.nextInt(this.allowedValues.length)];
               }

               return this.randomnessProvider.nextDouble(this.minimum, this.maximum);
          });
     }

     @Override
     public ValueBuilder<Double> thisOne (final double value) {
          return anyOf(value);
     }
}
