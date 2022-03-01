package com.dipisoft;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Session session = null;
        ChannelExec channel = null;

        System.out.println("----- Datos para la conexión -----");
        System.out.print("Dirección IP o nombre de dominio: ");
        String host = sc.nextLine();

        System.out.print("Puerto: ");
        int port = Integer.parseInt(sc.nextLine());

        System.out.println("\n----- Datos para el acceso -----");
        System.out.print("Usuario: ");
        String username = sc.nextLine();

        System.out.print("Contraseña: ");
        String password = sc.nextLine();

        while (true) {
            try {
                session = new JSch().getSession(username, host, port);
                session.setPassword(password);
                session.setConfig("StrictHostKeyChecking", "no");
                session.connect();

                channel = (ChannelExec) session.openChannel("exec");

                System.out.print("\nIntroduzca el nombre del registro a mostrar (incluyendo su extensión): ");
                String logName = sc.nextLine();

                channel.setCommand("cat /var/log/" + logName);
                ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
                channel.setOutputStream(responseStream);

                channel.connect();
                while (channel.isConnected()) {
                    Thread.sleep(100);
                }

                String responseString = new String(responseStream.toByteArray());

                if (responseString.isEmpty()) System.out.println(">> Error: El registro no existe.");
                else {
                    System.out.println("\n----- Mostrando contenido de '" + logName + "' ----- \n\n" + responseString
                            + "\n----- Fin del contenido del registro -----");
                }

                System.out.print("\n¿Desea inspeccionar otro archivo de registro? (y/n): ");
                if (!sc.nextLine().equals("y")) break;

            } catch (JSchException | InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (session != null) session.disconnect();
                if (channel != null) channel.disconnect();
            }
        }

        sc.close();
    }
}
