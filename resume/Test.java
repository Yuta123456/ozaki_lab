import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.util.*;
import java.util.List;

abstract class Human {
    public int age;
    public Human(int n){
        age = n;
    }
    //年齢を返すメソッド
    public int getAge(){
        return age;
    }
    public abstract double getGPA();
    public abstract int getKakin();
}
class Bunta extends Human {
    private int money = 250000;
    public Bunta (int n){
        super(n);
    }
    //課金額を返すメソッド
    public int getKakin(){
        return money;
    }

    //GPAを返すメソッド
    public double getGPA () {
        return 2.41;
    }
}
class Hishinuma extends Human {
    public Hishinuma (int n){
        super(n);
    }
    @Override
    public int getAge () {
        return 4;
    }
    //GPAを返すメソッド
    public double getGPA () {
        return 2.28;
    }
}
class Kuroko extends Human {
    public Kuroko (int n){
        super(n);
    }
    //GPAを返すメソッド
    public double getGPA () {
        return 2.45;
    }
}
public class Test {
    public static void main(String args[]){
        Human [ ] data = new Human[ 3 ];
        data[0] = new Bunta(21);
        data[1] = new Hishinuma(20);
        data[2] = new Kuroko(21);
        //example 1
        //わざと拡張for文を用いている。
        //Human型の配列に対して、それぞれgetAgeを呼び出す。
        for(Human human : data){
            System.out.println(human.getAge());
        }
        //example 2
        //わざと拡張for文を用いている。
        //Human型の配列に対して、それぞれgetAgeを呼び出す。
        //ここでも、それぞれすこしずつ振る舞いが違うことを注意
        for(Human human : data){
            System.out.println(human.getGPA());
        }
        //example 3
        //わざと拡張for文を用いている。
        //Human型の配列に対して、それぞれgetAgeを呼び出す。
        //エラー
        //System.out.println(data[0].getKakin());

    }
}