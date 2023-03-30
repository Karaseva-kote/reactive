package dao;

import com.mongodb.rx.client.MongoClient;
import com.mongodb.rx.client.MongoClients;
import com.mongodb.rx.client.MongoCollection;
import com.mongodb.rx.client.Success;
import model.Product;
import model.User;
import org.bson.Document;
import rx.Observable;

import java.util.Map;

public class MongoDao {
    private static final MongoClient client = MongoClients.create("mongodb://localhost:27017");
    private static final MongoCollection<Document> users = client.getDatabase("shop").getCollection("user");
    private static final MongoCollection<Document> products = client.getDatabase("shop").getCollection("product");

    public static Observable<Success> addUser(String name, String currency) {
        return users.insertOne(new Document(Map.of("name", name, "currency", currency)));
    }

    public static Observable<Success> addProduct(String name, double price) {
        return products.insertOne(new Document(Map.of("name", name, "price", price)));
    }

    public static Observable<Product> getProducts() {
        return products.find().toObservable().map(Product::new);
    }

    public static Observable<User> getUserByName(String name) {
        return users.find(new Document(Map.of("name", name))).toObservable().map(User::new);
    }
}
