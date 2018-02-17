package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList.DuplicatePersonException;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;

/**
 * Edits details of the person identified using the last displayed index.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits some details of the person "
            + "identified by the index number in the last shown person listing.\n"
            + "Parameters: INDEX [[p]p/PHONE] [[p]e/EMAIL] [[p]a/ADDRESS]\n"
            + "Example: " + COMMAND_WORD + " 1 p/23456789 a/311, Clementi Ave 2, #01-01";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final ReadOnlyPerson target;
    private final Person toAdd;

    /**
     * Convenience constructor using raw values.
     *
     * @throws IndexOutOfBoundsException if the target index is out of bounds of the last viewed listing
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public EditCommand (int targetVisibleIndex,
                        String phone, boolean isPhonePrivate,
                        String email, boolean isEmailPrivate,
                        String address, boolean isAddressPrivate) throws IndexOutOfBoundsException, IllegalValueException {
        super(targetVisibleIndex);
        this.target = getTargetPerson();
        this.toAdd = new Person(
                target.getName(),
                (phone == null) ? target.getPhone() : new Phone(phone, isPhonePrivate),
                (email == null) ? target.getEmail() : new Email(email, isEmailPrivate),
                (address == null) ? target.getAddress() : new Address(address, isAddressPrivate),
                target.getTags()
        );
    }

    @Override
    public CommandResult execute() {
        try {
            addressBook.removePerson(target);
            addressBook.addPerson(toAdd);
            return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, target));
        } catch (PersonNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        } catch (DuplicatePersonException dpe) {
            return new CommandResult(MESSAGE_DUPLICATE_PERSON);
        }
    }
}
