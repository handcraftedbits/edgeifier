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
 * A specification for generating {@link List} values.
 */

public interface ListValueSpec {
     /**
      * Specifies that any {@link List} value, with certain restrictions, may be generated.
      *
      * @param builder the {@link ValueBuilder} object used to generate the individual values of the list.
      * @return a {@link CollectionValueAnySpec} object used to apply constraints on the generated values.
      * @throws IllegalArgumentException if the {@link ValueBuilder} instance is null.
      */

     <T> CollectionValueAnySpec<List<T>> any (ValueBuilder<T> builder);
}
