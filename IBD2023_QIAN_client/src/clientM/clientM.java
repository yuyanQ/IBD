package clientM;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.*;
import java.net.*;
import java.io.*;

public class clientM {
	private static final String SERVER_IP = "172.21.36.149";
    private static final int SERVER_PORT = 5015;

	public static void main(String[] args) {
        try {
            // CrÃ©ation de la socket TCP pour le client
            Socket clientSocket = new Socket(SERVER_IP ,SERVER_PORT);
           
            // Flux d'entrÃ©e et de sortie pour communiquer avec le serveur
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // test1 : Réception de la demande du serveur
            // écrire un message
            
            String nom = "yuyan";
            String message = "inscription " + nom;
            out.println(message);
            out.println( "\n j'ai écrit un message au serveur");

            boolean success = Boolean.parseBoolean(in.readLine());
            System.out.println("Inscription de " + nom + " : " + success);

            // Désinscription du client précédemment inscrit
            String message2 = "désinscription ";
            out.println(message2);
            success = Boolean.parseBoolean(in.readLine());
            System.out.println("Désinscription de " + nom + " : " + success);

            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
}
