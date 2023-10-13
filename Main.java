import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Date;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Transaction transaction = new Transaction();
        String carWashName = "Budi&Wawan Car Wash";

        System.out.print("Masukkan nama pemesan: ");
        String customerName = scanner.nextLine();
        
        System.out.print("Masukkan alamat: ");
        String address = scanner.nextLine();
        
        System.out.print("Masukkan nomor telepon: ");
        String phoneNumber = scanner.nextLine();
        
        System.out.print("Masukkan email: ");
        String email = scanner.nextLine();
        
        Date arrivalTime = new Date();
        
        Customer customer = new Customer(transaction);
        customer.setName(customerName);
        customer.setCarWashName(carWashName);
        customer.setArrivalTime(arrivalTime);
        customer.setAddress(address);
        customer.setPhoneNumber(phoneNumber);
        customer.setEmail(email);

        while (true) {
            displayMenu(carWashName, customerName);

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        transaction.displayPriceTable();
                        transaction.displayWashingTypes();
                        customer.inputCustomerData();

                        break;

                    case 2:
                        String carType = customer.getCarType();
                        double carPrice = transaction.calculateTotalPrice(carType);

                        String washType = customer.getWashType();
                        double washPrice = transaction.calculateWashPrice(washType);

                        double totalPrice = carPrice + washPrice;
                        double discountedPrice = transaction.calculateDiscountedPrice(totalPrice);

                        customer.setTotalPrice(discountedPrice);

                        int earnedPoints = customer.calculatePoints(totalPrice);
                        customer.addPoints(earnedPoints);

                        
                        customer.setAddress(address);
                        customer.setPhoneNumber(phoneNumber);
                        customer.setEmail(email);


                        customer.displayCustomerData(carPrice, washPrice);
                        customer.resetState();
                        break;

                    case 3:
                        int customerPoints = customer.getPoints();
                        System.out.println("Points: " + customerPoints);
                        break;
                    case 4:
                        System.out.println("Informasi tentang " + transaction.getCarWashName() + ":");
                        displayInfo();
                        break;
                    case 5:
                        System.out.println("Terima kasih, program akan keluar.");
                        System.exit(0);
                    default:
                        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                }

                System.out.print("Tekan enter untuk kembali ke menu utama...");
                scanner.nextLine();
                clearScreen();

            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid. Harap masukkan angka.");
                scanner.nextLine();
            }
        }
    }
    private static void displayMenu(String carWashName, String customerName) {
        System.out.println("===================================");
        System.out.println("Selamat datang, " + customerName + " di " + carWashName);
        System.out.println("===================================");
        System.out.println("===== Menu Utama =====");
        System.out.println("1. Pilih Jenis Mobil dan Jenis Pencucian");
        System.out.println("2. Checkout");
        System.out.println("3. Point");
        System.out.println("4. Informasi Kami");
        System.out.println("5. Exit Program");
        System.out.print("Pilih opsi (1-5): ");
        System.out.println("//          _  _    _             _                             __         _               _              \r\n" + 
        "//    __ _ (_)| |_ | |__   _   _ | |__    _ __    __ _  _   _  / _|  __ _ | | _ __   _ __ | |_  _ __ ___  \r\n" + 
        "//   / _` || || __|| '_ \\ | | | || '_ \\  | '_ \\  / _` || | | || |_  / _` || || '_ \\ | '__|| __|| '_ ` _ \\ \r\n" + 
        "//  | (_| || || |_ | | | || |_| || |_) | | | | || (_| || |_| ||  _|| (_| || || |_) || |   | |_ | | | | | |\r\n" + 
        "//   \\__, ||_| \\__||_| |_| \\__,_||_.__/  |_| |_| \\__,_| \\__,_||_|   \\__,_||_|| .__/ |_|    \\__||_| |_| |_|\r\n" + 
        "//   |___/                                                                   |_|                          ");

        }
    private static void displayInfo() {
        System.out.println("===== Informasi Layanan Kami =====");
        System.out.println("Kami adalah Budi&Wawan Car Wash ,layanan cuci mobil yang menyediakan berbagai jenis pencucian");
        System.out.println("untuk berbagai jenis mobil. Kami menawarkan pencucian dasar, standar,");
        System.out.println("dan premium untuk memenuhi kebutuhan cuci mobil Anda.");
        System.out.println("Selain itu, kami juga memberikan diskon setiap pembelian pencucian.");
        System.out.println("Selamat menikmati layanan kami!");
        System.out.println("=================================");
    }
    private static void checkout(Customer customer, Transaction transaction) {
        double totalPrice = customer.getTotalPrice();
        
        System.out.println("========== Struk Pembayaran ==========");
        System.out.println("Nama Pemesan: " + (customer.getName() != null ? customer.getName() : "Belum diatur"));
        
        
        
        System.out.println("=====================================");
        int earnedPoints = customer.calculatePoints(totalPrice);
        int totalPoints = customer.getPoints();
        int freeWashes = totalPoints / 100; 
        System.out.println("Poin sebelum: " + totalPoints);
        System.out.println("Poin yang Diperoleh dari transaksi ini: " + earnedPoints);
        System.out.println("Poin setelah transaksi: " + (totalPoints + earnedPoints));
        System.out.println("Pencucian Gratis Diperoleh: " + freeWashes);
        System.out.println("=====================================");
    
        
        if (freeWashes > 0) {
            System.out.println("Anda memiliki " + freeWashes + " pencucian gratis.");
            System.out.print("Ingin menukarkan poin untuk pencucian gratis? (Ya/Tidak): ");
            String choice = scanner.nextLine();
    
            if (choice.equalsIgnoreCase("Ya")) {
                System.out.println("Penukaran poin berhasil. Anda mendapatkan pencucian gratis.");
               
                customer.deductPoints(freeWashes * 100);
            } else {
                System.out.println("Poin tidak digunakan.");
            }
        } else {
            System.out.println("Anda belum memiliki cukup poin untuk pencucian gratis.");
        }
    
        System.out.println("Tekan enter untuk kembali ke menu utama...");
        scanner.nextLine();
        clearScreen();
    }
    
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
