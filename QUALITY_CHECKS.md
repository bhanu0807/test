# Quality & Security Checks

This document outlines all the automated checks configured for this Spring Boot microservice.

---

## 🔍 Code Quality Checks

### 1. **Checkstyle** - Code Style Enforcement
- **Purpose**: Ensures consistent code formatting and style
- **Configuration**: Uses Google Java Style Guide
- **Run**: `mvn checkstyle:check`
- **Severity**: Warning
- **Checks**:
  - Naming conventions
  - Indentation
  - Import order
  - Whitespace
  - JavaDoc comments

### 2. **PMD** - Static Code Analysis
- **Purpose**: Detects common programming flaws
- **Run**: `mvn pmd:check`
- **Checks**:
  - Unused variables
  - Empty catch blocks
  - Unnecessary object creation
  - Complex expressions
  - Code duplication

### 3. **SpotBugs** - Bug Detection
- **Purpose**: Finds potential bugs in Java code
- **Run**: `mvn spotbugs:check`
- **Effort**: Maximum
- **Threshold**: Low
- **Checks**:
  - Null pointer dereferences
  - Infinite loops
  - Resource leaks
  - Concurrency issues
  - Security vulnerabilities

### 4. **SonarQube/SonarCloud** - Comprehensive Analysis
- **Purpose**: Complete code quality and security analysis
- **Run**: `mvn sonar:sonar`
- **Requires**: SONAR_TOKEN secret
- **Metrics**:
  - Code smells
  - Technical debt
  - Bugs
  - Vulnerabilities
  - Code coverage
  - Duplications
  - Maintainability rating
  - Reliability rating
  - Security rating

---

## 🔒 Security Checks

### 5. **OWASP Dependency Check**
- **Purpose**: Identifies known vulnerabilities in dependencies
- **Run**: `mvn org.owasp:dependency-check-maven:check`
- **Threshold**: CVSS 7+ (High)
- **Checks**:
  - CVE database matching
  - Vulnerable library versions
  - Known security issues

### 6. **CodeQL** - Security Analysis
- **Purpose**: Advanced semantic code analysis
- **Platform**: GitHub Advanced Security
- **Checks**:
  - SQL injection
  - Cross-site scripting (XSS)
  - Path traversal
  - Command injection
  - Insecure deserialization
  - Weak cryptography

### 7. **Trivy** - Container Vulnerability Scanning
- **Purpose**: Scans Docker images for vulnerabilities
- **Run**: Automated in CI/CD
- **Checks**:
  - OS package vulnerabilities
  - Application dependency vulnerabilities
  - Misconfigurations
  - Exposed secrets

### 8. **Snyk** - Dependency Security
- **Purpose**: Monitors and fixes vulnerabilities
- **Platform**: snyk.io
- **Requires**: SNYK_TOKEN secret
- **Threshold**: High severity
- **Features**:
  - Real-time vulnerability database
  - Automated fix PRs
  - License compliance

---

## 🧪 Testing & Coverage

### 9. **JUnit 5** - Unit Testing
- **Purpose**: Runs all unit tests
- **Run**: `mvn test`
- **Framework**: JUnit 5 + Mockito
- **Coverage**: Service layer, Controller layer

### 10. **JaCoCo** - Code Coverage
- **Purpose**: Measures test coverage
- **Run**: `mvn jacoco:report`
- **Minimum**: 50% line coverage
- **Reports**: `target/site/jacoco/index.html`
- **Metrics**:
  - Line coverage
  - Branch coverage
  - Method coverage
  - Class coverage

### 11. **Integration Tests**
- **Purpose**: Full application context testing
- **Profile**: `integration-tests`
- **Run**: `mvn verify -Pintegration-tests`
- **Framework**: Spring Boot Test + MockMvc

---

## 📦 Build & Dependency Checks

### 12. **Maven Enforcer Plugin**
- **Purpose**: Enforces build standards
- **Checks**:
  - Maven version ≥ 3.6
  - Java version ≥ 17
  - Banned dependencies (optional)
  - Dependency convergence

### 13. **Versions Plugin**
- **Purpose**: Checks for dependency updates
- **Run**: `mvn versions:display-dependency-updates`
- **Features**:
  - Outdated dependencies
  - Available updates
  - Plugin updates

### 14. **Dependency Review** (GitHub)
- **Purpose**: Reviews dependency changes in PRs
- **Platform**: GitHub Actions
- **Checks**:
  - New vulnerable dependencies
  - License changes
  - Security advisories

---

## 📄 License Compliance

### 15. **License Maven Plugin**
- **Purpose**: Ensures license compliance
- **Run**: `mvn license:check`
- **License**: Apache 2.0
- **Checks**:
  - Missing license headers
  - Incompatible licenses
  - Third-party licenses

---

## 🚀 CI/CD Pipeline Stages

The GitHub Actions workflow includes these jobs:

1. **Code Quality & Security Analysis**
   - Checkstyle
   - PMD
   - SpotBugs
   - OWASP Dependency Check
   - SonarCloud

2. **Build, Test & Coverage**
   - Maven build
   - JUnit tests
   - JaCoCo coverage
   - Codecov upload
   - Test reporting

3. **Docker Build & Security Scan**
   - Docker image build
   - Trivy vulnerability scan
   - Snyk container scan

4. **License Compliance Check**
   - License verification

5. **CodeQL Security Analysis**
   - Advanced security scanning

6. **Integration Tests**
   - Full application testing

7. **Performance Tests** (optional)
   - Load testing
   - Stress testing

8. **Dependency Review**
   - PR dependency checking

9. **Build Summary**
   - Aggregate results

---

## ⚙️ How to Run Checks Locally

### Run All Quality Checks
```bash
# Run all checks
mvn clean verify

# Individual checks
mvn checkstyle:check
mvn pmd:check
mvn spotbugs:check
mvn jacoco:report
mvn org.owasp:dependency-check-maven:check
```

### View Reports
```bash
# Coverage report
open target/site/jacoco/index.html

# Checkstyle report
open target/checkstyle-result.xml

# PMD report
open target/pmd.xml

# SpotBugs report
open target/spotbugsXml.xml

# OWASP report
open target/dependency-check-report.html
```

### Check for Dependency Updates
```bash
mvn versions:display-dependency-updates
mvn versions:display-plugin-updates
```

---

## 📊 Quality Gates

The following quality gates are enforced:

| Check | Threshold | Action |
|-------|-----------|--------|
| Code Coverage | ≥ 50% | Fail build |
| OWASP CVSS Score | < 7 | Fail build |
| CodeQL Alerts | 0 critical | Block merge |
| Test Failures | 0 | Fail build |
| SonarCloud Quality Gate | Pass | Block merge |
| License Issues | 0 | Warn |

---

## 🔧 Configuration Files

- **Checkstyle**: Uses built-in `google_checks.xml`
- **PMD**: Uses `/rulesets/java/quickstart.xml`
- **SpotBugs**: Default configuration
- **JaCoCo**: Configured in `pom.xml`
- **GitHub Actions**: `.github/workflows/ci.yml`

---

## 🔑 Required Secrets (GitHub)

To enable all checks, add these secrets to your GitHub repository:

```bash
SONAR_TOKEN          # SonarCloud authentication
SNYK_TOKEN           # Snyk authentication (optional)
CODECOV_TOKEN        # Codecov upload (optional)
```

### How to Add Secrets
1. Go to GitHub repository → Settings → Secrets and variables → Actions
2. Click "New repository secret"
3. Add each secret with the appropriate value

---

## 📈 Continuous Improvement

### Weekly Tasks
- [ ] Review OWASP dependency check report
- [ ] Check for dependency updates
- [ ] Review SonarCloud quality gate

### Monthly Tasks
- [ ] Update dependencies
- [ ] Review and address technical debt
- [ ] Update security policies

### Quarterly Tasks
- [ ] Audit all dependencies
- [ ] Review and update quality thresholds
- [ ] Security assessment

---

## 📚 Additional Resources

- [Checkstyle Documentation](https://checkstyle.org/)
- [PMD Documentation](https://pmd.github.io/)
- [SpotBugs Documentation](https://spotbugs.github.io/)
- [SonarQube Documentation](https://docs.sonarqube.org/)
- [OWASP Dependency Check](https://owasp.org/www-project-dependency-check/)
- [CodeQL Documentation](https://codeql.github.com/docs/)
- [Trivy Documentation](https://aquasecurity.github.io/trivy/)
- [JaCoCo Documentation](https://www.jacoco.org/jacoco/trunk/doc/)

---

## 🎯 Best Practices

1. **Run checks locally** before pushing code
2. **Fix critical issues** immediately
3. **Review warnings** regularly
4. **Keep dependencies updated**
5. **Monitor security advisories**
6. **Maintain test coverage** above threshold
7. **Document exceptions** when rules are disabled
8. **Regular code reviews**

---

**Last Updated**: 2026-02-06
