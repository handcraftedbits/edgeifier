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
import com.handcraftedbits.edgeifier.api.value.primitive.FloatValueAnySpec;
import com.handcraftedbits.edgeifier.api.value.primitive.FloatValueSpec;

class FloatValueImpl implements FloatValueAnySpec, FloatValueSpec {
     private float[] allowedValues;
     private float maximum = Float.MAX_VALUE;
     private float minimum = Float.MIN_VALUE;
     private final RandomnessProvider randomnessProvider;

     FloatValueImpl (final RandomnessProvider randomnessProvider) {
          this.randomnessProvider = randomnessProvider;
     }

     @Override
     public FloatValueAnySpec any () {
          return this;
     }

     @Override
     public ValueBuilder<Float> anyOf (final float... values) {
          if ((values == null) || (values.length == 0)) {
               throw new IllegalArgumentException(TypeMessages.getMessageMissingValues());
          }

          this.allowedValues = values;

          return this;
     }

     @Override
     public ValueBuilder<Float> atLeast (final float minimum) {
          this.minimum = minimum;

          return this;
     }

     @Override
     public ValueBuilder<Float> between (final float minimum, final float maximum) {
          if (maximum <= minimum) {
               throw new IllegalArgumentException(TypeMessages.getMessageInvalidMaximumValue(minimum, maximum));
          }

          this.maximum = maximum;
          this.minimum = minimum;

          return this;
     }

     @Override
     public ValueBuilder<Float> lessThan (final float maximum) {
          this.maximum = maximum;

          return this;
     }

     @Override
     public Stream<Float> stream () {
          return Stream.generate( () -> {
               if (this.allowedValues != null) {
                    return this.allowedValues[this.randomnessProvider.nextInt(this.allowedValues.length)];
               }

               return ((float) this.randomnessProvider.nextDouble(this.minimum, this.maximum));
          });
     }

     @Override
     public ValueBuilder<Float> thisOne (final float value) {
          return anyOf(value);
     }
}
