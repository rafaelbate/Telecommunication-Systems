/*
 * Sistemas de Telecomunicacoes 
 *          2017/2018
 */
package udpdemoproject;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author lflb@fct.unl.pt
 */
public class Chat_udp extends javax.swing.JFrame {

private javax.swing.Timer timer;// Timer object
public volatile int cnt;// Counter - messages left to send
private HashMap<String, String> record = new HashMap<String, String>();

public void write_record() {
    for (String remote : record.keySet()) { // for cycle over keys
        System.out.println("\nCommunication with " + remote);
        System.out.println(record.get(remote));// Write the last message from remore
}
record.clear(); // Clear the list
}

    /**
     * Creates new form Chat_udp
     */
    public Chat_udp() {
        initComponents();   // defined by NetBeans, create the graphical window
        sock = null;        // Set null value – meaning “not initialized”
        serv = null;        // Set null value – meaning “not initialized”
//        timer = null;
        try {
            // Get local IP and set port to 0
            InetAddress addr = InetAddress.getLocalHost();  // Get the local IP address
            jTextLocIP.setText(addr.getHostAddress());       // Set the IP text fields to 
            jTextRemIP.setText(addr.getHostAddress());       //    the local address
        } catch (UnknownHostException e) {
            System.err.println("Unable to determine local IP address: " + e);
            System.exit(-1);    // Close the application
        }
        jTextLocPort.setText("20000");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelLocal = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextLocIP = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextLocPort = new javax.swing.JTextField();
        jToggleButtonActive = new javax.swing.JToggleButton();
        jButtonClear = new javax.swing.JButton();
        jPanelRemote = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextRemIP = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextRemPort = new javax.swing.JTextField();
        jToggleButtonRecord = new javax.swing.JToggleButton();
        jPanelMessage = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTextMessage = new javax.swing.JTextField();
        jButtonSend = new javax.swing.JButton();
        jButtonSend5 = new javax.swing.JButton();
        jToggleButtonAll = new javax.swing.JToggleButton();
        jLabelLocal = new javax.swing.JLabel();
        jScrollPaneLocal = new javax.swing.JScrollPane();
        jTextAreaLocal = new javax.swing.JTextArea();
        jLabelRemote = new javax.swing.JLabel();
        jScrollPaneRemote = new javax.swing.JScrollPane();
        jTextAreaRemote = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat UDP");
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        jPanelLocal.setMaximumSize(new java.awt.Dimension(450, 40));
        jPanelLocal.setMinimumSize(new java.awt.Dimension(197, 38));
        jPanelLocal.setPreferredSize(new java.awt.Dimension(450, 38));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel3.setText("Local: ");
        jPanelLocal.add(jLabel3);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel1.setText("IP");
        jPanelLocal.add(jLabel1);

        jTextLocIP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextLocIP.setText("127.0.0.1");
        jTextLocIP.setMaximumSize(new java.awt.Dimension(120, 28));
        jTextLocIP.setPreferredSize(new java.awt.Dimension(120, 28));
        jPanelLocal.add(jTextLocIP);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel2.setText("Port");
        jPanelLocal.add(jLabel2);

        jTextLocPort.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextLocPort.setText("20000");
        jTextLocPort.setMaximumSize(new java.awt.Dimension(60, 28));
        jPanelLocal.add(jTextLocPort);

        jToggleButtonActive.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jToggleButtonActive.setText("Active");
        jToggleButtonActive.setMaximumSize(new java.awt.Dimension(90, 30));
        jToggleButtonActive.setPreferredSize(new java.awt.Dimension(80, 29));
        jToggleButtonActive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonActiveActionPerformed(evt);
            }
        });
        jPanelLocal.add(jToggleButtonActive);

        jButtonClear.setBackground(new java.awt.Color(220, 220, 100));
        jButtonClear.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jButtonClear.setForeground(new java.awt.Color(76, 76, 28));
        jButtonClear.setText("Clear");
        jButtonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearActionPerformed(evt);
            }
        });
        jPanelLocal.add(jButtonClear);

        getContentPane().add(jPanelLocal);

        jPanelRemote.setMaximumSize(new java.awt.Dimension(450, 40));
        jPanelRemote.setPreferredSize(new java.awt.Dimension(450, 38));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel6.setText("Remote: ");
        jPanelRemote.add(jLabel6);

        jLabel7.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel7.setText("IP");
        jPanelRemote.add(jLabel7);

        jTextRemIP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextRemIP.setText("127.0.0.1");
        jTextRemIP.setMaximumSize(new java.awt.Dimension(120, 28));
        jTextRemIP.setPreferredSize(new java.awt.Dimension(120, 28));
        jPanelRemote.add(jTextRemIP);

        jLabel8.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel8.setText("Port");
        jPanelRemote.add(jLabel8);

        jTextRemPort.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextRemPort.setText("20000");
        jPanelRemote.add(jTextRemPort);

        jToggleButtonRecord.setText("Record");
        jToggleButtonRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonRecordActionPerformed(evt);
            }
        });
        jPanelRemote.add(jToggleButtonRecord);

        getContentPane().add(jPanelRemote);

        jPanelMessage.setMaximumSize(new java.awt.Dimension(450, 40));
        jPanelMessage.setMinimumSize(new java.awt.Dimension(156, 38));
        jPanelMessage.setPreferredSize(new java.awt.Dimension(450, 38));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel9.setText("Message: ");
        jPanelMessage.add(jLabel9);

        jTextMessage.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextMessage.setText("Hello!");
        jTextMessage.setMaximumSize(new java.awt.Dimension(2147483647, 28));
        jTextMessage.setPreferredSize(new java.awt.Dimension(200, 28));
        jPanelMessage.add(jTextMessage);

        jButtonSend.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtonSend.setText(" Send ");
        jButtonSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSendActionPerformed(evt);
            }
        });
        jPanelMessage.add(jButtonSend);

        jButtonSend5.setText("Send5");
        jButtonSend5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSend5ActionPerformed(evt);
            }
        });
        jPanelMessage.add(jButtonSend5);

        getContentPane().add(jPanelMessage);

        jToggleButtonAll.setText("ALL");
        jToggleButtonAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonAllActionPerformed(evt);
            }
        });
        getContentPane().add(jToggleButtonAll);

        jLabelLocal.setFont(new java.awt.Font("Arial Black", 0, 15)); // NOI18N
        jLabelLocal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLocal.setText("Local");
        jLabelLocal.setMaximumSize(new java.awt.Dimension(50, 22));
        jLabelLocal.setNextFocusableComponent(jLabelLocal);
        jLabelLocal.setOpaque(true);
        jLabelLocal.setPreferredSize(new java.awt.Dimension(50, 22));
        getContentPane().add(jLabelLocal);

        jTextAreaLocal.setColumns(20);
        jTextAreaLocal.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextAreaLocal.setRows(5);
        jScrollPaneLocal.setViewportView(jTextAreaLocal);

        getContentPane().add(jScrollPaneLocal);

        jLabelRemote.setFont(new java.awt.Font("Arial Black", 0, 15)); // NOI18N
        jLabelRemote.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelRemote.setText("Remote");
        getContentPane().add(jLabelRemote);

        jTextAreaRemote.setColumns(20);
        jTextAreaRemote.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextAreaRemote.setRows(5);
        jScrollPaneRemote.setViewportView(jTextAreaRemote);

        getContentPane().add(jScrollPaneRemote);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     *  Handle "Active" button; starts and stops the chat application
     */
    private void jToggleButtonActiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonActiveActionPerformed
    if (jToggleButtonActive.isSelected()) {    // The button is ON                                                   
            int port;
            try {   // Read the port number in Local Port text field
                port = Integer.parseInt(jTextLocPort.getText());
            } catch (NumberFormatException e) {
                Log_loc("Invalid local port number: " + e + "\n");
                jToggleButtonActive.setSelected(false);     // Set the button off
                return;
            }
            try {
                sock = new DatagramSocket(port);    // Create UDP socket
                jTextLocPort.setText("" + sock.getLocalPort());
                jTextRemPort.setText("" + sock.getLocalPort());
                set_timer_function(1000);
                //
                serv = new Daemon_udp(this, sock);  // Create the receiver thread
                serv.start();                       // Start the receiver thread
                Log_loc("Chat_udp active\n");
            } catch (SocketException e) {
                Log_loc("Socket creation failure: " + e + "\n");
                jToggleButtonActive.setSelected(false);     // Set the button off
            }
            
        } else {    // The button is OFF
            // Stop timer 
            timer.stop();
        
            // Stop application        
            if (serv != null) {         // If thread is running
                serv.stopRunning();     // Stop the server thread
                serv = null;    // Thread will be garbadge collected after it stops
            }
            if (sock != null) {     // If socket is active
                sock.close();       // Close the socket
                sock = null;    // Forces garbadge collecting
            }
            Log_loc("Chat_udp stopped\n");
        }
    }//GEN-LAST:event_jToggleButtonActiveActionPerformed

    /**
     * Handle button "Send" - sends a message
     */
    private void jButtonSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSendActionPerformed
        send_packet();
    }//GEN-LAST:event_jButtonSendActionPerformed

    private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearActionPerformed
        jTextAreaLocal.setText("");
        jTextAreaRemote.setText("");
    }//GEN-LAST:event_jButtonClearActionPerformed

    private void jButtonSend5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSend5ActionPerformed
        
        if(!jToggleButtonActive.isSelected()){
            Log_loc("active=off!\n");
            }else{
                if(timer.isRunning()){    
                }else{ 
                    timer.start();
                }
            cnt=5;
            set_timer_function(1000);  
      }
        
    }//GEN-LAST:event_jButtonSend5ActionPerformed

    private void jToggleButtonRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonRecordActionPerformed
        // TODO add your handling code here:
        if(jToggleButtonRecord.isSelected()){
        }else{
            write_record();
        }
        
    }//GEN-LAST:event_jToggleButtonRecordActionPerformed

    private void jToggleButtonAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonAllActionPerformed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_jToggleButtonAllActionPerformed

    /**
     * Sends one packet to netip:port and logs the event
     * @param dp        - packet object
     * @param netip     - destination IP 
     * @param port      - destination port
     * @param message   - message contents
     */
    private void send_one_packet(DatagramPacket dp, InetAddress netip, int port, 
                                 String message) {
        try {
            dp.setAddress(netip);   // Set destination ip
            dp.setPort(port);       // Set destination port
            sock.send(dp);          // Send packet
            // Write message to jTextAreaLocal
            String to = netip.getHostAddress() + ":" + port;  // ‘name’ of remote host
            String log = formatter.format(new Date()) + " - sent to " + to
                    + " - '" + message + "'\n";
            Log_loc(log);           // Write to Local text area

        } catch (IOException e) {
            // Communications exception
            Log_loc("Error sending packet: " + e + "\n");
        } catch (Exception e) {
            // Other exception (e.g. null pointer, etc.)
            Log_loc("Error sending packet: " + e + "\n");
        }

    }

    /**
     * Sends a packet to the remote IP:port with the message
     */
    public synchronized void send_packet() {
        if (sock == null) {
            Log_loc("Socket isn't active!\n");
            return;
        }
        InetAddress netip;
        int nmaq;
        if(jToggleButtonAll.isSelected()){
           nmaq=11;
       }else{ 
        try { // Get IP address
            netip = InetAddress.getByName(jTextRemIP.getText());
        } catch (UnknownHostException e) {
            Log_loc("Invalid remote host address: " + e + "\n");
            return;
        }
        
        int port;
        try { // Get port
            port = Integer.parseInt(jTextRemPort.getText());
        } catch (NumberFormatException e) {
            Log_loc("Invalid remote port number: " + e + "\n");
            return;
        }
        nmaq=1;
        }
        String message= jTextMessage.getText();
        if (message.length() == 0) {
            Log_loc("Empty message: not sent\n");
            return;
        }
        int i;
        String ip=new String("172.16.54."+ nmaq+100);
        for(i=nmaq;i!=0;i--){
            if(nmaq==11){
             ip= ("172.16.54."+ i+100); 
             netip = InetAddress.getByName(ip);
            }  
        // Create and send packet
        ByteArrayOutputStream os = new ByteArrayOutputStream(); // Prepares a message 
        DataOutputStream dos = new DataOutputStream(os);        //   writting object
        try {
            dos.writeShort(message.length()); // Write the message's length to buffer
            dos.writeBytes(message);          // Write the message contents to buffers
            byte[] buffer = os.toByteArray();       // Convert to byte array   
            DatagramPacket dp = new DatagramPacket(buffer, buffer.length); // Create packet
            send_one_packet(dp, netip, port, message);  // Send packet and log the event
        } catch (Exception e) {
            // Catches all exceptions
            Log_loc("Error sending packet: " + e + "\n");
        }
    }
    }
    /**
     * Handles an incoming message
     * @param dp    Datagram packet
     * @param dis   Data input stream
     */
    public synchronized void receive_packet(DatagramPacket dp,DataInputStream dis) {
        
        

        try {
            Date date = new Date(); // Get reception hour
            // IP address of the sending host + port of sending host = User ID
            String from= dp.getAddress().getHostAddress() + ":" + dp.getPort();
            
            // Read packet fields using 'dis'
            int len_msg = dis.readShort();  // Read message length
            if (len_msg > MAX_PLENGTH) {
                Log_rem(formatter.format(date) + 
                        " - received message too long ("+len_msg+")from " +
                        from + "\n");
                return;
            }
            byte[] sbuf2 = new byte[len_msg];    // Create an array to store the message
            int n = dis.read(sbuf2, 0, len_msg); // returns number of byte read
            if (n != len_msg) {
                Log_rem(formatter.format(date) + 
                        " - received message too short from " + from + "\n");
                return;
            }
            String msg = new String(sbuf2, 0, n);   // Creates a String from the buffer
            if (dis.available() > 0) {  // More bytes after the message in the buffer
                Log_rem("Packet too long\n");
                return;
            }
            if (jToggleButtonRecord.isSelected()) { // If button is selected
                String str = formatter.format(date) + " - received from " + from + " - '" + msg + "'\n";
                record.put(from, str); // Put string into the list associated to key from
            }
            // Write message contents
            Log_rem(formatter.format(date) + " - received from " + from +
                    " - '" + msg + "'\n");

        } catch (IOException e) {
            Log_rem("Packet too short: " + e + "\n");
        }
    }

    /**
     * Define the timer's callback function and creates timer object
     * @param period - timer period in miliseconds
     */
   private void set_timer_function(int period) {
        java.awt.event.ActionListener act;

        act = new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timer.start();
                // Define the timer callback function
                if (cnt > 0) { // While there are more packets to send ...
                    send_packet();// Send the packet
                    cnt--;// Decrement the counter
                    } else { // Counter reached 0
                        timer.stop();// Stop the timer
                }
            }
        };
        // Create the timer's object
        timer = new javax.swing.Timer(period, act);
    }

    /**
     * Outputs a message to the "Local" text area
     * @param s message echoed
     */
    public synchronized void Log_loc(String s) {
        try {
            jTextAreaLocal.append(s);           // Write to the Local text area
            System.out.print("Local: " + s);   // Write to the terminal
        } catch (Exception e) {
            System.err.println("Error in Log_loc: " + e + "\n");
        }
    }

    /**
     * Outputs a message to the "Remote" text area
     * @param s message echoed
     */
    public synchronized void Log_rem(String s) {
        try {
            jTextAreaRemote.append(s);          // Write to the Remote text area
            System.out.print("Remote: " + s);  // Write to the terminal
        } catch (Exception e) {
            System.err.println("Error in Log_rem: " + e + "\n");
        }
    }

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Chat_udp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Chat_udp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Chat_udp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Chat_udp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Chat_udp().setVisible(true);
            }
        });
    }
    
    /**
     * Constant - Maximum packet length
     */
    public static final int MAX_PLENGTH = 8096;
    
    // Variables declaration
    private DatagramSocket sock;     // datagrama socket
    private Daemon_udp serv;         // thread for message reception
    // private javax.swing.Timer timer;    // Timer object
    // public volatile int cnt= 0;         // Counter - messages left to send
    private final java.text.SimpleDateFormat formatter =  // Formatter for dates
                    new java.text.SimpleDateFormat("hh:mm:ss");

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButtonSend;
    private javax.swing.JButton jButtonSend5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelLocal;
    private javax.swing.JLabel jLabelRemote;
    private javax.swing.JPanel jPanelLocal;
    private javax.swing.JPanel jPanelMessage;
    private javax.swing.JPanel jPanelRemote;
    private javax.swing.JScrollPane jScrollPaneLocal;
    private javax.swing.JScrollPane jScrollPaneRemote;
    private javax.swing.JTextArea jTextAreaLocal;
    private javax.swing.JTextArea jTextAreaRemote;
    private javax.swing.JTextField jTextLocIP;
    private javax.swing.JTextField jTextLocPort;
    private javax.swing.JTextField jTextMessage;
    private javax.swing.JTextField jTextRemIP;
    private javax.swing.JTextField jTextRemPort;
    private javax.swing.JToggleButton jToggleButtonActive;
    private javax.swing.JToggleButton jToggleButtonAll;
    private javax.swing.JToggleButton jToggleButtonRecord;
    // End of variables declaration//GEN-END:variables
}
