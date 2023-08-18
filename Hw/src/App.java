import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        try {
            String data = getUserData();
            String[] values = data.split(" ");

            if (values.length != 6) {
                throw new IllegalArgumentException("Неверное количество данных");
            }

            String surname = values[0];
            String firstName = values[1];
            String middleName = values[2];

            LocalDate birthDate = parseDate(values[3]);

            long phoneNumber = parsePhoneNumber(values[4]);
            if (phoneNumber == -1) {
                throw new IllegalArgumentException("Неверный формат номера телефона");
            }

            char gender = parseGender(values[5]);
            if (gender == '\0') {
                throw new IllegalArgumentException("Неверное значение пола");
            }

            String fileName = surname + ".txt";
            String output = surname + firstName + middleName + birthDate + " " + phoneNumber + gender;

            writeToFile(fileName, output);
            System.out.println("Данные успешно записаны в файл " + fileName);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка ввода данных: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String getUserData() {
        // Здесь можно использовать Scanner или другой способ для получения данных от пользователя
        // В данном примере для упрощения, данные хранятся в этой функции
        return "Иванов Иван Иванович 01.01.2000 1234567890 m";
    }

    private static LocalDate parseDate(String value) {
        String[] dateParts = value.split("\\.");
        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);

        return LocalDate.of(year, month, day);
    }

    private static long parsePhoneNumber(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static char parseGender(String value) {
        if (value.length() == 1 && (value.equals("m") || value.equals("f"))) {
            return value.charAt(0);
        }
        return '\0';
    }

    private static void writeToFile(String fileName, String content) throws IOException {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(content + "\n");
        }
    }
}