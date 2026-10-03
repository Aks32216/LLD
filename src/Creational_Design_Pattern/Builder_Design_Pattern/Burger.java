package Creational_Design_Pattern.Builder_Design_Pattern;

class B{
    private final String bun;
    private final String patty;
    private final boolean cheese;
    private final boolean onions;

    private B(Builder builder){
        this.bun=builder.bun;
        this.patty=builder.patty;
        this.cheese=builder.cheese;
        this.onions=builder.onions;
    }

    public static class Builder{
        // required
        private final String bun;
        private final String patty;
        // optional, false by default
        private boolean cheese=false;
        private boolean onions=false;

        public Builder(String bun,String patty){
            this.bun=bun;
            this.patty=patty;
        }

        public Builder cheese(boolean v){
            this.cheese=v;
            return this;
        }

        public Builder onion(boolean v){
            this.onions=v;
            return this;
        }

        public B build(){
            if(this.bun == null || this.patty==null){
                throw new IllegalArgumentException();
            }
            return new B(this);
        }
    }
}

public class Burger {
    public static void main(String[] args) {
        B burger=new B.Builder("sesame","vegetable").cheese(true).build();

    }
}
