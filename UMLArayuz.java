package Main;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UMLArayuz extends JFrame {
    private JPanel topPanel, leftPanel, centerPanel, rightPanel, bottomPanel;

    public UMLArayuz() {
        setTitle("UML Arayüzü");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(800, 600);

        // Top Panel
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        // Logo
        ImageIcon logoImage = new ImageIcon("logo.png");
        JLabel logoLabel = new JLabel(logoImage);
        topPanel.add(logoLabel, BorderLayout.WEST);

        // Tarih
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String formattedDate = dateFormat.format(currentDate);
        JLabel dateLabel = new JLabel(formattedDate);
        dateLabel.setHorizontalAlignment(JLabel.RIGHT);
        topPanel.add(dateLabel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // Left Panel
        leftPanel = new JPanel();
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setPreferredSize(new Dimension(getWidth() / 3, getHeight()));
        leftPanel.setLayout(new BorderLayout());

        // Database'de bulunan coinlerin listesi ve fiyatları
        JTextArea coinListArea = new JTextArea();
        coinListArea.setEditable(false);
        coinListArea.append("Coin List:\n");
        coinListArea.append("ID: 1, Name: Bitcoin, Price: $50000.00\n");
        coinListArea.append("ID: 2, Name: Ethereum, Price: $3000.00\n");
        coinListArea.append("ID: 3, Name: Litecoin, Price: $150.00\n");

        JScrollPane coinListScrollPane = new JScrollPane(coinListArea);
        leftPanel.add(coinListScrollPane, BorderLayout.CENTER);

        add(leftPanel, BorderLayout.WEST);

        // Center Panel
        centerPanel = new JPanel();
        centerPanel.setBackground(Color.RED);
        centerPanel.setPreferredSize(new Dimension(getWidth() / 3, getHeight()));
        // User'ın elinde bulunan coinlere göre pasta grafiği buraya eklenecek

        add(centerPanel, BorderLayout.CENTER);

        // Right Panel
        rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setPreferredSize(new Dimension(getWidth() / 3, getHeight()));
        rightPanel.setLayout(new BorderLayout());

        // Coin al-sat bölümü
        JPanel coinTradePanel = new JPanel();
        coinTradePanel.setLayout(new GridLayout(3, 1));

        JButton coinListButton = new JButton("Coin List");
        JTextField amountField = new JTextField();
        JButton buyButton = new JButton("Buy");
        JButton sellButton = new JButton("Sell");

        coinTradePanel.add(coinListButton);
        coinTradePanel.add(amountField);
        coinTradePanel.add(buyButton);
        coinTradePanel.add(sellButton);

        rightPanel.add(coinTradePanel, BorderLayout.NORTH);

        add(rightPanel, BorderLayout.EAST);

        // Bottom Panel
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        // Coin aktarımı bölümü
        JPanel transferPanel = new JPanel();
        transferPanel.setLayout(new GridLayout(1, 4));

        JTextField userIDField = new JTextField();
        JTextField coinIDField = new JTextField();
        JTextField amountTransferField = new JTextField();
        JButton withdrawButton = new JButton("Withdraw");

        transferPanel.add(userIDField);
        transferPanel.add(coinIDField);
        transferPanel.add(amountTransferField);
        transferPanel.add(withdrawButton);

        bottomPanel.add(transferPanel, BorderLayout.NORTH);

        // Account sekmesi
        JPanel accountPanel = new JPanel();
        accountPanel.setLayout(new GridLayout(3, 2));

        JLabel nameLabel = new JLabel("Name: John Doe");
        JLabel emailLabel = new JLabel("Email: johndoe@example.com");
        JLabel balanceLabel = new JLabel("Balance: $1000.00");

        accountPanel.add(new JLabel(""));
        accountPanel.add(new JLabel(""));
        accountPanel.add(nameLabel);
        accountPanel.add(emailLabel);
        accountPanel.add(balanceLabel);

        bottomPanel.add(accountPanel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new UMLArayuz();
    }
}
