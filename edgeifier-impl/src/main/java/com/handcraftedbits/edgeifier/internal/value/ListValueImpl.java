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
package com.handcraftedbits.edgeifier.internal.value;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.handcraftedbits.edgeifier.api.RandomnessProvider;
import com.handcraftedbits.edgeifier.api.value.ValueBuilder;
import com.handcraftedbits.edgeifier.api.value.collection.CollectionValueAnySpec;
import com.handcraftedbits.edgeifier.api.value.collection.ListValueSpec;

class ListValueImpl implements ListValueSpec {
     private final RandomnessProvider randomnessProvider;

     ListValueImpl (final RandomnessProvider randomnessProvder) {
          this.randomnessProvider = randomnessProvder;
     }

     @Override
     public <T> CollectionValueAnySpec<List<T>> any (final ValueBuilder<T> builder) {
          if (builder == null) {
               throw new IllegalArgumentException(TypeMessages.getMessageValueBuilderMustNotBeNull());
          }

          return new AbstractCollectionValueAnySpecImpl<List<T>>() {
               @Override
               List<T> createAndPopulateCollection (final int minimum, final int maximum) {
                    final Iterator<T> itemIterator = builder.stream().iterator();
                    final int length = (minimum == maximum) ? minimum : ListValueImpl.this.randomnessProvider.nextInt(
                         minimum, maximum);
                    final LinkedList<T> list = new LinkedList<>();

                    for (int i = 0; i < length; ++i) {
                         list.add(itemIterator.next());
                    }

                    return list;
               }
          };
     }
}
