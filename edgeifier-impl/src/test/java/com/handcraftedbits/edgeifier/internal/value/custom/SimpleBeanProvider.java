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
package com.handcraftedbits.edgeifier.internal.value.custom;

import com.handcraftedbits.edgeifier.api.Edgeifier;
import com.handcraftedbits.edgeifier.api.value.ValueBuilder;
import com.handcraftedbits.edgeifier.api.value.custom.CustomValueProvider;

class SimpleBeanProvider implements CustomValueProvider<SimpleBean> {
     @Override
     public SimpleBean generateValue (final Edgeifier edgeifier) {
          final ValueBuilder<String> builder = edgeifier.makeStringsLike().anyInAlphabet('a', 'z').repeat(10);

          return new SimpleBean(builder.stream().iterator().next());
     }

     @Override
     public void setProperty (final String name, final Object value) {
     }
}
