package com.github.polurival.stc.lint

import com.android.tools.lint.checks.infrastructure.LintDetectorTest
import com.android.tools.lint.checks.infrastructure.TestFile
import com.android.tools.lint.checks.infrastructure.TestLintTask
import org.junit.Test

/**
 *
 *
 * @author Юрий Польщиков on 05.12.2021
 */
class TooMuchIfPatternDetectorTest {

    @Test
    fun testTooMuchIfPatternDetector() {
        TestLintTask.lint()
            .files(TRIPLE_IF)
            //.allowCompilationErrors()
            .allowMissingSdk()
            .issues(TooMuchIfPatternDetector.IssueTooMuchIf)
            .run()
            .expect(
                """
                """.trimIndent()
            )
    }

    companion object {
        val TRIPLE_IF: TestFile = LintDetectorTest.kotlin(
            """
                package com.github.polurival.stc.lint

                class SampleTest {
    
                    fun test1() {
                        if (1 > 2) {
                            if (3 > 2) {
                                if (4 > 5) {
                                    val b = 8
                                }
                            }
                        }
                    }
                }
            """.trimIndent()
        ).indented()
    }
}
