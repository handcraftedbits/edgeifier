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

import java.util.Map;
import java.util.stream.Stream;

import com.handcraftedbits.edgeifier.api.Edgeifier;
import com.handcraftedbits.edgeifier.api.value.ValueBuilder;
import com.handcraftedbits.edgeifier.api.value.custom.CustomValueAnySpec;
import com.handcraftedbits.edgeifier.api.value.custom.CustomValueProvider;
import com.handcraftedbits.edgeifier.api.value.custom.CustomValueProviderFactory;
import com.handcraftedbits.edgeifier.api.value.custom.CustomValueSpec;

class CustomValueImpl implements CustomValueSpec {
     private final Edgeifier edgeifier;
     private final Map<Class<?>, CustomValueProviderFactory<?>> valueProviderFactories;

     CustomValueImpl (final Edgeifier edgeifier,
          final Map<Class<?>, CustomValueProviderFactory<?>> valueProviderFactories) {
          this.edgeifier = edgeifier;
          this.valueProviderFactories = valueProviderFactories;
     }

     @Override
     @SuppressWarnings("unchecked")
     public <T> CustomValueAnySpec<T> any (final Class<T> valueClass) {
          final CustomValueProviderFactory<T> valueProviderFactory;
          final CustomValueProvider<T> valueProvider;

          if (valueClass == null) {
               throw new IllegalArgumentException(TypeMessages.getMessageValueClassMustNotBeNull());
          }

          valueProviderFactory = (CustomValueProviderFactory<T>) this.valueProviderFactories.get(valueClass);

          if (valueProviderFactory == null) {
               throw new IllegalArgumentException(TypeMessages.getMessageValueProviderFactoryNotFound(valueClass));
          }

          valueProvider = valueProviderFactory.newCustomValueProvider();

          return new CustomValueAnySpec<T>() {
               @Override
               public Stream<T> stream () {
                    return Stream.generate( () -> valueProvider.generateValue(CustomValueImpl.this.edgeifier));
               }

               @Override
               public CustomValueAnySpec<T> withProperty (final String name, final Object value) {
                    if (name == null) {
                         throw new IllegalArgumentException(TypeMessages.getMessagePropertyNameMustNotBeNull());
                    }

                    valueProvider.setProperty(name, value);

                    return this;
               }
          };
     }

     @Override
     @SafeVarargs
     public final <T> ValueBuilder<T> anyOf (final T... values) {
          if ((values == null) || (values.length == 0)) {
               throw new IllegalArgumentException(TypeMessages.getMessageMissingValues());
          }

          for (final T value : values) {
               if (value == null) {
                    throw new IllegalArgumentException(TypeMessages.getMessageValueMustNotBeNull());
               }
          }

          return new ValueBuilder<T>() {
               @Override
               public Stream<T> stream () {
                    return Stream.generate( () -> {
                         final ValueBuilder<Integer> size = CustomValueImpl.this.edgeifier.makeIntsLike().any()
                              .between(0, values.length);

                         return values[size.stream().findFirst().get()];
                    });
               }
          };
     }

     @Override
     public <T> ValueBuilder<T> thisOne (final T value) {
          return anyOf(value);
     }
}
