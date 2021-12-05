package com.github.polurival.stc.lint

import com.android.tools.lint.checks.infrastructure.LintDetectorTest.manifest
import com.android.tools.lint.checks.infrastructure.TestFile
import com.android.tools.lint.checks.infrastructure.TestLintTask.lint
import com.github.polurival.stc.lint.NoisyDetector.Companion.NoisyIssue
import org.junit.Test

/**
 *
 *
 * @author Юрий Польщиков on 05.12.2021
 */
internal class NoisyDetectorTest {

    @Test
    fun testNoisyDetector() {
        lint().files(EMPTY_MANIFEST)
            .allowMissingSdk()
            .issues(NoisyIssue)
            .run()
            .expect(
                """
                        AndroidManifest.xml: Information: This is a noisy issue. Feel free to ignore for now. [NoisyIssueId]
                        0 errors, 0 warnings
                        """.trimIndent()
            )
    }

    companion object {
        val EMPTY_MANIFEST: TestFile = manifest(
            """
                <manifest xmlns:android="http://schemas.android.com/apk/res/android"
                          xmlns:tools="http://schemas.android.com/tools"
                          package="com.example">
                    <application>
                    </application>
                </manifest>
            """.trimIndent()
        ).indented()
    }
}