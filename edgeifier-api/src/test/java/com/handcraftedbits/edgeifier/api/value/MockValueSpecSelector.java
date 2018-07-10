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
package com.handcraftedbits.edgeifier.api.value;

import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.handcraftedbits.edgeifier.api.value.collection.ListValueSpec;
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
import com.handcraftedbits.edgeifier.api.value.string.StringValueSpecAdjustments;

class MockValueSpecSelector implements ValueSpecSelector {
     @Override
     public BooleanValueSpec makeBooleans () {
          return Mockito.mock(BooleanValueSpec.class);
     }

     @Override
     public ByteValueSpec makeBytesLike () {
          return Mockito.mock(ByteValueSpec.class);
     }

     @Override
     public CharacterValueSpec makeCharsLike () {
          return Mockito.mock(CharacterValueSpec.class);
     }

     @Override
     public DateValueSpec makeDatesLike () {
          return Mockito.mock(DateValueSpec.class);
     }

     @Override
     public DoubleValueSpec makeDoublesLike () {
          return Mockito.mock(DoubleValueSpec.class);
     }

     @Override
     public FloatValueSpec makeFloatsLike () {
          return Mockito.mock(FloatValueSpec.class);
     }

     @Override
     public IntegerValueSpec makeIntsLike () {
          return Mockito.mock(IntegerValueSpec.class);
     }

     @Override
     public ListValueSpec makeListsLike () {
          return Mockito.mock(ListValueSpec.class);
     }

     @Override
     public LongValueSpec makeLongsLike () {
          return Mockito.mock(LongValueSpec.class);
     }

     @Override
     public ShortValueSpec makeShortsLike () {
          return Mockito.mock(ShortValueSpec.class);
     }

     @Override
     public StringValueSpec makeStringsLike () {
          final StringValueSpecAdjustments mockStringValueSpecAdjustments =
               Mockito.mock(StringValueSpecAdjustments.class);
          final StringValueSpec mockStringValueSpec = Mockito.mock(StringValueSpec.class);

          Mockito.when(mockStringValueSpec.anyInAlphabet(ArgumentMatchers.any())).thenReturn(
               mockStringValueSpecAdjustments);
          Mockito.when(mockStringValueSpec.anyInRange(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt())).thenReturn(
               mockStringValueSpecAdjustments);

          return mockStringValueSpec;
     }

     @Override
     public CustomValueSpec makeValuesLike () {
          return Mockito.mock(CustomValueSpec.class);
     }
}
