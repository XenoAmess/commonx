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

import com.xenoamess.commons.code_generators.IllegalArgumentExceptionUtilsxCodeGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author XenoAmess
 * @see org.apache.commons.lang3.ArrayUtils
 */
public class IllegalArgumentExceptionUtilsxTest {
    /**
     * Instances of this class should NOT be constructed in standard programming.
     *
     * This constructor is public to permit tools that require a JavaBean instance
     * to operate.
     */
    public IllegalArgumentExceptionUtilsxTest() {
        super();
    }

    @Test
    public void IllegalArgumentExceptionUtilsxCodeGeneratorTest() {
        IllegalArgumentExceptionUtilsxCodeGenerator.main(null);
    }

    @Test
    public void isAnyNullInParamsTest() {
        Assertions.assertFalse(IllegalArgumentExceptionUtilsx.isAnyNullInParams());
        Assertions.assertTrue(IllegalArgumentExceptionUtilsx.isAnyNullInParams(null));
        Assertions.assertTrue(IllegalArgumentExceptionUtilsx.isAnyNullInParams(1, null));
        Assertions.assertTrue(IllegalArgumentExceptionUtilsx.isAnyNullInParams(2, 2, 2, null));
        Assertions.assertTrue(IllegalArgumentExceptionUtilsx.isAnyNullInParams(null, 4, 3, 3, 5));
        Assertions.assertTrue(IllegalArgumentExceptionUtilsx.isAnyNullInParams(4, null, 3, 5));
        Assertions.assertFalse(IllegalArgumentExceptionUtilsx.isAnyNullInParams(4, true, 3, 5));
        Assertions.assertFalse(IllegalArgumentExceptionUtilsx.isAnyNullInParams(new Object[]{null, null, null}));
        Assertions.assertFalse(IllegalArgumentExceptionUtilsx.isAnyNullInParams(1, new Object[]{null}));
        Assertions.assertFalse(IllegalArgumentExceptionUtilsx.isAnyNullInParams(1, (Object) new Object[]{null}));
    }

//    @Test
//    public void isNoneNullInParamsTest() {
//        Assertions.assertTrue(IllegalArgumentExceptionUtilsx.isNoneNullInParams());
//        Assertions.assertFalse(IllegalArgumentExceptionUtilsx.isNoneNullInParams(null));
//        Assertions.assertFalse(IllegalArgumentExceptionUtilsx.isNoneNullInParams(1, null));
//        Assertions.assertFalse(IllegalArgumentExceptionUtilsx.isNoneNullInParams(2, 2, 2, null));
//        Assertions.assertFalse(IllegalArgumentExceptionUtilsx.isNoneNullInParams(null, 4, 3, 3, 5));
//        Assertions.assertFalse(IllegalArgumentExceptionUtilsx.isNoneNullInParams(4, null, 3, 5));
//        Assertions.assertTrue(IllegalArgumentExceptionUtilsx.isNoneNullInParams(4, true, 3, 5));
//        Assertions.assertTrue(IllegalArgumentExceptionUtilsx.isNoneNullInParams(new Object[]{null, null, null}));
//        Assertions.assertTrue(IllegalArgumentExceptionUtilsx.isNoneNullInParams(1, new Object[]{null}));
//        Assertions.assertTrue(IllegalArgumentExceptionUtilsx.isNoneNullInParams(1, (Object) new Object[]{null}));
//    }


//    /**
//     * Checks if none of the CharSequences are empty ("") or null.</p>
//     *
//     * <pre>
//     * StringUtils.isNoneEmpty((String) null)    = false
//     * StringUtils.isNoneEmpty((String[]) null)  = true
//     * StringUtils.isNoneEmpty(null, "foo")      = false
//     * StringUtils.isNoneEmpty("", "bar")        = false
//     * StringUtils.isNoneEmpty("bob", "")        = false
//     * StringUtils.isNoneEmpty("  bob  ", null)  = false
//     * StringUtils.isNoneEmpty(new String[] {})  = true
//     * StringUtils.isNoneEmpty(new String[]{""}) = false
//     * StringUtils.isNoneEmpty(" ", "bar")       = true
//     * StringUtils.isNoneEmpty("foo", "bar")     = true
//     * </pre>
//     *
//     * @param css the CharSequences to check, may be null or empty
//     * @return {@code true} if none of the CharSequences are empty or null
//     * @since 3.2
//     */
//    public static boolean isNoneEmpty(final CharSequence... css) {
//        return !isAnyEmpty(css);
//    }
//
//    /**
//     * Checks if all of the CharSequences are empty ("") or null.</p>
//     *
//     * <pre>
//     * StringUtils.isAllEmpty(null)             = true
//     * StringUtils.isAllEmpty(null, "")         = true
//     * StringUtils.isAllEmpty(new String[] {})  = true
//     * StringUtils.isAllEmpty(null, "foo")      = false
//     * StringUtils.isAllEmpty("", "bar")        = false
//     * StringUtils.isAllEmpty("bob", "")        = false
//     * StringUtils.isAllEmpty("  bob  ", null)  = false
//     * StringUtils.isAllEmpty(" ", "bar")       = false
//     * StringUtils.isAllEmpty("foo", "bar")     = false
//     * </pre>
//     *
//     * @param css the CharSequences to check, may be null or empty
//     * @return {@code true} if all of the CharSequences are empty or null
//     * @since 3.6
//     */
//    public static boolean isAllNull(final CharSequence... css) {
//        if (ArrayUtils.isEmpty(css)) {
//            return true;
//        }
//        for (final CharSequence cs : css) {
//            if (cs != null) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    /**
//     * Checks if a CharSequence is not empty (""), not null and not whitespace only.</p>
//     *
//     * Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
//     *
//     * <pre>
//     * StringUtils.isNotBlank(null)      = false
//     * StringUtils.isNotBlank("")        = false
//     * StringUtils.isNotBlank(" ")       = false
//     * StringUtils.isNotBlank("bob")     = true
//     * StringUtils.isNotBlank("  bob  ") = true
//     * </pre>
//     *
//     * @param cs the CharSequence to check, may be null
//     * @return {@code true} if the CharSequence is
//     * not empty and not null and not whitespace only
//     * @since 2.0
//     * @since 3.0 Changed signature from isNotBlank(String) to isNotBlank(CharSequence)
//     */
//    public static boolean isNotBlank(final CharSequence cs) {
//        return !isBlank(cs);
//    }
//
//    /**
//     * Checks if any of the CharSequences are empty ("") or null or whitespace only.</p>
//     *
//     * Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
//     *
//     * <pre>
//     * StringUtils.isAnyBlank((String) null)    = true
//     * StringUtils.isAnyBlank((String[]) null)  = false
//     * StringUtils.isAnyBlank(null, "foo")      = true
//     * StringUtils.isAnyBlank(null, null)       = true
//     * StringUtils.isAnyBlank("", "bar")        = true
//     * StringUtils.isAnyBlank("bob", "")        = true
//     * StringUtils.isAnyBlank("  bob  ", null)  = true
//     * StringUtils.isAnyBlank(" ", "bar")       = true
//     * StringUtils.isAnyBlank(new String[] {})  = false
//     * StringUtils.isAnyBlank(new String[]{""}) = true
//     * StringUtils.isAnyBlank("foo", "bar")     = false
//     * </pre>
//     *
//     * @param css the CharSequences to check, may be null or empty
//     * @return {@code true} if any of the CharSequences are empty or null or whitespace only
//     * @since 3.2
//     */
//    public static boolean isAnyBlank(final CharSequence... css) {
//        if (ArrayUtils.isEmpty(css)) {
//            return false;
//        }
//        for (final CharSequence cs : css) {
//            if (isBlank(cs)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    /**
//     * Checks if none of the CharSequences are empty (""), null or whitespace only.</p>
//     *
//     * Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
//     *
//     * <pre>
//     * StringUtils.isNoneBlank((String) null)    = false
//     * StringUtils.isNoneBlank((String[]) null)  = true
//     * StringUtils.isNoneBlank(null, "foo")      = false
//     * StringUtils.isNoneBlank(null, null)       = false
//     * StringUtils.isNoneBlank("", "bar")        = false
//     * StringUtils.isNoneBlank("bob", "")        = false
//     * StringUtils.isNoneBlank("  bob  ", null)  = false
//     * StringUtils.isNoneBlank(" ", "bar")       = false
//     * StringUtils.isNoneBlank(new String[] {})  = true
//     * StringUtils.isNoneBlank(new String[]{""}) = false
//     * StringUtils.isNoneBlank("foo", "bar")     = true
//     * </pre>
//     *
//     * @param css the CharSequences to check, may be null or empty
//     * @return {@code true} if none of the CharSequences are empty or null or whitespace only
//     * @since 3.2
//     */
//    public static boolean isNoneBlank(final CharSequence... css) {
//        return !isAnyBlank(css);
//    }
//
//    /**
//     * Checks if all of the CharSequences are empty (""), null or whitespace only.</p>
//     *
//     * Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
//     *
//     * <pre>
//     * StringUtils.isAllBlank(null)             = true
//     * StringUtils.isAllBlank(null, "foo")      = false
//     * StringUtils.isAllBlank(null, null)       = true
//     * StringUtils.isAllBlank("", "bar")        = false
//     * StringUtils.isAllBlank("bob", "")        = false
//     * StringUtils.isAllBlank("  bob  ", null)  = false
//     * StringUtils.isAllBlank(" ", "bar")       = false
//     * StringUtils.isAllBlank("foo", "bar")     = false
//     * StringUtils.isAllBlank(new String[] {})  = true
//     * </pre>
//     *
//     * @param css the CharSequences to check, may be null or empty
//     * @return {@code true} if all of the CharSequences are empty or null or whitespace only
//     * @since 3.6
//     */
//    public static boolean isAllBlank(final CharSequence... css) {
//        if (ArrayUtils.isEmpty(css)) {
//            return true;
//        }
//        for (final CharSequence cs : css) {
//            if (isNotBlank(cs)) {
//                return false;
//            }
//        }
//        return true;
//    }

}
