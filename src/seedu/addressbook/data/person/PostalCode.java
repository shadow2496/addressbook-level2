package seedu.addressbook.data.person;

/**
 * Represents the postal code of a Person's address in the address book.
 */
public class PostalCode {

    public final String value;

    public PostalCode(String postalCode) {
        this.value = postalCode;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PostalCode // instanceof handles nulls
                && this.value.equals(((PostalCode) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
