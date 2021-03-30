package customer;

public class Customer {

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private int age;

    private int x;

    private int y;
    public Customer(int id, String name, int age , int x, int y) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.x = x;
        this.y = y;
    }

    public Customer(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
