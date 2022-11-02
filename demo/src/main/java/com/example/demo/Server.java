package com.example.demo;
import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Server {
    private JFrame frame;
    static JTextField entradaServidor = new JTextField();
    static JTextArea chat = new JTextArea();
    static ServerSocket ss;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    static TreeList AVLtreeList= new TreeList();

    static FilesManipulation treesBuilder= new FilesManipulation();

    public void insertTrees(String nameFile, boolean type) {
        AVLtreeList.insert(treesBuilder.insert(nameFile, type), nameFile);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Server window = new Server();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        String msgin = "";
        try {
            ss =  new ServerSocket(1201);
            s = ss.accept();
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());

            while (!msgin.equals("exit")) {
                msgin = din.readUTF();
                chat.setText(msgin);
            }
        } catch (Exception e) {
        }

    }

    /**
     * Create the application.
     */
    public Server() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 575, 517);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        chat.setBounds(61, 26, 360, 214);
        frame.getContentPane().add(chat);

        JButton enviar = new JButton("Enviar");
        enviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String msgout = "";
                    msgout = entradaServidor.getText().trim();
                    dout.writeUTF(msgout);
                } catch (Exception e2) {
                }
            }
        });
        enviar.setBounds(381, 286, 170, 47);
        frame.getContentPane().add(enviar);

        JLabel servidor = new JLabel("Servidor");
        servidor.setBounds(465, 72, 49, 14);
        frame.getContentPane().add(servidor);


        entradaServidor.setBounds(200, 300, 100, 30);
        frame.getContentPane().add(entradaServidor);
    }
}
