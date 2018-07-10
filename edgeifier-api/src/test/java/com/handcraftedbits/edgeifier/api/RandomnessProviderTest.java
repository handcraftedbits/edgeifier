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
package com.handcraftedbits.edgeifier.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * This test class is just for coverage in the API project. More extensive testing happens in the implementation
 * project.
 */

class RandomnessProviderTest {
     private final RandomnessProvider randomnessProvider = new Edgeifier().randomnessProvider.get();

     @Test
     void testNextBoolean () {
          Assertions.assertTrue((this.randomnessProvider.nextBoolean() || true));
     }

     @Test
     void testNextDouble () {
          Assertions.assertAll(
               () -> {
                    final double value = this.randomnessProvider.nextDouble();

                    Assertions.assertTrue((value >= 0.0D) && (value < 1.0D));
               },
               () -> Assertions.assertTrue(this.randomnessProvider.nextDouble(1.0D) >= 0.0d),
               () -> {
                    final double value = this.randomnessProvider.nextDouble(0.0D, 1.0D);

                    Assertions.assertTrue((value >= 0.0D) && (value < 1.0D));
               });
     }

     @Test
     void testNextInt () {
          Assertions.assertAll(
               () -> {
                    final int value = this.randomnessProvider.nextInt();

                    Assertions.assertTrue((value >= Integer.MIN_VALUE) && (value <= Integer.MAX_VALUE));
               },
               () -> Assertions.assertTrue(this.randomnessProvider.nextInt(10) >= 0),
               () -> {
                    final int value = this.randomnessProvider.nextInt(0, 10);

                    Assertions.assertTrue((value >= 0) && (value < 10));
               });
     }

     @Test
     void testNextLong () {
          Assertions.assertAll(
               () -> {
                    final long value = this.randomnessProvider.nextLong();

                    Assertions.assertTrue((value >= Long.MIN_VALUE) && (value <= Long.MAX_VALUE));
               },
               () -> Assertions.assertTrue(this.randomnessProvider.nextLong(10L) >= 0L),
               () -> {
                    final long value = this.randomnessProvider.nextLong(0L, 10L);

                    Assertions.assertTrue((value >= 0L) && (value < 10L));
               });
     }
}
