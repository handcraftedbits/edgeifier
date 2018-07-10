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
package com.handcraftedbits.edgeifier.api.value.collection;

import java.util.List;

import com.handcraftedbits.edgeifier.api.value.ValueBuilder;

/**
 * Used to specify constraints for {@link List} value generation.
 */

public interface CollectionValueAnySpec<T> extends ValueBuilder<T> {
     /**
      * Specifies that the generated {@link List} value must have an exact length.
      *
      * @param length an integer containing the length to use.
      * @return a {@link ValueBuilder} object used to generate {@link List} values.
      * @throws IllegalArgumentException if the given length is negative.
      */

     ValueBuilder<T> withLength (int length);

     /**
      * Specifies that the generated {@link List} value must have a length between the specified minimum (inclusive) and
      * maximum (exclusive) values.
      *
      * @param minimum an integer containing the minimum length to use.
      * @param maximum an integer containing the maximum length to use.
      * @return a {@link ValueBuilder} object used to generate {@link List} values.
      * @throws IllegalArgumentException if either parameter is negative or if the maximum length is less than the
      *              minimum length.
      */

     ValueBuilder<T> withLengthBetween (int minimum, int maximum);

     /**
      * Specifies that the generated {@link List} value must have a length less than a given maximum value.
      *
      * @param maximum an integer containing the maximum value for the generated {@link List} value.
      * @return a {@link ValueBuilder} object used to generate {@link List} values.
      * @throws IllegalArgumentException if the given length is less than or equal to zero.
      */

     ValueBuilder<T> withLengthLessThan (int maximum);
}
