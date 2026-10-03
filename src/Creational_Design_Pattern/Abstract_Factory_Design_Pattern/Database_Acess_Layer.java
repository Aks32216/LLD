package Creational_Design_Pattern.Abstract_Factory_Design_Pattern;

/*
Problem Statement:
Design a data-acess library that supports multiple databases (MySQL, PostgreSQL). Each
database needs its own Connection, Command, ResultSet implementation, and these
must always come from same database (a MySQL Conncection must never be used with a PostgreSQL Command)
The application chooses its database from config at startup and then works only against the common interfaces.
Design the creation layer, and explain what it costs to later add a new Product type like Transaction to every
database.
*/

import java.util.Scanner;

interface Connection{
    public void connect();
}

class MySQLConnection implements Connection{
    @Override
    public void connect() {
        System.out.println("Connected to MySQL DB");
    }
}

class PostgreSQLConnection implements Connection{
    @Override
    public void connect() {
        System.out.println("Connected to PostgreSQL DB");
    }
}

interface Command{
    public void execute();
}

class MySQLCommand implements Command{
    @Override
    public void execute() {
        System.out.println("Executing in MySQL DB");
    }
}

class PostgreSQLCommand implements Command{
    @Override
    public void execute() {
        System.out.println("Executing in PostgreSQL DB");
    }
}

interface ResultSet{
    public void result();
}

class MySQLResultSet implements ResultSet{
    @Override
    public void result() {
        System.out.println("Results in MySQL DB");
    }
}

class PostgreSQLResultSet implements ResultSet{
    @Override
    public void result() {
        System.out.println("Results in PostgreSQL DB");
    }
}

abstract class DB{
    abstract Connection createConnection();
    abstract Command createCommand();
    abstract ResultSet createResultSet();

    public void connect(){
        Connection connection=createConnection();
        connection.connect();
    }

    public void execute(){
        Command command=createCommand();
        command.execute();
    }

    public void result(){
        ResultSet resultSet=createResultSet();
        resultSet.result();
    }
}

class MySQLDB extends DB{

    @Override
    public Connection createConnection() {
        return new MySQLConnection();
    }

    @Override
    public Command createCommand() {
        return new MySQLCommand();
    }

    @Override
    public ResultSet createResultSet() {
        return new MySQLResultSet();
    }
}

class PostgreSQLDB extends DB{

    @Override
    public Connection createConnection() {
        return new PostgreSQLConnection();
    }

    @Override
    public Command createCommand() {
        return new PostgreSQLCommand();
    }

    @Override
    public ResultSet createResultSet() {
        return new PostgreSQLResultSet();
    }
}

public class Database_Acess_Layer {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        String db_type=scn.nextLine();
        DB db;
        switch (db_type){
            case "postgres": db=new PostgreSQLDB(); break;
            case "mysql": db=new MySQLDB(); break;
            default: throw new IllegalArgumentException();
        }
        db.connect();
        db.execute();
        db.result();

    }
}
