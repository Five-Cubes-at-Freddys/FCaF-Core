package fr.tartur.fcaf.common.log;

public enum MessageType {

    SUCCESS("§6[§2FCaF§6] §a"),
    NOTE("§8[§fFCaF§8] §7"),
    WARNING("§6[§eFCaF§6] §e"),
    ERROR("§8[§4FCaF§8] §c");

    private final String prefix;

    MessageType(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return this.prefix;
    }
}
