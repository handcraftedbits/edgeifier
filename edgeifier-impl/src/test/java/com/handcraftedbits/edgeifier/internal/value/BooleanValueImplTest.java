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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BooleanValueImplTest {
     @Test
     void testAnyValid () {
          Assertions.assertTrue(ValueHelper.edgeifier.makeBooleans().stream().limit(ValueHelper.STREAM_LENGTH)
               .allMatch(item -> item.equals(true) || item.equals(false)));
     }

     @Test
     void testThisOneValid () {
          Assertions.assertTrue(ValueHelper.edgeifier.makeBooleans().thisOne(false).stream()
               .limit(ValueHelper.STREAM_LENGTH).noneMatch(item -> item.equals(true)));
     }
}
