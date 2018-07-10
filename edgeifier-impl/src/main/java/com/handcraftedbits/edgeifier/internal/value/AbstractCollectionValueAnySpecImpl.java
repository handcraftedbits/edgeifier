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

import java.util.stream.Stream;

import com.handcraftedbits.edgeifier.api.value.ValueBuilder;
import com.handcraftedbits.edgeifier.api.value.collection.CollectionValueAnySpec;

abstract class AbstractCollectionValueAnySpecImpl<T> implements CollectionValueAnySpec<T> {
     private int maximum;
     private int minimum;

     abstract T createAndPopulateCollection (int minimum, int maximum);

     private ValueBuilder<T> createValueBuilder () {
          return new ValueBuilder<T>() {
               @Override
               public Stream<T> stream () {
                    return Stream.generate( () -> {
                         return createAndPopulateCollection(AbstractCollectionValueAnySpecImpl.this.minimum,
                              AbstractCollectionValueAnySpecImpl.this.maximum);
                    });
               }
          };
     }

     @Override
     public Stream<T> stream () {
          return createValueBuilder().stream();
     }

     @Override
     public ValueBuilder<T> withLength (final int length) {
          return withLengthBetween(length, length);
     }

     @Override
     public ValueBuilder<T> withLengthBetween (final int minimum, final int maximum) {
          if ((minimum < 0) || (maximum < 0)) {
               throw new IllegalArgumentException(TypeMessages.getMessageNegativeLength(minimum, maximum));
          }

          if (maximum < minimum) {
               throw new IllegalArgumentException(TypeMessages.getMessageInvalidMaximumLength(minimum, maximum));
          }

          this.maximum = maximum;
          this.minimum = minimum;

          return createValueBuilder();
     }

     @Override
     public ValueBuilder<T> withLengthLessThan (final int maximum) {
          if (maximum <= this.minimum) {
               throw new IllegalArgumentException(TypeMessages.getMessageInvalidMaximumLength(this.minimum, maximum));
          }

          this.maximum = maximum;

          return createValueBuilder();
     }
}
