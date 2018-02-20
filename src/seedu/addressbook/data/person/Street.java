package seedu.addressbook.data.person;

/**
 * Represents the street of a Person's address in the address book.
 */
public class Street {

    public final String value;

    public Street(String street) {
        this.value = street;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Street // instanceof handles nulls
                && this.value.equals(((Street) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
