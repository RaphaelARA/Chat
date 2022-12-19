package chat.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class client extends javax.swing.JFrame {

        // Variables
        private javax.swing.JTextField ecriture_msg;
        private javax.swing.JButton envoi;
        private javax.swing.JScrollPane jScrollPane1;
        private static javax.swing.JTextArea zone_msg;
        static Socket socket;
        static DataInputStream din;
        static DataOutputStream dout; 
    
    public client() {
        initComponents();
    }

    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        zone_msg = new javax.swing.JTextArea();
        ecriture_msg = new javax.swing.JTextField();
        envoi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Client");
        setBackground(new java.awt.Color(0, 0, 0));
        setResizable(false);

        jScrollPane1.setBackground(new java.awt.Color(51, 51, 51));

        zone_msg.setBackground(new java.awt.Color(51, 51, 51));
        zone_msg.setColumns(20);
        zone_msg.setForeground(new java.awt.Color(0, 204, 204));
        zone_msg.setRows(5);
        jScrollPane1.setViewportView(zone_msg);

        ecriture_msg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ecriture_msgActionPerformed(evt);
            }
        });

        envoi.setBackground(new java.awt.Color(0, 0, 0));
        envoi.setForeground(new java.awt.Color(255, 255, 0));
        envoi.setText("Envoyer");
        envoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                envoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ecriture_msg, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(envoi)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ecriture_msg, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(envoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }

    private void ecriture_msgActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void envoiActionPerformed(java.awt.event.ActionEvent evt) {
        try{
            String msgout = "";
            msgout = ecriture_msg.getText().trim();
            dout.writeUTF(msgout);
        } catch (Exception e){
            
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new client().setVisible(true);
            }
        });
        
        try{
            socket = new Socket("127.0.0.1",1201); //adresse ip, adresse locale satria anaty même ordinateur no miasa eto
            din = new DataInputStream(socket.getInputStream());
            dout = new DataOutputStream(socket.getOutputStream());
            String msgin = "";
            while(!msgin.equals("exit")){
                msgin = din.readUTF();
                zone_msg.setText(zone_msg.getText().trim()+"\n Serveur: "+msgin); //affichage des messages depuis le côté serveur
            }
        }catch(Exception e){
            
        }
    }
}
