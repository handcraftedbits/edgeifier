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

/**
 * Selects the appropriate value specification to build values of a given type.
 */

public interface ValueSpecSelector {
     /**
      * Retrieves a {@link ValueSpecSelector} used to build boolean values.
      *
      * @return a {@link BooleanValueSpec} object used to build boolean values.
      */

     BooleanValueSpec makeBooleans ();

     /**
      * Retrieves a {@link ValueSpecSelector} used to build byte values.
      *
      * @return a {@link ByteValueSpec} object used to build byte values.
      */

     ByteValueSpec makeBytesLike ();

     /**
      * Retrieves a {@link ValueSpecSelector} used to build character values.
      *
      * @return a {@link CharacterValueSpec} object used to build character values.
      */

     CharacterValueSpec makeCharsLike ();

     /**
      * Retrieves a {@link ValueSpecSelector} used to build date values.
      *
      * @return a {@link DateValueSpec} object used to build date values.
      */

     DateValueSpec makeDatesLike ();

     /**
      * Retrieves a {@link ValueSpecSelector} used to build double values.
      *
      * @return a {@link DoubleValueSpec} object used to build double values.
      */

     DoubleValueSpec makeDoublesLike ();

     /**
      * Retrieves a {@link ValueSpecSelector} used to build float values.
      *
      * @return a {@link FloatValueSpec} object used to build float values.
      */

     FloatValueSpec makeFloatsLike ();

     /**
      * Retrieves a {@link ValueSpecSelector} used to build integer values.
      *
      * @return an {@link IntegerValueSpec} object used to build integer values.
      */

     IntegerValueSpec makeIntsLike ();

     /**
      * Retrieves a {@link ValueSpecSelector} used to build list values.
      *
      * @return a {@link ListValueSpec} object used to build list values.
      */

     ListValueSpec makeListsLike ();

     /**
      * Retrieves a {@link ValueSpecSelector} used to build long values.
      *
      * @return a {@link LongValueSpec} object used to build long values.
      */

     LongValueSpec makeLongsLike ();

     /**
      * Retrieves a {@link ValueSpecSelector} used to build short values.
      *
      * @return a {@link ShortValueSpec} object used to build short values.
      */

     ShortValueSpec makeShortsLike ();

     /**
      * Retrieves a {@link ValueSpecSelector} used to build {@link String} values.
      *
      * @return a {@link StringValueSpec} object used to build {@link String} values.
      */

     StringValueSpec makeStringsLike ();

     /**
      * Retrieves a {@link ValueSpecSelector} used to build custom values.
      *
      * @return a {@link CustomValueSpec} object used to build custom values.
      */

     CustomValueSpec makeValuesLike ();
}
