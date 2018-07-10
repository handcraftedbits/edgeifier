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
package com.handcraftedbits.edgeifier.api.value.primitive;

import com.handcraftedbits.edgeifier.api.value.ValueBuilder;

/**
 * A specification for generating long values.
 */

public interface LongValueSpec {
     /**
      * Specifies that any long value, with certain restrictions, may be generated.
      *
      * @return a {@link LongValueAnySpec} object used to apply constraints on the generated values.
      */

     LongValueAnySpec any ();

     /**
      * Specifies that any of the provided long values may be generated.
      *
      * @param values a long array containing the values that may be generated.
      * @return a {@link ValueBuilder} object used to generate values.
      * @throws IllegalArgumentException if the array of values is null or empty.
      */

     ValueBuilder<Long> anyOf (long... values);

     /**
      * Specifies that a specific long value must be generated.
      *
      * @param value a long containing the value to generate.
      * @return a {@link ValueBuilder} object used to generate values.
      */

     ValueBuilder<Long> thisOne (long value);
}
