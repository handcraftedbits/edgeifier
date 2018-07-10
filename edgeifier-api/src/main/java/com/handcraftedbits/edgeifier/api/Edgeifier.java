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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import java.util.SplittableRandom;
import java.util.stream.Stream;

import com.handcraftedbits.edgeifier.api.value.ValueBuilder;
import com.handcraftedbits.edgeifier.api.value.ValueSpecSelector;
import com.handcraftedbits.edgeifier.api.value.ValueSpecSelectorFactory;
import com.handcraftedbits.edgeifier.api.value.collection.ListValueSpec;
import com.handcraftedbits.edgeifier.api.value.custom.CustomValueProviderFactory;
import com.handcraftedbits.edgeifier.api.value.custom.CustomValueSpec;
import com.handcraftedbits.edgeifier.api.value.primitive.BooleanValueSpec;
import com.handcraftedbits.edgeifier.api.value.primitive.ByteValueSpec;
import com.handcraftedbits.edgeifier.api.value.primitive.CharacterValueSpec;
import com.handcraftedbits.edgeifier.api.value.primitive.DateValueSpec;
import com.handcraftedbits.edgeifier.api.value.primitive.DoubleValueSpec;
import com.handcraftedbits.edgeifier.api.value.primitive.FloatValueSpec;
import com.handcraftedbits.edgeifier.api.value.primitive.IntegerValueSpec;
import com.handcraftedbits.edgeifier.api.value.primitive.LongValueSpec;
import com.handcraftedbits.edgeifier.api.value.primitive.ShortValueSpec;
import com.handcraftedbits.edgeifier.api.value.string.StringValueSpec;

/**
 * Used to create random values that match a particular value specification.
 */

public final class Edgeifier implements ValueSpecSelector {
     private static final ValueSpecSelectorFactory factory = ServiceLoader.load(ValueSpecSelectorFactory.class)
          .iterator().next();

     static {
          Edgeifier.registerClasspathCustomValueProviderFactories(Thread.currentThread().getContextClassLoader());
     }

     final ThreadLocalRandomnessProvider randomnessProvider;

     /**
      * Creates an Edgeifier using the current time as a seed.
      */

     public Edgeifier () {
          this(System.currentTimeMillis());
     }

     /**
      * Creates an Edgeifier using a particular seed.
      *
      * @param seed a long containing the seed to use.
      */

     public Edgeifier (final long seed) {
          this.randomnessProvider = new ThreadLocalRandomnessProvider(new SplittableRandom(seed));
     }

     static void registerClasspathCustomValueProviderFactories (final ClassLoader classLoader) {
          @SuppressWarnings("rawtypes")
          final Iterator<CustomValueProviderFactory> valueProviderFactories;

          // Register any CustomValueProviderFactory classes on the classpath.

          valueProviderFactories = ServiceLoader.load(CustomValueProviderFactory.class, classLoader).iterator();

          while (valueProviderFactories.hasNext()) {
               Edgeifier.factory.registerCustomValueProviderFactory(valueProviderFactories.next());
          }
     }

     /**
      * Combines one or more {@link ValueBuilder} instances such that the value generated will come from one of the
      * provided {@link ValueBuilder} instances, selected at random.
      *
      * @param builders an array containing the {@link ValueBuilder} instances to use.
      * @return a {@link ValueBuilder} object that combines the provided {@link ValueBuilder} instances.
      * @throws IllegalArgumentException if the array is null, empty, or if any of the {@link ValueBuilder} instances
      *              are null.
      */

     @SafeVarargs
     public final <T> ValueBuilder<T> makeAnyOf (final ValueBuilder<T>... builders) {
          final List<Iterator<T>> iterators;

          if ((builders == null) || (builders.length == 0)) {
               throw new IllegalArgumentException("must provide at least one ValueBuilder instance");
          }

          iterators = new ArrayList<>(builders.length);

          for (final ValueBuilder<T> builder : builders) {
               if (builder == null) {
                    throw new IllegalArgumentException("ValueBuilder instance must not be null");
               }

               iterators.add(builder.stream().iterator());
          }

          return new ValueBuilder<T>() {
               @Override
               public Stream<T> stream () {
                    return Stream.generate( () -> {
                         return iterators.get(Edgeifier.this.randomnessProvider.get().nextInt(builders.length)).next();
                    });
               }
          };
     }

     @Override
     public BooleanValueSpec makeBooleans () {
          return newValueSpecSelector().makeBooleans();
     }

     @Override
     public ByteValueSpec makeBytesLike () {
          return newValueSpecSelector().makeBytesLike();
     }

     @Override
     public CharacterValueSpec makeCharsLike () {
          return newValueSpecSelector().makeCharsLike();
     }

     @Override
     public DateValueSpec makeDatesLike () {
          return newValueSpecSelector().makeDatesLike();
     }

     @Override
     public DoubleValueSpec makeDoublesLike () {
          return newValueSpecSelector().makeDoublesLike();
     }

     @Override
     public FloatValueSpec makeFloatsLike () {
          return newValueSpecSelector().makeFloatsLike();
     }

     @Override
     public IntegerValueSpec makeIntsLike () {
          return newValueSpecSelector().makeIntsLike();
     }

     @Override
     public ListValueSpec makeListsLike () {
          return newValueSpecSelector().makeListsLike();
     }

     @Override
     public LongValueSpec makeLongsLike () {
          return newValueSpecSelector().makeLongsLike();
     }

     @Override
     public ShortValueSpec makeShortsLike () {
          return newValueSpecSelector().makeShortsLike();
     }

     @Override
     public StringValueSpec makeStringsLike () {
          return newValueSpecSelector().makeStringsLike();
     }

     @Override
     public CustomValueSpec makeValuesLike () {
          return newValueSpecSelector().makeValuesLike();
     }

     private ValueSpecSelector newValueSpecSelector () {
          return Edgeifier.factory.newValueSpecSelector(this, this.randomnessProvider.get());
     }

     /**
      * Registers a {@link CustomValueProviderFactory} for use with {@link CustomValueSpec}.
      *
      * @param valueProviderFactory a {@link CustomValueProviderFactory} object containing the custom value provider
      *             factory to register.
      * @throws IllegalArgumentException if the {@link CustomValueProviderFactory} instance is null.
      */

     public void registerCustomValueProviderFactory (final CustomValueProviderFactory<?> valueProviderFactory) {
          if (valueProviderFactory == null) {
               throw new IllegalArgumentException("CustomValueProviderFactory instance must not be null");
          }

          Edgeifier.factory.registerCustomValueProviderFactory(valueProviderFactory);
     }

     private static final class DefaultRandomnessProvider implements RandomnessProvider {
          private final SplittableRandom random;

          private DefaultRandomnessProvider (final SplittableRandom random) {
               this.random = random;
          }

          @Override
          public boolean nextBoolean () {
               return this.random.nextBoolean();
          }

          @Override
          public double nextDouble () {
               return this.random.nextDouble();
          }

          @Override
          public double nextDouble (final double bound) {
               return this.random.nextDouble(bound);
          }

          @Override
          public double nextDouble (final double origin, final double bound) {
               return this.random.nextDouble(origin, bound);
          }

          @Override
          public int nextInt () {
               return this.random.nextInt();
          }

          @Override
          public int nextInt (final int bound) {
               return this.random.nextInt(bound);
          }

          @Override
          public int nextInt (final int origin, final int bound) {
               return this.random.nextInt(origin, bound);
          }

          @Override
          public long nextLong () {
               return this.random.nextLong();
          }

          @Override
          public long nextLong (final long bound) {
               return this.random.nextLong(bound);
          }

          @Override
          public long nextLong (final long origin, final long bound) {
               return this.random.nextLong(origin, bound);
          }
     }

     /*
      * SplittableRandom is not thread-safe, so whenever another thread accesses the RandomnessProvider instance we'll
      * split off the SplittableRandom instance into a new one.
      */

     static final class ThreadLocalRandomnessProvider extends ThreadLocal<RandomnessProvider> {
          private final SplittableRandom random;

          private ThreadLocalRandomnessProvider (final SplittableRandom random) {
               this.random = random;
          }

          @Override
          protected synchronized RandomnessProvider initialValue () {
               return new DefaultRandomnessProvider(this.random.split());
          }
     }
}
