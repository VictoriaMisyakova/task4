package task4;

import com.github.javafaker.Faker;
import com.univocity.parsers.csv.CsvFormat;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;
import java.security.SecureRandom;
import java.util.Locale;

public class task4 {
    private static final String[] countriesUS = {"US", "USA", "United States", "United States of America"};
    private static final String[] countriesRU = {"Россия", "Российская Федерация", "РФ"};
    private static final String[] countriesBY = {"РБ", "Рэспубліка Беларусь", "Беларусь"};

    private static String[] getListOfCountryNames(String localeName) {
        switch (localeName) {
            case "en_US":
                return countriesUS;
            case "ru_RU":
                return countriesRU;
            case "by_BY":
                return countriesBY;
            default:
                System.out.println("Please, choose between en_US, ru_RU and be_BY");
                System.exit(0);
        }
        return null;
    }

    public static void main(String[] args) {
        if (args[1].equals("be_BY"))
            args[1] = "by_BY";
        CsvWriterSettings settings = new CsvWriterSettings();
        CsvFormat format = new CsvFormat();
        format.setDelimiter(';');
        settings.setFormat(format);
        CsvWriter writer = new CsvWriter(System.out, settings);
        Faker faker = new Faker(new Locale(args[1]));
        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            String[] listOfCountryNames = getListOfCountryNames(args[1]);
            SecureRandom random = new SecureRandom();
            writer.writeRow(faker.name().fullName(),
                    listOfCountryNames[random.nextInt(listOfCountryNames.length)],
                    faker.address().state(),
                    faker.address().city(),
                    faker.address().streetAddress(),
                    faker.address().zipCode(),
                    faker.phoneNumber().phoneNumber());
        }
        writer.close();
    }
}
