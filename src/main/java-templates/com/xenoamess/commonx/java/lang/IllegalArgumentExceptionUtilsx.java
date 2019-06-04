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
