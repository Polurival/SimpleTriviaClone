package com.github.polurival.stc.lint

import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Context
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.Location
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.XmlContext
import com.android.tools.lint.detector.api.XmlScanner
import org.w3c.dom.Element

/**
 * Пример из
 * https://github.com/tikurahul/lint-experiments/blob/master/lint/src/main/java/com/rahulrav/NoisyDetector.kt
 *
 * @author Юрий Польщиков on 05.12.2021
 */
class NoisyDetector : Detector(), XmlScanner {

    companion object {

        internal const val NoisyIssueDescription = "This is a noisy issue. Feel free to ignore for now."

        internal val NoisyIssue = Issue.create(
            id = "NoisyIssueId",
            briefDescription = NoisyIssueDescription,
            explanation = NoisyIssueDescription,
            category = Category.CORRECTNESS,
            priority = 4,
            severity = Severity.INFORMATIONAL,
            implementation = Implementation(NoisyDetector::class.java, Scope.MANIFEST_SCOPE)
        )
    }

    override fun getApplicableElements() = listOf("manifest")

    override fun visitElement(context: XmlContext, element: Element) {
    }

    override fun afterCheckFile(context: Context) {
        context.report(NoisyIssue, Location.create(context.file), NoisyIssueDescription)
    }
}
