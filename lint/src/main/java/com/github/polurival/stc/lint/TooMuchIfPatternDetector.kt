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
import org.jetbrains.uast.UIfExpression
import org.jetbrains.uast.UMethod
import org.jetbrains.uast.visitor.AbstractUastVisitor
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

/**
 *
 *
 * @author Юрий Польщиков on 05.12.2021
 */
class TooMuchIfPatternDetector : Detector(), SourceCodeScanner {

    companion object {

        internal const val DESCRIPTION = "More Than 2 If blocks"

        internal val IssueTooMuchIf = Issue.create(
            "TooMuchIf",
            DESCRIPTION,
            "Simplify it, for example, retrieve in separate method",
            Category.PRODUCTIVITY,
            5,
            Severity.WARNING,
            Implementation(
                TooMuchIfPatternDetector::class.java,
                EnumSet.of(Scope.JAVA_FILE, Scope.TEST_SOURCES)
            )
        )
    }

    override fun getApplicableUastTypes() = listOf(UIfExpression::class.java)

    override fun createUastHandler(context: JavaContext) = IfVisitor(context)

    class IfVisitor(private val context: JavaContext) : UElementHandler() {

        override fun visitMethod(node: UMethod) {
            val counter = AtomicInteger(0)
            node.accept(Counter(counter))
            if (counter.get() >= 3) {
                context.report(IssueTooMuchIf, node, context.getNameLocation(node), DESCRIPTION)
            }
        }
    }

    class Counter(private var counter: AtomicInteger) : AbstractUastVisitor() {

        override fun visitIfExpression(node: UIfExpression): Boolean {
            counter.getAndIncrement()
            return counter.get() >= 3
        }
    }
}
