public class ContactList {
    private int CAPACITY = 2;
    private String[] contactList = new String[CAPACITY];
    private int contactListOffset = 0;
    private int[] freeCells = new int[CAPACITY / 2];
    private int freeCellsOffset = 0; // {0, 4}

    public void add(String name, String number) {
        if (contactListOffset >= CAPACITY){
            CAPACITY = CAPACITY * 2;
            String[] newContactList = new String[CAPACITY];
            System.arraycopy(contactList, 0, newContactList, 0, contactListOffset);
            contactList = newContactList;

            int[] newFreeCells = new int[CAPACITY/2];
            System.arraycopy(freeCells, 0, newFreeCells, 0, freeCellsOffset);
            freeCells = newFreeCells;
        }

        //if freecellsofset = 0; there is no free cells -> we add new contact in the end
        if (freeCellsOffset == 0) {
            contactList[contactListOffset] = name;
            contactList[contactListOffset + 1] = number;
            contactListOffset = contactListOffset + 2;
        } else {
            int lastFreeCell = freeCells[freeCellsOffset - 1];
            contactList[lastFreeCell] = name;
            contactList[lastFreeCell + 1] = number;
            freeCellsOffset--;
        }
    }

    public boolean delete(String name) {
        for (int i = 0; i < contactListOffset; i = i + 2) {
            String next = contactList[i];

            if (next == null) {
                continue;
            } else if (next.equalsIgnoreCase(name)) {
                contactList[i] = null;
                contactList[i+1] = null;
                freeCells[freeCellsOffset] = i;
                freeCellsOffset++;

                return true;
            }
        }

        return false;
    }
    public boolean numberExist(String name){
        boolean exist = false;
        for (int i = 0; i < contactListOffset; i = i + 2) {
            String next = contactList[i];

            if (next == null) {
                continue;
            } else if (next.equalsIgnoreCase(name)) {
                exist = true;
                break;
            }
        }
        return exist;
    }

    public void editName(String oldName, String newName) {
        for (int i = 0; i < contactList.length; i = i + 2) {
            String next = contactList[i];

            if(next == null) {
                continue;
            } else if (next.equalsIgnoreCase(oldName)) {
                contactList[i] = newName;
                break;
            }
        }
    }

    public void editNumber(String name, String newNumber) {
        for (int i = 0; i < contactListOffset; i = i + 2) {
            String next = contactList[i];
            if (next == null) {
                continue;
            } else if (next.equalsIgnoreCase(name)) {
                contactList[i + 1] = newNumber;
                break;
            }
        }
    }

    public String search(String name) {
        String number = null;

        for (int i = 0; i < contactListOffset; i = i + 2) {
            if(contactList[i] == null){
                continue;
            }else if (contactList[i].equalsIgnoreCase(name)) {
                number = contactList[i + 1];
                break;
            }
        }

        return number;
    }

    public String toJson() {
        boolean afterFirst = false;

        StringBuilder result = new StringBuilder("{ ");

        for (int i = 0; i < contactListOffset; i = i + 2) {
            if (contactList[i] == null) {
                continue;
            }

            boolean isLastEntry = ((i + 2) == contactListOffset);
            String entry = "";

            if (afterFirst) {
                entry = entry + "  ";
            }

            entry += "\"" + contactList[i] + "\"" + " : " + "\"" + contactList[i + 1] + "\"";
            afterFirst = true;
            result.append(entry);

            if (!isLastEntry) {
                result.append(", \n");
            }
        }
        result.append(" }");

        return result.toString();
    }

    public void print() {
        System.out.println(this.toJson());
    }
}
