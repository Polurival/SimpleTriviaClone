package com.github.polurival.stc.game

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 *
 *
 * @author Юрий Польщиков on 11.11.2021
 */
class SampleTest {
    @Test
    fun test1() {
        if (1 > 2) {
            if (3 > 2) {
                if (4 > 5) {
                    val b = 8
                }
            }
        }
        assertThat("1", equalTo("1"))
    }
}