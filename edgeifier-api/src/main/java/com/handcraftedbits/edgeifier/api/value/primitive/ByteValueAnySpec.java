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
package com.handcraftedbits.edgeifier.api.value.primitive;

import com.handcraftedbits.edgeifier.api.value.ValueBuilder;

/**
 * Used to specify constraints for byte value generation.
 */

public interface ByteValueAnySpec extends ValueBuilder<Byte> {
     /**
      * Specifies that the generated value must be greater than or equal to the given value.
      *
      * @param minimum a byte containing the minimum value (inclusive).
      * @return a {@link ValueBuilder} object used to generate values.
      */

     ValueBuilder<Byte> atLeast (byte minimum);

     /**
      * Specifies that the generated value must be greater than or equal to the minimum value and less than the maximum
      * value.
      *
      * @param minimum a byte containing the minimum value (inclusive).
      * @param maximum a byte containing the maximum value (exclusive).
      * @return a {@link ValueBuilder} object used to generate values.
      * @throws IllegalArgumentException if the maximum value is less than or equal to the minimum value.
      */

     ValueBuilder<Byte> between (byte minimum, byte maximum);

     /**
      * Specifies that the generated value must be less than the given value.
      *
      * @param maximum a byte containing the maximum value (exclusive).
      * @return a {@link ValueBuilder} object used to generate values.
      */

     ValueBuilder<Byte> lessThan (byte maximum);
}
