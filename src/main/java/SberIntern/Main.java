package SberIntern;

import java.sql.*;
import java.util.Calendar;


public class Main {
    static double[] t = new double[5];

    public static void main(String[] args) throws ClassNotFoundException {



        String url =  "jdbc:mysql://localhost:3306/test?useUnicode=true&useSSL=true&use" +
                "JDBCCompliantTimezoneShift=true" +
                "&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "user";
        String password = "user";

        Class.forName("com.mysql.cj.jdbc.Driver");

        try(Connection connection = DriverManager.getConnection(url, username,password);

            Statement statement = connection.createStatement()){

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from weektomonths ");

            ResultSet res = preparedStatement.executeQuery();

            while (res.next()){
                Date date  = res.getDate("weekdate");

                Calendar cal = Calendar.getInstance();
                cal.setTime(date);

                double sum = res.getInt("summa");

                for(int i=0; i<5; i++){

                    cal.add(Calendar.DAY_OF_YEAR, i);
                    for(int k=0; k<5;k++){
                        if( cal.get(Calendar.MONTH) == k+1){
                            t[k] = t[k] + sum/5;
                        }
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e){

        }
        for(int i=0; i<t.length;i++){
            System.out.println(t[i]);
        }


    }
}
