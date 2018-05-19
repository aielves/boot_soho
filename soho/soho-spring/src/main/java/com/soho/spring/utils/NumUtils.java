package com.soho.spring.utils;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

/**
 * Created by shadow on 2017/5/17.
 */
public class NumUtils {

    private static int[] numbers = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    public static String add(Object s1, Object s2, int scale) {
        BigDecimal b1 = toBigDecimal(s1);
        BigDecimal b2 = toBigDecimal(s2);
        BigDecimal decimal = b1.add(b2);
        return digit(decimal.toString(), scale);
    }

    public static String subtract(Object s1, Object s2, int scale) {
        BigDecimal b1 = toBigDecimal(s1);
        BigDecimal b2 = toBigDecimal(s2);
        BigDecimal decimal = b1.subtract(b2);
        return digit(decimal.toString(), scale);
    }

    public static String multiply(Object s1, Object s2, int scale) {
        BigDecimal b1 = toBigDecimal(s1);
        BigDecimal b2 = toBigDecimal(s2);
        if (b1.compareTo(BigDecimal.ZERO) == 0 || b2.compareTo(BigDecimal.ZERO) == 0) {
            return digit(0, scale);
        }
        BigDecimal decimal = b1.multiply(b2);
        return digit(decimal.toString(), scale);
    }

    public static String divide(Object s1, Object s2, int scale) {
        BigDecimal b1 = toBigDecimal(s1);
        BigDecimal b2 = toBigDecimal(s2);
        String decimal = "0";
        if (b2.compareTo(BigDecimal.ZERO) != 0) {
            decimal = b1.divide(b2, 20, BigDecimal.ROUND_HALF_UP).toString();
        }
        return digit(decimal.toString(), scale);
    }

    // s1 < s2
    public static boolean LT(Object s1, Object s2) {
        BigDecimal b1 = toBigDecimal(s1);
        BigDecimal b2 = toBigDecimal(s2);
        if (b1.compareTo(b2) < 0) {
            return true;
        }
        return false;
    }

    // s1 > s2
    public static boolean GT(Object s1, Object s2) {
        BigDecimal b1 = toBigDecimal(s1);
        BigDecimal b2 = toBigDecimal(s2);
        if (b1.compareTo(b2) > 0) {
            return true;
        }
        return false;
    }

    // s1 <= s2
    public static boolean LTE(Object s1, Object s2) {
        BigDecimal b1 = toBigDecimal(s1);
        BigDecimal b2 = toBigDecimal(s2);
        if (b1.compareTo(b2) <= 0) {
            return true;
        }
        return false;
    }

    // s1 >= s2
    public static boolean GTE(Object s1, Object s2) {
        BigDecimal b1 = toBigDecimal(s1);
        BigDecimal b2 = toBigDecimal(s2);
        if (b1.compareTo(b2) >= 0) {
            return true;
        }
        return false;
    }

    // s1 = s2
    public static boolean EQ(Object s1, Object s2) {
        BigDecimal b1 = toBigDecimal(s1);
        BigDecimal b2 = toBigDecimal(s2);
        if (b1.compareTo(b2) == 0) {
            return true;
        }
        return false;
    }

    // split number str
    public static String digit(Object number, int scale) {
        if (scale <= 0)
            scale = 0;
        String str1 = "";
        String str2 = "";
        String number1 = number.toString();
        if (number1.indexOf(".") != -1) {
            String[] split_str = number1.split("\\.");
            str1 = split_str[0];
            str2 = split_str[1];
            if (str2.length() > scale) {
                str2 = str2.substring(0, scale);
                if (!"".equals(str2)) {
                    str2 = cover4zero(str2, scale);
                }
            } else {
                str2 = cover4zero(str2, scale);
            }
            if (!"".equals(str2)) {
                str1 = str1 + "." + str2;
            }
        } else {
            str1 = number1;
            if (scale > 0) {
                str2 = cover4zero("0", scale);
            }
            if (!"".equals(str2)) {
                str1 = str1 + "." + str2;
            }
        }
        return str1;
    }

    private static String cover4zero(String str2, int scale) {
        if (str2.length() >= scale) {
            return str2;
        }
        int len = scale - str2.length();
        for (int i = 0; i < len; i++) {
            str2 = str2 + "0";
        }
        return str2;
    }

    private static BigDecimal toBigDecimal(Object object) {
        String string = null;
        if (object instanceof Integer) {
            string = Integer.toString((Integer) object);
        } else if (object instanceof Long) {
            string = Long.toString((Long) object);
        } else if (object instanceof Double) {
            string = Double.toString((Double) object);
        } else if (object instanceof Float) {
            string = Float.toString((Float) object);
        } else {
            string = String.valueOf(object);
        }
        return new BigDecimal(string);
    }

    public static String getIntSN(int len) {
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < (len < 1 ? 1 : len); i++) {
            buffer.append(numbers[random.nextInt(numbers.length)]);
        }
        return buffer.toString();
    }

    public static String getStrSN() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            System.out.println(getStrSN());
        }
        System.out.println(System.currentTimeMillis() - start);
    }

}
