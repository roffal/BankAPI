import java.sql.*;

public class Main {

    // Адрес БД => Протокол : Производитель : Расположение СУБД (для сетевых указать имя или IP удаленного сервер, номера портов
    public static final String DB_URL = "jdbc:h2:/Users/iyakozlina/SBER/ProjectTest/src/Main.java";
    // Имя драйвера
    public static final String DB_Driver = "org.h2.Driver";

    public static void main(String[] args) {
        try {
            //загрузка драйвера, его регистрация с помощью DriverManager
            Class.forName(DB_Driver);

            Connection connection = DriverManager.getConnection(DB_URL);
//соединениесБД

            System.out.println("Соединение с СУБД выполнено.");
            connection.close();
// отключение от БД

            System.out.println("Отключение от СУБД выполнено.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
// обработка ошибки  Class.forName

            System.out.println("JDBC драйвер для СУБД не найден!");
        } catch (SQLException e) {
            e.printStackTrace();
// обработка ошибок  DriverManager.getConnection

            System.out.println("Ошибка SQL !");
        }
    }
}