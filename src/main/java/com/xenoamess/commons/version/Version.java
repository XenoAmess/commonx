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

package com.xenoamess.commons.version;

import com.xenoamess.commons.io.FileUtilsx;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

/**
 * A version class that represent a package's version.
 * It will read the /VERSION/${package name of this class}.VERSION file
 * That file is a template and shall be replaced and filled by maven when
 * start up.
 * However if the file is not found then VERSION will be VERSION_MISSING,
 * And a waring message will be write to System.err
 * <p>
 * See pom of this project for more information.
 *
 * @author XenoAmess
 * @version 0.6.0
 */
public class Version implements Comparable<Version> {

    private final String versionString;

    /**
     * <p>Constructor for Version.</p>
     *
     * @param versionString a {@link String} object.
     */
    public Version(String versionString) {
        if (!StringUtils.isBlank(versionString)) {
            this.versionString = versionString;
        } else {
            this.versionString = VERSION_MISSING;
        }
    }

    /**
     * Constant <code>VERSION_MISSING="VersionMissing"</code>
     */
    public static final String VERSION_MISSING = "VersionMissing";

    /**
     * <p>compareVersions.</p>
     *
     * @param versionString1 a {@link String} object.
     * @param versionString2 a {@link String} object.
     * @return a int.
     */
    public static int compareVersions(String versionString1, String versionString2) {
        String snapshotString = "-SNAPSHOT";
        if (versionString1.endsWith(snapshotString)) {
            versionString1 = versionString1.substring(0, versionString1.length() - snapshotString.length());
            versionString1 += ".-2";
        }
        if (versionString2.endsWith(snapshotString)) {
            versionString2 = versionString2.substring(0, versionString2.length() - snapshotString.length());
            versionString2 += ".-2";
        }
        String[] versionStringSegments1 = versionString1.split("\\.");
        String[] versionStringSegments2 = versionString2.split("\\.");
        int length = Math.max(versionStringSegments1.length, versionStringSegments2.length);
        for (int i = 0; i < length; i++) {
            String versionStringSegment1 = versionStringSegments1.length > i ? versionStringSegments1[i] : "-1";
            String versionStringSegment2 = versionStringSegments2.length > i ? versionStringSegments2[i] : "-1";

            if (versionStringSegment1.equals(versionStringSegment2)) {
                continue;
            }
            int versionIntSegment1 = Integer.parseInt(versionStringSegment1);
            int versionIntSegment2 = Integer.parseInt(versionStringSegment2);
            if (versionIntSegment1 != versionIntSegment2) {
                return versionIntSegment1 - versionIntSegment2;
            }
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Version version) {
        return compareVersions(this.versionString, version.versionString);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Version)) {
            return false;
        }
        Version version = (Version) o;
        return Objects.equals(versionString, version.versionString);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(versionString);
    }

    public static Version loadPackageVersion(Class classObject) {
        String res;
        res = FileUtilsx.loadString(classObject, "/VERSION/" + classObject.getPackage().getName() + ".VERSION");
        if (StringUtils.isBlank(res)) {
            res = VERSION_MISSING;
            System.err.println("version missing for package " + classObject.getPackage().getName() + "!");
        }
        return new Version(res.trim());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.versionString;
    }
}
