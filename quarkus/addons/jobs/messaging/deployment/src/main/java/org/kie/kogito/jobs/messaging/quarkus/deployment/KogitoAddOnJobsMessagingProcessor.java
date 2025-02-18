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

package org.kie.kogito.jobs.messaging.quarkus.deployment;

import java.util.ArrayList;
import java.util.List;

import org.kie.kogito.quarkus.addons.common.deployment.KogitoCapability;
import org.kie.kogito.quarkus.addons.common.deployment.OneOfCapabilityKogitoAddOnProcessor;

import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.builditem.nativeimage.ReflectiveClassBuildItem;

class KogitoAddOnJobsMessagingProcessor extends OneOfCapabilityKogitoAddOnProcessor {

    private static final String FEATURE = "kogito-addon-jobs-messaging-extension";

    KogitoAddOnJobsMessagingProcessor() {
        super(KogitoCapability.PROCESSES, KogitoCapability.SERVERLESS_WORKFLOW);
    }

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    public ReflectiveClassBuildItem jobsApiReflection() {
        List<Class<?>> reflectiveClasses = new ArrayList<>();
        reflectiveClasses.addAll(org.kie.kogito.jobs.api.utils.ReflectionUtils.apiReflectiveClasses());
        reflectiveClasses.addAll(org.kie.kogito.jobs.service.api.utils.ReflectionUtils.apiReflectiveClasses());
        return new ReflectiveClassBuildItem(true,
                true,
                true,
                reflectiveClasses.toArray(new Class[] {}));
    }
}
