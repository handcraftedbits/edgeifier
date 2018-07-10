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

import com.handcraftedbits.edgeifier.api.Edgeifier;

/**
 * Used to generate random custom values.
 *
 * @param <T> the custom value type that will be generated.
 */

public interface CustomValueProvider<T> {
     /**
      * Generates a custom value.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return an Object containing the generated custom value.
      */

     T generateValue (Edgeifier edgeifier);

     /**
      * Sets a property that controls custom value generation.
      *
      * @param name a {@link String} containing the property name.
      * @param value an {@link Object} containing the property value.
      */

     void setProperty (String name, Object value);
}
