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
package com.handcraftedbits.edgeifier.internal.value.custom;

public class NameBean implements Comparable<NameBean> {
     private final String firstName;
     private final String lastName;

     public NameBean (final String firstName, final String lastName) {
          this.firstName = firstName;
          this.lastName = lastName;
     }

     @Override
     public int compareTo (final NameBean o) {
          return 0;
     }

     @Override
     public boolean equals (final Object obj) {
          final NameBean other;

          if (obj == null) {
               return false;
          }

          if (!(obj instanceof NameBean)) {
               return false;
          }

          other = (NameBean) obj;

          return (other.firstName.equals(this.firstName) && other.lastName.equals(this.lastName));
     }

     public String getFirstName () {
          return this.firstName;
     }

     public String getLastName () {
          return this.lastName;
     }

     @Override
     public int hashCode () {
          return super.hashCode();
     }
}
