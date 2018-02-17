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

    private Phone phone;
    private Email email;
    private Address address;

    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public EditCommand (int targetVisibleIndex,
                        String phone, boolean isPhonePrivate,
                        String email, boolean isEmailPrivate,
                        String address, boolean isAddressPrivate) throws IllegalValueException {
        super(targetVisibleIndex);
        this.phone = (phone == null) ? null : new Phone(phone, isPhonePrivate);
        this.email = (email == null) ? null : new Email(email, isEmailPrivate);
        this.address = (address == null) ? null : new Address(address, isAddressPrivate);
    }

    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyPerson target = getTargetPerson();
            addressBook.removePerson(target);
            addressBook.addPerson(new Person(
                    target.getName(),
                    (phone == null) ? target.getPhone() : phone,
                    (email == null) ? target.getEmail() : email,
                    (address == null) ? target.getAddress() : address,
                    target.getTags()
            ));
            return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, target));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (PersonNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        } catch (DuplicatePersonException dpe) {
            return new CommandResult(MESSAGE_DUPLICATE_PERSON);
        }
    }
}
