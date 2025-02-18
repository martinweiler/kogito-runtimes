/*
 * Copyright 2022 Red Hat, Inc. and/or its affiliates.
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

package org.kie.kogito.serverless.workflow.io;

import java.net.URI;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ClassPathContentLoaderTest {

    private static final String PATH = "specs/external1.yaml";

    @Test
    void testNoPrefixPath() {
        testPath("");
    }

    @Test
    void testPrefixPath() {
        testPath("classpath:");
    }

    @Test
    void testPrefixSlashPath() {
        testPath("classpath://");
    }

    void testPath(String prefix) {
        ClassPathContentLoader contentLoader = new ClassPathContentLoader(URI.create(prefix + PATH), Optional.empty());
        assertThat(contentLoader.classpath()).isEqualTo(PATH);
    }

}
