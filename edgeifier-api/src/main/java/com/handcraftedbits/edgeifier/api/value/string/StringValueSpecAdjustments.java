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
package com.handcraftedbits.edgeifier.api.value.string;

import com.handcraftedbits.edgeifier.api.value.ValueBuilder;

/**
 * Used to specify additional restrictions on a {@link String} value specification.
 */

public interface StringValueSpecAdjustments extends StringValueSpecFragment, ValueBuilder<String> {
     /**
      * Specifies that the String value specification is optional.
      *
      * @return a {@link StringValueSpecFragment} object used to define additional {@link String} value specifications,
      *         if desired.
      */

     StringValueSpecFragment optional ();

     /**
      * Specifies that the {@link String} value specification will be repeated a number of times.
      *
      * @param times an integer containing the number of times to repeat the {@link String} value specification.
      * @return a {@link StringValueSpecFragment} object used to define additional {@link String} value specifications,
      *         if desired.
      * @throws IllegalArgumentException if the number of times specified is negative.
      */

     StringValueSpecFragment repeat (int times);

     /**
      * Specifies that the {@link String} value specification will be repeated between a minimum and maximum number of
      * times.
      *
      * @param minTimes an integer containing the minimum number of times to repeat the {@link String} value
      *        specification.
      * @param maxTimes an integer containing the maximum number of times to repeat the {@link String} value
      *        specification.
      * @return a {@link StringValueSpecFragment} object used to define additional {@link String} value specifications,
      *         if desired.
      * @throws IllegalArgumentException if either of the arguments is negative or if the maximum number of times to
      *         repeat is less than the minimum number of times to repeat.
      */

     StringValueSpecFragment repeat (int minTimes, int maxTimes);
}
