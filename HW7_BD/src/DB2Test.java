import javax.management.StringValueExp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class DB2Test {

    private static Scanner input = new Scanner(System.in);
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;


    public void openConnection()
    {
        try {
            DriverManager.registerDriver(new com.ibm.db2.jcc.DB2Driver());
        }
        catch(Exception cnfex) {
            System.out.println("Problem in loading or registering IBM DB2 JDBC driver");
            cnfex.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection("jdbc:db2://62.44.108.24:50000/SAMPLE", "db2admin", "db2admin");
            statement = connection.createStatement();
        }
        catch(SQLException s){
            s.printStackTrace();
        }
    }

    public void closeConnection(){
        try {
            if(null != connection) {
                resultSet.close();
                statement.close();
                connection.close();
            }
        }
        catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public void select(String stmnt, int column) {

        try{

            resultSet = statement.executeQuery(stmnt);

            String result = "";

            while(resultSet.next()) {

                for (int i = 1; i <= column; i++) {

                    result += resultSet.getString(i);

                    if (i == column) result += " \n";
                    else             result += ", ";
                }
            }

            System.out.println("Обработка : " + stmnt + "\n");
            System.out.println("Резултат:  \n");
            System.out.println("---------------------------------- \n");
            System.out.println(result);

        }
        catch (SQLException s)
        {
            s.printStackTrace();
        }

    }

    public void insert(String stmnt) {

        try{

            statement.executeUpdate(stmnt);

        }

        catch (SQLException s){

            s.printStackTrace();

        }

        System.out.println("Успешно добавено!");

    }


    public void delete(String stmnt) {

        try{

            statement.executeUpdate(stmnt);

        }

        catch (SQLException s){

            s.printStackTrace();

        }

        System.out.println("Успешно изтрито!");

    }


    private static void option1(DB2Test db2Obj)
    {
        System.out.print("Изберете дали желаете да търсите по име/номер: ");
        String criteria = input.next();
        if(criteria.equals("име"))
        {
            System.out.print("Име: ");
            String name = input.next();;
            db2Obj.select("SELECT * FROM FN71924.BOOK WHERE BOOK_NAME = " + name, 11);
        }
        else if(criteria.equals("номер"))
        {
            System.out.print("Номер: ");
            String number = input.next();
            db2Obj.select("SELECT * FROM FN71924.BOOK WHERE SERIAL_NUMBER = " + number, 11);
        }
        else
        {
            System.out.println("Всички книги:");
            db2Obj.select("SELECT * FROM FN71924.BOOK", 11);
        }

    }

    private static void option2(DB2Test db2Obj)
    {
        System.out.print("Търсене на книжарница по адрес:  ");
        String address = input.nextLine();
        db2Obj.select("SELECT * FROM FN71924.BOOKSTORE WHERE ADDRESS = '" + address + "'",2);



    }

    private static void option3(DB2Test db2Obj)
    {
        System.out.print("Търсене на служител по номер:  ");
        int number = input.nextInt();
        db2Obj.select("SELECT * FROM FN71924.STAFF WHERE STAFF_NUMBER = " + number, 4);

    }

    private static void option4(DB2Test db2Obj)
    {
        System.out.print("Търсене на клиент по име:  ");
        String name = input.nextLine();
        db2Obj.select("SELECT * FROM FN71924.CLIENT WHERE CLIENT_NAME ='" + name+"'",5);

    }

    private static void option5(DB2Test db2Obj)
    {
            db2Obj.select("SELECT * FROM FN71924.BOOK", 11);

    }

    private static void option6(DB2Test db2Obj)
    {
        db2Obj.select("SELECT * FROM FN71924.STAFF", 4);

    }

    private static void option7(DB2Test db2Obj) {
        String name;
        System.out.print("Име: ");
        name = input.nextLine();

        String phone;
        do {
            System.out.print("Телефонен номер (10 цифри): ");
            phone = input.nextLine();
        } while(!phone.matches("[0-9]{10}"));

        String bookstoreName;
        System.out.print("Локация: ");
        bookstoreName = input.nextLine();

        db2Obj.insert("INSERT INTO FN71924.STAFF(NAME,STAFF_PHONE_NUMBER,BOOKSTORE_NAME)"+" VALUES ('"+name+"','"+phone+"','"+bookstoreName+"')");
    }


    private static void option8(DB2Test db2Obj) {
        String name;
        System.out.print("Име: ");
        name = input.nextLine();


        int discount;
        do {
            System.out.print("Процент намаление: ");
            discount = input.nextInt();
            input.nextLine();
        } while(!(discount>=0 && discount<=100));

        double rating=0.0;

        double price;
        System.out.print("Цена: ");
        price = input.nextDouble();
        input.nextLine();

        int inventory;
        do {
            System.out.print("Наличност: ");
            inventory = input.nextInt();
            input.nextLine();
        } while(!(inventory>=0));

        String publishingHouse;
        System.out.print("Издателство: ");
        publishingHouse = input.nextLine();

        String authorName;
        System.out.print("Име на автор: ");
        authorName = input.nextLine();

        String genre;
        System.out.print("Жанр: ");
        genre = input.nextLine();

        int publishingYear;
        do {
            System.out.print("Година на издаване: ");
            publishingYear = input.nextInt();
            input.nextLine();
        } while(!(publishingYear>=0));


        String bookstoreName;
        System.out.print("Налична в книжарница: ");
        bookstoreName = input.nextLine();

        db2Obj.insert("INSERT INTO FN71924.BOOK(BOOK_NAME,DISCOUNT,RATING,PRICE,INVENTORY_LEFT,PUBLISHING_HOUSE,AUTHOR_NAME,GENRE,PUBLISHING_YEAR,BOOKSTORE_NAME)"+" VALUES ('"+name+"', '"+ discount+"', '"+rating+"', '"+price+"' , '"+ inventory +"' , '"+publishingHouse+"' , '"+authorName+"' , '"+genre+"' , '"+publishingYear+"' , '"+bookstoreName+"')");
    }


    private static void option9(DB2Test db2Obj) {

        String name;
        System.out.print("Име: ");
        name = input.nextLine();

        String address;
        System.out.print("Адрес: ");
        address = input.nextLine();


        db2Obj.insert("INSERT INTO FN71924.BOOKSTORE VALUES ('" + name + "', '" + address+"')");
    }


    private static void option10(DB2Test db2Obj) {
        String name;
        System.out.print("Име: ");
        name = input.nextLine();

        String phone;
        do {
            System.out.print("Телефонен номер (10 цифри): ");
            phone = input.nextLine();
        } while(!phone.matches("[0-9]{10}"));

        String email;
        System.out.print("Email: ");
        email = input.nextLine();

        String bookstoreName;
        System.out.print("Локация: ");
        bookstoreName = input.nextLine();

        db2Obj.insert("INSERT INTO FN71924.CLIENT(CLIENT_NAME,CLIENT_PHONE_NUMBER,EMAIL,BOOKSTORE_NAME)"+" VALUES ('"+name+"','"+phone+"','"+email+"','"+bookstoreName+"')");
    }

    private static void option11(DB2Test db2Obj) {
        int num;
        System.out.print("Номер на служител за уволнение: ");
        num=input.nextInt();
        db2Obj.delete("DELETE FROM FN71924.STAFF WHERE STAFF_NUMBER = '" + num + "'");

    }

    public static void info(DB2Test db2Obj)
    {
        System.out.println("Какво бихте искали да направите?");
        System.out.println("1 - Търсене на книга по име или сериен номер");
        System.out.println("2 - Търсене на книжарница по адрес");
        System.out.println("3 - Търсене на служител по номер");
        System.out.println("4 - Търсене на клиент по име");
        System.out.println("5 - Всички книги");
        System.out.println("6 - Всички служители");
        System.out.println("7 - Наемане на служител");
        System.out.println("8 - Зареждане на книга");
        System.out.println("9 - Отваряне на нов магазин");
        System.out.println("10 - Регистрация на нов клиент");
        System.out.println("11 - Уволнение на служител");
        System.out.println("12 - Изход");

        int optionChosen;
        do
        {
            System.out.print("Избор на опция: ");
            optionChosen = input.nextInt();
            input.nextLine();

            switch(optionChosen)
            {
                case 1:
                {
                    option1(db2Obj);
                    break;
                }
                case 2:
                {
                    option2(db2Obj);
                    break;
                }
                case 3:
                {
                    option3(db2Obj);
                    break;
                }
                case 4:
                {
                    option4(db2Obj);
                    break;
                }
                case 5:
                {
                   option5(db2Obj);
                    break;
                }
                case 6:
                {
                    option6(db2Obj);
                    break;
                }
                case 7:
                {
                    option7(db2Obj);
                    break;
                }
                case 8:
                {
                    option8(db2Obj);
                    break;
                }
                case 9:
                {
                    option9(db2Obj);
                    break;
                }
                case 10:
                {
                    option10(db2Obj);
                    break;
                }
                case 11:
                {
                    option11(db2Obj);
                    break;
                }

            }
        } while(optionChosen != 12);
        System.out.println("Излизане..");

    }

    public static void main(String[] args){

        DB2Test db2Obj = new DB2Test();
        db2Obj.openConnection();
        info(db2Obj);
        db2Obj.closeConnection();

    }

}
   
    
     
   

 