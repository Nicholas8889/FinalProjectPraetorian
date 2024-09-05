import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

class Transaction {
    private ShoeStore store;
    private JTextArea receiptArea; 

    public Transaction(ShoeStore store, JTextArea receiptArea) {
        this.store = store;
        this.receiptArea = receiptArea;
    }

    public void processTransaction(String shoeCode, int quantity, int payment) {
       
        SwingUtilities.invokeLater(() -> {
            if (quantity <= 0) {
                appendToReceipt("Quantity must be greater than zero.");
                return;
            }
            
            if (payment <= 0) {
                appendToReceipt("Payment must be greater than zero.");
                return;
            }

            Shoe selectedShoe = null;
            for (Shoe shoe : store.getShoes()) {
                if (shoe.getCode().equalsIgnoreCase(shoeCode)) {
                    selectedShoe = shoe;
                    break;
                }
            }

            if (selectedShoe == null) {
                appendToReceipt("Shoe not found.");
                return;
            }

            int totalPrice = selectedShoe.getPrice() * quantity;
            if (payment < totalPrice) {
                appendToReceipt("Payment is less than total price. Transaction failed.");
            } else {
                int change = payment - totalPrice;
                appendToReceipt("Transaction successful!");
                appendToReceipt("Shoe: " + selectedShoe.getModel());
                appendToReceipt("Color: " + selectedShoe.getColor());
                appendToReceipt("Quantity: " + quantity);
                appendToReceipt("Total Price: " + totalPrice);
                appendToReceipt("Payment: " + payment);
                appendToReceipt("Change: " + change);
            }
        });
    }

    private void appendToReceipt(String message) {
        
        SwingUtilities.invokeLater(() -> {
            receiptArea.append(message + "\n");
            receiptArea.setCaretPosition(receiptArea.getDocument().getLength()); // Scroll to the end
        });
    }
}
 