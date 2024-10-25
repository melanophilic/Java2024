// Интерфейс для работы с социальными сетями
interface SocialNetwork {
    void connect();
    void publishPost(String message);
}

// Конкретная реализация для Facebook (адаптируемый класс)
class Facebook implements SocialNetwork {
    private String accessToken;

    public Facebook(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public void connect() {
        System.out.println("Подключение к Facebook...");
        System.out.println("Авторизация с токеном: " + accessToken);
        System.out.println("Успешное подключение к Facebook.");
    }

    @Override
    public void publishPost(String message) {
        System.out.println("Facebook: Публикация поста: " + message);
    }
}

// Конкретная реализация для Twitter (адаптируемый класс)
class Twitter {
    private String consumerKey;
    private String consumerSecret;
    private String accessToken;
    private String accessTokenSecret;

    public Twitter(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret) {
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.accessToken = accessToken;
        this.accessTokenSecret = accessTokenSecret;
    }

    public void connectAPI() {
        System.out.println("Подключение к Twitter API...");
        System.out.println("Consumer Key: " + consumerKey);
        System.out.println("Consumer Secret: " + consumerSecret);
        System.out.println("Access Token: " + accessToken);
        System.out.println("Access Token Secret: " + accessTokenSecret);
        System.out.println("Успешное подключение к Twitter API.");
    }

    public void postTweet(String tweet) {
        System.out.println("Twitter: Публикация твита: " + tweet);
    }
}

// Конкретная реализация для Instagram (адаптируемый класс)
class Instagram {
    private String username;
    private String password;

    public Instagram(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void login() {
        System.out.println("Вход в Instagram...");
        System.out.println("Имя пользователя: " + username);
        System.out.println("Пароль: " + password);
        System.out.println("Успешный вход в Instagram.");
    }

    public void uploadPhoto(String photoPath, String caption) {
        System.out.println("Instagram: Загрузка фото: " + photoPath);
        System.out.println("Подпись: " + caption);
    }
}

// Адаптер для Twitter
class TwitterAdapter implements SocialNetwork {
    private Twitter twitter;

    public TwitterAdapter(Twitter twitter) {
        this.twitter = twitter;
    }

    @Override
    public void connect() {
        twitter.connectAPI();
    }

    @Override
    public void publishPost(String message) {
        twitter.postTweet(message);
    }
}

// Адаптер для Instagram
class InstagramAdapter implements SocialNetwork {
    private Instagram instagram;

    public InstagramAdapter(Instagram instagram) {
        this.instagram = instagram;
    }

    @Override
    public void connect() {
        instagram.login();
    }

    @Override
    public void publishPost(String message) {
        instagram.uploadPhoto(message, "Фото из моего приложения");
    }
}

public class Main {
    public static void main(String[] args) {
        // Используем Facebook напрямую
        SocialNetwork facebook = new Facebook("facebook_token");
        facebook.connect();
        facebook.publishPost("Привет из Facebook!");

        System.out.println("-------------------");

        // Используем Twitter через адаптер
        Twitter twitter = new Twitter("consumerKey", "consumerSecret", "accessToken", "accessTokenSecret");
        SocialNetwork twitterAdapter = new TwitterAdapter(twitter);
        twitterAdapter.connect();
        twitterAdapter.publishPost("Привет из Twitter!");

        System.out.println("-------------------");

        // Используем Instagram через адаптер
        Instagram instagram = new Instagram("username", "password");
        SocialNetwork instagramAdapter = new InstagramAdapter(instagram);
        instagramAdapter.connect();
        instagramAdapter.publishPost("photo.jpg");
    }
}
