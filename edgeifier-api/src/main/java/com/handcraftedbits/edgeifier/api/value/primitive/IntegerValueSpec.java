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
 * A specification for generating integer values.
 */

public interface IntegerValueSpec {
     /**
      * Specifies that any integer value, with certain restrictions, may be generated.
      *
      * @return an {@link IntegerValueAnySpec} object used to apply constraints on the generated values.
      */

     IntegerValueAnySpec any ();

     /**
      * Specifies that any of the provided integer values may be generated.
      *
      * @param values a integer array containing the values that may be generated.
      * @return a {@link ValueBuilder} object used to generate values.
      * @throws IllegalArgumentException if the array of values is null or empty.
      */

     ValueBuilder<Integer> anyOf (int... values);

     /**
      * Specifies that a specific integer value must be generated.
      *
      * @param value a integer containing the value to generate.
      * @return a {@link ValueBuilder} object used to generate values.
      */

     ValueBuilder<Integer> thisOne (int value);
}
