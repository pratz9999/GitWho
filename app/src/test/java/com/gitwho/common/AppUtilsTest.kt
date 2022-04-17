package com.gitwho.common

import com.google.common.truth.Truth
import org.junit.Test

class AppUtilsTest {

    @Test
    fun `Check count, in million`() {
        val result = AppUtils.getAlphaCount(Constants.MILLION)
        Truth.assertThat(result).isEqualTo("1M")
    }

    @Test
    fun `Check count, in thousand`() {
        val result = AppUtils.getAlphaCount(Constants.THOUSAND)
        Truth.assertThat(result).isEqualTo("1K")
    }

    @Test
    fun `Check count, in hundred`() {
        val result = AppUtils.getAlphaCount(100)
        Truth.assertThat(result).isEqualTo("100")
    }

    @Test
    fun `Check count, in zero`() {
        val result = AppUtils.getAlphaCount(0)
        Truth.assertThat(result).isEqualTo("0")
    }
}