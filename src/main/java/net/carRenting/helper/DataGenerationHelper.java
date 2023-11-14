package net.carRenting.helper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DataGenerationHelper {

    // Customer

    private static final String[] names = { "Mónica", "José Antonio", "Laura", "Lucas", "Eva", "Eloy", "Jesús", "Alan",
            "Pablo", "Paula", "Raquel", "Nieves", "Elena", "Sergio", "Jaime", "Fernando", "Rafael" };

    private static final String[] surnames = {
            "Alcañiz", "Puig", "Ayala", "Farell", "Ferrer", "Esteve", "González", "Rozalén", "Lara", "Velarte",
            "Latorre", "Briones", "Maldonado", "Suárez", "McLure", "Alarcón", "Molero", "Marín", "Muñoz", "García",
            "Navarro", "López", "Navas", "Aguilar", "Ortega", "Fabra", "Romero", "Díaz", "Cano", "Roselló", "Gómez",
            "Serrano", "Quilez", "Aznar", "Aparici"
    };

    private static final String[] phonePrefixes = { "600", "601", "602", "603", "604", "605", "606", "607", "608",
            "609" };

    private static final String[] domains = { "gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "example.com" };

    private static final String[] streetNames = { "Main", "Oak", "Maple", "Elm", "Cedar", "Pine", "Birch", "Willow",
            "Holly", "Poplar" };
    private static final String[] streetTypes = { "St", "Ave", "Blvd", "Dr", "Ln", "Rd", "Ct", "Pl" };

    private static final String[] cities = { "New York", "Los Angeles", "Chicago", "Houston", "Phoenix", "Philadelphia",
            "San Antonio", "Dallas", "San Diego", "San Jose" };

    private static final String[] provinces = { "California", "Texas", "Florida", "New York", "Pennsylvania",
            "Illinois", "Ohio", "Georgia", "North Carolina", "Michigan" };

    private static final String[] countries = { "United States", "Canada", "United Kingdom", "Germany", "France",
            "Japan", "China", "India", "Brazil", "Australia" };

    private static final String[] usernames = {
            "user123", "coolCoder", "gamerGirl", "javaMaster", "codingWizard",
            "dataGeek", "techExplorer", "webDevPro", "codeNinja", "byteBender",
            "codeSlinger", "javaJunkie", "devGuru", "bugHunter", "debuggerKing",
            "codeCruncher", "scriptHero", "codeWarrior", "pixelPirate", "cryptoCoder",
    };

    public static String getRandomName() {
        return names[(int) (Math.random() * names.length)];
    }

    public static String getRandomSurname() {
        return surnames[(int) (Math.random() * surnames.length)];
    }

    public static String getRandomPhoneNumber() {
        return phonePrefixes[(int) (Math.random() * phonePrefixes.length)] + generateRandomNumericString(7);
    }

    public static String getRandomEmail() {
        return getRandomUsername() + "@" + domains[(int) (Math.random() * domains.length)];
    }

    public static String getRandomAddress() {
        return generateRandomNumericString(3) + " " + streetNames[(int) (Math.random() * streetNames.length)] + " "
                + streetTypes[(int) (Math.random() * streetTypes.length)];
    }

    public static String getRandomCity() {
        return cities[(int) (Math.random() * cities.length)];
    }

    public static String getRandomProvince() {
        return provinces[(int) (Math.random() * provinces.length)];
    }

    public static String getRandomPostalCode() {
        return generateRandomNumericString(5);
    }

    public static String getRandomCountry() {
        return countries[(int) (Math.random() * countries.length)];
    }

    public static int getRandomInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public static String getRandomUsername() {
        return usernames[(int) (Math.random() * usernames.length)];
    }

    public static LocalDateTime getRadomDate() {
        long minDay = LocalDate.of(2020, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2023, 10, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay).atTime(getRandomInt(0, 23), getRandomInt(0, 59), getRandomInt(0, 59));
    }

    private static String generateRandomNumericString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char) ('0' + (int) (Math.random() * 10)));
        }
        return sb.toString();
    }

    // Car

    private static final String[] carBrands = { "Toyota", "Ford", "Honda", "Chevrolet", "Volkswagen", "Nissan",
            "Hyundai", "BMW", "Mercedes-Benz", "Audi" };
    private static final String[] carModels = { "Camry", "Fusion", "Civic", "Malibu", "Jetta", "Altima", "Elantra",
            "X5", "E-Class", "A4" };
    private static final String[] transmissions = { "Automatic", "Manual" };
    private static final String[] fuels = { "Gasoline", "Diesel", "Electric", "Hybrid" };
    private static final int[] doors = { 2, 4 };
    private static final int[] seats = { 2, 4, 5 };
    private static final String[] colors = { "Red", "Blue", "Black", "White", "Silver", "Gray", "Green", "Yellow",
            "Orange", "Brown" };
    private static final String[] carImages = { "car1.jpg", "car2.jpg", "car3.jpg", "car4.jpg", "car5.jpg" };

    private static Random random = new Random();

    public static String getRandomCarBrand() {
        return carBrands[(int) (Math.random() * carBrands.length)];
    }

    public static String getRandomCarModel() {
        return carModels[(int) (Math.random() * carModels.length)];
    }

    public static int getRandomCarYear() {
        int currentYear = Year.now().getValue();
        return 1930 + (int) (Math.random() * (currentYear - 1930 + 1));
    }

    public static String getRandomTransmission() {
        return transmissions[(int) (Math.random() * transmissions.length)];
    }

    public static String getRandomFuel() {
        return fuels[(int) (Math.random() * fuels.length)];
    }

    public static int getRandomDoors() {
        return doors[(int) (Math.random() * doors.length)];
    }

    public static int getRandomSeats() {
        return seats[(int) (Math.random() * seats.length)];
    }

    public static String getRandomColor() {
        return colors[(int) (Math.random() * colors.length)];
    }

    public static int getRandomHorsePower() {
        return 40 + (int) (Math.random() * (1200 - 40 + 1));
    }

    public static String getRandomImage() {
        // Obtén un índice aleatorio para seleccionar una imagen de coche del array
        int randomIndex = random.nextInt(carImages.length);
        return carImages[randomIndex];
    }

    // Rental

    private static final String[] locations = { "Airport", "Downtown", "Suburb", "Train Station", "Hotel" };

    public static LocalDateTime getRandomPickupDate() {
        int daysToAdd = (int) (Math.random() * 14); // Para una fecha aleatoria en los próximos 14 días
        return LocalDateTime.now().plusDays(daysToAdd);
    }

    public static LocalDateTime getRandomDropoffDate(LocalDateTime pickupDate) {
        int daysToAdd = 1 + (int) (Math.random() * 30); // Para una fecha aleatoria entre 1 y 30 días después de la
                                                        // fecha de recogida
        return pickupDate.plusDays(daysToAdd);
    }

    public static String getRandomPickupLocation() {
        return locations[(int) (Math.random() * locations.length)];
    }

    public static String getRandomDropoffLocation() {
        return locations[(int) (Math.random() * locations.length)];
    }

    public static float getRandomCost() {
        return 50 + (float) (Math.random() * 450); // Para un costo aleatorio entre 50 y 500
    }
}
