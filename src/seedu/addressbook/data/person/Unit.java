package seedu.addressbook.data.person;

/**
 * Represents the unit of a Person's address in the address book.
 */
public class Unit {

    public final String value;

    public Unit(String unit) {
        this.value = unit;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Unit // instanceof handles nulls
                && this.value.equals(((Unit) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
