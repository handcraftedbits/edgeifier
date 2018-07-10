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

import com.handcraftedbits.edgeifier.api.RandomnessProvider;
import com.handcraftedbits.edgeifier.api.value.ValueBuilder;
import com.handcraftedbits.edgeifier.api.value.primitive.BooleanValueSpec;

class BooleanValueImpl implements BooleanValueSpec {
     private Boolean fixedValue;
     private final RandomnessProvider randomnessProvider;

     BooleanValueImpl (final RandomnessProvider randomnessProvider) {
          this.randomnessProvider = randomnessProvider;
     }

     @Override
     public Stream<Boolean> stream () {
          return Stream.generate( () -> {
               return (this.fixedValue != null ? this.fixedValue : this.randomnessProvider.nextBoolean());
          });
     }

     @Override
     public ValueBuilder<Boolean> thisOne (final boolean value) {
          this.fixedValue = value;

          return this;
     }
}
