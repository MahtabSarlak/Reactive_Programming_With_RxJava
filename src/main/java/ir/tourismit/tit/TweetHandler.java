package ir.tourismit.tit;

import com.github.kongpf8848.rx.observables.MathObservable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class TweetHandler {
    private final Observable<Tweet> tweetObservable;

    public TweetHandler(Observable<Tweet> tweetObservable) {
        this.tweetObservable = tweetObservable;
    }


    public void emitAuthorsName(Hashtag hashtag) {
        Observable<Author> authorObservable = tweetObservable.filter(tweet -> tweet.hashtags.contains(hashtag)).map(tweet -> tweet.author);
        Observer<Author> observer = new Observer<Author>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Author s) {
                System.out.println(s.name);
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("onError" + e);
            }

            @Override
            public void onComplete() {
                System.out.println("Complete!");
            }
        };
        authorObservable.subscribe(observer);
    }

    public void emitNumberOfTweets() {
        Observable<Integer> numberOfTweetObservable = tweetObservable.map(tweet -> 1);
        MathObservable.sumInteger(numberOfTweetObservable).subscribe(integer -> System.out.println("Number of tweets is: " + integer));
    }
}
