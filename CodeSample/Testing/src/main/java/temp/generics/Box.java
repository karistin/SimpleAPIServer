package temp.generics;

/*
* 제너릭 왜 사용? Object 쓰지
* 컴파일시 타임에 객체의 타입을 체크할 수 있다.
* */
public class Box <M, T>{
//    제너릭 클래스
    /*
    *
    * T : type
    * E : Elemnet
    * K : Key
    * V : Value
    * N : Number
    * */
    private M material;
    private T item;


    public M getMaterial() {
        return material;
    }

    public void setMaterial(M material) {
        this.material = material;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }
}
