package seedu.addressbook.data.person;

/**
 * Represents the block of a Person's address in the address book.
 */
public class Block {

    public final String value;

    public Block(String block) {
        this.value = block;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Block // instanceof handles nulls
                && this.value.equals(((Block) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
