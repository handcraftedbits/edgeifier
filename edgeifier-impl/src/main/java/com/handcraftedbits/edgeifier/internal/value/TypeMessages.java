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

final class TypeMessages {
     private TypeMessages () {
     }

     static String getMessageDateMustNotBeNull () {
          return "date must not be null";
     }

     static String getMessageInvalidMaximumDate (final LocalDateTime earliest, final LocalDateTime latest) {
          return String.format("cannot generate date between %s and %s; latest date is less than or equal to " +
               "earliest date", earliest, latest);
     }

     static String getMessageInvalidMaximumLength (final int minimum, final int maximum) {
          return String.format("cannot generate collection with length between %s and %s; maximum length is less " +
               "than minimum length", minimum, maximum);
     }

     static String getMessageInvalidMaximumRepetitionAmount (final int minimum, final int maximum) {
          return String.format("cannot generate String with minimum and maximum repetions of %s and %s; maximum " +
               "repetition amount is less than minimum repetition amount", minimum, maximum);
     }

     static String getMessageInvalidMaximumValue (final Object minimum, final Object maximum) {
          return String.format("cannot generate value between %s and %s; maximum value is less than or equal to " +
               "minimum value", minimum, maximum);
     }

     static String getMessageInvalidRange (final Object begin, final Object end) {
          return String.format("cannot generate value in range '%s'-'%s'; end value is less than beginning value",
               begin, end);
     }

     static String getMessageMissingDates () {
          return "must provide at least one date";
     }

     static String getMessageMissingValues () {
          return "must provide at least one value";
     }

     static String getMessageNegativeLength (final int minimum, final int maximum) {
          return String.format("cannot generate collection with length between %s and %s; minimum and or maximum " +
               "length is negative", minimum, maximum);
     }

     static String getMessagePropertyNameMustNotBeNull () {
          return "property name must not be null";
     }

     static String getMessageRepetitionAmountMustNotBeNegative () {
          return "repetition amount must not be negative";
     }

     static String getMessageValueBuilderMustNotBeNull () {
          return "ValueBuilder must not be null";
     }

     static String getMessageValueClassMustNotBeNull () {
          return "custom value class must not be null";
     }

     static String getMessageValueMustNotBeNull () {
          return "value must not be null";
     }

     static String getMessageValueProviderFactoryNotFound (final Class<?> valueClass) {
          return String.format("cannot find CustomValueProviderFactory for custom value class %s",
               valueClass.getName());
     }
}
