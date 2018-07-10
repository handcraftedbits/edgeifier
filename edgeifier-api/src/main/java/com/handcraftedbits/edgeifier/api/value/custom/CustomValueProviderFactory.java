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

/**
 * Used to create {@link CustomValueProvider} instances.
 *
 * @param <T> the custom value type that will be generated.
 */

public interface CustomValueProviderFactory<T> {
     /**
      * Creates a new {@link CustomValueProvider} instance that will be used to generate custom values.
      *
      * @return a {@link CustomValueProvider} object.
      */

     CustomValueProvider<T> newCustomValueProvider ();

     /**
      * Retrieves the {@link Class} for the custom value that will be generated.
      *
      * @return a {@link Class} object containing the type of the custom value that will be generated.
      */

     Class<T> valueClass ();
}
