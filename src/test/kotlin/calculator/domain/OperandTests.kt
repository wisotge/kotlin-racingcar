package calculator.domain

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class OperandTests {

    @ParameterizedTest
    @CsvSource("1, 2", "3, 4", "-1, -5")
    fun `2개의 피연산자를 더하면 두 피연산자의 합을 확인할 수 있다`(n1: Double, n2: Double) {
        assertThat(Operand(n1) + Operand(n2)).isEqualTo(Operand(n1 + n2))
    }

    @ParameterizedTest
    @CsvSource("2, 1", "0, 4", "-1, -5")
    fun `2개의 피연산자를 빼면 두 피연산자의 차를 확인할 수 있다`(n1: Double, n2: Double) {
        assertThat(Operand(n1) - Operand(n2)).isEqualTo(Operand(n1 - n2))
    }

    @ParameterizedTest
    @CsvSource("1, 2", "3, -4", "-1, -5")
    fun `2개의 피연산자를 곱하면 두 피연산자의 곱을 확인할 수 있다`(n1: Double, n2: Double) {
        assertThat(Operand(n1) * Operand(n2)).isEqualTo(Operand(n1 * n2))
    }

    @ParameterizedTest
    @CsvSource("1, 2", "-3, 4", "-1, -5")
    fun `2개의 피연산자를 나누면 두 피연산자의 몫을 확인할 수 있다`(n1: Double, n2: Double) {
        assertThat(Operand(n1) / Operand(n2)).isEqualTo(Operand(n1 / n2))
    }

    @ParameterizedTest
    @CsvSource("1, 0", "0, 0")
    fun `2개의 피연산자를 나눌 때 0으로 나누면 에러가 발생한다`(n1: Double, n2: Double) {
        Assertions.assertThatThrownBy { Operand(n1) / Operand(n2) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("0으로 나눌 수 없습니다")
    }

    @ParameterizedTest
    @ValueSource(strings = ["one", "둘"])
    fun `숫자가 아닌 문자열이 입력되면 예외가 발생한다`(input: String) {
        Assertions.assertThatThrownBy { Operand.of(input) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("숫자로 변환할 수 없는 문자입니다")
    }
}