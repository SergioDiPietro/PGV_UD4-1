package com.dipisoft;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("----- Datos para la conexión ----- ");
        System.out.print("Dirección IP o nombre de dominio: ");
        String address = sc.nextLine();

        System.out.print("Puerto: ");
        int port = Integer.parseInt(sc.nextLine());

        System.out.println("\n----- Datos para acceso ----- ");
        System.out.print("Usuario: ");
        String username = sc.nextLine();

        System.out.print("Contraseña: ");
        String password = sc.nextLine();

        System.out.println("\n" + address + ":" + port + ", " + username + ":" + password);

        sc.close();
    }
}
