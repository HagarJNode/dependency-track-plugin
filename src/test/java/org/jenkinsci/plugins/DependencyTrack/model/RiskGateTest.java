/*
 * Copyright 2020 OWASP.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jenkinsci.plugins.DependencyTrack.model;

import hudson.model.Result;
import java.util.stream.IntStream;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * @author Ronny "Sephiroth" Perinke <sephiroth@sephiroth-j.de>
 */
@Execution(ExecutionMode.CONCURRENT)
class RiskGateTest {

    @ParameterizedTest(name = "expect result={10} given total failed thresholds[critical={0},high={1},medium={2},low={3},unassigned={4}] and having findings[critical={5},high={6},medium={7},low={8},unassigned={9}]")
    @CsvFileSource(resources = "/failedTotalFindings.csv", numLinesToSkip = 1)
    void failedTotalFindingsOnly(Integer thCrit, Integer thHigh, Integer thMedium, Integer thLow, Integer thUnassigned, int numCrit, int numHigh, int numMed, int numLow, int numUnassigned, Result expectedResult) {
        final Thresholds th = new Thresholds();
        th.totalFindings.failedCritical = thCrit;
        th.totalFindings.failedHigh = thHigh;
        th.totalFindings.failedMedium = thMedium;
        th.totalFindings.failedLow = thLow;
        th.totalFindings.failedUnassigned = thUnassigned;
        final SeverityDistribution sd = new SeverityDistribution(0);
        IntStream.rangeClosed(1, numCrit).mapToObj(i -> Severity.CRITICAL).forEach(sd::add);
        IntStream.rangeClosed(1, numHigh).mapToObj(i -> Severity.HIGH).forEach(sd::add);
        IntStream.rangeClosed(1, numMed).mapToObj(i -> Severity.MEDIUM).forEach(sd::add);
        IntStream.rangeClosed(1, numLow).mapToObj(i -> Severity.LOW).forEach(sd::add);
        IntStream.rangeClosed(1, numUnassigned).mapToObj(i -> Severity.UNASSIGNED).forEach(sd::add);

        RiskGate uut = new RiskGate(th);

        final ViolationDistribution violationDistribution = new ViolationDistribution(0);

        assertThat(uut.evaluate(sd, new SeverityDistribution(0), violationDistribution, violationDistribution)).isEqualTo(expectedResult);
    }

    @ParameterizedTest(name = "expect result={10} given total unstable thresholds[critical={0},high={1},medium={2},low={3},unassigned={4}] and having findings[critical={5},high={6},medium={7},low={8},unassigned={9}]")
    @CsvFileSource(resources = "/unstableTotalFindings.csv", numLinesToSkip = 1)
    void unstableTotalFindingsOnly(Integer thCrit, Integer thHigh, Integer thMedium, Integer thLow, Integer thUnassigned, int numCrit, int numHigh, int numMed, int numLow, int numUnassigned, Result expectedResult) {
        final Thresholds th = new Thresholds();
        th.totalFindings.unstableCritical = thCrit;
        th.totalFindings.unstableHigh = thHigh;
        th.totalFindings.unstableMedium = thMedium;
        th.totalFindings.unstableLow = thLow;
        th.totalFindings.unstableUnassigned = thUnassigned;
        final SeverityDistribution sd = new SeverityDistribution(0);
        IntStream.rangeClosed(1, numCrit).mapToObj(i -> Severity.CRITICAL).forEach(sd::add);
        IntStream.rangeClosed(1, numHigh).mapToObj(i -> Severity.HIGH).forEach(sd::add);
        IntStream.rangeClosed(1, numMed).mapToObj(i -> Severity.MEDIUM).forEach(sd::add);
        IntStream.rangeClosed(1, numLow).mapToObj(i -> Severity.LOW).forEach(sd::add);
        IntStream.rangeClosed(1, numUnassigned).mapToObj(i -> Severity.UNASSIGNED).forEach(sd::add);

        RiskGate uut = new RiskGate(th);

        final ViolationDistribution violationDistribution = new ViolationDistribution(0);

        assertThat(uut.evaluate(sd, new SeverityDistribution(0), violationDistribution, violationDistribution)).isEqualTo(expectedResult);
    }

    @ParameterizedTest(name = "expect result={10} given total thresholds[critical={0},high={1},medium={2},low={3},unassigned={4}] and having findings[critical={5},high={6},medium={7},low={8},unassigned={9}]")
    @CsvFileSource(resources = "/failedTotalFindings.csv", numLinesToSkip = 1)
    void totalFindings(Integer thCrit, Integer thHigh, Integer thMedium, Integer thLow, Integer thUnassigned, int numCrit, int numHigh, int numMed, int numLow, int numUnassigned, Result expectedResult) {
        final Thresholds th = new Thresholds();
        th.totalFindings.failedCritical = th.totalFindings.unstableCritical = thCrit;
        th.totalFindings.failedHigh = th.totalFindings.unstableHigh = thHigh;
        th.totalFindings.failedMedium = th.totalFindings.unstableMedium = thMedium;
        th.totalFindings.failedLow = th.totalFindings.unstableLow = thLow;
        th.totalFindings.failedUnassigned = th.totalFindings.unstableUnassigned = thUnassigned;
        final SeverityDistribution sd = new SeverityDistribution(0);
        IntStream.rangeClosed(1, numCrit).mapToObj(i -> Severity.CRITICAL).forEach(sd::add);
        IntStream.rangeClosed(1, numHigh).mapToObj(i -> Severity.HIGH).forEach(sd::add);
        IntStream.rangeClosed(1, numMed).mapToObj(i -> Severity.MEDIUM).forEach(sd::add);
        IntStream.rangeClosed(1, numLow).mapToObj(i -> Severity.LOW).forEach(sd::add);
        IntStream.rangeClosed(1, numUnassigned).mapToObj(i -> Severity.UNASSIGNED).forEach(sd::add);

        RiskGate uut = new RiskGate(th);

        final ViolationDistribution violationDistribution = new ViolationDistribution(0);

        assertThat(uut.evaluate(sd, new SeverityDistribution(0), violationDistribution, violationDistribution)).isEqualTo(expectedResult);
    }

    @ParameterizedTest(name = "expect result={10} given new failed thresholds[critical={0},high={1},medium={2},low={3},unassigned={4}] and having findings[critical={5},high={6},medium={7},low={8},unassigned={9}]")
    @CsvFileSource(resources = "/failedTotalFindings.csv", numLinesToSkip = 1)
    void failedNewFindingsOnly(Integer thCrit, Integer thHigh, Integer thMedium, Integer thLow, Integer thUnassigned, int numCrit, int numHigh, int numMed, int numLow, int numUnassigned, Result expectedResult) {
        final Thresholds th = new Thresholds();
        th.newFindings.failedCritical = thCrit;
        th.newFindings.failedHigh = thHigh;
        th.newFindings.failedMedium = thMedium;
        th.newFindings.failedLow = thLow;
        th.newFindings.failedUnassigned = thUnassigned;
        final SeverityDistribution sd = new SeverityDistribution(0);
        IntStream.rangeClosed(1, numCrit).mapToObj(i -> Severity.CRITICAL).forEach(sd::add);
        IntStream.rangeClosed(1, numHigh).mapToObj(i -> Severity.HIGH).forEach(sd::add);
        IntStream.rangeClosed(1, numMed).mapToObj(i -> Severity.MEDIUM).forEach(sd::add);
        IntStream.rangeClosed(1, numLow).mapToObj(i -> Severity.LOW).forEach(sd::add);
        IntStream.rangeClosed(1, numUnassigned).mapToObj(i -> Severity.UNASSIGNED).forEach(sd::add);

        RiskGate uut = new RiskGate(th);

        final ViolationDistribution violationDistribution = new ViolationDistribution(0);

        assertThat(uut.evaluate(sd, new SeverityDistribution(0), violationDistribution, violationDistribution)).isEqualTo(expectedResult);
    }

    @ParameterizedTest(name = "expect result={10} given new unstable thresholds[critical={0},high={1},medium={2},low={3},unassigned={4}] and having findings[critical={5},high={6},medium={7},low={8},unassigned={9}]")
    @CsvFileSource(resources = "/unstableTotalFindings.csv", numLinesToSkip = 1)
    void unstableNewFindingsOnly(Integer thCrit, Integer thHigh, Integer thMedium, Integer thLow, Integer thUnassigned, int numCrit, int numHigh, int numMed, int numLow, int numUnassigned, Result expectedResult) {
        final Thresholds th = new Thresholds();
        th.newFindings.unstableCritical = thCrit;
        th.newFindings.unstableHigh = thHigh;
        th.newFindings.unstableMedium = thMedium;
        th.newFindings.unstableLow = thLow;
        th.newFindings.unstableUnassigned = thUnassigned;
        final SeverityDistribution sd = new SeverityDistribution(0);
        IntStream.rangeClosed(1, numCrit).mapToObj(i -> Severity.CRITICAL).forEach(sd::add);
        IntStream.rangeClosed(1, numHigh).mapToObj(i -> Severity.HIGH).forEach(sd::add);
        IntStream.rangeClosed(1, numMed).mapToObj(i -> Severity.MEDIUM).forEach(sd::add);
        IntStream.rangeClosed(1, numLow).mapToObj(i -> Severity.LOW).forEach(sd::add);
        IntStream.rangeClosed(1, numUnassigned).mapToObj(i -> Severity.UNASSIGNED).forEach(sd::add);

        RiskGate uut = new RiskGate(th);

        final ViolationDistribution violationDistribution = new ViolationDistribution(0);

        assertThat(uut.evaluate(sd, new SeverityDistribution(0), violationDistribution, violationDistribution)).isEqualTo(expectedResult);
    }

    @ParameterizedTest(name = "expect result={8} given new thresholds[critical={0},high={1},medium={2},low={3}] for both and having findings[critical={4},high={5},medium={6},low={7}]")
    @CsvFileSource(resources = "/failedTotalFindings.csv", numLinesToSkip = 1)
    void newFindings(Integer thCrit, Integer thHigh, Integer thMedium, Integer thLow, Integer thUnassigned, int numCrit, int numHigh, int numMed, int numLow, int numUnassigned, Result expectedResult) {
        final Thresholds th = new Thresholds();
        th.newFindings.failedCritical = th.newFindings.unstableCritical = thCrit;
        th.newFindings.failedHigh = th.newFindings.unstableHigh = thHigh;
        th.newFindings.failedMedium = th.newFindings.unstableMedium = thMedium;
        th.newFindings.failedLow = th.newFindings.unstableLow = thLow;
        th.newFindings.failedUnassigned = th.newFindings.unstableUnassigned = thUnassigned;
        final SeverityDistribution sd = new SeverityDistribution(0);
        IntStream.rangeClosed(1, numCrit).mapToObj(i -> Severity.CRITICAL).forEach(sd::add);
        IntStream.rangeClosed(1, numHigh).mapToObj(i -> Severity.HIGH).forEach(sd::add);
        IntStream.rangeClosed(1, numMed).mapToObj(i -> Severity.MEDIUM).forEach(sd::add);
        IntStream.rangeClosed(1, numLow).mapToObj(i -> Severity.LOW).forEach(sd::add);
        IntStream.rangeClosed(1, numUnassigned).mapToObj(i -> Severity.UNASSIGNED).forEach(sd::add);

        RiskGate uut = new RiskGate(th);

        final ViolationDistribution violationDistribution = new ViolationDistribution(0);

        assertThat(uut.evaluate(sd, new SeverityDistribution(0), violationDistribution, violationDistribution)).isEqualTo(expectedResult);
    }

    @ParameterizedTest(name = "expect result={7} given total failed thresholds[fail={0},warn={1},info={2}] and having violations[fail={3},warn={4},info={5}]")
    @CsvFileSource(resources = "/failedTotalViolations.csv", numLinesToSkip = 1)
    void failedTotalViolationsOnly(Integer thFail, Integer thWarn, Integer thInfo, int numFail, int numWarn, int numInfo, Result expectedResult) {
        final Thresholds th = new Thresholds();
        th.totalViolations.failedFail = thFail;
        th.totalViolations.failedWarn = thWarn;
        th.totalViolations.failedInfo = thInfo;

        final SeverityDistribution severityDistribution = new SeverityDistribution(0);
        final ViolationDistribution violationDistribution = new ViolationDistribution(0);

        IntStream.rangeClosed(1, numFail).mapToObj(i -> ViolationState.FAIL).forEach(violationDistribution::add);
        IntStream.rangeClosed(1, numWarn).mapToObj(i -> ViolationState.WARN).forEach(violationDistribution::add);
        IntStream.rangeClosed(1, numInfo).mapToObj(i -> ViolationState.INFO).forEach(violationDistribution::add);

        RiskGate uut = new RiskGate(th);

        assertThat(uut.evaluate(severityDistribution, severityDistribution, violationDistribution, new ViolationDistribution(0))).isEqualTo(expectedResult);
    }

    @ParameterizedTest(name = "expect result={7} given total failed thresholds[fail={0},warn={1},info={2}] and having violations[fail={3},warn={4},info={5}]")
    @CsvFileSource(resources = "/unstableTotalViolations.csv", numLinesToSkip = 1)
    void unstableTotalViolationsOnly(Integer thFail, Integer thWarn, Integer thInfo, int numFail, int numWarn, int numInfo, Result expectedResult) {
        final Thresholds th = new Thresholds();
        th.totalViolations.unstableFail = thFail;
        th.totalViolations.unstableWarn = thWarn;
        th.totalViolations.unstableInfo = thInfo;

        final SeverityDistribution severityDistribution = new SeverityDistribution(0);
        final ViolationDistribution violationDistribution = new ViolationDistribution(0);

        IntStream.rangeClosed(1, numFail).mapToObj(i -> ViolationState.FAIL).forEach(violationDistribution::add);
        IntStream.rangeClosed(1, numWarn).mapToObj(i -> ViolationState.WARN).forEach(violationDistribution::add);
        IntStream.rangeClosed(1, numInfo).mapToObj(i -> ViolationState.INFO).forEach(violationDistribution::add);

        RiskGate uut = new RiskGate(th);

        assertThat(uut.evaluate(severityDistribution, severityDistribution, violationDistribution, new ViolationDistribution(0))).isEqualTo(expectedResult);
    }

    @ParameterizedTest(name = "expect result={7} given total failed thresholds[fail={0},warn={1},info={2}] and having violations[fail={3},warn={4},info={5}]")
    @CsvFileSource(resources = "/failedTotalViolations.csv", numLinesToSkip = 1)
    void totalViolations(Integer thFail, Integer thWarn, Integer thInfo, int numFail, int numWarn, int numInfo, Result expectedResult) {
        final Thresholds th = new Thresholds();
        th.totalViolations.failedFail = th.totalViolations.unstableFail = thFail;
        th.totalViolations.failedWarn = th.totalViolations.unstableWarn = thWarn;
        th.totalViolations.failedInfo = th.totalViolations.unstableInfo = thInfo;

        final SeverityDistribution severityDistribution = new SeverityDistribution(0);
        final ViolationDistribution violationDistribution = new ViolationDistribution(0);

        IntStream.rangeClosed(1, numFail).mapToObj(i -> ViolationState.FAIL).forEach(violationDistribution::add);
        IntStream.rangeClosed(1, numWarn).mapToObj(i -> ViolationState.WARN).forEach(violationDistribution::add);
        IntStream.rangeClosed(1, numInfo).mapToObj(i -> ViolationState.INFO).forEach(violationDistribution::add);

        RiskGate uut = new RiskGate(th);

        assertThat(uut.evaluate(severityDistribution, severityDistribution, violationDistribution, new ViolationDistribution(0))).isEqualTo(expectedResult);
    }

    @ParameterizedTest(name = "expect result={7} given total failed thresholds[fail={0},warn={1},info={2}] and having violations[fail={3},warn={4},info={5}]")
    @CsvFileSource(resources = "/failedTotalViolations.csv", numLinesToSkip = 1)
    void failedNewViolationsOnly(Integer thFail, Integer thWarn, Integer thInfo, int numFail, int numWarn, int numInfo, Result expectedResult) {
        final Thresholds th = new Thresholds();
        th.newViolations.failedFail = thFail;
        th.newViolations.failedWarn = thWarn;
        th.newViolations.failedInfo = thInfo;

        final SeverityDistribution severityDistribution = new SeverityDistribution(0);
        final ViolationDistribution violationDistribution = new ViolationDistribution(0);

        IntStream.rangeClosed(1, numFail).mapToObj(i -> ViolationState.FAIL).forEach(violationDistribution::add);
        IntStream.rangeClosed(1, numWarn).mapToObj(i -> ViolationState.WARN).forEach(violationDistribution::add);
        IntStream.rangeClosed(1, numInfo).mapToObj(i -> ViolationState.INFO).forEach(violationDistribution::add);

        RiskGate uut = new RiskGate(th);

        assertThat(uut.evaluate(severityDistribution, severityDistribution, violationDistribution, new ViolationDistribution(0))).isEqualTo(expectedResult);
    }

    @ParameterizedTest(name = "expect result={7} given total failed thresholds[fail={0},warn={1},info={2}] and having violations[fail={3},warn={4},info={5}]")
    @CsvFileSource(resources = "/unstableTotalViolations.csv", numLinesToSkip = 1)
    void unstableNewViolationsOnly(Integer thFail, Integer thWarn, Integer thInfo, int numFail, int numWarn, int numInfo, Result expectedResult) {
        final Thresholds th = new Thresholds();
        th.newViolations.unstableFail = thFail;
        th.newViolations.unstableWarn = thWarn;
        th.newViolations.unstableInfo = thInfo;

        final SeverityDistribution severityDistribution = new SeverityDistribution(0);
        final ViolationDistribution violationDistribution = new ViolationDistribution(0);

        IntStream.rangeClosed(1, numFail).mapToObj(i -> ViolationState.FAIL).forEach(violationDistribution::add);
        IntStream.rangeClosed(1, numWarn).mapToObj(i -> ViolationState.WARN).forEach(violationDistribution::add);
        IntStream.rangeClosed(1, numInfo).mapToObj(i -> ViolationState.INFO).forEach(violationDistribution::add);

        RiskGate uut = new RiskGate(th);

        assertThat(uut.evaluate(severityDistribution, severityDistribution, violationDistribution, new ViolationDistribution(0))).isEqualTo(expectedResult);
    }

    @ParameterizedTest(name = "expect result={7} given total failed thresholds[fail={0},warn={1},info={2}] and having violations[fail={3},warn={4},info={5}]")
    @CsvFileSource(resources = "/failedTotalViolations.csv", numLinesToSkip = 1)
    void newViolations(Integer thFail, Integer thWarn, Integer thInfo, int numFail, int numWarn, int numInfo, Result expectedResult) {
        final Thresholds th = new Thresholds();
        th.newViolations.failedFail = th.newViolations.unstableFail = thFail;
        th.newViolations.failedWarn = th.newViolations.unstableWarn = thWarn;
        th.newViolations.failedInfo = th.newViolations.unstableInfo = thInfo;

        final SeverityDistribution severityDistribution = new SeverityDistribution(0);
        final ViolationDistribution violationDistribution = new ViolationDistribution(0);

        IntStream.rangeClosed(1, numFail).mapToObj(i -> ViolationState.FAIL).forEach(violationDistribution::add);
        IntStream.rangeClosed(1, numWarn).mapToObj(i -> ViolationState.WARN).forEach(violationDistribution::add);
        IntStream.rangeClosed(1, numInfo).mapToObj(i -> ViolationState.INFO).forEach(violationDistribution::add);

        RiskGate uut = new RiskGate(th);

        assertThat(uut.evaluate(severityDistribution, severityDistribution, violationDistribution, new ViolationDistribution(0))).isEqualTo(expectedResult);
    }
}
