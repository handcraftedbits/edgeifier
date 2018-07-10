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
 * Used to specify the end of a {@link String} value specification fragment.
 */

public interface StringValueSpecFragment extends ValueBuilder<String> {
     /**
      * Indicates that the current {@link String} value specification fragment has finished and another one will be
      * declared.
      *
      * @return a {@link StringValueSpec} object used to declare the next {@link String} value specification fragment.
      */

     StringValueSpec plus ();
}
