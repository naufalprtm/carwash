public class Transaction {
    private double sedanPrice = 50000.0;
    private double suvPrice = 80000.0;
    private double hatchbackPrice = 40000.0;
    private double truckPrice = 100000.0;
    private double minivanPrice = 70000.0;  
    private double sportsCarPrice = 150000.0;  
    private double discount = 0.5;
    private double basicWashPrice = 30000.0;
    private double standardWashPrice = 50000.0; 
    private double premiumWashPrice = 100000.0;
    private double taxRate = 0.5;  
    private String carWashName = "Budi&Wawan Car Wash";
    
    public String[] getCarTypes() {
        return new String[]{"Sedan", "SUV", "Hatchback", "Truck", "Minivan", "Sports Car"};
    }


    public double calculateTotalPrice(String carType) {
        double price = 0.0;

        if (carType.equalsIgnoreCase("sedan")) {
            price = sedanPrice;
        } else if (carType.equalsIgnoreCase("suv")) {
            price = suvPrice;
        } else if (carType.equalsIgnoreCase("hatchback")) {
            price = hatchbackPrice;
        } else if (carType.equalsIgnoreCase("truck")) {
            price = truckPrice;
        } else if (carType.equalsIgnoreCase("minivan")) {
            price = minivanPrice;
        } else if (carType.equalsIgnoreCase("sports car")) {
            price = sportsCarPrice;
        }

        return price;
    }
    public String[] getWashTypes() {
        return new String[]{"Pencucian Dasar", "Pencucian Standar", "Pencucian Premium"};
    }

    public void displayPriceTable() {
        System.out.println("//          _  _    _             _                             __         _               _              \r\n" + 
        "//    __ _ (_)| |_ | |__   _   _ | |__    _ __    __ _  _   _  / _|  __ _ | | _ __   _ __ | |_  _ __ ___  \r\n" + 
        "//   / _` || || __|| '_ \\ | | | || '_ \\  | '_ \\  / _` || | | || |_  / _` || || '_ \\ | '__|| __|| '_ ` _ \\ \r\n" + 
        "//  | (_| || || |_ | | | || |_| || |_) | | | | || (_| || |_| ||  _|| (_| || || |_) || |   | |_ | | | | | |\r\n" + 
        "//   \\__, ||_| \\__||_| |_| \\__,_||_.__/  |_| |_| \\__,_| \\__,_||_|   \\__,_||_|| .__/ |_|    \\__||_| |_| |_|\r\n" + 
        "//   |___/                                                                   |_|                          ");

        
        System.out.println("============= Daftar Harga Pencucian =============");
        System.out.printf("| %-12s | %-10s |\n", "Car Type", "Price");
        System.out.println("|--------------|------------|");
        System.out.printf("| %-12s | Rp %.2f   |\n", "Sedan", sedanPrice);
        System.out.printf("| %-12s | Rp %.2f   |\n", "SUV", suvPrice);
        System.out.printf("| %-12s | Rp %.2f   |\n", "Hatchback", hatchbackPrice);
        System.out.printf("| %-12s | Rp %.2f   |\n", "Truck", truckPrice);
        System.out.printf("| %-12s | Rp %.2f   |\n", "Minivan", minivanPrice);
        System.out.printf("| %-12s | Rp %.2f   |\n", "Sports Car", sportsCarPrice);
        System.out.println("======================================");
    }
        public void displayWashingTypes() {
        System.out.println("============= Daftar Harga Jenis Pencucian =============");
        System.out.printf("| %-20s | %.2f       |\n", "Basic Wash", basicWashPrice);
        System.out.printf("| %-20s | %.2f       |\n", "Standard Wash", standardWashPrice);
        System.out.printf("| %-20s | %.2f       |\n", "Premium Wash", premiumWashPrice);
        System.out.println("======================================");
    }

    public double calculateWashPrice(String washType) {
        double price = 0.0;

        if (washType.equalsIgnoreCase("Pencucian Dasar")) {
            price = basicWashPrice;
        } else if (washType.equalsIgnoreCase("Pencucian Standar")) {
            price = standardWashPrice;
        } else if (washType.equalsIgnoreCase("Pencucian Premium")) {
            price = premiumWashPrice;
        }

        return price;
    }

    public String getCarWashName() {
        return carWashName;
    }

    public double calculateDiscountedPrice(double totalPrice) {
        double totalPriceWithTax = totalPrice * (1 + taxRate);  
        return totalPriceWithTax - (totalPriceWithTax * discount);
    }
}
