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
package com.handcraftedbits.edgeifier.api.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.handcraftedbits.edgeifier.api.Edgeifier;

/**
 * This test class is just for coverage in the API project. More extensive testing happens in the implementation
 * project.
 */

class UnicodeBuilderTest {
     private final Edgeifier edgeifier = new Edgeifier();

     @Test
     void testForC () {
          Assertions.assertNotNull(UnicodeBuilder.forC(this.edgeifier));

          // Do this one again to ensure that we cover the case where the solo character array is cached.

          Assertions.assertNotNull(UnicodeBuilder.forC(this.edgeifier));
     }

     @Test
     void testForL () {
          Assertions.assertNotNull(UnicodeBuilder.forL(this.edgeifier));
     }

     @Test
     void testForM () {
          Assertions.assertNotNull(UnicodeBuilder.forM(this.edgeifier));
     }

     @Test
     void testForN () {
          Assertions.assertNotNull(UnicodeBuilder.forN(this.edgeifier));
     }

     @Test
     void testForP () {
          Assertions.assertNotNull(UnicodeBuilder.forP(this.edgeifier));
     }

     @Test
     void testForS () {
          Assertions.assertNotNull(UnicodeBuilder.forS(this.edgeifier));
     }

     @Test
     void testForZ () {
          Assertions.assertNotNull(UnicodeBuilder.forZ(this.edgeifier));
     }
}
