/*
 * This file is part of Dependency-Track Jenkins plugin.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jenkinsci.plugins.DependencyTrack.model;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@RequiredArgsConstructor
@Value
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Component implements Serializable {
  private static final long serialVersionUID = -4825926766668357091L;

  @EqualsAndHashCode.Include
  String uuid;
  String name;
  String group;
  String version;
  String purl;
}
