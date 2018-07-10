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
 * Used to specify constraints for date generation.
 */

public interface DateValueAnySpec extends ValueBuilder<LocalDateTime> {
     /**
      * Specifies that the generated date must be before the given date.
      *
      * @param latest a {@link LocalDateTime} object containing the latest date (exclusive).
      * @return a {@link ValueBuilder} object used to generate dates.
      * @throws IllegalArgumentException if the latest date is null.
      */

     ValueBuilder<LocalDateTime> before (LocalDateTime latest);

     /**
      * Specifies that the generated date must be on or after the earliest date and on or before the latest date.
      *
      * @param earliest a {@link LocalDateTime} object containing the earliest date (inclusive).
      * @param latest a {@link LocalDateTime} object containing the latest date (exclusive).
      * @return a {@link ValueBuilder} object used to generate dates.
      * @throws IllegalArgumentException if either argument is null or the latest date is on or before the earliest
      *         date.
      */

     ValueBuilder<LocalDateTime> between (LocalDateTime earliest, LocalDateTime latest);

     /**
      * Specifies that the generated date must be on or after the given date.
      *
      * @param earliest a {@link LocalDateTime} object containing the earliest date (inclusive).
      * @return a {@link ValueBuilder} object used to generate dates.
      * @throws IllegalArgumentException if the earliest date is null.
      */

     ValueBuilder<LocalDateTime> from (LocalDateTime earliest);
}
