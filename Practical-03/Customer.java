public class Customer implements Cloneable {
    public static class Address {
        private final String line;
        private final String city;
        private final String pincode;

        public Address(String line, String city, String pincode) {
            this.line = line;
            this.city = city;
            this.pincode = pincode;
        }

        public String getLine() { return line; }
        public String getCity() { return city; }
        public String getPincode() { return pincode; }

        @Override
        public String toString() {
            return line + ", " + city + " - " + pincode;
        }
    }

    private String name;
    private Address address;

    public Customer(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public String getName() { return name; }
    public Address getAddress() { return address; }

    @Override
    public Customer clone() {
        try {
            return (Customer) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return name + " (" + address + ")";
    }
}
