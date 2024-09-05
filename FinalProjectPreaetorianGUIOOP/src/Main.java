

import java.util.Scanner;
import javax.swing.*;
import java.awt.event.*;

public class Main {
    private static Scanner input = new Scanner(System.in);
    private static ShoeStore store = new ShoeStore();

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        int menu = 0;
        do {
            System.out.println("Aplikasi Transaksi Sepatu");
            System.out.println("==================================");
            System.out.println("1. View Sepatu");
            System.out.println("2. Menambahkan Sepatu");
            System.out.println("3. Update Sepatu");
            System.out.println("4. Hapus Sepatu");
            System.out.println("5. Transaksi Sepatu");
            System.out.println("6. Close Program");
            System.out.print(">>");

            menu = input.nextInt();
            input.nextLine(); // Consume newline

            switch (menu) {
                case 1:
                    store.viewShoes();
                    System.out.println("Press enter to continue...");
                    input.nextLine();
                    break;
                case 2:
                    addShoe();
                    break;
                case 3:
                    updateShoe();
                    break;
                case 4:
                    deleteShoe();
                    break;
                case 5:
                    createTransactionGUI();
                    break;
                default:
                    System.out.println("Masukan angka 1-6");
                    break;
            }
        } while (menu != 6);
    }

    private void addShoe() {
        String model = getInput("Input model sepatu (at least 1 character): ");
        String brand = getInput("Input merk sepatu (at least 1 character): ");
        String color = getInput("Input warna sepatu (at least 1 character): ");
        int price = getIntInput("Input harga sepatu (greater than 100,000): ", 100000);

        store.addShoe(model, brand, color, price);
    }

    private void updateShoe() {
        store.viewShoes();
        String code = getInput("Input id sepatu yang ingin di update :");
        String model = getInput("Input model sepatu (at least 1 character): ");
        String brand = getInput("Input merk sepatu (at least 1 character): ");
        String color = getInput("Input warna sepatu (at least 1 character): ");
        int price = getIntInput("Input harga sepatu (greater than 100,000): ", 100000);

        store.updateShoe(code, model, brand, color, price);
    }

    private void deleteShoe() {
        store.viewShoes();
        String code = getInput("Input id sepatu yang ingin di delete :");
        store.deleteShoe(code);
    }

    private String getInput(String prompt) {
        String inputStr;
        do {
            System.out.println(prompt);
            inputStr = input.nextLine();
        } while (inputStr.length() < 1);
        return inputStr;
    }

    private int getIntInput(String prompt, int min) {
        int number;
        do {
            System.out.println(prompt);
            number = input.nextInt();
            input.nextLine(); // Consume newline
        } while (number <= min);
        return number;
    }

    private void createTransactionGUI() {
        JFrame frame = new JFrame("Shoe Transaction");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel shoeLabel = new JLabel("Select Shoe:");
        shoeLabel.setBounds(20, 20, 100, 25);
        frame.add(shoeLabel);

        JComboBox<String> shoeComboBox = new JComboBox<>();
        for (Shoe shoe : store.getShoes()) {
            shoeComboBox.addItem(shoe.getCode());
        }
        shoeComboBox.setBounds(150, 20, 200, 25);
        frame.add(shoeComboBox);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(20, 60, 100, 25);
        frame.add(quantityLabel);

        JTextField quantityField = new JTextField();
        quantityField.setBounds(150, 60, 200, 25);
        frame.add(quantityField);

        JLabel paymentLabel = new JLabel("Payment:");
        paymentLabel.setBounds(20, 100, 100, 25);
        frame.add(paymentLabel);

        JTextField paymentField = new JTextField();
        paymentField.setBounds(150, 100, 200, 25);
        frame.add(paymentField);

        JButton transactionButton = new JButton("Complete Transaction");
        transactionButton.setBounds(100, 140, 200, 30);
        frame.add(transactionButton);

        JTextArea resultArea = new JTextArea();
        resultArea.setBounds(20, 180, 350, 150);
        resultArea.setEditable(false);
        frame.add(resultArea);

       
        transactionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedShoe = (String) shoeComboBox.getSelectedItem();
                int quantity = Integer.parseInt(quantityField.getText());
                int payment = Integer.parseInt(paymentField.getText());

                Transaction transaction = new Transaction(store, resultArea);
                transaction.processTransaction(selectedShoe, quantity, payment);
            }
        });

        frame.setVisible(true);
    }
}