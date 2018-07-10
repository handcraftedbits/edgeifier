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

import com.handcraftedbits.edgeifier.api.RandomnessProvider;

class RangedCharacterClass implements CharacterClass {
     private final int begin;
     private final int length;
     private final RandomnessProvider randomnessProvider;

     RangedCharacterClass (final RandomnessProvider randomnessProvider, final int begin, final int end) {
          this.begin = begin;
          this.length = (end - begin) + 1;
          this.randomnessProvider = randomnessProvider;
     }

     @Override
     public int get () {
          return (this.randomnessProvider.nextInt(this.length) + this.begin);
     }

}
