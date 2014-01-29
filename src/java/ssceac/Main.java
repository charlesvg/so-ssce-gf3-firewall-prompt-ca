/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ssceac;

import javax.naming.InitialContext;
import ssce.ejb.SessionBeanRemote;

/**
 *
 * @author administrator
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        String serverIp = "193.53.40.234";
        String serverPort = "3700";
        String sessionBeanClassName = "ssce.ejb.SessionBeanRemote";
        
        System.setProperty("org.omg.CORBA.ORBInitialHost", serverIp);
        System.setProperty("org.omg.CORBA.ORBInitialPort", serverPort);
        
        // Force it to connect straight to the SSL port
        //System.setProperty("com.sun.CSIV2.ssl.standalone.client.required","true");

        InitialContext ctx = new InitialContext();
        ctx.addToEnvironment("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
        ctx.addToEnvironment("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
        ctx.addToEnvironment("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        ctx.addToEnvironment("org.omg.CORBA.ORBInitialHost", serverIp);
        ctx.addToEnvironment("org.omg.CORBA.ORBInitialPort", serverPort);
        
        System.out.println("EJB Lookup, server=" + serverIp + ":" + serverPort + " bean=" + sessionBeanClassName);
        SessionBeanRemote sessionBean = (SessionBeanRemote) ctx.lookup(sessionBeanClassName);
        System.out.println("Lookup complete.");
        String helloWorld = sessionBean.getHelloWorld();
        System.out.println("Lookup result = " + helloWorld);
    }
    
}
