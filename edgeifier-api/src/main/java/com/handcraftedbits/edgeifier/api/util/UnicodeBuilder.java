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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.handcraftedbits.edgeifier.api.Edgeifier;
import com.handcraftedbits.edgeifier.api.value.ValueBuilder;

/**
 * Used to create {@link ValueBuilder} instances capable of generating random values selected from a particular
 * <a href="https://en.wikipedia.org/wiki/Unicode_character_property#General_Category">Unicode character category</a>.
 */

public final class UnicodeBuilder {
     private static Map<Integer, Category> categories;

     private UnicodeBuilder () {
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code Cc},
      * {@code Cf}, {@code Cn}, {@code Co}, and {@code Cs} Unicode character categories.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forC (final Edgeifier edgeifier) {
          return edgeifier.makeAnyOf(UnicodeBuilder.forCc(edgeifier),
               UnicodeBuilder.forCf(edgeifier),
               UnicodeBuilder.forCn(edgeifier),
               UnicodeBuilder.forCo(edgeifier),
               UnicodeBuilder.forCs(edgeifier));
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code Cc} (other,
      * control) Unicode character category.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forCc (final Edgeifier edgeifier) {
          return UnicodeBuilder.getValueBuilderForCategory(edgeifier, Character.CONTROL);
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code Cf} (other,
      * format) Unicode character category.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forCf (final Edgeifier edgeifier) {
          return UnicodeBuilder.getValueBuilderForCategory(edgeifier, Character.FORMAT);
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code Cn} (other,
      * not assigned) Unicode character category.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forCn (final Edgeifier edgeifier) {
          return UnicodeBuilder.getValueBuilderForCategory(edgeifier, Character.UNASSIGNED);
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code Co} (other,
      * private use) Unicode character category.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forCo (final Edgeifier edgeifier) {
          return UnicodeBuilder.getValueBuilderForCategory(edgeifier, Character.PRIVATE_USE);
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code Cs} (other,
      * surrogate) Unicode character category.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forCs (final Edgeifier edgeifier) {
          return UnicodeBuilder.getValueBuilderForCategory(edgeifier, Character.SURROGATE);
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code Ll},
      * {@code Lm}, {@code Lo}, {@code Lt}, and {@code Lu} Unicode character categories.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forL (final Edgeifier edgeifier) {
          return edgeifier.makeAnyOf(UnicodeBuilder.forLl(edgeifier),
               UnicodeBuilder.forLm(edgeifier),
               UnicodeBuilder.forLo(edgeifier),
               UnicodeBuilder.forLt(edgeifier),
               UnicodeBuilder.forLu(edgeifier));
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code Ll}
      * (letter, lowercase) Unicode character category.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forLl (final Edgeifier edgeifier) {
          return UnicodeBuilder.getValueBuilderForCategory(edgeifier, Character.LOWERCASE_LETTER);
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code Lm}
      * (letter, modifier) Unicode character category.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forLm (final Edgeifier edgeifier) {
          return UnicodeBuilder.getValueBuilderForCategory(edgeifier, Character.MODIFIER_LETTER);
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code Lo}
      * (letter, other) Unicode character category.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forLo (final Edgeifier edgeifier) {
          return UnicodeBuilder.getValueBuilderForCategory(edgeifier, Character.OTHER_LETTER);
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code Lt}
      * (letter, titlecase) Unicode character category.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forLt (final Edgeifier edgeifier) {
          return UnicodeBuilder.getValueBuilderForCategory(edgeifier, Character.TITLECASE_LETTER);
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code Lu}
      * (letter, uppercase) Unicode character category.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forLu (final Edgeifier edgeifier) {
          return UnicodeBuilder.getValueBuilderForCategory(edgeifier, Character.UPPERCASE_LETTER);
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code Mc},
      * {@code Me}, and {@code Mn} Unicode character categories.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forM (final Edgeifier edgeifier) {
          return edgeifier.makeAnyOf(UnicodeBuilder.forMc(edgeifier),
               UnicodeBuilder.forMe(edgeifier),
               UnicodeBuilder.forMn(edgeifier));
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code Mc} (mark,
      * spacing, combining) Unicode character category.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forMc (final Edgeifier edgeifier) {
          return UnicodeBuilder.getValueBuilderForCategory(edgeifier, Character.COMBINING_SPACING_MARK);
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code Me} (mark,
      * enclosing) Unicode character category.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forMe (final Edgeifier edgeifier) {
          return UnicodeBuilder.getValueBuilderForCategory(edgeifier, Character.ENCLOSING_MARK);
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code Mn} (mark,
      * nonspacing) Unicode character category.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forMn (final Edgeifier edgeifier) {
          return UnicodeBuilder.getValueBuilderForCategory(edgeifier, Character.NON_SPACING_MARK);
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code Nd},
      * {@code Nl}, and {@code No} Unicode character categories.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forN (final Edgeifier edgeifier) {
          return edgeifier.makeAnyOf(UnicodeBuilder.forNd(edgeifier),
               UnicodeBuilder.forNl(edgeifier),
               UnicodeBuilder.forNo(edgeifier));
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code Nd}
      * (number, decimal digit) Unicode character category.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forNd (final Edgeifier edgeifier) {
          return UnicodeBuilder.getValueBuilderForCategory(edgeifier, Character.DECIMAL_DIGIT_NUMBER);
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code Nl}
      * (number, letter) Unicode character category.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forNl (final Edgeifier edgeifier) {
          return UnicodeBuilder.getValueBuilderForCategory(edgeifier, Character.LETTER_NUMBER);
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code No}
      * (nummber, other) Unicode character category.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forNo (final Edgeifier edgeifier) {
          return UnicodeBuilder.getValueBuilderForCategory(edgeifier, Character.OTHER_NUMBER);
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code Pc},
      * {@code Pd}, {@code Pe}, {@code Pf}, {@code Pi}, {@code Po}, and {@code Ps} Unicode character categories.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forP (final Edgeifier edgeifier) {
          return edgeifier.makeAnyOf(UnicodeBuilder.forPc(edgeifier),
               UnicodeBuilder.forPd(edgeifier),
               UnicodeBuilder.forPe(edgeifier),
               UnicodeBuilder.forPf(edgeifier),
               UnicodeBuilder.forPi(edgeifier),
               UnicodeBuilder.forPo(edgeifier),
               UnicodeBuilder.forPs(edgeifier));
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code Pc}
      * (punctuation, connector) Unicode character category.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forPc (final Edgeifier edgeifier) {
          return UnicodeBuilder.getValueBuilderForCategory(edgeifier, Character.CONNECTOR_PUNCTUATION);
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code Pd}
      * (punctuation, dash) Unicode character category.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forPd (final Edgeifier edgeifier) {
          return UnicodeBuilder.getValueBuilderForCategory(edgeifier, Character.DASH_PUNCTUATION);
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code Pe}
      * (punctuation, close) Unicode character category.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forPe (final Edgeifier edgeifier) {
          return UnicodeBuilder.getValueBuilderForCategory(edgeifier, Character.END_PUNCTUATION);
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code Pf}
      * (punctuation, final quote) Unicode character category.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forPf (final Edgeifier edgeifier) {
          return UnicodeBuilder.getValueBuilderForCategory(edgeifier, Character.FINAL_QUOTE_PUNCTUATION);
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code Pi}
      * (punctuation, initial quote) Unicode character category.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forPi (final Edgeifier edgeifier) {
          return UnicodeBuilder.getValueBuilderForCategory(edgeifier, Character.INITIAL_QUOTE_PUNCTUATION);
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code Po}
      * (punctuation, other) Unicode character category.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forPo (final Edgeifier edgeifier) {
          return UnicodeBuilder.getValueBuilderForCategory(edgeifier, Character.OTHER_PUNCTUATION);
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code Ps}
      * (punctuation, open) Unicode character category.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forPs (final Edgeifier edgeifier) {
          return UnicodeBuilder.getValueBuilderForCategory(edgeifier, Character.START_PUNCTUATION);
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code Sc},
      * {@code Sk}, {@code Sm}, and {@code So}, Unicode character categories.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forS (final Edgeifier edgeifier) {
          return edgeifier.makeAnyOf(UnicodeBuilder.forSc(edgeifier),
               UnicodeBuilder.forSk(edgeifier),
               UnicodeBuilder.forSm(edgeifier),
               UnicodeBuilder.forSo(edgeifier));
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code Sc}
      * (symbol, currency) Unicode character category.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forSc (final Edgeifier edgeifier) {
          return UnicodeBuilder.getValueBuilderForCategory(edgeifier, Character.CURRENCY_SYMBOL);
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code Sk}
      * (symbol, modifier) Unicode character category.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forSk (final Edgeifier edgeifier) {
          return UnicodeBuilder.getValueBuilderForCategory(edgeifier, Character.MODIFIER_SYMBOL);
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code Sm}
      * (symbol, math) Unicode character category.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forSm (final Edgeifier edgeifier) {
          return UnicodeBuilder.getValueBuilderForCategory(edgeifier, Character.MATH_SYMBOL);
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code So}
      * (symbol, other) Unicode character category.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forSo (final Edgeifier edgeifier) {
          return UnicodeBuilder.getValueBuilderForCategory(edgeifier, Character.OTHER_SYMBOL);
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code Zl},
      * {@code Zp}, and {@code Zs} Unicode character categories.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forZ (final Edgeifier edgeifier) {
          return edgeifier.makeAnyOf(UnicodeBuilder.forZl(edgeifier),
               UnicodeBuilder.forZp(edgeifier),
               UnicodeBuilder.forZs(edgeifier));
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code Zl}
      * (separator, line) Unicode character category.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forZl (final Edgeifier edgeifier) {
          return UnicodeBuilder.getValueBuilderForCategory(edgeifier, Character.CURRENCY_SYMBOL);
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code Zp}
      * (separator, paragraph) Unicode character category.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forZp (final Edgeifier edgeifier) {
          return UnicodeBuilder.getValueBuilderForCategory(edgeifier, Character.MODIFIER_SYMBOL);
     }

     /**
      * Creates a {@link ValueBuilder} instance capable of generating random values selected from the {@code Zs}
      * (separator, space) Unicode character category.
      *
      * @param edgeifier an {@link Edgeifier} object containing the Edgeifier to use.
      * @return a {@link ValueBuilder} object used to generate random characters.
      */

     public static ValueBuilder<String> forZs (final Edgeifier edgeifier) {
          return UnicodeBuilder.getValueBuilderForCategory(edgeifier, Character.MATH_SYMBOL);
     }

     private static ValueBuilder<String> getValueBuilderForCategory (final Edgeifier edgeifier, final int category) {
          UnicodeBuilder.initCategories(edgeifier);

          return UnicodeBuilder.categories.get(category).toValueBuilder(edgeifier);
     }

     private static synchronized void initCategories (final Edgeifier edgeifier) {
          if (UnicodeBuilder.categories == null) {
               UnicodeBuilder.categories = new HashMap<>();

               for (int i = Character.MIN_CODE_POINT; i <= Character.MAX_CODE_POINT; ++i) {
                    Category category;
                    final int categoryType = Character.getType(i);

                    category = UnicodeBuilder.categories.get(categoryType);

                    if (category == null) {
                         category = new Category();

                         UnicodeBuilder.categories.put(categoryType, category);
                    }

                    category.add(i);
               }
          }
     }

     private static final class Category {
          private final LinkedList<CharacterRange> ranges = new LinkedList<>();
          private List<Integer> soloCharacterList = new ArrayList<>();
          private int[] soloCharacters;

          private void add (final int codePoint) {
               final CharacterRange range = this.ranges.peekLast();

               if (range == null) {
                    // This is the first character in the category.

                    this.ranges.add(new CharacterRange(codePoint, codePoint));
               }

               else {
                    if (codePoint == (range.end + 1)) {
                         // Just update the end of the range.

                         range.end++;
                    }

                    else {
                         if (range.start == range.end) {
                              // The last range was actually a single character that doesn't belong to a range.

                              this.soloCharacterList.add(range.start);

                              this.ranges.removeLast();
                         }

                         this.ranges.add(new CharacterRange(codePoint, codePoint));
                    }
               }
          }

          @SuppressWarnings("unchecked")
          private ValueBuilder<String> toValueBuilder (final Edgeifier edgeifier) {
               final ValueBuilder<String>[] builders;
               int i = 0;

               if (this.soloCharacters == null) {
                    this.soloCharacters = this.soloCharacterList.stream().mapToInt(j -> j).toArray();
                    this.soloCharacterList = null;
               }

               builders = (ValueBuilder<String>[]) new ValueBuilder[this.ranges.size() +
                    (this.soloCharacters.length == 0 ? 0 : 1)];

               if (this.soloCharacters.length > 0) {
                    builders[i++] = edgeifier.makeStringsLike().anyInAlphabet(this.soloCharacters);
               }

               for (final CharacterRange range : this.ranges) {
                    builders[i++] = edgeifier.makeStringsLike().anyInRange(range.start, range.end);
               }

               return edgeifier.makeAnyOf(builders);
          }
     }

     private static final class CharacterRange {
          int end;
          int start;

          private CharacterRange (final int start, final int end) {
               this.end = end;
               this.start = start;
          }
     }
}
