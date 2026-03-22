package fashiony;

//Operation class is used to push into stack which will store necessarry data
// about operation type and version to then undo actions.

//It uses three constructors for specific actions.
public class Operation {
    OpType type;
    int index;
    Version version;
    String tag;
    String oldNote;

    public Operation(OpType type, Version version) {
        this.type = type;
        this.version = version;
    }
    public Operation(OpType type, Version version, int index) {
        this.type = type;
        this.version = version;
        this.index = index;
    }

    public Operation(OpType type, Version version, String value) {
        this.type = type;
        this.version = version;

        if (type == OpType.ADD_TAG || type == OpType.REMOVE_TAG) {
            this.tag = value;
        } else if (type == OpType.EDIT_NOTE) {
            this.oldNote = value;
        }
    }

    @Override
    public String toString() {
        return "Operation{" +
                "type=" + type +
                ", versionID=" + (version != null ? version.id : "null") +
                (tag != null ? ", tag=" + tag : "") +
                '}';
    }
}
