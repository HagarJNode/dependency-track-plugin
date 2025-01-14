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

import hudson.model.Result;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
public class RiskGate implements Serializable {

    private static final long serialVersionUID = 171256230735670985L;

    @NonNull
    private final Thresholds thresholds;

    /**
     * Evaluates if the current results meet or exceed the defined threshold.
     *
     * @param currentDistribution currentDistribution
     * @param previousDistribution previousDistribution
     * @param currentViolationDistribution currentViolationDistribution
     * @param previousViolationDistribution previousViolationDistribution
     * @return a Result
     */
    public Result evaluate(@NonNull final SeverityDistribution currentDistribution,
                           @NonNull final SeverityDistribution previousDistribution,
                           @NonNull final ViolationDistribution currentViolationDistribution,
                           @NonNull final ViolationDistribution previousViolationDistribution) {
        Result result = Result.SUCCESS;
        if ((thresholds.totalFindings.failedCritical != null && currentDistribution.getCritical() > 0 && currentDistribution.getCritical() >= thresholds.totalFindings.failedCritical)
                || (thresholds.totalFindings.failedHigh != null && currentDistribution.getHigh() > 0 && currentDistribution.getHigh() >= thresholds.totalFindings.failedHigh)
                || (thresholds.totalFindings.failedMedium != null && currentDistribution.getMedium() > 0 && currentDistribution.getMedium() >= thresholds.totalFindings.failedMedium)
                || (thresholds.totalFindings.failedLow != null && currentDistribution.getLow() > 0 && currentDistribution.getLow() >= thresholds.totalFindings.failedLow)
                || (thresholds.totalFindings.failedUnassigned != null && currentDistribution.getUnassigned()> 0 && currentDistribution.getUnassigned()>= thresholds.totalFindings.failedUnassigned)) {

            return Result.FAILURE;
        }
        if ((thresholds.totalFindings.unstableCritical != null && currentDistribution.getCritical() > 0 && currentDistribution.getCritical() >= thresholds.totalFindings.unstableCritical)
                || (thresholds.totalFindings.unstableHigh != null && currentDistribution.getHigh() > 0 && currentDistribution.getHigh() >= thresholds.totalFindings.unstableHigh)
                || (thresholds.totalFindings.unstableMedium != null && currentDistribution.getMedium() > 0 && currentDistribution.getMedium() >= thresholds.totalFindings.unstableMedium)
                || (thresholds.totalFindings.unstableLow != null && currentDistribution.getLow() > 0 && currentDistribution.getLow() >= thresholds.totalFindings.unstableLow)
                || (thresholds.totalFindings.unstableUnassigned != null && currentDistribution.getUnassigned()> 0 && currentDistribution.getUnassigned()>= thresholds.totalFindings.unstableUnassigned)) {

            result = Result.UNSTABLE;
        }

        if ((thresholds.newFindings.failedCritical != null && currentDistribution.getCritical() > 0 && currentDistribution.getCritical() >= previousDistribution.getCritical() + thresholds.newFindings.failedCritical)
              || (thresholds.newFindings.failedHigh != null && currentDistribution.getHigh() > 0 && currentDistribution.getHigh() >= previousDistribution.getHigh() + thresholds.newFindings.failedHigh)
              || (thresholds.newFindings.failedMedium != null && currentDistribution.getMedium() > 0 && currentDistribution.getMedium() >= previousDistribution.getMedium() + thresholds.newFindings.failedMedium)
              || (thresholds.newFindings.failedLow != null && currentDistribution.getLow() > 0 && currentDistribution.getLow() >= previousDistribution.getLow() + thresholds.newFindings.failedLow)
              || (thresholds.newFindings.failedUnassigned != null && currentDistribution.getUnassigned() > 0 && currentDistribution.getUnassigned() >= previousDistribution.getUnassigned() + thresholds.newFindings.failedUnassigned)) {

          return Result.FAILURE;
        }

        if ((thresholds.newFindings.unstableCritical != null && currentDistribution.getCritical() > 0 && currentDistribution.getCritical() >= previousDistribution.getCritical() + thresholds.newFindings.unstableCritical)
              || (thresholds.newFindings.unstableHigh != null && currentDistribution.getHigh() > 0 && currentDistribution.getHigh() >= previousDistribution.getHigh() + thresholds.newFindings.unstableHigh)
              || (thresholds.newFindings.unstableMedium != null && currentDistribution.getMedium() > 0 && currentDistribution.getMedium() >= previousDistribution.getMedium() + thresholds.newFindings.unstableMedium)
              || (thresholds.newFindings.unstableLow != null && currentDistribution.getLow() > 0 && currentDistribution.getLow() >= previousDistribution.getLow() + thresholds.newFindings.unstableLow)
              || (thresholds.newFindings.unstableUnassigned != null && currentDistribution.getUnassigned() > 0 && currentDistribution.getUnassigned() >= previousDistribution.getUnassigned() + thresholds.newFindings.unstableUnassigned)) {

          result = Result.UNSTABLE;
        }

        if ((thresholds.totalViolations.failedFail != null && currentViolationDistribution.getFail() > 0 && currentViolationDistribution.getFail() >= thresholds.totalViolations.failedFail)
                || (thresholds.totalViolations.failedWarn != null && currentViolationDistribution.getWarn() > 0 && currentViolationDistribution.getWarn() >= thresholds.totalViolations.failedWarn)
                || (thresholds.totalViolations.failedInfo != null && currentViolationDistribution.getInfo() > 0 && currentViolationDistribution.getInfo() >= thresholds.totalViolations.failedInfo)) {

            return Result.FAILURE;
        }
        if ((thresholds.totalViolations.unstableFail != null && currentViolationDistribution.getFail() > 0 && currentViolationDistribution.getFail() >= thresholds.totalViolations.unstableFail)
                || (thresholds.totalViolations.unstableWarn != null && currentViolationDistribution.getWarn() > 0 && currentViolationDistribution.getWarn() >= thresholds.totalViolations.unstableWarn)
                || (thresholds.totalViolations.unstableInfo != null && currentViolationDistribution.getInfo() > 0 && currentViolationDistribution.getInfo() >= thresholds.totalViolations.unstableInfo)) {

            result = Result.UNSTABLE;
        }

        if ((thresholds.newViolations.failedFail != null && currentViolationDistribution.getFail() > 0 && currentViolationDistribution.getFail() >= previousViolationDistribution.getFail() + thresholds.newViolations.failedFail)
                || (thresholds.newViolations.failedWarn != null && currentViolationDistribution.getWarn() > 0 && currentViolationDistribution.getWarn() >= previousViolationDistribution.getWarn() + thresholds.newViolations.failedWarn)
                || (thresholds.newViolations.failedInfo != null && currentViolationDistribution.getInfo() > 0 && currentViolationDistribution.getInfo() >= previousViolationDistribution.getInfo() + thresholds.newViolations.failedInfo)) {

            return Result.FAILURE;
        }
        if ((thresholds.newViolations.unstableFail != null && currentViolationDistribution.getFail() > 0 && currentViolationDistribution.getFail() >= previousViolationDistribution.getFail() + thresholds.newViolations.unstableFail)
                || (thresholds.newViolations.unstableWarn != null && currentViolationDistribution.getWarn() > 0 && currentViolationDistribution.getWarn() >= previousViolationDistribution.getWarn() + thresholds.newViolations.unstableWarn)
                || (thresholds.newViolations.unstableInfo != null && currentViolationDistribution.getInfo() > 0 && currentViolationDistribution.getInfo() >= previousViolationDistribution.getInfo() + thresholds.newViolations.unstableInfo)) {

            result = Result.UNSTABLE;
        }

        return result;
    }
}
