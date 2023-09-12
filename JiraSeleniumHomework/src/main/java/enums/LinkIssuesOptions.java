package enums;

public enum LinkIssuesOptions {
    IS_BLOCKED_BY("is blocked by"),
    BLOCKS("blocks"),
    IS_CLONED_BY ("is cloned by"),
    CLONES ("clones"),
    CREATED_BY("created by"),
    CREATED("created"),
    IS_DUPLICATED_BY("is duplicated by"),
    DUPLICATES("duplicates"),
    RELATES_TO("relates to"),
    IS_TESTED_BY("is tested by"),
    TESTS("tests");

    private String option;

    private LinkIssuesOptions (String optionText) {
        option = optionText;
    }

    public String toString() {
        return option;
    }
}


