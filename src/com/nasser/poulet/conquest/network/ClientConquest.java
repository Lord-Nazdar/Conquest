package com.nasser.poulet.conquest.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.sun.swing.internal.plaf.synth.resources.synth_sv;

import java.io.IOException;

/**
 * Created by Thomas on 1/2/14.
 */
public class ClientConquest{
    private Client client;
    public ClientConquest( String address ){
        client = new Client();
        client.start();

        Network.register(client);

        client.addListener(new Listener() {
            public void connected (Connection connection) {
               System.out.println("Connected");
            }

            public void disconnected (Connection connection) {
                System.out.println("Disconnected!");
            }
        });
        try {
            client.connect(5000,address,Network.port);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendSyncRequest(){
        client.sendTCP(new Network.RequestBoard());
    }

    public void sendReady(){
        client.sendTCP(new Network.SetReady());
    }

    public void sendForceStart(){
        client.sendTCP(new Network.forceStart());
    }

    public Client getClient() {
        return client;
    }

    public void close(){
        client.close();
    }
}
