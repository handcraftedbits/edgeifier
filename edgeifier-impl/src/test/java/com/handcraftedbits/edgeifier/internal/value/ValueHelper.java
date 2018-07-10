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

import java.time.LocalDateTime;
import java.util.stream.Stream;

import com.handcraftedbits.edgeifier.api.Edgeifier;

final class ValueHelper {
     static final int STREAM_LENGTH = 1000;
     static final Edgeifier edgeifier = new Edgeifier(1480323660000L);

     private ValueHelper () {
     }

     static <T extends Comparable<T>> boolean allOfStreamAtLeast (final Stream<T> stream, final T value) {
          return stream.limit(ValueHelper.STREAM_LENGTH).allMatch(item -> item.compareTo(value) >= 0);
     }

     static boolean allOfStreamBefore (final Stream<LocalDateTime> stream, final LocalDateTime date) {
          return stream.limit(ValueHelper.STREAM_LENGTH).allMatch(item -> item.compareTo(date) < 0);
     }

     static boolean allOfStreamIsOrIsAfter (final Stream<LocalDateTime> stream, final LocalDateTime date) {
          return stream.limit(ValueHelper.STREAM_LENGTH).allMatch(item -> item.compareTo(date) >= 0);
     }

     static <T extends Comparable<T>> boolean allOfStreamLessThan (final Stream<T> stream, final T value) {
          return stream.limit(ValueHelper.STREAM_LENGTH).allMatch(item -> item.compareTo(value) < 0);
     }

     static boolean streamOnlyContains (final Stream<LocalDateTime> stream, final LocalDateTime... dates) {
          return stream.limit(ValueHelper.STREAM_LENGTH).allMatch(item -> {
               for (final LocalDateTime date : dates) {
                    if (item.equals(date)) {
                         return true;
                    }
               }

               return false;
          });
     }

     @SafeVarargs
     static final <T extends Comparable<T>> boolean streamOnlyContains (final Stream<T> stream, final T... values) {
          return stream.limit(ValueHelper.STREAM_LENGTH).allMatch(item -> {
               for (final T value : values) {
                    if (item.equals(value)) {
                         return true;
                    }
               }

               return false;
          });
     }

     static boolean streamOnlyHasDatesBetween (final Stream<LocalDateTime> stream, final LocalDateTime earliest,
          final LocalDateTime latest) {
          return stream.limit(ValueHelper.STREAM_LENGTH).allMatch(item -> (item.compareTo(earliest) >= 0) &&
               (item.compareTo(latest) <= 0));
     }

     static <T extends Comparable<T>> boolean streamOnlyHasValuesBetween (final Stream<T> stream, final T start,
          final T end) {
          return stream.limit(ValueHelper.STREAM_LENGTH).allMatch(item -> (item.compareTo(start) >= 0) &&
               (item.compareTo(end) <= 0));
     }
}
