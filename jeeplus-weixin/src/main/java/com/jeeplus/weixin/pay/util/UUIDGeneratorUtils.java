package com.jeeplus.weixin.pay.util;


import java.net.InetAddress;
import java.security.SecureRandom;
import java.util.UUID;

/**
 * UUID工具类，用来产生一个唯一的标记号UUID
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-01-06 9:02.
 */
public class UUIDGeneratorUtils {

    private static SecureRandom SEEDER_STATIC=null;
    private static byte[] ADDRESS=null;
    private static String MID_VALUE_STATIC    = null;
    private SecureRandom seeder = null;


    static {
        try{
            ADDRESS= InetAddress.getLocalHost().getAddress();
            StringBuffer stringBuffer=new StringBuffer(8);
            stringBuffer.append(toHex(toInt(ADDRESS),8));
            MID_VALUE_STATIC=stringBuffer.toString();
            SEEDER_STATIC=new SecureRandom();
            SEEDER_STATIC.nextInt();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    public UUIDGeneratorUtils(){
        StringBuffer buffer=new StringBuffer(16);
        buffer.append(MID_VALUE_STATIC);
        buffer.append(toHex(System.identityHashCode(this),8));
        seeder=new SecureRandom();
        seeder.nextInt();
    }


    /**
     * 该方法用来产生一个32位的唯一的标记String
     * @param object
     * @return
     */
    public static String getUUID(Object object){
        StringBuffer uid = new StringBuffer(32);

        // get the system time
        long currentTimeMillis = System.currentTimeMillis();
        uid.append(toHex((int) (currentTimeMillis & -1L), 8));

        // get the internet address
        uid.append(MID_VALUE_STATIC);

        // get the object hash value
        uid.append(toHex(System.identityHashCode(object), 8));

        // get the random number
        uid.append(toHex(getRandom(), 8));

        return uid.toString();
    }




    /**
     * 获得一个UUID
     *
     * @return String UUID
     */
    public static String getUUID()
    {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 获得指定数目的UUID
     *
     * @param number
     *            int 需要获得的UUID数量
     * @return String[] UUID数组
     */
    public static String[] getUUID(int number)
    {
        if (number < 1)
        {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++)
        {
            ss[i] = getUUID();
        }
        return ss;
    }


    private static String toHex(int value, int length) {
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        StringBuffer buffer = new StringBuffer(length);
        int shift = length - 1 << 2;
        for (int i = -1; ++i < length;) {
            buffer.append(hexDigits[value >> shift & 0xf]);
            value <<= 4;
        }

        return buffer.toString();
    }


    private static int toInt(byte[] bytes) {
        int value = 0;
        for (int i = -1; ++i < bytes.length;) {
            value <<= 8;
            value |= bytes[i];
        }

        return value;
    }

    private static synchronized int getRandom() {
        return SEEDER_STATIC.nextInt();
    }



}
