package com.jeeplus.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * 获取本机IP地址的工具类
 * Created by yuzp17311 on 2016/9/2.
 */
public class IPUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(IPUtils.class);


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
            } else {
                // 如果是Linux操作系统
                boolean bFindIP = false;
                Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
                while (netInterfaces.hasMoreElements()) {
                    if (bFindIP) {
                        break;
                    }
                    NetworkInterface ni = netInterfaces
                            .nextElement();
                    Enumeration<InetAddress> ips = ni.getInetAddresses();
                    while (ips.hasMoreElements()) {
                        ip = ips.nextElement();
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


    public static String getMacAddress() {
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
                LOGGER.error("exception occur on invoke getMacAddress methed:"+e);
            }
        }
        return mac;
    }

    public static String getIPAddr(HttpServletRequest request) {
        String ip = null;
        try {
            ip = request.getHeader("x-forwarded-for");
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } catch (Exception e) {
            LOGGER.error("IPUtils ERROR ", e);
        }

//        //使用代理，则获取第一个IP地址

//        if(StringUtils.isEmpty(ip) && ip.length() > 15) {

//			if(ip.indexOf(",") > 0) {

//				ip = ip.substring(0, ip.indexOf(","));

//			}

//		}


        return ip;
    }

}
