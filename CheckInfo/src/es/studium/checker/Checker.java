package es.studium.checker;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Checker {

    public static void main(String[] args) {
        String baseIp = "192.168.0.";
        ArrayList<String> hostFounds = new ArrayList<>();
        
        try {
            for (int i = 1; i <= 256; i++) {
                String ip = baseIp + i;
                System.out.println("-> Dirección IP actual: " + ip);
                InetAddress address = InetAddress.getByName(ip);
                if (address.isReachable(500)) {
                    pruebaMetodos(address, hostFounds);
                }
            }
            showFounds(hostFounds);
        } catch (IOException e) {
            System.out.println("Debes estar conectado");
            System.out.println(e);
        }
    }

    private static void showFounds(ArrayList<String> hostFounds) {
        System.out.println(hostFounds);
    }

    private static void pruebaMetodos(InetAddress dir, ArrayList<String> hostFounds) {
        System.out.println("\t getHostName: " + dir.getHostName());
        System.out.println("\t getHostAddress: " + dir.getHostAddress());
        System.out.println("\t toString: " + dir.toString());
        System.out.println("\t getCanonicalHostName: " + dir.getCanonicalHostName());
        hostFounds.add(dir.getHostAddress()); 
        System.out.println("IPs con respuesta" +hostFounds+"\n");
    }
}
