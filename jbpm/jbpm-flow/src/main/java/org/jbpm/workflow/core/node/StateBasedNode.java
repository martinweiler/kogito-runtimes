/*
 * Copyright 2010 Red Hat, Inc. and/or its affiliates.
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
package org.jbpm.workflow.core.node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.process.core.timer.Timer;
import org.jbpm.process.instance.impl.actions.CompleteStateBasedNodeInstanceAction;
import org.jbpm.workflow.core.DroolsAction;
import org.jbpm.workflow.core.impl.DroolsConsequenceAction;
import org.jbpm.workflow.core.impl.ExtendedNodeImpl;

import static org.jbpm.ruleflow.core.Metadata.ACTION;

public class StateBasedNode extends ExtendedNodeImpl {

    private static final long serialVersionUID = 510l;

    private Map<Timer, DroolsAction> timers;

    private List<String> boundaryEvents;

    private transient String duration;

    public Map<Timer, DroolsAction> getTimers() {
        return timers;
    }

    public void setTimeout(String duration) {
        this.duration = duration;
        Timer timer = new Timer();
        timer.setDelay(duration);
        DroolsConsequenceAction timeoutAction = new DroolsConsequenceAction("java", null);
        timeoutAction.setMetaData(ACTION, new CompleteStateBasedNodeInstanceAction(getNodeUniqueId()));
        addTimer(timer, timeoutAction);
    }

    public String getTimeout() {
        return duration;
    }

    public void addTimer(Timer timer, DroolsAction action) {
        if (timers == null) {
            timers = new HashMap<>();
        }
        if (timer.getId() == 0) {
            long id = 0;
            for (Timer t : timers.keySet()) {
                if (t.getId() > id) {
                    id = t.getId();
                }
            }
            timer.setId(++id);
        }
        timers.put(timer, action);
    }

    public void removeAllTimers() {
        if (timers != null) {
            timers.clear();
        }
    }

    public void addBoundaryEvents(String boundaryEvent) {
        if (this.boundaryEvents == null) {
            this.boundaryEvents = new ArrayList<>();
        }
        this.boundaryEvents.add(boundaryEvent);
    }

    public void setBoundaryEvents(List<String> boundaryEvents) {
        this.boundaryEvents = boundaryEvents;
    }

    public List<String> getBoundaryEvents() {
        return boundaryEvents;
    }

}
