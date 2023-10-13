import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Customer {
    private String name;
    private String carType;
    private double totalPrice;
    private String washType;
    private int points;
    private String carWashName;
    private Transaction transaction;
    private static final double TAX_RATE = 0.1;
    private static final int POINTS_DIVISOR = 10;
    private String providerName;
    private Date arrivalTime;
    private Date dateOfService;
    private String address;
    private String phoneNumber;
    private String email;

    public void inputCustomerData() {
        Scanner scanner = new Scanner(System.in);

        String[] carTypes = transaction.getCarTypes();
        String[] washTypes = transaction.getWashTypes();

        System.out.println("Pilih jenis mobil:");
        for (int i = 0; i < carTypes.length; i++) {
            System.out.println((i + 1) + ". " + carTypes[i]);
        }

        int carChoice = scanner.nextInt();

        if (carChoice >= 1 && carChoice <= carTypes.length) {
            this.carType = carTypes[carChoice - 1];
        } else {
            System.out.println("Pilihan jenis mobil tidak valid. Memilih Sedan secara default.");
            this.carType = "Sedan";
        }

        System.out.println("Pilih jenis pencucian:");
        for (int i = 0; i < washTypes.length; i++) {
            System.out.println((i + 1) + ". " + washTypes[i]);
        }

        int washChoice = scanner.nextInt();

        if (washChoice >= 1 && washChoice <= washTypes.length) {
            this.washType = washTypes[washChoice - 1];
        } else {
            System.out.println("Pilihan jenis pencucian tidak valid. Memilih Pencucian Dasar secara default.");
            this.washType = "Pencucian Dasar";
            System.out.print("Masukkan tanggal (dd/MM/yyyy): ");
        String dateString = scanner.next();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.dateOfService = dateFormat.parse(dateString);
        } catch (Exception e) {
            System.out.println("Format tanggal tidak valid. Menggunakan tanggal hari ini.");
            this.dateOfService = new Date();
        }

        System.out.print("Masukkan jam kedatangan (HH:mm): ");
        String timeString = scanner.next();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        try {
            this.arrivalTime = timeFormat.parse(timeString);
        } catch (Exception e) {
            System.out.println("Format jam kedatangan tidak valid. Menggunakan waktu saat ini.");
            this.arrivalTime = new Date();
        }
    }
}

public void displayCustomerData(double carPrice, double washPrice) {
    DecimalFormat currencyFormatter = new DecimalFormat("Rp #,###.00");
    System.out.println("Nama Pencucian: " + transaction.getCarWashName());
    System.out.println("========== Struk Pembayaran ==========");
    System.out.println("Nama Pemesan: " + (this.name != null ? this.name : "Belum diatur"));
    System.out.println("Jenis Mobil: " + (this.carType != null ? this.carType : "Belum diatur"));
    System.out.println("Jenis Pencucian: " + (this.washType != null ? this.washType : "Belum diatur"));
    System.out.println("Harga per Unit Mobil: " + (this.carType != null ? currencyFormatter.format(carPrice) : "Belum diatur"));
    System.out.println("Harga Pencucian: " + (this.washType != null ? currencyFormatter.format(washPrice) : "Belum diatur"));
    System.out.println("=====================================");
    double totalBeforeTax = carPrice + washPrice;
    double totalAfterTax = totalBeforeTax * (1 + TAX_RATE);
    System.out.println("Total Harga (Sebelum Pajak): " + (this.carType != null && this.washType != null ? currencyFormatter.format(totalBeforeTax) : "Belum diatur"));
    System.out.println("Total Harga (Setelah Pajak): " + (this.carType != null && this.washType != null ? currencyFormatter.format(totalAfterTax) : "Belum diatur"));

    int pointsEarned = calculatePoints(totalBeforeTax);
    System.out.println("Poin yang Diperoleh: " + (this.carType != null && this.washType != null ? String.valueOf(pointsEarned) : "Belum diatur"));
    System.out.println("=====================================");
    int totalPoints = pointsEarned + this.points;
    int freeWashes = calculateFreeWashes();  
    System.out.println("Pencucian Gratis Diperoleh: " + (this.carType != null && this.washType != null ? String.valueOf(freeWashes) : "Belum diatur"));

  
    System.out.println("Ingin menukarkan poin untuk pencucian gratis? (y/n)");
    Scanner scanner = new Scanner(System.in);
    String choice = scanner.nextLine().trim().toLowerCase();
    if (choice.equals("y")) {
        redeemFreeWash(freeWashes);  
    }

    System.out.println("Poin setelah transaksi: " + (totalPoints - (freeWashes * 100)));  
    System.out.println("=====================================");
    System.out.println("Pencucian Gratis Diperoleh: " + (this.carType != null && this.washType != null ? String.valueOf(freeWashes) : "Belum diatur"));
    System.out.println("=====================================");
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    System.out.println("Waktu Kedatangan: " + (this.arrivalTime != null ? this.arrivalTime.toString() : "Belum diatur")); 
    System.out.println("Penyedia Jasa: " + this.carWashName);
    System.out.println("Alamat: " + this.address);
    System.out.println("Nomor Telepon: " + this.phoneNumber);
    System.out.println("Email: " + this.email);
    System.out.println("=====================================");
    System.out.println("Terimakasih telah menggunakan jasa kami " + (this.name != null ? this.name : "Belum diatur"));
    System.out.println("Budi&Wawan Car Wash");
    System.out.println("//          _  _    _             _                             __         _               _              \r\n" + 
    "//    __ _ (_)| |_ | |__   _   _ | |__    _ __    __ _  _   _  / _|  __ _ | | _ __   _ __ | |_  _ __ ___  \r\n" + 
    "//   / _` || || __|| '_ \\ | | | || '_ \\  | '_ \\  / _` || | | || |_  / _` || || '_ \\ | '__|| __|| '_ ` _ \\ \r\n" + 
    "//  | (_| || || |_ | | | || |_| || |_) | | | | || (_| || |_| ||  _|| (_| || || |_) || |   | |_ | | | | | |\r\n" + 
    "//   \\__, ||_| \\__||_| |_| \\__,_||_.__/  |_| |_| \\__,_| \\__,_||_|   \\__,_||_|| .__/ |_|    \\__||_| |_| |_|\r\n" + 
    "//   |___/                                                                   |_|                          ");
}

public void redeemFreeWash(int numberOfFreeWashes) {
    if (numberOfFreeWashes > 0) {
        System.out.println("Anda menukarkan " + numberOfFreeWashes + " pencucian gratis!");
        this.points -= numberOfFreeWashes * 100;  
    } else {
        System.out.println("Anda tidak memiliki cukup poin untuk menukarkan pencucian gratis.");
    }
}
    
public int calculatePoints(double totalPrice) {
    return (int) (totalPrice / 100000) * 10;  
}
    public String getCarType() {
        return this.carType;
    }
    
    public String getWashType() {
        return this.washType;
    }
    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }
    public void setCarType(String carType) {
        this.carType = carType;
    }
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getName() {
        return this.name;
    }
    public void deductPoints(int pointsToDeduct) {
        this.points -= pointsToDeduct;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public int getPoints() {
        return this.points;
    }
    public void addPoints(int points) {
        this.points += points;
    }
    public void resetState() {
        this.carType = null;
        this.washType = null;
        this.totalPrice = 0.0;
       
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public void setCarWashName(String carWashName) {
        this.carWashName = carWashName;
    }

    public String getCarWashName() {
        return this.carWashName;
    }
    public void setName(String name) {
            this.name = name;
        }
    public int calculateFreeWashes() {
            return this.points / 100;  
        }
    public Customer(Transaction transaction) {
        this.transaction = transaction;
        this.points = 0;
    }
}
