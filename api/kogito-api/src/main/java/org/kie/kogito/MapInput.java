/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.kogito;

import java.util.Map;

/**
 * To be implemented by classes which can be populated from a Map
 */
public interface MapInput {

    /**
     * Fills the class with information retrieved from the map
     * 
     * @param params Map containing keys which matches names of fields
     *        in the class
     */
    default MapInput fromMap(Map<String, Object> params) {
        return Models.fromMap(this, params);
    }

}
