package customer;

/**
 * Customer
 */
public abstract class Customer {
    @Override
    public boolean equals(Object obj) {
        return this.getClass().equals(obj.getClass()) && this.hashCode() == obj.hashCode();
    }
}