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

import java.time.LocalDateTime;

import com.handcraftedbits.edgeifier.api.value.ValueBuilder;

/**
 * A specification for generating dates.
 */

public interface DateValueSpec {
     /**
      * Specifies that any date, with certain restrictions, may be generated.
      *
      * @return a {@link DateValueAnySpec} object used to apply constraints on the generated dates.
      */

     DateValueAnySpec any ();

     /**
      * Specifies that any of the provided dates may be generated.
      *
      * @param values an array of {@link LocalDateTime} objects containing the dates that may be generated.
      * @return a {@link ValueBuilder} object used to generate dates.
      * @throws IllegalArgumentException if the array of dates is null, empty, or if any of the dates are null.
      */

     ValueBuilder<LocalDateTime> anyOf (LocalDateTime... values);

     /**
      * Specifies that a specific date must be generated.
      *
      * @param value a {@link LocalDateTime} object containing the date to generate.
      * @return a {@link ValueBuilder} instance used to generate dates.
      */

     ValueBuilder<LocalDateTime> thisOne (LocalDateTime value);
}
