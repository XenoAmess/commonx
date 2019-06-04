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
     * Assertions.assertFalse(ArrayUtilsx.isAnyNull());
     * Assertions.assertTrue(ArrayUtilsx.isAnyNull(null));
     * Assertions.assertTrue(ArrayUtilsx.isAnyNull(1, null));
     * Assertions.assertTrue(ArrayUtilsx.isAnyNull(2, 2, 2, null));
     * Assertions.assertTrue(ArrayUtilsx.isAnyNull(null, 4, 3, 3, 5));
     * Assertions.assertTrue(ArrayUtilsx.isAnyNull(4, null, 3, 5));
     * Assertions.assertFalse(ArrayUtilsx.isAnyNull(4, true, 3, 5));
     * Assertions.assertFalse(ArrayUtilsx.isAnyNull(new Object[]{null, null, null}));
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
     * StringUtils.isNoneEmpty((String) null)    = false
     * StringUtils.isNoneEmpty((String[]) null)  = true
     * StringUtils.isNoneEmpty(null, "foo")      = false
     * StringUtils.isNoneEmpty("", "bar")        = false
     * StringUtils.isNoneEmpty("bob", "")        = false
     * StringUtils.isNoneEmpty("  bob  ", null)  = false
     * StringUtils.isNoneEmpty(new String[] {})  = true
     * StringUtils.isNoneEmpty(new String[]{""}) = false
     * StringUtils.isNoneEmpty(" ", "bar")       = true
     * StringUtils.isNoneEmpty("foo", "bar")     = true
     * </pre>
     *
     * @return {@code true} if none of the parameters are null
     * @author XenoAmess
     */
    public static boolean isNoneNullInParams(Object param0, Object param1) {
        return !isAnyNullInParams(param0, param1);
    }

//
//    /**
//     * <p>Checks if all of the CharSequences are empty ("") or null.</p>
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
//     * <p>Checks if a CharSequence is not empty (""), not null and not whitespace only.</p>
//     *
//     * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
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
//     * <p>Checks if any of the CharSequences are empty ("") or null or whitespace only.</p>
//     *
//     * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
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
//     * <p>Checks if none of the CharSequences are empty (""), null or whitespace only.</p>
//     *
//     * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
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
//     * <p>Checks if all of the CharSequences are empty (""), null or whitespace only.</p>
//     *
//     * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
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
