package ir.tourismit.tit;

import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.List;


public class Main {

    static final Hashtag RXJAVA = new Hashtag("#rxjava");

    public static void main(String[] args) {
        TweetHandler tweetHandler = new TweetHandler(createObservable());
        System.out.println("First, it prints the authors of the tweets that have #rxjava and then prints the number of all tweets.");
        tweetHandler.emitAuthorsName(RXJAVA); //This function is asynchronous
        tweetHandler.emitNumberOfTweets();//This function is asynchronous
    }

    private static List<Tweet> sampleTweets() {
        List<Tweet> tweetList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Tweet tweet;
            if (i % 4 == 0)
                tweet = new Tweet(new Author("author name is: RxJava" + i), System.currentTimeMillis(), "tweet" + i + " #rxjava");
            else
                tweet = new Tweet(new Author("author name is: akka" + i), System.currentTimeMillis(), "tweet" + i + " #akka");
            tweetList.add(tweet);
        }
        return tweetList;
    }

    private static Observable<Tweet> createObservable() {
        return Observable.fromIterable(sampleTweets());
    }
}
