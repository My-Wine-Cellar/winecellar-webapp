/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.util;

import java.util.Base64;

/**
 * Utils for images
 */
public class Image {

    /**
     * Private constructor
     */
    private Image() {
    }

    /**
     * Encode a byte sequence
     * @param bytes The bytes
     * @return The encoded string
     */
    public static String encode(byte[] bytes) {
        if (bytes == null) {
            return null;
        }

        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * Decode a string
     * @param str The string
     * @return The decoded bytes
     */
    public static byte[] decode(String str) {
        if (str == null) {
            return null;
        }

        return Base64.getDecoder().decode(str);
    }
}
