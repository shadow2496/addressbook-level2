package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "123, some street, #12-34, 231534";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS =
            "Person addresses should be entered in the following format: "
            + "BLOCK, STREET, UNIT, POSTAL_CODE";

    public static final Pattern ADDRESS_ARGS_FORMAT = // ',' commas are reserved for delimiter prefixes.
            Pattern.compile("(?<block>[^,]+), (?<street>[^,]+)"
                    + ", (?<unit>[^,]+), (?<postalCode>[^,]+)");

    private static Matcher matcher;

    private final Block block;
    private final Street street;
    private final Unit unit;
    private final PostalCode postalCode;
    private boolean isPrivate;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        this.isPrivate = isPrivate;
        if (!isValidAddress(address.trim())) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        this.block = new Block(matcher.group("block"));
        this.street = new Street(matcher.group("street"));
        this.unit = new Unit(matcher.group("unit"));
        this.postalCode = new PostalCode(matcher.group("postalCode"));
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        matcher = ADDRESS_ARGS_FORMAT.matcher(test);
        return matcher.matches();
    }

    public Block getBlock() {
        return block;
    }

    public Street getStreet() {
        return street;
    }

    public Unit getUnit() {
        return unit;
    }

    public PostalCode getPostalCode() {
        return postalCode;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getBlock() + ", ")
                .append(getStreet() + ", ")
                .append(getUnit() + ", ")
                .append(getPostalCode());
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.hasSameData((Address) other)); // state check
    }

    /**
     * Returns true if all data in this object is the same as that in another.
     */
    private boolean hasSameData(Address other) {
        return other == this // short circuit if same object
                || (other != null // this is first to avoid NPE below
                && this.getBlock().equals(other.getBlock()) // state checks here onwards
                && this.getStreet().equals(other.getStreet())
                && this.getUnit().equals(other.getUnit())
                && this.getPostalCode().equals(other.getPostalCode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(block, street, unit, postalCode);
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
