package CH15;

public class Boundary {
    Human<Basketball> h = new Human<>();
}

interface Working{

}

interface Sports extends Working{
    
}

interface Ball extends Sports{
    
}

class Basketball implements Ball{

}

class Human<T extends Working>{

}

class Athlete<T extends Sports> extends Human<T>{

}

class BasketballPlayer<T extends Basketball> extends Athlete<T>{    //可以extend interface

}

class H2<T extends Human>{}

//class H3<T extends BasketballPlayer> extends Human<T>{}     //不可以extend class
