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

import java.net.URL;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.handcraftedbits.edgeifier.api.value.ValueBuilder;
import com.handcraftedbits.edgeifier.internal.value.custom.NameBean;
import com.handcraftedbits.edgeifier.internal.value.custom.SimpleBean;
import com.handcraftedbits.edgeifier.internal.value.custom.SimpleBeanProviderFactory;

class CustomValueImplTest {
     @Test
     void testAnyDynamicRegistration () {
          final SimpleBean bean;
          final ValueBuilder<SimpleBean> builder;
          final Exception e;

          e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeValuesLike().any(SimpleBean.class);
          });

          Assertions.assertEquals(TypeMessages.getMessageValueProviderFactoryNotFound(SimpleBean.class),
               e.getMessage());

          ValueHelper.edgeifier.registerCustomValueProviderFactory(new SimpleBeanProviderFactory());

          builder = ValueHelper.edgeifier.makeValuesLike().any(SimpleBean.class);
          bean = builder.stream().iterator().next();

          Assertions.assertAll(
               () -> Assertions.assertNotNull(bean),
               () -> Assertions.assertEquals(10, bean.getValue().length()));
     }

     @Test
     void testAnyNullPropertyName () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeValuesLike().any(NameBean.class).withProperty(null, "abc");
          });

          Assertions.assertEquals(TypeMessages.getMessagePropertyNameMustNotBeNull(), e.getMessage());
     }

     @Test
     void testAnyNullValueClass () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeValuesLike().any(null);
          });

          Assertions.assertEquals(TypeMessages.getMessageValueClassMustNotBeNull(), e.getMessage());
     }

     @Test
     void testAnyOfMissingValues () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeValuesLike().anyOf(new String[0]);
          });

          Assertions.assertEquals(TypeMessages.getMessageMissingValues(), e.getMessage());
     }

     @Test
     void testAnyOfNullValue () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeValuesLike().anyOf((String) null);
          });

          Assertions.assertEquals(TypeMessages.getMessageValueMustNotBeNull(), e.getMessage());
     }

     @Test
     void testAnyOfNullValues () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeValuesLike().anyOf((String[]) null);
          });

          Assertions.assertEquals(TypeMessages.getMessageMissingValues(), e.getMessage());
     }

     @Test
     void testAnyOfValid () {
          final ValueBuilder<String> builder = ValueHelper.edgeifier.makeValuesLike().anyOf("a", "b", "c");

          Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), "a", "b", "c"));
     }

     @Test
     void testAnyUnknownValueClass () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeValuesLike().any(URL.class);
          });

          Assertions.assertEquals(TypeMessages.getMessageValueProviderFactoryNotFound(URL.class), e.getMessage());
     }

     @Test
     void testAnyValid () {
          final ValueBuilder<NameBean> builder = ValueHelper.edgeifier.makeValuesLike().any(NameBean.class)
               .withProperty("lastName", "Last");
          final NameBean expected = new NameBean("First", "Last");

          Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), expected));
     }

     @Test
     void testThisOneNullValue () {
          final Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
               ValueHelper.edgeifier.makeValuesLike().thisOne(null);
          });

          Assertions.assertEquals(TypeMessages.getMessageValueMustNotBeNull(), e.getMessage());
     }

     @Test
     void testThisOneValid () {
          final ValueBuilder<String> builder = ValueHelper.edgeifier.makeValuesLike().thisOne("a");

          Assertions.assertTrue(ValueHelper.streamOnlyContains(builder.stream(), "a"));
     }
}
