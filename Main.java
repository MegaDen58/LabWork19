package com.company;
import java.util.ArrayList;
import java.util.List;

interface Observable {
    void notifyObservers();
    void regObserver(Observer obs);
}

interface Observer{
    void update(String news);
}

class Group implements Observable{
    List<Observer> list = new ArrayList<>();
    String info;
    void setInfo(String info){
        this.info = info;
        notifyObservers();
    }

    @Override
    public void notifyObservers() {
        for(Observer obs: list){
            obs.update(info);
        }
    }

    @Override
    public void regObserver(Observer o) {
        list.add(o);
    }
}
class User implements Observer{
    String name;
    public User(String name){
        this.name = name;
    }

    @Override
    public void update(String info) {
        System.out.println(name + " получил новость от сообщества "+ info + "\n======================");
    }
}

public class Main {
    public static void main(String[] args) {
        Group csgo = new Group();
        Group memes = new Group();
        Group sport = new Group();
        Group news = new Group();
        User vasya = new User("Вася");
        User dima = new User("Дима");
        // Подписываем Васю на 3 группы
        csgo.regObserver(vasya);
        memes.regObserver(vasya);
        news.regObserver(vasya);
        // Подписываем Диму на 4 группы
        csgo.regObserver(dima);
        news.regObserver(dima);
        sport.regObserver(dima);
        memes.regObserver(dima);

        csgo.setInfo("CSGO\nБумыча кикнули из нави :(");
        memes.setInfo("MEMES\nШтирлиц пришел на встречу со связным в знакомый бар и заказал\n" +
                "100 грамм водки.\n" +
                "- Водка у нас кончилась еще два дня назад, - извинился бармен.\n" +
                "- Ну, тогда 100 грамм коньячку.\n" +
                "- Коньячок у нас кончился вчера, - огорченно сказал бармен.\n" +
                "- Ну, а пиво-то есть? - спросил Штирлиц.\n" +
                "- Увы, закончилось сегодня утром, - сказал бармен.\n" +
                "\"Значит, связной уже здесь\", - подумал Штирлиц...\n");
        news.setInfo("NEWS\nКолобок повесился");
        sport.setInfo("SPORT\nСпорт - жестокая игра");
    }
}
