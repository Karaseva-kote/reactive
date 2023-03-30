import dao.MongoDao;
import io.reactivex.netty.protocol.http.server.HttpServer;
import rx.Observable;

public class Server {

    public static void main(final String[] args) {
        HttpServer
                .newServer(8080)
                .start((req, resp) -> {
                    String request = req.getDecodedPath().substring(1);
                    String[] list = request.split("/");
                    Observable<String> response = switch (list[0]) {
                        case "signup" -> MongoDao.addUser(list[1], list[2]).map(s -> list[1] + " is sign up");
                        case "add_product" -> MongoDao.addProduct(list[1], Double.parseDouble(list[2]))
                                .map(s -> list[1] + " is added");
                        case "show_products_for" -> MongoDao.getUserByName(list[1])
                                .flatMap(user -> MongoDao.getProducts().map(product -> product.show(user.getCurrency())));
                        default -> Observable.just("error");
                    };
                    return resp.writeString(response.map(s -> s + "\n"));
                })
                .awaitShutdown();
    }
}