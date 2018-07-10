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
package com.handcraftedbits.edgeifier.api.value.custom;

import com.handcraftedbits.edgeifier.api.value.ValueBuilder;

/**
 * A specification for generating custom values.
 */

public interface CustomValueSpec {
     /**
      * Specifies that any custom value, with certain restrictions, may be generated.
      *
      * @param valueClass a {@link Class} object containing the type of the value to generate.
      * @return a {@link CustomValueAnySpec} object used to apply constraints on the generated values.
      * @throws IllegalArgumentException if the value class is null or if a corresponding
      *              {@link CustomValueProviderFactory} has not been registered.
      */

     <T> CustomValueAnySpec<T> any (Class<T> valueClass);

     /**
      * Specifies that any of the provided custom values may be generated.
      *
      * @param values an array containing the custom values that may be generated.
      * @return a {@link ValueBuilder} object used to generate values.
      * @throws IllegalArgumentException if the array of custom values is null or empty.
      */

     @SuppressWarnings("unchecked")
     <T> ValueBuilder<T> anyOf (T... values);

     /**
      * Specifies that a specific custom value must be generated.
      *
      * @param value the custom value to generate.
      * @return a {@link ValueBuilder} object used to generate values.
      * @throws IllegalArgumentException if the custom value is null.
      */

     <T> ValueBuilder<T> thisOne (T value);
}
