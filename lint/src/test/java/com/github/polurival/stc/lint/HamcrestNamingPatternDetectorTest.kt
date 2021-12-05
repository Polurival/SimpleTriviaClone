package com.github.polurival.stc.lint

import com.android.tools.lint.checks.infrastructure.LintDetectorTest
import com.android.tools.lint.checks.infrastructure.TestFile
import com.android.tools.lint.checks.infrastructure.TestLintTask
import org.junit.Test

/**
 * Тестирование HamcrestNamingPatternDetector
 *
 * @author Юрий Польщиков on 05.12.2021
 */
class HamcrestNamingPatternDetectorTest {

    @Test
    fun testHamcrestNamingPatternDetector() {
        TestLintTask.lint()
            .files(HAMCREST)
            .allowCompilationErrors()
            .allowMissingSdk()
            .issues(HamcrestNamingPatternDetector.IssueHamcrestImport)
            .run()
            .expect(
                """
                    src/com/github/polurival/stc/lint/SampleTest.kt:3: Warning: Hamcrest is deprecated [HamcrestImport]
                    import org.hamcrest.CoreMatchers.equalTo
                           ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    src/com/github/polurival/stc/lint/SampleTest.kt:4: Warning: Hamcrest is deprecated [HamcrestImport]
                    import org.hamcrest.MatcherAssert.assertThat
                           ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    0 errors, 2 warnings
                """.trimIndent()
            )
    }

    companion object {
        val HAMCREST: TestFile = LintDetectorTest.kotlin(
            """
                package com.github.polurival.stc.lint

                import org.hamcrest.CoreMatchers.equalTo
                import org.hamcrest.MatcherAssert.assertThat
                import org.junit.Test

                class SampleTest {
                    @Test
                    fun test1() {
                        assertThat("1", equalTo("1"))
                    }
                }
            """.trimIndent()
        ).indented()
    }
}
