package lambdas

import org.junit.Test as test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class Solution {

    @test fun anonymous_function() {
        fun sum(x: Int, y: Int): Int = x + y
        assertEquals(sum(1, 1), 2)

        // Q: Create an anonymous sum function
        val sumNoName = fun(x: Int, y: Int): Int = x + y
        assertEquals(sumNoName(1, 1), 2)
    }

    @test fun lambda_expression() {
        // type is not necessary if defined inside expression, necessary if not defined inside expression
        val sumLambda: (Int, Int) -> Int = {x: Int, y: Int -> x + y}
        assertEquals(sumLambda(1, 1), 2)

        // Q: Create a lambda that sums three integers
        val sumThreeIntegers = {x: Int, y: Int, z: Int -> x + y + z}
        assertEquals(sumThreeIntegers(1, 2, 3), 6)
    }

    @test fun lambda_with_receiver() {
        val three = with(1) {
            plus(2)
        }

        val sum: Int.(Short) -> Int = {
            other -> plus(other)
        }

        assertEquals(three, 3)
        assertEquals(sum(1, 2), 3)
        assertEquals(1.sum(2), 3)

        // Q: What is wrong with this?
        val four = 1.apply { plus(3) }
        assertEquals(four, 4)

        // Q: Create a lambda with receiver and with no parameters
        val multiplyByThree: Int.() -> Int = { times(3) }
        assertEquals(3.multiplyByThree(), 9)
    }

    @test
    fun `should pass lambda as a reference`() {
        val transformer: (Int, (Int) -> Int) -> Int = { value, transform -> transform(value) }
        val timesTwoLambda: (Int) -> Int = { it * 2}

        assertEquals(transformer(2, timesTwoLambda), 4)
    }
}