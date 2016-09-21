package com.jeeplus.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 获取本机IP地址的工具类
 * Created by yuzp17311 on 2016/9/2.
 */
public class IPAddressUtils {


    /**
     * 判断当前主机系统是否是Window OS
     *
     * @return String
     */
    public static boolean isWindowsOS() {
        boolean isWindowsOS = false;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().indexOf("windows") > -1) {
            isWindowsOS = true;
        }
        return isWindowsOS;
    }

    /**
     * 获取本机ip地址，并自动区分Windows还是linux操作系统
     *
     * @return String
     */
    public static String getLocalIP() {
        String sIP = "";
        InetAddress ip = null;
        try {
            // 如果是Windows操作系统
            if (isWindowsOS()) {
                ip = InetAddress.getLocalHost();
            }else {
                // 如果是Linux操作系统
                boolean bFindIP = false;
                Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface.getNetworkInterfaces();
                while (netInterfaces.hasMoreElements()) {
                    if (bFindIP) {
                        break;
                    }
                    NetworkInterface ni = (NetworkInterface) netInterfaces
                            .nextElement();
                    Enumeration<InetAddress> ips = ni.getInetAddresses();
                    while (ips.hasMoreElements()) {
                        ip = (InetAddress) ips.nextElement();
                        if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() // 127.开头的都是lookback地址
                                && ip.getHostAddress().indexOf(":") == -1) {
                            bFindIP = true;
                            break;
                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (null != ip) {
            sIP = ip.getHostAddress();
        }
        return sIP;
    }


    public static String getMacAddress(){
        String mac = "";
        String line = "";
        String os = System.getProperty("os.name");
        if (os != null && os.startsWith("Windows")) {
            try {
                String command = "cmd.exe /c ipconfig /all";
                Process p = Runtime.getRuntime().exec(command);
                BufferedReader br = new BufferedReader(new InputStreamReader(p
                        .getInputStream()));
                while ((line = br.readLine()) != null) {
                    if (line.indexOf("Physical Address") > 0) {
                        int index = line.indexOf(":") + 2;
                        mac = line.substring(index);
                        break;
                    }
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mac;
    }

}
