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

import com.handcraftedbits.edgeifier.api.Edgeifier;
import com.handcraftedbits.edgeifier.api.RandomnessProvider;
import com.handcraftedbits.edgeifier.api.value.custom.CustomValueProviderFactory;
import com.handcraftedbits.edgeifier.api.value.custom.CustomValueSpec;

/**
 * Creates {@link ValueSpecSelector} instances and allows registration of {@link CustomValueProviderFactory} instances.
 */

public interface ValueSpecSelectorFactory {
     /**
      * Creates a {@link ValueSpecSelector} instance.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @param randomnessProvider a {@link RandomnessProvider} object containing the randomness provider to use.
      * @return a {@link ValueSpecSelector} object used to select value specifications.
      */

     ValueSpecSelector newValueSpecSelector (Edgeifier edgeifier, RandomnessProvider randomnessProvider);

     /**
      * Registers a {@link CustomValueProviderFactory} for use with {@link CustomValueSpec}.
      *
      * @param valueProviderFactory a {@link CustomValueProviderFactory} object containing the custom value provider
      *        factory to register.
      * @throws IllegalArgumentException if the {@link CustomValueProviderFactory} instance is null.
      */

     void registerCustomValueProviderFactory (CustomValueProviderFactory<?> valueProviderFactory);
}
