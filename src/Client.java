import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class Client extends JFrame implements ActionListener {

    private JTextArea taMessageHistory = new JTextArea(30, 30);
    private JTextField tfMessage = new JTextField();
    private JButton btnSubmit = new JButton("Enviar");

    private JTextField tfAddress = new JTextField();
    private JTextField tfPort = new JTextField();
    private JTextField tfUsername = new JTextField();

    private Socket server;
    private BufferedWriter out;
    private BufferedReader in;

    public Client() {
        Object[] texts = {tfAddress, tfPort, tfUsername};
        JOptionPane.showMessageDialog(null, texts);

        setVisible(true);
        setResizable(false);

        JPanel content = new JPanel();
        GroupLayout layout = new GroupLayout(content);
        content.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addComponent(taMessageHistory)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(tfMessage)
                        .addComponent(btnSubmit)
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(taMessageHistory)
                .addGroup(layout.createParallelGroup()
                        .addComponent(tfMessage)
                        .addComponent(btnSubmit)
                )
        );

        layout.linkSize(SwingConstants.VERTICAL, btnSubmit, tfMessage);


        content.add(taMessageHistory);
        content.add(tfMessage);
        content.add(btnSubmit);

        taMessageHistory.setLineWrap(true);
        taMessageHistory.setFocusable(false);
        taMessageHistory.setBorder(tfMessage.getBorder());
        btnSubmit.addActionListener(this);

        setContentPane(content);
        pack();
    }

    public void connect(String ip, int port){
        try {
            server = new Socket(ip, port);
            System.out.println("Connected.");
            out = new BufferedWriter(new OutputStreamWriter(server.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void listen(){
            try {
                while(!server.isClosed()) {
                    if (in.ready()) {
                        taMessageHistory.append(in.readLine() + "\n");
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    public void submit(){
        try {
            out.write(tfMessage.getText() + "\r");
            out.flush();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        tfMessage.setText("");
    }

    public void stop(){
        try{
            out.close();
            in.close();
            server.close();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.connect("localhost", 5555);
        client.listen();
        client.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(btnSubmit.getActionCommand())){
            submit();
        }
    }
}
