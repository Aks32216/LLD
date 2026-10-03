package Java;

/*
Enums are predefined set of constants or named constants
*/

enum PaymentStatus{
    SUCCESS,
    FAILED,
    PENDING
}

/*
Internally enums extends Enum and translates into class with static final class instance pointing to same type
since the fields are static so declared once per class. and each value in enum points to unique ref.
and no object can be created as constructor is private.

so this gives unique property to compare enums with their value.
final class PaymentStatus extends Enum<PaymentStatus>{
    public static final PaymentStatus SUCCESS = new PaymentStatus();
    public static final PaymentStatus FAILED = new PaymentStatus();
    public static final PaymentStatus PENDING = new PaymentStatus();

    private PaymentStatus(){
    }
}


enum class extends Enum which provides two methods
- name
- ordinal

but values() and valueOf(String) is compiler generated

values() -> returs a list of ENUM values defined so that we can iterate
valueOf(String) -> takes a string and converts to one of enum values defined.
 if it contains a value not present, then it will throuw illegalArgument error

 */


enum Direction {
    NORTH(0) {
        @Override
        public void move(Position position) {
            position.y++;
        }
    },

    SOUTH(180) {
        @Override
        public void move(Position position) {
            position.y--;
        }
    },

    EAST(90) {
        @Override
        public void move(Position position) {
            position.x++;
        }
    },

    WEST(270) {
        @Override
        public void move(Position position) {
            position.x--;
        }
    };

    private int degree;

    Direction(int degree) {
        this.degree = degree;
    }

    public int getDegree() {
        return degree;
    }

    public abstract void move(Position position);
}

class Position {
    int x;
    int y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Enums {
    public static void main(String[] args) {
        PaymentStatus success = PaymentStatus.SUCCESS;
        PaymentStatus failure = PaymentStatus.FAILED;
        PaymentStatus pending = PaymentStatus.PENDING;

        System.out.println(success);
        System.out.println(failure);
        System.out.println(pending);
        System.out.println(success.name());
        System.out.println(success.ordinal());

        for(PaymentStatus p:PaymentStatus.values()){
            System.out.println(p+" "+p.ordinal());
        }

//        Direction north=Direction.NORTH;
//        Direction east = Direction.EAST;
//        System.out.println(north.getDegree());
//        System.out.println(east.getDegree());

        Position p = new Position(1,1);
        Direction.NORTH.move(p);
        Direction.SOUTH.move(p);
        Direction.WEST.move(p);

        System.out.println(p.x + " "+ p.y);
    }
}
