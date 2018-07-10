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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Stream;

import com.handcraftedbits.edgeifier.api.RandomnessProvider;
import com.handcraftedbits.edgeifier.api.value.ValueBuilder;
import com.handcraftedbits.edgeifier.api.value.string.StringValueSpec;
import com.handcraftedbits.edgeifier.api.value.string.StringValueSpecAdjustments;
import com.handcraftedbits.edgeifier.api.value.string.StringValueSpecFragment;

class StringValueImpl implements StringValueSpec, StringValueSpecAdjustments, StringValueSpecFragment {
     private final LinkedList<StringFragment> fragments = new LinkedList<>();
     private final RandomnessProvider randomnessProvider;

     StringValueImpl (final RandomnessProvider randomnessProvider) {
          this.fragments.add(new StringFragment());
          this.randomnessProvider = randomnessProvider;
     }

     @Override
     public StringValueSpecAdjustments anyInAlphabet (final int... values) {
          if ((values == null) || (values.length == 0)) {
               throw new IllegalArgumentException(TypeMessages.getMessageMissingValues());
          }

          this.fragments.peekLast().characterClass = new EnumeratedCharacterClass(this.randomnessProvider, values);

          return this;
     }

     @Override
     public StringValueSpecAdjustments anyInRange (final int begin, final int end) {
          if (begin > end) {
               throw new IllegalArgumentException(TypeMessages.getMessageInvalidRange(
                    new String(Character.toChars(begin)), new String(Character.toChars(end))));
          }

          this.fragments.peekLast().characterClass = new RangedCharacterClass(this.randomnessProvider, begin, end);

          return this;
     }

     @Override
     public StringValueSpecAdjustments anyOf (final String... values) {
          if ((values == null) || (values.length == 0)) {
               throw new IllegalArgumentException(TypeMessages.getMessageMissingValues());
          }

          for (final String value : values) {
               if (value == null) {
                    throw new IllegalArgumentException(TypeMessages.getMessageValueMustNotBeNull());
               }
          }

          this.fragments.peekLast().values = values;

          return this;
     }

     @Override
     public StringValueSpecAdjustments builder (final ValueBuilder<?> builder) {
          if (builder == null) {
               throw new IllegalArgumentException(TypeMessages.getMessageValueBuilderMustNotBeNull());
          }

          this.fragments.peekLast().iterator = builder.stream().iterator();

          return this;
     }

     @Override
     public StringValueSpecFragment optional () {
          return repeat(0, 1);
     }

     @Override
     public StringValueSpec plus () {
          this.fragments.add(new StringFragment());

          return this;
     }

     @Override
     public StringValueSpecFragment repeat (final int times) {
          return repeat(times, times);
     }

     @Override
     public StringValueSpecFragment repeat (final int minTimes, final int maxTimes) {
          if ((maxTimes < 0) || (minTimes < 0)) {
               throw new IllegalArgumentException(TypeMessages.getMessageRepetitionAmountMustNotBeNegative());
          }

          if (maxTimes < minTimes) {
               throw new IllegalArgumentException(TypeMessages.getMessageInvalidMaximumRepetitionAmount(minTimes,
                    maxTimes));
          }

          this.fragments.peekLast().maxRepeat = maxTimes;
          this.fragments.peekLast().minRepeat = minTimes;

          return this;
     }

     @Override
     public Stream<String> stream () {
          return Stream.generate( () -> {
               final StringBuilder result = new StringBuilder();

               this.fragments.forEach(fragment -> fragment.toString(result));

               return result.toString();
          });
     }

     @Override
     public StringValueSpecAdjustments thisOne (final String value) {
          return anyOf(value);
     }

     private final class StringFragment {
          private CharacterClass characterClass;
          private Iterator<?> iterator;
          private int maxRepeat = 1;
          private int minRepeat = 1;
          private String[] values;

          private void toString (final StringBuilder result) {
               final int repeat;

               if (this.minRepeat != this.maxRepeat) {
                    // Randomly-generated repeat value.

                    repeat = StringValueImpl.this.randomnessProvider.nextInt(this.minRepeat, this.maxRepeat + 1);
               }

               else {
                    // Fixed repeat value.

                    repeat = this.minRepeat;
               }

               for (int i = 0; i < repeat; ++i) {
                    if (this.characterClass != null) {
                         result.append(Character.toChars(this.characterClass.get()));
                    }

                    if (this.iterator != null) {
                         result.append(this.iterator.next());
                    }

                    if (this.values != null) {
                         result.append(this.values[StringValueImpl.this.randomnessProvider.nextInt(
                              this.values.length)]);
                    }
               }
          }
     }
}
