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

import java.util.Map;

import com.handcraftedbits.edgeifier.api.Edgeifier;
import com.handcraftedbits.edgeifier.api.RandomnessProvider;
import com.handcraftedbits.edgeifier.api.value.ValueSpecSelector;
import com.handcraftedbits.edgeifier.api.value.collection.ListValueSpec;
import com.handcraftedbits.edgeifier.api.value.custom.CustomValueProviderFactory;
import com.handcraftedbits.edgeifier.api.value.custom.CustomValueSpec;
import com.handcraftedbits.edgeifier.api.value.primitive.BooleanValueSpec;
import com.handcraftedbits.edgeifier.api.value.primitive.ByteValueSpec;
import com.handcraftedbits.edgeifier.api.value.primitive.CharacterValueSpec;
import com.handcraftedbits.edgeifier.api.value.primitive.DateValueSpec;
import com.handcraftedbits.edgeifier.api.value.primitive.DoubleValueSpec;
import com.handcraftedbits.edgeifier.api.value.primitive.FloatValueSpec;
import com.handcraftedbits.edgeifier.api.value.primitive.IntegerValueSpec;
import com.handcraftedbits.edgeifier.api.value.primitive.LongValueSpec;
import com.handcraftedbits.edgeifier.api.value.primitive.ShortValueSpec;
import com.handcraftedbits.edgeifier.api.value.string.StringValueSpec;

class ValueSpecSelectorImpl implements ValueSpecSelector {
     private final Edgeifier edgeifier;
     private final RandomnessProvider randomnessProvider;
     private final Map<Class<?>, CustomValueProviderFactory<?>> valueProviderFactories;

     ValueSpecSelectorImpl (final Edgeifier edgeifier, final RandomnessProvider randomnessProvider,
          final Map<Class<?>, CustomValueProviderFactory<?>> valueProviderFactories) {
          this.edgeifier = edgeifier;
          this.randomnessProvider = randomnessProvider;
          this.valueProviderFactories = valueProviderFactories;
     }

     @Override
     public BooleanValueSpec makeBooleans () {
          return new BooleanValueImpl(this.randomnessProvider);
     }

     @Override
     public ByteValueSpec makeBytesLike () {
          return new ByteValueImpl(this.randomnessProvider);
     }

     @Override
     public CharacterValueSpec makeCharsLike () {
          return new CharacterValueImpl(this.randomnessProvider);
     }

     @Override
     public DateValueSpec makeDatesLike () {
          return new DateValueImpl(this.randomnessProvider);
     }

     @Override
     public DoubleValueSpec makeDoublesLike () {
          return new DoubleValueImpl(this.randomnessProvider);
     }

     @Override
     public FloatValueSpec makeFloatsLike () {
          return new FloatValueImpl(this.randomnessProvider);
     }

     @Override
     public IntegerValueSpec makeIntsLike () {
          return new IntegerValueImpl(this.randomnessProvider);
     }

     @Override
     public ListValueSpec makeListsLike () {
          return new ListValueImpl(this.randomnessProvider);
     }

     @Override
     public LongValueSpec makeLongsLike () {
          return new LongValueImpl(this.randomnessProvider);
     }

     @Override
     public ShortValueSpec makeShortsLike () {
          return new ShortValueImpl(this.randomnessProvider);
     }

     @Override
     public StringValueSpec makeStringsLike () {
          return new StringValueImpl(this.randomnessProvider);
     }

     @Override
     public CustomValueSpec makeValuesLike () {
          return new CustomValueImpl(this.edgeifier, this.valueProviderFactories);
     }
}
