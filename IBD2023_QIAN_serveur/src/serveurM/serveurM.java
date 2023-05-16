package serveurM;

import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.io.*;

public class serveurM {
	private static final String SERVER_IP = "172.21.36.149";
    private static final int SERVER_PORT = 5015;
	public static void main(String[] args) {
        try {
            // Création de la socket TCP pour le serveur
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Serveur en attente de connexions...");
           
            	// Attente de la connexion d'un client
            	Socket clientSocket = serverSocket.accept();
            	System.out.println("Connexion entrante : " + clientSocket);

            	// Flux d'entrée et de sortie pour communiquer avec le client
            	PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            	BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            	// Réception de la demande du client
            	String message = in.readLine();
            	System.out.println("Message reçu : " + message);
            	
            	String name = message.split(" ")[1];
                String addressClient = clientSocket.getInetAddress().getHostAddress();
                List<Map.Entry<String, String>> clientList = new ArrayList<>();
                //boolean exists = clientList.anyMatch(name);
                boolean exists = clientList.stream().anyMatch(entry -> entry.getKey().equals(name));
            	// Extraire le nom choisi pour l'inscription
          
                if (message.startsWith("inscription ")) {
                    
                    if (exists) {
                    	out.println("False");
                    	out.println( name + "  Vous êtes déjà inscrit auparavant.");
                    }else {
                    	clientList.add(new AbstractMap.SimpleEntry<>(name, addressClient));
                        out.println("true");
                    	//Entry<String, String> info = new Entry<String, String>();
                    	//clientList.add(info);
                    	 // Envoyer un message de confirmation d'inscription au client
                        out.println("Bienvenue " + name + " ! Vous êtes inscrit sur notre serveur.");
                    }
                    
                    // Fermer les flux d'entrée et de sortie et la socket du client

                }
                
                if (message.startsWith("désinscription ")) {
                	System.out.println("Message reçu : " + message);
                    //boolean exists = clientList.anyMatch(name);
                    boolean existsInListe = clientList.stream().anyMatch(entry -> entry.getKey().equals(name));
                    if (existsInListe) {
                    	clientList.remove(new AbstractMap.SimpleEntry<>(name, addressClient));
                    	out.println("True");
                    	out.println("Au revoir " + name + "  Vous êtes libre maintenant.");
                    }else {
                        out.println("False");
                    	//Entry<String, String> info = new Entry<String, String>();
                    	//clientList.add(info);
                    	 // Envoyer un message de confirmation d'inscription au client
                        out.println( name + " ! Vous êtes désinscrit auparavant.");
                    }
                    
                    // Fermer les flux d'entrée et de sortie et la socket du client

                }
                
                in.close();
                out.close();
                clientSocket.close();
          
           
    } catch (IOException e) {
        e.printStackTrace();
        }
    }
   	
}
