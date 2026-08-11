package solid.DIP;

class MySQLDatabases {  // Low-level module
    public void saveToSQL(String data) {
        System.out.println(
                "Executing SQL Query: INSERT INTO users VALUES('"
                        + data + "');"
        );
    }
}

class MongoDBDatabases {  // Low-level module
    public void saveToMongo(String data) {
        System.out.println(
                "Executing MongoDB Function: db.users.insert({name: '"
                        + data + "'})"
        );
    }
}

class UserServices {  // High-level module (Tightly coupled)
    private final MySQLDatabases sqlDb = new MySQLDatabases();
    private final MongoDBDatabases mongoDb = new MongoDBDatabases();

    public void storeUserToSQL(String user) {
        // MySQL-specific code
        sqlDb.saveToSQL(user);
    }

    public void storeUserToMongo(String user) {
        // MongoDB-specific code
        mongoDb.saveToMongo(user);
    }
}

public class DIPViolated {
    public static void main(String[] args) {
        UserServices service = new UserServices();
        service.storeUserToSQL("Sourav");
        service.storeUserToMongo("Kumar");
    }
}

