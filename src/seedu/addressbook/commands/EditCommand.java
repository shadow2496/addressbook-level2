package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.ReadOnlyPerson;

/**
 * Edits details of the person identified using the last displayed index.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person "
            + "identified by the index number in the last shown person listing.\n"
            + "Parameters: INDEX [[p]p/PHONE] [[p]e/EMAIL] [[p]a/ADDRESS]\n"
            + "Example: " + COMMAND_WORD + " 1 p/23456789 a/311, Clementi Ave 2, #01-01";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";

    private final Phone phone;
    private final Email email;
    private final Address address;

    public EditCommand (int targetVisibleIndex,
                        String phone, boolean isPhonePrivate,
                        String email, boolean isEmailPrivate,
                        String address, boolean isAddressPrivate) throws IllegalValueException{
        super(targetVisibleIndex);
        this.phone = new Phone(phone, isPhonePrivate);
        this.email = new Email(email, isEmailPrivate);
        this.address = new Address(address, isAddressPrivate);
    }

    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyPerson target = getTargetPerson();
            if (!addressBook.containsPerson(target)) {
                return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
            }
            return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, target));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
    }
}
