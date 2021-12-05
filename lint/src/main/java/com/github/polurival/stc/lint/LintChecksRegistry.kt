package com.github.polurival.stc.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.Issue
import com.github.polurival.stc.lint.HamcrestNamingPatternDetector.Companion.IssueHamcrestImport
import com.github.polurival.stc.lint.NoisyDetector.Companion.NoisyIssue
import com.github.polurival.stc.lint.TooMuchIfPatternDetector.Companion.IssueTooMuchIf

/**
 * Custom Lint Checkers
 *
 * @author Юрий Польщиков on 05.12.2021
 */
class LintChecksRegistry : IssueRegistry() {

    override val issues: List<Issue>
        get() = listOf(
            NoisyIssue,
            IssueHamcrestImport,
            IssueTooMuchIf
        )
}
