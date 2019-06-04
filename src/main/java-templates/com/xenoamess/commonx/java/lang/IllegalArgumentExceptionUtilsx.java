/*
 * MIT License
 *
 * Copyright (c) 2019 XenoAmess
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.xenoamess.commonx.java.lang;

/**
 * Notice that most of the contents in this class is generated, and there be a certain way to build this code.
 * <p>
 * First, run the main function in IllegalArgumentExceptionUtilsxCodeGenerator to generate template files.
 * <p>
 * Then use mvn compile to compile the project.
 * <p>
 * When you get this source the required template files shall be generated well, as you can compile it directly.
 * <p>
 * But if you want to modify things in IllegalArgumentExceptionUtilsx and IllegalArgumentExceptionUtilsxCodeGenerator
 * , I'm afraid you might have to do it once for yourself.
 *
 * @author XenoAmess
 */
public class IllegalArgumentExceptionUtilsx {
    // generated
    // don't delete or modify anything looks like comments if you don't know about template in maven.
    //-----------------------------------------------------------------------

    /**
     * <p>Checks if any of the parameters are null.</p>
     *
     * <pre>
     * Assertions.assertFalse(IllegalArgumentExceptionUtilsx.isAnyNullInParams());
     * Assertions.assertTrue(IllegalArgumentExceptionUtilsx.isAnyNullInParams(null));
     * Assertions.assertTrue(IllegalArgumentExceptionUtilsx.isAnyNullInParams(1, null));
     * Assertions.assertTrue(IllegalArgumentExceptionUtilsx.isAnyNullInParams(2, 2, 2, null));
     * Assertions.assertTrue(IllegalArgumentExceptionUtilsx.isAnyNullInParams(null, 4, 3, 3, 5));
     * Assertions.assertTrue(IllegalArgumentExceptionUtilsx.isAnyNullInParams(4, null, 3, 5));
     * Assertions.assertFalse(IllegalArgumentExceptionUtilsx.isAnyNullInParams(4, true, 3, 5));
     * Assertions.assertFalse(IllegalArgumentExceptionUtilsx.isAnyNullInParams(new Object[]{null, null, null}));
     * </pre>
     * <p>
     * @return {@code true} if any of the parameters are null
     * @author XenoAmess
     */
    //${generateIsAnyNullInParams}

    /**
     * <p>If any of the parameters are null, throw an IllegalArgumentException about it.</p>
     *
     * @author XenoAmess
     */
    //${generateIsAnyNullInParamsThenThrowIllegalArgumentException}

    /**
     * <p>Checks if none of the parameters are null.</p>
     *
     * <pre>
     * Assertions.assertTrue(IllegalArgumentExceptionUtilsx.isNoneNullInParams());
     * Assertions.assertFalse(IllegalArgumentExceptionUtilsx.isNoneNullInParams(null));
     * Assertions.assertFalse(IllegalArgumentExceptionUtilsx.isNoneNullInParams(1, null));
     * Assertions.assertFalse(IllegalArgumentExceptionUtilsx.isNoneNullInParams(2, 2, 2, null));
     * Assertions.assertFalse(IllegalArgumentExceptionUtilsx.isNoneNullInParams(null, 4, 3, 3, 5));
     * Assertions.assertFalse(IllegalArgumentExceptionUtilsx.isNoneNullInParams(4, null, 3, 5));
     * Assertions.assertTrue(IllegalArgumentExceptionUtilsx.isNoneNullInParams(4, true, 3, 5));
     * Assertions.assertTrue(IllegalArgumentExceptionUtilsx.isNoneNullInParams(new Object[]{null, null, null}));
     * Assertions.assertTrue(IllegalArgumentExceptionUtilsx.isNoneNullInParams(1, new Object[]{null}));
     * Assertions.assertTrue(IllegalArgumentExceptionUtilsx.isNoneNullInParams(1, (Object) new Object[]{null}));
     * </pre>
     * <p>
     * @return {@code true} if none of the parameters are null
     * @author XenoAmess
     */
    //${generateIsNoneNullInParams}

    /**
     * <p>Checks if all of the parameters are null.</p>
     *
     * @return {@code true} if all of the parameters are null
     * @author XenoAmess
     */
    //${generateIsAllNullInParams}

    /**
     * <p>If all of the parameters are null, throw an IllegalArgumentException about it.</p>
     *
     * @author XenoAmess
     */
    //${generateIsAllNullInParamsThenThrowIllegalArgumentException}

}
