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

import java.util.stream.Stream;

/**
 * Builds randomly-generated values of a specific type.
 *
 * @param <T> the value type to generate.
 */

public interface ValueBuilder<T> {
     /**
      * Retrieves an infinite stream of randomly-generated values.
      *
      * @return a {@link Stream} object containing an infinite stream of randomly-generated values.
      */

     Stream<T> stream ();
}
