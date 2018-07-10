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
 * Used to specify constraints for custom value generation.
 */

public interface CustomValueAnySpec<T> extends ValueBuilder<T> {
     /**
      * Specifies that a particular property will be used as a constraint on generated custom values.
      *
      * @param name a {@link String} object containing the property name.
      * @param value an {@link Object} containing the property value.
      * @return a {@link CustomValueAnySpec} object used to apply further constraints on the generated values.
      * @throws IllegalArgumentException if the property name is null.
      */

     CustomValueAnySpec<T> withProperty (String name, Object value);
}
