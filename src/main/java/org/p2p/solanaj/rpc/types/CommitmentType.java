package org.p2p.solanaj.rpc.types;

public enum CommitmentType {
    FINALIZED("finalized"),
    CONFIRMED("confirmed"),
    PROCESSED("processed");

    private final String value;

    CommitmentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
