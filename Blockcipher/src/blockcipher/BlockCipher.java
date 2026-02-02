package blockcipher;
import java.util.Scanner;

public class BlockCipher {

    // Encrypt using a block cipher with shift-based encryption
   public static String encrypt(String plainText, int keySize, int keyShift) {
        // Remove spaces from the plaintext
        plainText = plainText.replace(" ", "");

        // Padding the plaintext with 'x' if it's not divisible by key_size
        while (plainText.length() % keySize != 0) {
            plainText += 'x';
        }

        // Simple block cipher (shift each character in the block by key_shift)
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < plainText.length(); i += keySize) {
            String block = plainText.substring(i, i + keySize);
            StringBuilder encryptedBlock = new StringBuilder();

            // Encrypt each character in the block and display the encrypted block
            for (int j = 0; j < block.length(); j++) {
                // Rotate each character in the block by the key_shift value
                char c = block.charAt(j);
                encryptedBlock.append((char) (((c - 'a' + keyShift) % 26) + 'a'));
            }

            // Display the encrypted block
            System.out.println("Encrypted Block: " + encryptedBlock.toString());

            // Append the encrypted block to the final encrypted text
            encryptedText.append(encryptedBlock);
        }

        return encryptedText.toString();
    }

    // Decrypt the encrypted text by reversing the process
    public static String decrypt(String encryptedText, int keySize, int keyShift) {
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < encryptedText.length(); i += keySize) {
            String block = encryptedText.substring(i, i + keySize);
            StringBuilder decryptedBlock = new StringBuilder();

            // Decrypt each character in the block and display the decrypted block
            for (int j = 0; j < block.length(); j++) {
                char c = block.charAt(j);
                decryptedBlock.append((char) (((c - 'a' - keyShift + 26) % 26) + 'a'));
            }

            // Display the decrypted block
            System.out.println("Decrypted Block: " + decryptedBlock.toString());

            // Append the decrypted block to the final decrypted text
            decryptedText.append(decryptedBlock);
        }

        // Remove padding 'x' (if any)
        String result = decryptedText.toString().replaceAll("x+$", "");
        return result;
    }

    // Function to handle menu interface and get user input
    public static void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Block Cipher Menu ---");
            System.out.println("1. Encrypt");
            System.out.println("2. Decrypt");
            System.out.println("3. Exit");

            // User choice input
            System.out.print("Please select an option (1/2/3): ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                // Encrypt operation
                System.out.print("Enter plain text (only alphabets): ");
                String plainText = scanner.nextLine().toLowerCase();
                System.out.print("Enter the block size (integer): ");
                int blockSize = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter the key shift (integer): ");
                int keyShift = Integer.parseInt(scanner.nextLine());

                String encryptedText = encrypt(plainText, blockSize, keyShift);
                System.out.println("\nEncrypted Text: " + encryptedText);

            } else if (choice.equals("2")) {
                // Decrypt operation
                System.out.print("Enter encrypted text: ");
                String encryptedText = scanner.nextLine().toLowerCase();
                System.out.print("Enter the block size (integer): ");
                int blockSize = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter the key shift (integer): ");
                int keyShift = Integer.parseInt(scanner.nextLine());

                String decryptedText = decrypt(encryptedText, blockSize, keyShift);
                System.out.println("\nDecrypted Text: " + decryptedText);

            } else if (choice.equals("3")) {
                // Exit the program
                System.out.println("Exiting the program. Goodbye!");
                break;

            } else {
                System.out.println("Invalid choice. Please select a valid option.");
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        menu();
    }
}
