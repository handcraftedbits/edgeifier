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
import com.handcraftedbits.edgeifier.api.value.primitive.ByteValueAnySpec;
import com.handcraftedbits.edgeifier.api.value.primitive.ByteValueSpec;

class ByteValueImpl implements ByteValueAnySpec, ByteValueSpec {
     private byte[] allowedValues;
     private byte maximum = Byte.MAX_VALUE;
     private byte minimum = Byte.MIN_VALUE;
     private final RandomnessProvider randomnessProvider;

     ByteValueImpl (final RandomnessProvider randomnessProvider) {
          this.randomnessProvider = randomnessProvider;
     }

     @Override
     public ByteValueAnySpec any () {
          return this;
     }

     @Override
     public ValueBuilder<Byte> anyOf (final byte... values) {
          if ((values == null) || (values.length == 0)) {
               throw new IllegalArgumentException(TypeMessages.getMessageMissingValues());
          }

          this.allowedValues = values;

          return this;
     }

     @Override
     public ValueBuilder<Byte> atLeast (final byte minimum) {
          this.minimum = minimum;

          return this;
     }

     @Override
     public ValueBuilder<Byte> between (final byte minimum, final byte maximum) {
          if (maximum <= minimum) {
               throw new IllegalArgumentException(TypeMessages.getMessageInvalidMaximumValue(minimum, maximum));
          }

          this.maximum = maximum;
          this.minimum = minimum;

          return this;
     }

     @Override
     public ValueBuilder<Byte> lessThan (final byte maximum) {
          this.maximum = maximum;

          return this;
     }

     @Override
     public Stream<Byte> stream () {
          return Stream.generate( () -> {
               if (this.allowedValues != null) {
                    return this.allowedValues[this.randomnessProvider.nextInt(this.allowedValues.length)];
               }

               return ((byte) this.randomnessProvider.nextInt(this.minimum, this.maximum));
          });
     }

     @Override
     public ValueBuilder<Byte> thisOne (final byte value) {
          return anyOf(value);
     }
}
