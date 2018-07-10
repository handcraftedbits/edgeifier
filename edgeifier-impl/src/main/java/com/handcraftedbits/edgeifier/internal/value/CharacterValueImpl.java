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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.handcraftedbits.edgeifier.api.RandomnessProvider;
import com.handcraftedbits.edgeifier.api.value.ValueBuilder;
import com.handcraftedbits.edgeifier.api.value.primitive.CharacterValueAnySpec;
import com.handcraftedbits.edgeifier.api.value.primitive.CharacterValueSpec;

class CharacterValueImpl implements CharacterValueAnySpec, CharacterValueSpec {
     private final List<CharacterClass> classes = new ArrayList<>();
     private char maximum = Character.MAX_VALUE;
     private char minimum = Character.MIN_VALUE;
     private final RandomnessProvider randomnessProvider;

     CharacterValueImpl (final RandomnessProvider randomnessProvider) {
          this.randomnessProvider = randomnessProvider;
     }

     @Override
     public CharacterValueAnySpec any () {
          return this;
     }

     @Override
     public ValueBuilder<Character> anyOf (final char... values) {
          final int[] intValues;

          if ((values == null) || (values.length == 0)) {
               throw new IllegalArgumentException(TypeMessages.getMessageMissingValues());
          }

          intValues = new int[values.length];

          for (int i = 0; i < values.length; ++i) {
               intValues[i] = values[i];
          }

          this.classes.add(new EnumeratedCharacterClass(this.randomnessProvider, intValues));

          return this;
     }

     @Override
     public ValueBuilder<Character> atLeast (final char minimum) {
          this.minimum = minimum;

          return this;
     }

     @Override
     public ValueBuilder<Character> between (final char minimum, final char maximum) {
          if (maximum < minimum) {
               throw new IllegalArgumentException(TypeMessages.getMessageInvalidRange(minimum, maximum));
          }

          if (maximum == minimum) {
               throw new IllegalArgumentException(TypeMessages.getMessageInvalidMaximumValue("'" + minimum + "'",
                    "'" + maximum + "'"));
          }

          this.maximum = maximum;
          this.minimum = minimum;

          return this;
     }

     @Override
     public ValueBuilder<Character> lessThan (final char maximum) {
          this.maximum = maximum;

          return this;
     }

     @Override
     public Stream<Character> stream () {
          return Stream.generate( () -> {
               if (!this.classes.isEmpty()) {
                    return (char) this.classes.get(this.randomnessProvider.nextInt(this.classes.size())).get();
               }

               return ((char) this.randomnessProvider.nextInt(this.minimum, this.maximum));
          });
     }

     @Override
     public ValueBuilder<Character> thisOne (final char value) {
          return anyOf(value);
     }
}
