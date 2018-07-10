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

import java.net.URL;
import java.util.Enumeration;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.handcraftedbits.edgeifier.api.value.ValueBuilder;
import com.handcraftedbits.edgeifier.api.value.custom.TestCustomValueProviderFactory;

/**
 * This test class is just for coverage in the API project. More extensive testing happens in the implementation
 * project.
 */

class EdgeifierTest {
     private final Edgeifier edgeifier = new Edgeifier();

     @Test
     void testMakeAnyOf () {
          @SuppressWarnings("unchecked")
          final ValueBuilder<String> mockValueBuilder = (ValueBuilder<String>) Mockito.mock(ValueBuilder.class);

          Mockito.when(mockValueBuilder.stream()).thenReturn(Stream.of("a"));

          Assertions.assertEquals("a", this.edgeifier.makeAnyOf(mockValueBuilder).stream().iterator().next());
     }

     @Test
     void testMakeAnyOfEmptyValueBuilders () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               this.edgeifier.makeAnyOf();
          });

          Assertions.assertEquals("must provide at least one ValueBuilder instance", e.getMessage());
     }

     @Test
     void testMakeAnyOfNullValueBuilder () {
          final Exception e;
          @SuppressWarnings("unchecked")
          final ValueBuilder<String> mockValueBuilder = (ValueBuilder<String>) Mockito.mock(ValueBuilder.class);

          e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               this.edgeifier.makeAnyOf(mockValueBuilder, null, mockValueBuilder);
          });

          Assertions.assertEquals("ValueBuilder instance must not be null", e.getMessage());
     }

     @Test
     void testMakeAnyOfNullValueBuilders () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               this.edgeifier.makeAnyOf((ValueBuilder<String>[]) null);
          });

          Assertions.assertEquals("must provide at least one ValueBuilder instance", e.getMessage());
     }

     @Test
     void testMakeBooleans () {
          Assertions.assertNotNull(this.edgeifier.makeBooleans());
     }

     @Test
     void testMakeBytesLike () {
          Assertions.assertNotNull(this.edgeifier.makeBytesLike());
     }

     @Test
     void testMakeCharsLike () {
          Assertions.assertNotNull(this.edgeifier.makeCharsLike());
     }

     @Test
     void testMakeDatesLike () {
          Assertions.assertNotNull(this.edgeifier.makeDatesLike());
     }

     @Test
     void testMakeDoublesLike () {
          Assertions.assertNotNull(this.edgeifier.makeDoublesLike());
     }

     @Test
     void testMakeFloatsLike () {
          Assertions.assertNotNull(this.edgeifier.makeFloatsLike());
     }

     @Test
     void testMakeIntsLike () {
          Assertions.assertNotNull(this.edgeifier.makeIntsLike());
     }

     @Test
     void testMakeListsLike () {
          Assertions.assertNotNull(this.edgeifier.makeListsLike());
     }

     @Test
     void testMakeLongsLike () {
          Assertions.assertNotNull(this.edgeifier.makeLongsLike());
     }

     @Test
     void testMakeShortsLike () {
          Assertions.assertNotNull(this.edgeifier.makeShortsLike());
     }

     @Test
     void testMakeStringsLike () {
          Assertions.assertNotNull(this.edgeifier.makeStringsLike());
     }

     @Test
     void testMakeValuesLike () {
          Assertions.assertNotNull(this.edgeifier.makeValuesLike());
     }

     @Test
     void testRegisterCustomValueProviderFactoryClasspathMissing () throws Throwable {
          final ClassLoader classLoader = Mockito.mock(ClassLoader.class);
          @SuppressWarnings("unchecked")
          final Enumeration<URL> mockResources = Mockito.mock(Enumeration.class);

          Mockito.when(mockResources.hasMoreElements()).thenReturn(false);
          Mockito.when(classLoader.getResources(ArgumentMatchers.anyString())).thenReturn(mockResources);

          Edgeifier.registerClasspathCustomValueProviderFactories(classLoader);
     }

     @Test
     void testRegisterCustomValueProviderFactoryNull () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               this.edgeifier.registerCustomValueProviderFactory(null);
          });

          Assertions.assertEquals("CustomValueProviderFactory instance must not be null", e.getMessage());
     }

     @Test
     void testRegisterCustomValueProviderFactoryValid () {
          this.edgeifier.registerCustomValueProviderFactory(new TestCustomValueProviderFactory());
     }
}
