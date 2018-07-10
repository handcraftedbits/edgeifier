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

/**
 * Used to generate random numbers of various types.
 */

public interface RandomnessProvider {
     /**
      * Returns a random boolean value.
      *
      * @return a boolean containing a random value.
      */

     boolean nextBoolean ();

     /**
      * Returns a random double value between {@code 0.0} (inclusive) and {@code 1.0} (exclusive).
      *
      * @return a double containing a random value.
      */

     double nextDouble ();

     /**
      * Returns a random double value between {@code 0.0} (inclusive) and {@code bound} (exclusive).
      *
      * @param bound a double containing the maximum value (exclusive) to use.
      * @return a double containing a random value.
      * @throws IllegalArgumentException if {@code bound} is less than or equal to {@code 0.0}.
      */

     double nextDouble (double bound);

     /**
      * Returns a random double value between {@code origin} (inclusive) and {@code bound} (exclusive).
      *
      * @param origin a double containing the minimum value (exclusive) to use.
      * @param bound a double containing the maximum value (exclusive) to use.
      * @return a double containing a random value.
      * @throws IllegalArgumentException if {@code bound} is less than or equal to {@code origin}.
      */

     double nextDouble (double origin, double bound);

     /**
      * Returns a random integer value between {@link Integer#MIN_VALUE} (inclusive) and {@link Integer#MAX_VALUE}
      * (exclusive).
      *
      * @return an integer containing a random value.
      */

     int nextInt ();

     /**
      * Returns a random integer value between {@code 0} (inclusive) and {@code bound} (exclusive).
      *
      * @param bound an integer containing the maximum value (exclusive) to use.
      * @return an integer containing a random value.
      * @throws IllegalArgumentException if {@code bound} is less than or equal to {@code 0}.
      */

     int nextInt (int bound);

     /**
      * Returns a random integer value between {@code origin} (inclusive) and {@code bound} (exclusive).
      *
      * @param origin an integer containing the minimum value (exclusive) to use.
      * @param bound an integer containing the maximum value (exclusive) to use.
      * @return an integer containing a random value.
      * @throws IllegalArgumentException if {@code bound} is less than or equal to {@code origin}.
      */

     int nextInt (int origin, int bound);

     /**
      * Returns a random long value between {@link Long#MIN_VALUE} (inclusive) and {@link Long#MAX_VALUE} (exclusive).
      *
      * @return a long containing a random value.
      */

     long nextLong ();

     /**
      * Returns a random long value between {@code 0} (inclusive) and {@code bound} (exclusive).
      *
      * @param bound a long containing the maximum value (exclusive) to use.
      * @return a long containing a random value.
      * @throws IllegalArgumentException if {@code bound} is less than or equal to {@code 0}.
      */

     long nextLong (long bound);

     /**
      * Returns a random long value between {@code origin} (inclusive) and {@code bound} (exclusive).
      *
      * @param origin a long containing the minimum value (exclusive) to use.
      * @param bound a long containing the maximum value (exclusive) to use.
      * @return a long containing a random value.
      * @throws IllegalArgumentException if {@code bound} is less than or equal to {@code origin}.
      */

     long nextLong (long origin, long bound);
}
