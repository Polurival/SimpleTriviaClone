package com.github.polurival.stc.lint

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.SourceCodeScanner
import org.jetbrains.uast.UImportStatement
import java.util.*

/**
 * @author Юрий Польщиков on 05.12.2021
 */
class HamcrestNamingPatternDetector : Detector(), SourceCodeScanner /*Detector.UastScanner*/ {

    companion object {

        internal const val DESCRIPTION = "Hamcrest is deprecated"

        internal val IssueHamcrestImport = Issue.create(
            "HamcrestImport",
            DESCRIPTION,
            "Use Google Truth instead",
            Category.CORRECTNESS,
            5,
            Severity.WARNING,
            Implementation(
                HamcrestNamingPatternDetector::class.java,
                EnumSet.of(Scope.JAVA_FILE, Scope.TEST_SOURCES)
            )
        )
    }

    override fun getApplicableUastTypes() = listOf(UImportStatement::class.java)

    override fun createUastHandler(context: JavaContext) = HamcrestInvalidImportHandler(context)

    class HamcrestInvalidImportHandler(private val context: JavaContext) : UElementHandler() {

        override fun visitImportStatement(node: UImportStatement) {
            node.importReference?.let { importReference ->
                if (importReference.asSourceString().contains("org.hamcrest.")) {
                    context.report(IssueHamcrestImport, node, context.getLocation(importReference), DESCRIPTION)
                }
            }
        }
    }
}
