package net.carRenting.helper;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.Random;

public class DataGenerationHelper {

    // User

    private static final String[] names = { "Mónica", "José Antonio", "Laura", "Lucas", "Eva", "Eloy", "Jesús", "Alan",
            "Pablo", "Paula", "Raquel", "Nieves", "Elena", "Sergio", "Jaime", "Fernando", "Rafael" };

    private static final String[] surnames = {
            "Alcañiz", "Puig", "Ayala", "Farell", "Ferrer", "Esteve", "González", "Rozalén", "Lara", "Velarte",
            "Latorre", "Briones", "Maldonado", "Suárez", "McLure", "Alarcón", "Molero", "Marín", "Muñoz", "García",
            "Navarro", "López", "Navas", "Aguilar", "Ortega", "Fabra", "Romero", "Díaz", "Cano", "Roselló", "Gómez",
            "Serrano", "Quilez", "Aznar", "Aparici"
    };

    private static final String[] domains = { "gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "example.com" };

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

    public static String getRandomEmail() {
        return getRandomUsername() + "@" + domains[(int) (Math.random() * domains.length)];
    }

    public static int getRandomInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public static String getRandomUsername() {
        return usernames[(int) (Math.random() * usernames.length)];
    }

    // Car

    private static final String[] carBrands = { "Toyota", "Ford", "Honda", "Chevrolet", "Volkswagen", "Nissan",
            "Hyundai", "BMW", "Mercedes-Benz", "Audi" };
    private static final String[] carModels = { "Camry", "Fusion", "Civic", "Malibu", "Jetta", "Altima", "Elantra",
            "X5", "E-Class", "A4" };


    public static String getRandomCarBrand() {
        return carBrands[(int) (Math.random() * carBrands.length)];
    }

    public static String getRandomCarModel() {
        return carModels[(int) (Math.random() * carModels.length)];
    }

    public static Integer getRandomCarYear() {
        int currentYear = Year.now().getValue();
        return 1930 + (int) (Math.random() * (currentYear - 1930 + 1));
    }

    // Rental

    public static LocalDateTime getRandomStartDate() {
        int daysToAdd = (int) (Math.random() * 14); // Para una fecha aleatoria en los próximos 14 días
        return LocalDateTime.now().plusDays(daysToAdd);
    }

    public static LocalDateTime getRandomEndDate(LocalDateTime startDate) {
        int daysToAdd = 1 + (int) (Math.random() * 30); // Para una fecha aleatoria entre 1 y 30 días después de la
                                                        // fecha de recogida
        return startDate.plusDays(daysToAdd);
    }

    public static Double getRandomPrice() {
        return 30 + (double) (Math.random() * 170); // Para un costo aleatorio entre 30 y 200
    }
}
